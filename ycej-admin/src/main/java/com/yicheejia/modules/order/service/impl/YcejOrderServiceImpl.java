package com.yicheejia.modules.order.service.impl;

import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.common.annotation.ShopFilter;
import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.Constant;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.common.utils.R;
import com.yicheejia.common.utils.SerialNoSerUtils;
import com.yicheejia.common.utils.redis.SerialType;
import com.yicheejia.modules.operate.entity.InventoryEntity;
import com.yicheejia.modules.operate.service.InventoryService;
import com.yicheejia.modules.order.dao.YcejOrderDao;
import com.yicheejia.modules.order.entity.OrderFileEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.order.service.OrderFileService;
import com.yicheejia.modules.order.service.YcejOrderDetailService;
import com.yicheejia.modules.order.service.YcejOrderService;
import com.yicheejia.modules.shop.entity.CustomerEntity;
import com.yicheejia.modules.shop.entity.IntentionCustomerEntity;
import com.yicheejia.modules.shop.service.CustomerService;
import com.yicheejia.modules.shop.service.IntentionCustomerService;
import com.yicheejia.modules.sys.entity.SysRoleEntity;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.entity.SysUserRoleEntity;
import com.yicheejia.modules.sys.service.SysRoleService;
import com.yicheejia.modules.sys.service.SysUserRoleService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.workflow.service.ActivitiBaseService;



@Service("ycejOrderService")
public class YcejOrderServiceImpl extends ServiceImpl<YcejOrderDao, YcejOrderEntity> implements YcejOrderService {
	@Autowired
	private YcejOrderDao orderDao;
	@Autowired
    private YcejOrderDetailService ycejOrderDetailService;
	@Autowired
    private CustomerService customerService;
	@Autowired
	private ActivitiBaseService activiti;
	@Autowired
	private IntentionCustomerService intentionCustomerService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
    private OrderFileService orderFileService;
	@Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
	//日志
    protected Logger logger = LoggerFactory.getLogger(YcejOrderService.class);
	@Autowired
	SerialNoSerUtils serialNoUtils;
    @Override
    @ShopFilter
    public LayuiPage queryPage(Map<String, Object> params) {
    	//Start增加时间范围搜索 update by lw 2018-10-12
    	if(null != params.get("rDate") && !params.get("rDate").toString().isEmpty()) {
        	String rDate = (String) params.get("rDate");
        	String[] rDates = rDate.split(" ");
        	String rStartDate = rDates[0];
        	String rEndDate = rDates[2];
        	params.put("rStartDate", rStartDate);
        	params.put("rEndDate", rEndDate);
    	}
    	//end增加时间范围搜索 update by lw 2018-10-12
    	Page<YcejOrderEntity> page = new Query<YcejOrderEntity>(params).getPage();

    	page.setRecords(baseMapper.queryOrder(page,params));
    	return new LayuiPage(page.getRecords(), page.getTotal());
    }
    @Override
    @Transactional
    public void insertOrder(YcejOrderEntity ycejOrder){
    	try {
    		ycejOrder.setInsertTime(DateUtils.getDate());
    		
    		ycejOrder.setUpdateTime(DateUtils.getDate());
    		ycejOrder.setInsertId(ShiroUtils.getUserId()+"");
    		String shopNo = ycejOrder.getShopNo();
    		shopNo = "D"+shopNo.substring(shopNo.length()-4,shopNo.length());
    		String orderNo = serialNoUtils.getSerialNumber(SerialType.order_no,shopNo);//生成订单编号
    		ycejOrder.setOrderNo(orderNo);
    		baseMapper.insert(ycejOrder);//订单保存
    		ycejOrderDetailService.saveDetail(ycejOrder);//费用明细
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据保存异常！"+e.getMessage(),e);
			throw new RRException("插入错误！");
		}
    }
    @Override
    public Map updateOrderById(YcejOrderEntity ycejOrder){
		 ycejOrder.setUpdateTime(DateUtils.getDate());
		 ycejOrder.setUpdateId(ShiroUtils.getUserId()+"");
		 /*if(StringUtils.isBlank(ycejOrder.getOrderNo())){
			 String shopNo = ycejOrder.getShopNo();
			 shopNo = "D"+shopNo.substring(shopNo.length()-4,shopNo.length());
			 String orderNo = serialNoUtils.getSerialNumber(SerialType.order_no,shopNo);//生成订单编号
			 ycejOrder.setOrderNo(orderNo);
		 }*/
    	 baseMapper.updateById(ycejOrder);
    	 Map map = new HashMap();
    	 return map;
    }
    //提交订单状态修改为运营待审核
    @Override
    //@StartFlow(id="id",processKey=WfConstants.PROCESS_ORDER_KEY)
    public void updateOrder(Map<String, Object> params){
    	 params.put("updateTime", DateUtils.getDate());
    	 baseMapper.updateOrder(params);
    }
    
	@Override
	@SysLog(value = "财务审核",id = "id")
	//@ExecuteTask(id = "id",processKey=WfConstants.PROCESS_ORDER_KEY )
	@Transactional
	public R submitService(List<YcejOrderEntity> list) {
		 try {
			 List<CustomerEntity> array = new ArrayList<CustomerEntity>();
			 List<IntentionCustomerEntity> intention = new ArrayList<IntentionCustomerEntity>();
			 Map<String, Object> params = new HashMap<String, Object>();
			 for(YcejOrderEntity ycejOrder : list){
				 ycejOrder.setUpdateTime(DateUtils.getDate());
				 String id = ycejOrder.getId();
				 YcejOrderEntity order = baseMapper.selectById(id);
				 if(StringUtils.isBlank(order.getId()+"")){
					 return R.error("未查询到订单号为"+order.getOrderNo()+"的相关订单！");
				 } 
				 /**CustomerEntity customer = new CustomerEntity();
				 customer.setCustomerName(order.getCustomerName());
				 customer.setCustomerTel(order.getContactInformation());
				 customer.setCardNo(order.getIdNo());
				 customer.setCarType(order.getCarModelId()+"");
				 customer.setBuyDate(order.getInsertTime());
				 customer.setCarColor(order.getAppearanceColour());
				 customer.setBuyManner(order.getSellType());
				 customer.setHandoverTime(DateUtils.getDate());
				 customer.setHandoverShop(order.getShopId()+"");
				 customer.setCustomerState(1);
				 customer.setInsUser(ycejOrder.getUpdateId());
				 customer.setUpdTime(DateUtils.getDate());
				 customer.setInsTime(DateUtils.getDate());
				 customer.setUpdUser(ycejOrder.getUpdateId());
				 if(StringUtils.isNotBlank(order.getShopId())){
					 customer.setShopId(Integer.parseInt(order.getShopId()));
				 }
				 customer.setRemark(id);//订单id暂时存入备注
				 array.add(customer);
				 //意向客户处理
				 IntentionCustomerEntity  incustomer = new IntentionCustomerEntity();
				 incustomer.setOrderNo(id);
				 incustomer.setShopId(Integer.parseInt(order.getShopId()));
				 incustomer.setIdCardNo(order.getIdNo());
				 incustomer.setCustomerTel(order.getContactInformation());
				 incustomer.setCustomerName(order.getCustomerName());*/
				 /*params.put("customerName", customer.getCustomerName());
				 params.put("idCardNo",  customer.getCardNo());
				 params.put("customerTel", customer.getCustomerTel());
				 List<IntentionCustomerEntity> intentionCust = intentionCustomerService.selectIntentionCust(params);
				 if( intentionCust != null && intentionCust.size() > 0 ){
					 intentionCustomerService.updateCustomer(incustomer);
				 }*/
				 //intention.add(incustomer);
				 //库存处理
				/* InventoryEntity inventory = new InventoryEntity();
				 if(StringUtils.isNotBlank(order.getInventoryId())){
					 inventory.setInventoryId(order.getInventoryId());
					 if(StringUtils.isNotBlank(ycejOrder.getUpdateId())){
						 inventory.setUpdateId(Integer.parseInt(ycejOrder.getUpdateId()));
					 }
					 inventory.setUpdateTime(DateUtils.getDate());
					 inventory.setCarStatus(3);
					 inventoryService.updateinventory(inventory) ;
				 }
				 */
			 }
			 if(list.size() > 0){
				 this.updateBatchById(list);//财务已确认订单状态更新
			 }
			 /**if(array.size() > 0){
				//风控客户处理	
				 Map<String,String> map = new HashMap<String,String>();
				 for(CustomerEntity customer : array){
					 String cardNo = customer.getCardNo();
					 params.put("cardNo", cardNo);
					 List<CustomerEntity> cust = customerService.selectCustByCard(params);
					 params.put("idCardNo",  cardNo);
					 params.put("customerTel", customer.getCustomerTel());
					 List<IntentionCustomerEntity> intentionCust = intentionCustomerService.selectIntentionCust(params);
					 if(cust.size() > 0 && (intentionCust .size() <= 0 || intentionCust == null)){//根据身份证号查询风控客户和意向客户是否存在，都不存在新增风控客户，存在更新为成交客户
						 customerService.updateCustomer(customer);//更新成交客户
					 }else{
						 //意向客户处理
						 if(intentionCust.size() > 0){//这个地方是批量插入
							if (!map.containsKey(customer.getRemark())) {
								intentionCustomerService.updateCustomer(intention);
								map.put(customer.getRemark(), customer.getRemark());
							} 
						 }
						 customerService.insert(customer) ;
					 }
				 }
			 }*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RRException("保存错误!"+e.getMessage());
		}
    	 return R.ok();
	}
	@Override
	public List<YcejOrderEntity> queryOrder() {
		EntityWrapper<YcejOrderEntity> wrapper = new EntityWrapper<>();
		return this.selectList(wrapper);
	}
	
	/**
	 *定时任务批量更新订单状态为下一步即资源待匹配
	 */
	@Override
	@SysLog("订单资源待匹配")
	@ExecuteTask(id = "id",processKey=WfConstants.PROCESS_ORDER_KEY,args = {"status"} )
	@Transactional
	public R updateOrderBatch(Map<String, Object> params) {
		baseMapper.updateOrder(params);
		return R.ok().put(WfConstants.ACTION, 1);
	}
	
	/**
	   * 工作流
	   */
	  @Override
	  @ExecuteTask(id = "id",processKey=WfConstants.PROCESS_ORDER_KEY,args = {"status"} )
	  @Transactional
	  public R updateTask(YcejOrderEntity ycejOrder,Map<String, Object> params){
	    return R.ok().put(WfConstants.ACTION, 1).put(WfConstants.REMARK, params.get("remark"));
	  }
	
	/**
	 * POS定金支付完毕修改订单状态，并触发工作流
	 */
	@Override
	@ExecuteTask(id = "id",processKey=WfConstants.PROCESS_ORDER_KEY,args = {"status"} )
	public R updateOrderByPayment(Map<String, Object> params) {
    	 baseMapper.updateOrderByPay(params);
    	 return R.ok().put(WfConstants.ACTION, 1); 
	}
	
	/**
	 * 订单取消时修改库存状态
	 * @param params
	 */
	@Override
	public void updateInventory(Map<String, Object> params) {
		String status = params.get("status")+"";
		try {
			String id = params.get("id")+"";
			InventoryEntity inventory = new InventoryEntity();
			YcejOrderEntity order = baseMapper.selectById(id);
			if("06".equals(status)){
				 if(StringUtils.isNotBlank(order.getInventoryId())){
					 inventory.setInventoryId(order.getInventoryId());
					 //inventory.setUpdateId( ShiroUtils.getUserId());
					 inventory.setUpdateTime(DateUtils.getDate());
					 inventory.setCarStatus(5);
					 inventoryService.updateinventory(inventory) ;
				 }
			}
			
			if("12".equals(status) ){
				if(StringUtils.isNotBlank(order.getInventoryId())){
					inventory.setInventoryId(order.getInventoryId());
					//inventory.setUpdateId(ShiroUtils.getUserId()+"");
					inventory.setUpdateTime(DateUtils.getDate());
					inventory.setCarStatus(1);
					inventoryService.updateinventory(inventory) ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RRException("保存错误");
		}
	}
	
	/**
	 * 订单退回
	 * @param params
	 */
	@Override
	@Transactional
	public void returnCheck(Map<String, Object> params) {
		String id = params.get("id")+"";
        String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
    	if(!activiti.getUserHaveTasksUser(userId,id)){
			throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
		}
		String status = params.get("status")+"";
		//String id = params.get("id")+"";
		YcejOrderEntity order = baseMapper.selectById(id);
		//03定金待支付，05资源匹配，10财务确认时可退回
		if("03".equals(status)){
			params.put("status", "01");
		}else if("05".equals(status)){
			params.put("status", "03"); 
			params.put("inventoryId", 0);
			//退回同时库存更新过来
			InventoryEntity inventory = new InventoryEntity();
			if(StringUtils.isNotBlank(order.getInventoryId())){
				inventory.setInventoryId(order.getInventoryId());
				//inventory.setUpdateId(ShiroUtils.getUserId()+"");
				inventory.setUpdateTime(DateUtils.getDate());
				inventory.setCarStatus(1);
				inventoryService.updateinventory(inventory) ;
			}
		}else if("10".equals(status)){
			if(order.getSellType() == 1){//全款
				params.put("status", "08");
			}
			if(order.getSellType() == 2){//分期
				params.put("status", "09");
			}
		}else{
			//throw new RRException("操作错误！"); 
		}
		params.put("updateId", ShiroUtils.getUserId()+"");
		this.updateOrder(params);
		
	}
	/**
	 * 尾款/首付款时提交
	 * @param order
	 */
	@Override
	public void submitOne(YcejOrderEntity order) {
		try {
			Long uId=ShiroUtils.getUserId();
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("user_id", uId);
        	List<SysUserRoleEntity> list = sysUserRoleService.selectByMap(map);
        	Boolean flag = false;
        	for (SysUserRoleEntity sysUserRoleEntity : list) {
    			SysRoleEntity entity = sysRoleService.selectById(sysUserRoleEntity.getRoleId());
    			String roleName = entity.getRoleName();
    			if (roleName.contains("店长")) {//只能店长提交
    				flag = true;
    			}
    		}
        	if (!flag) {
        		throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
    		}
        	//判断在待支付时刷卡完成后(刷卡完成会修改订单状态为已支付)直接点提交
        	YcejOrderEntity yjorder = baseMapper.selectById(order.getId());
        	if(!"08".equals(yjorder.getStatus()) && !"09".equals(yjorder.getStatus())){
        		throw new RRException("请刷新页面！");
        	}
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	String[] method = WfConstants.payMethod;
	    	String paymethod = order.getPayMethod();
	    	if(!Arrays.asList(method).contains(paymethod)){
	    		logger.error("付款方式不为转账时请刷卡支付！");
				throw new RRException("付款方式不为转账时请刷卡支付！");
	    	}
	    	for (String key : method) {
                if (order.getPayMethod().contains(key)) {
            		if(order.getSellType() ==  1){//全款
            			params.put("fileType", "04");//尾款转账凭证
            			//params.put("status", "081");//尾款已支付
            		}else if(order.getSellType() ==  2 || order.getSellType() ==  3){//分期或者优壹车
            			params.put("fileType", "05");//首付款转账凭证
            			//params.put("status", "091");//首付款已支付
            		}
            		params.put("status", "10");
    				params.put("orderId", order.getId());
    				List<OrderFileEntity> list1 = orderFileService.queryList(params);
    				if(list1.size() > 0){
    					params.put("id", order.getId());
    					params.put("updateId", ShiroUtils.getUserId()+"");
    					params.put("commercialIfout", order.getCommercialIfout());
    					ycejOrderDetailService.modifyDetail(order);
    					baseMapper.updateOrder(params);
    					//this.updateOrder(params);
    				}else{
    					logger.error("付款方式为转账时请上传转账小票！");
    					throw new RRException("付款方式为转账时请上传转账小票！");
    				}
                }
            }
	    	/*if(StringUtils.isNotBlank(order.getPayMethod()) && "04".equals(order.getPayMethod())){//转账

			}*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			throw new RRException(e.getMessage());
		}
	}
	/**
	 * 定金支付
	 */
	@Override
	public void submitTwo(YcejOrderEntity order) {
		String id = order.getId();
        String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
    	if(!activiti.getUserHaveTasksUser(userId,id)){
			throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
		}
    	//判断在待支付时刷卡完成后(刷卡完成会修改订单状态为已支付)直接点提交
    	YcejOrderEntity yjorder = baseMapper.selectById(order.getId());
    	if(!"03".equals(yjorder.getStatus())){
    		throw new RRException("请刷新页面！");
    	}
    	Map<String, Object> params = new HashMap<String, Object>();
    	String[] method = WfConstants.payMethod;
    	String paymethod = order.getPayMethod();
    	if(!Arrays.asList(method).contains(paymethod)  && order.getIsDisposable() == 1){//一次性付款需刷卡或者转账支付
    		logger.error("付款方式不为转账时请刷卡支付！");
			throw new RRException("付款方式不为转账时请刷卡支付！");
    	}
    	for (String key : method) {
            if (order.getPayMethod().contains(key)) {
            	//只有定金待支付
        		params.put("fileType", "03");//定金转账凭证
            	params.put("status", "05");
				params.put("orderId", order.getId());
				List<OrderFileEntity> list1 = orderFileService.queryList(params);
				if(list1.size() > 0){
					params.put("id", order.getId());
					params.put("updateId", ShiroUtils.getUserId()+"");
					ycejOrderDetailService.modifyDetail(order);
					//baseMapper.updateOrder(params);
					this.updateOrder(params);
				}else{
					logger.error("付款方式为转账时请上传转账小票！");
					throw new RRException("付款方式为转账时请上传转账小票！");
				}
            }
        }
	}
	/**
	 * 资源待匹配时 确认匹配时处理
	 * @param params
	 */
	@Override
	public void updateByOrder(Map<String, Object> params) {
		String inventoryId = params.get("inventoryId")+"";
		/*InventoryEntity invent = inventoryService.selectById(inventoryId);
		if(StringUtils.isNotBlank(invent.getInventoryId())){
			Integer carSource = invent.getCarSource();
			if(carSource == 2 ){//保证金车
				throw new RRException("保证金车辆请提交采购单！");
			}
		}*/
		//库存处理
		/* InventoryEntity inventory = new InventoryEntity();
		 if(StringUtils.isNotBlank(inventoryId)){
			 inventory.setInventoryId(inventoryId);
			 //inventory.setUpdateId( ShiroUtils.getUserId());
			 inventory.setUpdateTime(DateUtils.getDate());
			 inventory.setCarStatus(5);
			 inventoryService.updateinventory(inventory) ;
		 }*/
		 params.put("updateId", ShiroUtils.getUserId());
		 this.updateOrder(params);
	}
	
	/**
	 * 风控客户与意向客户处理成交客户
	 * @param params
	 */
	@Override
	public void modifyCustomer(Map<String, Object> params) {
		try {
			String id = params.get("id")+"";
			YcejOrderEntity order = baseMapper.selectById(id);
			CustomerEntity customer = new CustomerEntity();
			customer.setCustomerName(order.getCustomerName());
			customer.setCustomerTel(order.getContactInformation());
			customer.setCardNo(order.getIdNo());
			customer.setCarType(order.getCarModelId()+"");
			customer.setBuyDate(order.getInsertTime());
			customer.setCarColor(order.getAppearanceColour());
			customer.setBuyManner(order.getSellType());
			customer.setHandoverTime(DateUtils.getDate());
			customer.setHandoverShop(order.getShopId()+"");
			customer.setCustomerState(1);
			customer.setInsUser(ShiroUtils.getUserId()+"");
			customer.setUpdTime(DateUtils.getDate());
			customer.setInsTime(DateUtils.getDate());
			customer.setUpdUser(ShiroUtils.getUserId()+"");
			if(StringUtils.isNotBlank(order.getShopId())){
				customer.setShopId(Integer.parseInt(order.getShopId()));
			}
			customer.setRemark(id);//订单id暂时存入备注
			//意向客户处理
			IntentionCustomerEntity  incustomer = new IntentionCustomerEntity();
			incustomer.setOrderNo(id);
			incustomer.setShopId(Integer.parseInt(order.getShopId()));
			incustomer.setIdCardNo(order.getIdNo());
			incustomer.setCustomerTel(order.getContactInformation());
			incustomer.setCustomerName(order.getCustomerName());
			//风控客户处理	
			String cardNo = customer.getCardNo();
			params.put("cardNo", cardNo);
			List<CustomerEntity> cust = customerService.selectCustByCard(params);
			params.put("idCardNo",  cardNo);
			params.put("customerTel", customer.getCustomerTel());
			List<IntentionCustomerEntity> intentionCust = intentionCustomerService.selectIntentionCust(params);
			if(cust.size() > 0 && (intentionCust .size() <= 0 || intentionCust == null)){//根据身份证号查询风控客户和意向客户是否存在，都不存在新增风控客户，存在更新为成交客户
				customerService.updateCustomer(customer);//更新成交客户
			}else{
				//意向客户处理
				if(intentionCust.size() > 0){//这个地方是批量插入
					intentionCustomerService.updateCustomer(incustomer);
				}
				customerService.insert(customer) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("成交客户处理异常:" + e.getMessage());
			throw new RRException("成交客户处理异常:" + e.getMessage());
		}
	}
	
	@Override
	public void modifyInventory(Map<String, Object> params) {
		try {
			String id = params.get("id")+"";
			YcejOrderEntity order = baseMapper.selectById(id);
			InventoryEntity inventory = new InventoryEntity();
			if(StringUtils.isNotBlank(order.getInventoryId())){
				inventory.setInventoryId(order.getInventoryId());
				//inventory.setUpdateId(Integer.parseInt(ShiroUtils.getUserId()));
				inventory.setUpdateTime(DateUtils.getDate());
				inventory.setCarStatus(3);
				inventoryService.updateinventory(inventory) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("库存处理异常:" + e.getMessage());
			throw new RRException("库存处理异常:" + e.getMessage());
		}
	}
	/**
	 * 优壹车拆分--获取列表
	 */
	@Override
	@ShopFilter
	public LayuiPage queryCheapCarOrderPage(Map<String, Object> params) {

    	//Start增加时间范围搜索 update by lw 2018-10-12
    	if(null != params.get("rDate") && !params.get("rDate").toString().isEmpty()) {
        	String rDate = (String) params.get("rDate");
        	String[] rDates = rDate.split(" ");
        	String rStartDate = rDates[0];
        	String rEndDate = rDates[2];
        	params.put("rStartDate", rStartDate);
        	params.put("rEndDate", rEndDate);
    	}
    	//end增加时间范围搜索 update by lw 2018-10-12
    	Page<YcejOrderEntity> page = new Query<YcejOrderEntity>(params).getPage();

    	page.setRecords(baseMapper.queryCheapCarOrder(page,params));
    	return new LayuiPage(page.getRecords(), page.getTotal());
	}
}
