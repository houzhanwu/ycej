package com.yicheejia.modules.financialmanage.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.calcite.DDLColumn;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.financialmanage.dao.YcejFinanceOrderPaydetailDao;
import com.yicheejia.modules.financialmanage.entity.ImportDataEntity;
import com.yicheejia.modules.financialmanage.entity.MoneyConfirmEntity;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceOrderPaydetailEntity;
import com.yicheejia.modules.financialmanage.service.ImportDataService;
import com.yicheejia.modules.financialmanage.service.YcejFinanceOrderPaydetailService;
import com.yicheejia.modules.order.entity.OrderPaydetailEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.order.service.YcejOrderService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;


@Service("ycejFinanceOrderPaydetailService")
public class YcejFinanceOrderPaydetailServiceImpl extends ServiceImpl<YcejFinanceOrderPaydetailDao, YcejFinanceOrderPaydetailEntity> implements YcejFinanceOrderPaydetailService {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private YcejOrderService ycejOrderService;
	@Autowired
	private ImportDataService importDataService;
	
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	
    	Object payDate=params.get("payDate");
		if (payDate!=null && !"".equals(payDate.toString())) {
			//销售日期
			String rDate = (String) params.get("payDate");
	    	String[] rDates = rDate.split(" ");
	    	String startSaleDate = rDates[0];
	    	String endSaleDate = rDates[2];
	    	params.put("startSaleDate", startSaleDate);
	    	params.put("endSaleDate", endSaleDate);
		}
    	Page<YcejFinanceOrderPaydetailEntity> page=new Query<YcejFinanceOrderPaydetailEntity>(params).getPage();
    	page.setRecords(baseMapper.selectAllPage(page,params));
        return new LayuiPage(page.getRecords(), page.getTotal());
    }
    /*
     * 用户每次确认都要判断该笔订单的总金额是否和POS流水中该订单所有流水的金额总和是否相等，相等，更改订单的状态。
     */
    @Transactional
	@Override
	public void checkBatchIds(List<String> asList,String remark) {
    	String userid=String.valueOf(ShiroUtils.getUserEntity().getUserId());
    	List<YcejOrderEntity> orderList = new ArrayList<>();
    	Map<String, Object> hashMap = new HashMap<>();
    	hashMap.put("remark", remark);
    	//先进行判断金额更新订单状态，再更新支付明细状态，否则SQL中根据支付完成状态获取的金额会不准
    	try {
	    	for (String long1 : asList) {
				//1、更新支付明细数据状态为收款已确认
				Map<String, Object> ma = new HashMap<>();
				ma.put("updateId", userid);
				ma.put("orderPayId", long1);
				ma.put("remark", remark);
				//单条更新支付明细状态
				baseMapper.updateOneStateByCondition(ma);
				//获取该流水下的订单金额，和该订单下的所有流水支付状态为收款已确认的流水金额
				Map<String, Object> map = baseMapper.selectOrderPayAndWaterPay(long1);
				//判断尾款应付金额和实付金额是否相等，相等更改支付明细状态并触发工作流
				if (map.get("amount1").equals(map.get("pay_amount1")) && !map.get("pay_type").equals("定金")) {
					YcejOrderEntity ycejOrderEntity = new YcejOrderEntity();
					ycejOrderEntity.setUpdateId(userid);
					ycejOrderEntity.setId(String.valueOf(map.get("id")));
					ycejOrderEntity.setStatus("10");
					ycejOrderEntity.setUpdateTime(DateUtils.getDate());
					orderList.add(ycejOrderEntity);
				}
			}
			
				if (orderList!=null && orderList.size()>0) {
					R r = ycejOrderService.submitService(orderList);
					Map<String,String> orderMap = new HashMap<String,String>();
					for(YcejOrderEntity ycejOrder  : orderList){
						//只执行一次工作流
						if (!orderMap.containsKey(ycejOrder.getId())) {
							ycejOrderService.updateTask(ycejOrder,hashMap);//工作流
							orderMap.put(ycejOrder.getId(), ycejOrder.getId());
						} 
						
					 }
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new RRException(e.getMessage());
			}
		
	}
    /**
     * 导入POS流水,展示确认收款页面
     */
    @Transactional
	@Override
	public Map<String, Object> inportPos(MultipartFile file,Map<String, Object> params, HttpServletRequest request) {
		//获取导入文件中的数据
    	Map<String, Object> map = new HashMap<>();
		List<String> excellist=new ArrayList<>();
		List<YcejFinanceOrderPaydetailEntity> payDetailEntityList = new ArrayList<>();
		//系统收款总账
		Map<String, Object> map1 = new HashMap<>();
		//系统收款总账明细
		List<Map<String, Object>> mapList = new ArrayList<>();
		//银盛总账
        Map<String, Object> map2 = new HashMap<>();
        //账户类型为借记卡的银盛总账
        Map<String, Object> map3 = new HashMap<>();
        //账户类型为信用卡的银盛总账
        Map<String, Object> map4 = new HashMap<>();
        Object payDateTime=params.get("payDate");
		if (payDateTime!=null && !"".equals(payDateTime.toString())) {
			//销售日期
			String rDate = (String) params.get("payDate");
	    	String[] rDates = rDate.split(" ");
	    	String startSaleDate = rDates[0];
	    	String endSaleDate = rDates[2];
	    	params.put("startSaleDate", startSaleDate);
	    	params.put("endSaleDate", endSaleDate);
		}
		List<ImportDataEntity> importDataEntityList = new ArrayList<>();
		try {
		Map<String,Object> ma = ExcelUtils.readExcel(file);
		//支付明细
	    excellist = (List<String>) ma.get("list");
	    //交易时间
	    String payDate = (String) ma.get("payDate");
	    String s1=payDate.replaceAll( "\\s+ ", ",");
    	String[] s2=s1.split(",");
    	payDate = s2[2];
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	Date date = sdf.parse(payDate);
    	String name=ShiroUtils.getUserEntity().getName();
    	//银盛总账笔数
    	int totalCount = 0;
    	//银盛总账交易金额
    	BigDecimal totalMoney = new BigDecimal(0);
    	//银盛总账商户费用
    	BigDecimal totalFees = new BigDecimal(0);
    	//银盛总账结算金额
    	BigDecimal totalAmount = new BigDecimal(0);
    	
    	//账户类型为借记卡的银盛总账笔数
    	int count1 = 0;
    	//账户类型为借记卡的银盛总账交易金额
    	BigDecimal totalMoney1 = new BigDecimal(0);
    	//账户类型为借记卡的银盛总账商户费用
    	BigDecimal totalFees1 = new BigDecimal(0);
    	//账户类型为借记卡的银盛总账结算金额
    	BigDecimal totalAmount1 = new BigDecimal(0);
    	//账户类型
    	String accountType1 = "";
    	
    	//账户类型为信用卡的银盛总账笔数
    	int count2 = 0;
    	//账户类型为信用卡的银盛总账交易金额
    	BigDecimal totalMoney2 = new BigDecimal(0);
    	//账户类型为信用卡的银盛总账商户费用
    	BigDecimal totalFees2 = new BigDecimal(0);
    	//账户类型为信用卡的银盛总账结算金额
    	BigDecimal totalAmount2 = new BigDecimal(0);
    	//账户类型
    	String accountType2 = "";
    	if (excellist != null && excellist.size()>0) {
    		totalCount = excellist.size();
			for (String string : excellist) {
				String str=string.replaceAll( "\\s+ ", ",");
    			String[] dd=str.split(",");
    			totalMoney = totalMoney.add(new BigDecimal(dd[5]));
    			totalFees =	totalFees.add(new BigDecimal(dd[6]));
    			totalAmount = totalAmount.add(new BigDecimal(dd[7]));
    			if ("借记账户".equals(dd[3])) {
    				count1 +=1;
    				totalMoney1 = totalMoney1.add(new BigDecimal(dd[5]));
    				totalFees1 = totalFees1.add(new BigDecimal(dd[6]));
    				totalAmount1 = totalAmount1.add(new BigDecimal(dd[7]));
    				accountType1 = dd[3];
				}else{
					count2 +=1;
					totalMoney2 = totalMoney2.add(new BigDecimal(dd[5]));
					totalFees2 = totalFees2.add(new BigDecimal(dd[6]));
					totalAmount2 = totalAmount2.add(new BigDecimal(dd[7]));
					accountType2 = dd[3];
				}
    			
    			//将数据插入到数据库表中
    			ImportDataEntity dataEntity = new ImportDataEntity();
    			dataEntity.setTradeDate(date);//交易日期
    			dataEntity.setTerminalNo(dd[0]);//终端编号
    			dataEntity.setTradeMoney(new BigDecimal(dd[5]));//交易金额
    			dataEntity.setAccountNo(dd[2]);//主账号
    			dataEntity.setSysReferenceNumber(dd[9]);//系统参考号（目前取得是系统跟踪号）
    			dataEntity.setAccountType(dd[3]);//账户类型
    			dataEntity.setInsertId(name);//新增操作人
    			dataEntity.setUpdateId(name);
    			dataEntity.setMerchantFees(new BigDecimal(dd[6]));//商户费用
    			dataEntity.setSettlementAmount(new BigDecimal(dd[7]));//结算金额
    			dataEntity.setPayType("1");
    			//将导入的数据放到实体类中传到前台展示
    			importDataEntityList.add(dataEntity);
//    			importDataService.insert(dataEntity);
			}
		}else{
			throw new RRException("无数据!");
		}
    	//从库里查询支付明细和插入的excel明细
    	//支付明细
    	payDetailEntityList = baseMapper.selectListByCondition(params);
    	//收款总账
    	map1 = baseMapper.selectTotalForPos(params);
    	mapList = baseMapper.selectPosTotalDetailByPayMethod(params);
    	/*//银盛总账
    	map2 = importDataService.selectTotalForPos(params);
    	//根据账户类型获取银盛总账明细
    	map3 = importDataService.selectTotalForPosByAccountType(params);
    	//从数据库查询导入pos明细
    	importDataEntityList = importDataService.selectListByCondition(params);*/
    	map2.put("totalCount", totalCount);
    	map2.put("totalMoney", totalMoney);
    	map2.put("totalFees", totalFees);
    	map2.put("totalAmount", totalAmount);
    	map2.put("payDate", payDate);
    	map3.put("count1", count1);
    	map3.put("totalMoney1", totalMoney1);
    	map3.put("totalFees1", totalFees1);
    	map3.put("totalAmount1", totalAmount1);
    	map3.put("accountType1", accountType1);
    	map4.put("count2", count2);
    	map4.put("totalMoney2", totalMoney2);
    	map4.put("totalFees2", totalFees2);
    	map4.put("totalAmount2", totalAmount2);
    	map4.put("accountType2", accountType2);
    	
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RRException(e.getMessage());
		}
	    map.put("payDetailEntityList", payDetailEntityList);
	    map.put("map1", map1);
	    map.put("mapList", mapList);
	    map.put("map2", map2);
	    map.put("map3", map3);
	    map.put("map4", map4);
	    map.put("importDataEntityList", importDataEntityList);
	    
	    return map;
	}
/*    @Transactional
    @Override
    public Map<String, Object> inportPos(MultipartFile file, HttpServletRequest request) {
    	//查询数据库，获取所有支付方式为POS的支付明细
    	List<YcejFinanceOrderPaydetailEntity> list=baseMapper.selectAll();
    	List<String> longlist=new ArrayList<>();
    	List<String>  errorList= new ArrayList<>();
    	int a = 0;
    	//获取导入文件中的数据
    	List<String> excellist=null;
    	try {
    		Map<String,Object> ma = ExcelUtils.readExcel(file);
    		excellist = (List<String>) ma.get("list");
    		String payDate = (String) ma.get("payDate");
    		String s1=payDate.replaceAll( "\\s+ ", ",");
    		String[] s2=s1.split(",");
    		payDate = s2[2];
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Date date = sdf.parse("payDate");
    		List<YcejOrderEntity> orderList = new ArrayList<>();
    		
    		String userid=String.valueOf(ShiroUtils.getUserEntity().getUserId());
    		logger.info("导入文件数据:"+excellist);
    		for (String string : excellist) {
    			String str=string.replaceAll( "\\s+ ", ",");
    			String[] dd=str.split(",");
    			//用来标记excel中该条数据在数据库中是否存在
    			int d = 0;
    			for (YcejFinanceOrderPaydetailEntity tt : list) {
    				//导入数据匹配数据库数据
    				if (dd[8].equals(tt.getSys_ref_no())&&dd[5].equals(tt.getPayAmount().toString())) {
    					d = 1;
    					longlist.add(tt.getOrderPayId());
    					//获取该流水下的订单金额，和该订单下的所有流水支付状态为已支付的流水金额
    					Map<String, Object> map = baseMapper.selectOrderPayAndWaterPay(tt.getOrderPayId());
    					//判断订单金额和所有流水金额是否相等，相等调用方法更新订单表中订单状态
    					if (map.get("amount1").equals(map.get("pay_amount1")) && !map.get("pay_type").equals("定金")) {
    						YcejOrderEntity ycejOrderEntity = new YcejOrderEntity();
    						ycejOrderEntity.setUpdateId(userid);
    						ycejOrderEntity.setId(String.valueOf(map.get("id")));
    						ycejOrderEntity.setStatus("10");
    						ycejOrderEntity.setUpdateTime(DateUtils.getDate());
    						orderList.add(ycejOrderEntity);
    						
    					}
    				}
    			}
    			if (d==0) {//表示该条数据数据库中没有对应数据，加入到errorList中
    				errorList.add(dd[8]);//系统参考号
    			}
    		}
    		
    		
    		if (orderList!=null && orderList.size()>0) {
    			R r = ycejOrderService.submitService(orderList);
    			Map<String,String> orderMap = new HashMap<String,String>();
    			for(YcejOrderEntity ycejOrder  : orderList){
    				//只执行一次工作流
    				if (!orderMap.containsKey(ycejOrder.getId())) {
    					ycejOrderService.updateTask(ycejOrder);//工作流
    					orderMap.put(ycejOrder.getId(), ycejOrder.getId());
    				} 
    				
    			}
    		}
    		
    	} catch (Exception e) {
    		logger.error(e.getMessage());
    		throw new RRException(e.getMessage());
    	}
    	
    	
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("errorList", errorList);
    	//如果匹配上，更新支付明细状态为 财务已审核
    	if (longlist!=null&&longlist.size()>0) {
    		//后更新支付明细状态，不然影响前面金额值计算
    		String userid=String.valueOf(ShiroUtils.getUserEntity().getUserId());
    		Map<String, Object> hmMap = new HashMap<>();
    		hmMap.put("updateId", userid);
    		hmMap.put("list", longlist);
    		baseMapper.updateStateByIds(hmMap);
    		m.put("success", longlist.size());
    		a=excellist.size()-longlist.size();
    	}else{
    		m.put("success", 0);
    		if (excellist!=null&&excellist.size()>0) {
    			a=excellist.size();
    		}
    	}
    	//未匹配总条数
    	m.put("error", a);
    	//匹配上的条数
    	return m;
    }
*/	
    /**
     * 导入银行统一模板数据
     */
    @Transactional
	@Override
	public Map<String, Object> inportZcard(MultipartFile file,Map<String, Object> params,HttpServletRequest request) {
		//查询数据库，获取所有支付方式为银行的支付明细
				List<YcejFinanceOrderPaydetailEntity> list=baseMapper.selectAllCard();
				List<String> longlist=new ArrayList<>();
				List<String> errorList = new ArrayList<>();
				//转账流水总账笔数
		    	int totalbankCount = 0;
		    	//转账流水总账交易金额
		    	BigDecimal totalBankMoney = new BigDecimal(0);
		    	Object payDateTime=params.get("payDate");
				if (payDateTime!=null && !"".equals(payDateTime.toString())) {
					//销售日期
					String rDate = (String) params.get("payDate");
			    	String[] rDates = rDate.split(" ");
			    	String startSaleDate = rDates[0];
			    	String endSaleDate = rDates[2];
			    	params.put("startSaleDate", startSaleDate);
			    	params.put("endSaleDate", endSaleDate);
				}
				//获取导入文件中的数据
				 List<String[]> excellist = new ArrayList<>();
				 List<ImportDataEntity> importDataEntityList = new ArrayList<>();
				 List<Map<String, Object>> map1 = new ArrayList<>();
				 Map<String, Object> map2 = new HashMap<>();
				 List<YcejFinanceOrderPaydetailEntity> paydetailList = new ArrayList<>();
				 String name=ShiroUtils.getUserEntity().getName();
				 String payDate = "";
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				 try {
			    excellist = ExcelUtils.readZExcel(file);
			    if (excellist != null && excellist.size()>0) {
			    	payDate =excellist.get(0)[1];
			    	
			    	totalbankCount = excellist.size();
			    		for (String[] string : excellist) {
			    			totalBankMoney = totalBankMoney.add(new BigDecimal(string[2]));
			    			//将数据插入到数据库表中
			    			ImportDataEntity dataEntity = new ImportDataEntity();
			    			dataEntity.setAccountType(string[0]);//支付类别
			    			Date dae = null;
			    			if (string[1].contains(":")) {
			    				String stri = string[1];
			    				String[] strin = stri.split(" ");
			    				dae=DateUtils.parse(strin[0], "yyyyMMdd");
							}else if (string[1].contains("-")) {
								dae=DateUtils.parse(string[1], "yyyy-MM-dd");
							}else if (string[1].contains("/")) {
								dae=DateUtils.parse(string[1], "yyyy/MM/dd");
							}
			    			dataEntity.setTradeDate(dae);//交易时间
			    			dataEntity.setTradeMoney(new BigDecimal(string[2]));//贷方发生额（收入）
			    			dataEntity.setAccountName(string[3]);//对方户名
			    			dataEntity.setAccountNo(string[4]);//对方账号
			    			dataEntity.setRemark(string[5]);//备注
			    			dataEntity.setBankSerialNumber(string[6]);//账户明细编号-交易流水号
			    			dataEntity.setInsertId(name);//新增操作人
			    			dataEntity.setUpdateId(name);
			    			dataEntity.setPayType("2");
			    			//将插入的数据暂存到实体类中返回前台
			    			importDataEntityList.add(dataEntity);
			    			
//			    			importDataService.insert(dataEntity);
			    		}
			    }else{
						throw new RRException("无数据!");
				}
			   //导入银行转账页面展示支付明细
			    paydetailList = baseMapper.selectDetailListForImport(params);
			    //银行转账 展示系统收款总账,是list
			    map1 = baseMapper.selectBankTotal(params);
			    //银行转账 确认收款页面 导入数据流水总账
			    map2.put("payDate", payDate);
			    map2.put("totalbankCount", totalbankCount);
			    map2.put("totalBankMoney", totalBankMoney);
//			    map2 = importDataService.selectBankTotalByImport(params);
//			    //从数据库查询导入转账明细
//		    	importDataEntityList = importDataService.selectListByCondition(params);
			    
				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new RRException(e.getMessage());
				}
			    Map<String, Object> map = new HashMap<String, Object>();
				map.put("paydetailList", paydetailList);
				map.put("map1", map1);
				map.put("map2", map2);
				map.put("importDataEntityList", importDataEntityList);
			    
			    return map;
	}
    /**
     * 银行转账页面 确认收款
     */
    @Override
    @Transactional
    public Map<String, Object> bankMoneyConfirm(MoneyConfirmEntity moneyConfirmEntity){
    	YcejFinanceOrderPaydetailEntity orderPaydetailEntity = moneyConfirmEntity.getOrderPaydetailEntity();
    	ImportDataEntity importDataEntity = moneyConfirmEntity.getImportDataEntity();
    	Map<String, Object> params = moneyConfirmEntity.getMap();
    	Map<String, Object> hashMap = new HashMap<>();
    	hashMap.put("remark", moneyConfirmEntity.getRemark());
    	Object payDateTime=params.get("payDate");
		if (payDateTime!=null && !"".equals(payDateTime.toString())) {
			//销售日期
			String rDate = (String) params.get("payDate");
	    	String[] rDates = rDate.split(" ");
	    	String startSaleDate = rDates[0];
	    	String endSaleDate = rDates[2];
	    	params.put("startSaleDate", startSaleDate);
	    	params.put("endSaleDate", endSaleDate);
		}
    	String remark = moneyConfirmEntity.getRemark();//收款备注
    	String userid=String.valueOf(ShiroUtils.getUserEntity().getUserId());
    	String name = ShiroUtils.getUserEntity().getName();
    	List<YcejOrderEntity> orderList = new ArrayList<>();
    	//1、更新支付明细数据状态为收款已确认
		Map<String, Object> ma = new HashMap<>();
		ma.put("updateId", userid);
		ma.put("orderPayId", orderPaydetailEntity.getOrderPayId());
		ma.put("remark", remark);
		Map<String, Object> m = new HashMap<>();
		try {
			//单条更新支付明细状态
			//判断导入数据金额和库里金额是否相等
			int flg = importDataEntity.getTradeMoney().compareTo(orderPaydetailEntity.getPayAmount());
			if (flg == 0) {
				baseMapper.updateOneStateByCondition(ma);
			}else {
				logger.info("导入数据与系统交易金额不相等");
				throw new Exception("交易金额不相等,请核对后再确认");
			}
			//2、将该条导入的数据保存到数据库中，并将支付明细主键保存到导入数据表中
			//根据支付明细主键查询导入数据表 如果返回不为空执行更新操作，否则执行新增插入操作
			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("pay_id", orderPaydetailEntity.getOrderPayId());
			List<ImportDataEntity> importList = importDataService.selectByMap(columnMap);
			if (importList!=null && importList.size()>0) {
				//更新
				importDataEntity.setId(importList.get(0).getId());
				importDataEntity.setUpdateTime(DateUtils.getDate());
				importDataEntity.setUpdateId(name);
				importDataService.updateById(importDataEntity);
			}else{
				//新增
				importDataEntity.setPayId(orderPaydetailEntity.getOrderPayId());
				importDataEntity.setInsertTime(DateUtils.getDate());
				importDataEntity.setUpdateTime(DateUtils.getDate());
				importDataEntity.setUpdateId(name);
				importDataEntity.setInsertId(name);
				importDataService.insert(importDataEntity);
				
			}
			//3、进行判断该条明细对应的订单所有金额是否已收款，是触发工作流
			Map<String, Object> map = baseMapper.selectOrderPayAndWaterPay(orderPaydetailEntity.getOrderPayId());
			//判断订单金额和所有流水金额是否相等，相等更新订单表中订单状态
			if (map.get("amount1").equals(map.get("pay_amount1")) && !"定金".equals(map.get("pay_type"))) {
				YcejOrderEntity ycejOrderEntity = new YcejOrderEntity();
				ycejOrderEntity.setUpdateId(userid);
				ycejOrderEntity.setId(String.valueOf(map.get("id")));
				ycejOrderEntity.setStatus("10");
				ycejOrderEntity.setUpdateTime(DateUtils.getDate());
				orderList.add(ycejOrderEntity);
			}
			if (orderList!=null && orderList.size()>0) {
				R r = ycejOrderService.submitService(orderList);
				Map<String,String> orderMap = new HashMap<String,String>();
				for(YcejOrderEntity ycejOrder  : orderList){
					//只执行一次工作流
					if (!orderMap.containsKey(ycejOrder.getId())) {
						ycejOrderService.updateTask(ycejOrder,hashMap);//工作流
						orderMap.put(ycejOrder.getId(), ycejOrder.getId());
					} 
					
				 }
			}
			//银行确认页面确认过后重载数据
			List<YcejFinanceOrderPaydetailEntity> paydetailList = new ArrayList<>();
		
			List<Map<String, Object>> list = new ArrayList<>();
			 //导入银行转账页面展示支付明细
		    paydetailList = baseMapper.selectDetailListForImport(params);
		    //银行转账 展示系统收款总账,是list
		    list = baseMapper.selectBankTotal(params);
			m.put("list", list);
			m.put("payDetailEntityList", paydetailList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RRException("对账失败!");
		}
		return m;
    }
    
    
    
    
	@Transactional
	@Override
	public Map<String, Object> inportJcard(MultipartFile file, HttpServletRequest request) {
		//查询数据库，获取所有支付方式为银行的支付明细
		List<YcejFinanceOrderPaydetailEntity> list=baseMapper.selectAllCard();
		List<String> longlist=new ArrayList<>();
		List<String> errorList = new ArrayList<>();
		int a = 0;
		//获取导入文件中的数据
		List<String[]> excellist = null;
		try {
	    excellist = ExcelUtils.readJExcel(file);
	    List<YcejOrderEntity> orderList = new ArrayList<>();
    	String userid=String.valueOf(ShiroUtils.getUserEntity().getUserId());
	    logger.info("导入文件数据:"+excellist);
	    for (String[] string : excellist) {
	    	//用来标记excel中该条数据在数据库中是否存在
	    	int d = 0;
	    	for (YcejFinanceOrderPaydetailEntity tt : list) {
		    	//导入数据匹配数据库数据
		    	if (string[1].equals(tt.getSys_ref_no())&&string[0].equals(tt.getPayAmount().toString())) {
		    		d = 1;
		    		longlist.add(tt.getOrderPayId());
		    		//获取该流水下的订单金额，和该订单下的所有流水支付状态为已支付的流水金额
					Map<String, Object> map = baseMapper.selectOrderPayAndWaterPay(tt.getOrderPayId());
					//判断订单金额和所有流水金额是否相等，相等更新订单表中订单状态
					if (map.get("amount1").equals(map.get("pay_amount1")) && !"定金".equals(map.get("pay_type"))) {
						YcejOrderEntity ycejOrderEntity = new YcejOrderEntity();
						ycejOrderEntity.setUpdateId(userid);
						ycejOrderEntity.setId(String.valueOf(map.get("id")));
						ycejOrderEntity.setStatus("10");
						ycejOrderEntity.setUpdateTime(DateUtils.getDate());
						orderList.add(ycejOrderEntity);
					}
				}
			}
	    	if (d==0) {//表示该条数据数据库中没有对应数据，加入到errorList中
				errorList.add(string[0]);//系统参考号
			}
	    }
	    
	    
	    	if (orderList!=null && orderList.size()>0) {
	    		R r = ycejOrderService.submitService(orderList);
	    		Map<String,String> orderMap = new HashMap<String,String>();
				for(YcejOrderEntity ycejOrder  : orderList){
					//只执行一次工作流
					if (!orderMap.containsKey(ycejOrder.getId())) {
						ycejOrderService.updateTask(ycejOrder,new HashMap<>());//工作流
						orderMap.put(ycejOrder.getId(), ycejOrder.getId());
					} 
					
				 }
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RRException(e.getMessage());
		}
	    
	    
	    Map<String, Object> m = new HashMap<String, Object>();
			m.put("errorList", errorList);
	    //如果匹配上，更新支付明细状态为 财务已审核
	    if (longlist!=null&&longlist.size()>0) {
	    	//后更新支付明细状态，不然影响前面金额值计算
	    	String userid=String.valueOf(ShiroUtils.getUserEntity().getUserId());
	    	Map<String, Object> hmMap = new HashMap<>();
			hmMap.put("updateId", userid);
			hmMap.put("list", longlist);
			baseMapper.updateStateByIds(hmMap);
	    	m.put("success", longlist.size());
	    	a=excellist.size()-longlist.size();
		}else{
			m.put("success", 0);
			if (excellist!=null&&excellist.size()>0) {
				a=excellist.size();
			}
		}
	    //未匹配总条数
	    m.put("error", a);
	    //匹配上的条数
	    return m;
	}
	@Override
	public List<YcejFinanceOrderPaydetailEntity> selectExportData(List<String> list) {
		
		return baseMapper.selectExportData(list);
	}
	/**
	 *  根据支付时间、支付方式获取总账明细
	 */
	@Override
	public List<Map<String, Object>> selectTotalDetail(Map<String, Object> params) {
		Object payDateTime=params.get("payDate");
		if (payDateTime!=null && !"".equals(payDateTime.toString())) {
			//销售日期
			String rDate = (String) params.get("payDate");
	    	String[] rDates = rDate.split(" ");
	    	String startSaleDate = rDates[0];
	    	String endSaleDate = rDates[2];
	    	params.put("startSaleDate", startSaleDate);
	    	params.put("endSaleDate", endSaleDate);
		}
		return baseMapper.selectTotalDetail(params);
	}
	/**
	 * 根据用户筛选条件在导入页面展示收款总账
	 */
	@Override
	public Map<String, Object> selectTotalForImport(Map<String, Object> params) {
		
		return baseMapper.selectTotalForImport(params);
	}
	/**
	 *  根据支付时间、支付方式获取收款总账
	 */
	@Override
	public Map<String, Object> selectTotal(Map<String, Object> params) {
		Object payDateTime=params.get("payDate");
		if (payDateTime!=null && !"".equals(payDateTime.toString())) {
			//销售日期
			String rDate = (String) params.get("payDate");
	    	String[] rDates = rDate.split(" ");
	    	String startSaleDate = rDates[0];
	    	String endSaleDate = rDates[2];
	    	params.put("startSaleDate", startSaleDate);
	    	params.put("endSaleDate", endSaleDate);
		}
		
		return baseMapper.selectTotal(params);
	}
	/**
	 * POS确认收款
	 */
	@Transactional
	@Override
	public Map<String, Object> posMoneyConfirm(MoneyConfirmEntity moneyConfirmEntity) {
		//遍历数据根据系统参考号进行匹配
		List<YcejFinanceOrderPaydetailEntity> orderPayDetailList = moneyConfirmEntity.getOrderPayDetailList();
		List<ImportDataEntity> importDataList = moneyConfirmEntity.getImportDataList();
		String remark = moneyConfirmEntity.getRemark();
		Map<String, Object> hashMap = new HashMap<>();
    	hashMap.put("remark", remark);
		Map<String, Object> params = moneyConfirmEntity.getMap();
		Object payDateTime=params.get("payDate");
		if (payDateTime!=null && !"".equals(payDateTime.toString())) {
			//销售日期
			String rDate = (String) params.get("payDate");
	    	String[] rDates = rDate.split(" ");
	    	String startSaleDate = rDates[0];
	    	String endSaleDate = rDates[2];
	    	params.put("startSaleDate", startSaleDate);
	    	params.put("endSaleDate", endSaleDate);
		}
		String userid=String.valueOf(ShiroUtils.getUserEntity().getUserId());
		String name = ShiroUtils.getUserEntity().getName();
		List<YcejOrderEntity> orderList = new ArrayList<>();
		//系统收款总账明细
		List<Map<String, Object>> mapList = new ArrayList<>();
		int count = 0;
		Map<String, Object> countMap = new HashMap<>();
		try {
			if (orderPayDetailList !=null && orderPayDetailList.size()>0 && importDataList != null && importDataList.size()>0) {
				for (YcejFinanceOrderPaydetailEntity orderPayDetail : orderPayDetailList) {
					
					for (ImportDataEntity importDataEntity : importDataList) {
							int flag = orderPayDetail.getPayAmount().compareTo(importDataEntity.getTradeMoney());
							if (orderPayDetail.getSys_ref_no().trim().substring(orderPayDetail.getSys_ref_no().trim().length()-6).equals(importDataEntity.getSysReferenceNumber()) && flag == 0) {
							//每次匹配成功，1、更新支付明细数据状态为收款已确认2、把表格中的该条数据保存到数据库，并将支付明细主键保存到导入数据表中3、进行判断该条明细对应的订单所有金额是否已收款，是触发工作流
							//1、更新支付明细数据状态为收款已确认
							Map<String, Object> map = new HashMap<>();
							map.put("updateId", userid);
							map.put("orderPayId", orderPayDetail.getOrderPayId());
							map.put("remark", remark);
							//单条更新支付明细状态
							baseMapper.updateOneStateByCondition(map);
							//2、将支付明细主键保存到导入数据表中
							
							//根据支付明细主键查询导入数据表 如果返回不为空执行更新操作，否则执行新增插入操作
							Map<String, Object> columnMap = new HashMap<>();
							columnMap.put("pay_id", orderPayDetail.getOrderPayId());
							List<ImportDataEntity> importList = importDataService.selectByMap(columnMap);
							if (importList!=null && importList.size()>0) {
								//更新
								importDataEntity.setId(importList.get(0).getId());
								importDataEntity.setUpdateTime(DateUtils.getDate());
								importDataEntity.setUpdateId(name);
								importDataService.updateById(importDataEntity);
							}else{
								//新增
								importDataEntity.setPayId(orderPayDetail.getOrderPayId());
								importDataEntity.setInsertTime(DateUtils.getDate());
								importDataEntity.setUpdateTime(DateUtils.getDate());
								importDataEntity.setUpdateId(name);
								importDataEntity.setInsertId(name);
								importDataService.insert(importDataEntity);
								
							}
							//3、进行判断该条明细对应的订单所有金额是否已收款，是触发工作流
							Map<String, Object> ma = baseMapper.selectOrderPayAndWaterPay(orderPayDetail.getOrderPayId());
							//判断订单金额和所有流水金额是否相等，相等更新订单表中订单状态
							if (ma.get("amount1").equals(ma.get("pay_amount1")) && !"定金".equals(ma.get("pay_type"))) {
								YcejOrderEntity ycejOrderEntity = new YcejOrderEntity();
								ycejOrderEntity.setUpdateId(userid);
								ycejOrderEntity.setId(String.valueOf(ma.get("id")));
								ycejOrderEntity.setStatus("10");
								ycejOrderEntity.setUpdateTime(DateUtils.getDate());
								orderList.add(ycejOrderEntity);
							}
							
							count = count + 1;
						}
					}
				}
			}
			if (orderList!=null && orderList.size()>0) {
				R r = ycejOrderService.submitService(orderList);
				Map<String,String> orderMap = new HashMap<String,String>();
				for(YcejOrderEntity ycejOrder  : orderList){
					//只执行一次工作流
					if (!orderMap.containsKey(ycejOrder.getId())) {
						ycejOrderService.updateTask(ycejOrder,hashMap);//工作流
						orderMap.put(ycejOrder.getId(), ycejOrder.getId());
					} 
					
				 }
			}
			countMap.put("count", count);
			//pos确认页面确认过后重载数据
			List<YcejFinanceOrderPaydetailEntity> payDetailEntityList = new ArrayList<>();
			Map<String, Object> m = new HashMap<>();
			Map<String, Object> map1 = new HashMap<>(); 
			//支付明细
	    	payDetailEntityList = baseMapper.selectListByCondition(params);
	    	//收款总账
	    	map1 = baseMapper.selectTotalForPos(params);
	    	mapList = baseMapper.selectPosTotalDetailByPayMethod(params);
	    	m.put("mapList", mapList);
			m.put("map1", map1);
			m.put("payDetailEntityList", payDetailEntityList);
			countMap.put("m", m);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RRException("对账失败!");
		}
		return countMap;
	}
	/**
	 * 银行转账页面进行名字搜索查询
	 */
	@Override
	public LayuiPage queryPageForQueryName(Map<String, Object> params) {
		Object payDateTime=params.get("payDate");
		if (payDateTime!=null && !"".equals(payDateTime.toString())) {
			//销售日期
			String rDate = (String) params.get("payDate");
	    	String[] rDates = rDate.split(" ");
	    	String startSaleDate = rDates[0];
	    	String endSaleDate = rDates[2];
	    	params.put("startSaleDate", startSaleDate);
	    	params.put("endSaleDate", endSaleDate);
		}
		Page<YcejFinanceOrderPaydetailEntity> page=new Query<YcejFinanceOrderPaydetailEntity>(params).getPage();
    	page.setRecords(baseMapper.selectListForQueryName(page,params));
        return new LayuiPage(page.getRecords(), page.getTotal());
	}
	/**
	 * pos确认页面确认过后重载数据
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> selectReloadPosData(Map<String, Object> params) {
		List<YcejFinanceOrderPaydetailEntity> payDetailEntityList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>(); 
		//支付明细
    	payDetailEntityList = baseMapper.selectListByCondition(params);
    	//收款总账
    	map1 = baseMapper.selectTotal(params);
		map.put("map1", map1);
		map.put("payDetailEntityList", payDetailEntityList);
		return map;
	}
	/**
	 * 银行确认页面确认过后重载数据
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> selectReloadBankData(Map<String, Object> params) {
		List<YcejFinanceOrderPaydetailEntity> paydetailList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		 //导入银行转账页面展示支付明细
	    paydetailList = baseMapper.selectDetailListForImport(params);
	    //银行转账 展示系统收款总账,是list
	    list = baseMapper.selectBankTotal(params);
		map.put("list", list);
		map.put("payDetailEntityList", paydetailList);
		return map;
	}
}
