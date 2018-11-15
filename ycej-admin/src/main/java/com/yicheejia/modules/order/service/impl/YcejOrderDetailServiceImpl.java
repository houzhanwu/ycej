package com.yicheejia.modules.order.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.order.dao.OrderPaydetailDao;
import com.yicheejia.modules.order.dao.RepaymentDao;
import com.yicheejia.modules.order.dao.YcejOrderDetailDao;
import com.yicheejia.modules.order.entity.OrderPaydetailEntity;
import com.yicheejia.modules.order.entity.SalePromotionEntity;
import com.yicheejia.modules.order.entity.YcejOrderDetailEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.order.service.OrderPaydetailService;
import com.yicheejia.modules.order.service.SalePromotionService;
import com.yicheejia.modules.order.service.YcejOrderDetailService;
import com.yicheejia.modules.order.service.YcejOrderService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;



@Service("ycejOrderDetailService")
public class YcejOrderDetailServiceImpl extends ServiceImpl<YcejOrderDetailDao, YcejOrderDetailEntity> implements YcejOrderDetailService {
	@Autowired
	private YcejOrderDetailDao orderDetailDao;
	@Autowired
	private OrderPaydetailDao orderPayDetailDao;
	@Autowired
	private OrderPaydetailService orderPaydetailService;
	@Autowired
    private YcejOrderService ycejOrderService;
	@Autowired
	private SalePromotionService salePromotionService;
	@Autowired
	private RepaymentDao repaymentDao;
	//日志
    protected Logger logger = LoggerFactory.getLogger(YcejOrderDetailService.class);
    
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<YcejOrderDetailEntity> page = this.selectPage(
                new Query<YcejOrderDetailEntity>(params).getPage(),
                new EntityWrapper<YcejOrderDetailEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }
    /**
     * 新增与更新合并了
     */
	@Override
	@Transactional
	public void saveDetail(YcejOrderEntity order) {
		//实体接收后，使用map
		Map<String, Object> params = new HashMap<String, Object>();
		params = amountByOrder(order);
		List<YcejOrderDetailEntity> list = new ArrayList<YcejOrderDetailEntity>();
		String paytype = "";
		String payMethod = "";
		String payDate = "";
		BigDecimal amount = BigDecimal.ZERO;
		String orderNo = "";
		try {
			orderNo = order.getOrderNo();
			payMethod =order.getPayMethod();
			payDate = order.getPayDate();
			list =  orderDetail(params,order);
			params.clear();
			//params.put("payType", "01");//只有定金时候可以修改，也就是暂存的时候，后面只有提交流转都是走新增，前端也没有放值
			//费用明细数据 先根据主键查询是否存在数据，不存在新增，存在走修改
			params.put("orderNo", orderNo);
			
			List<YcejOrderDetailEntity> detail = orderDetailDao.query(params);
			/*if(detail.size()>0 && order.getAmount01() != null){//只能更新费用明细的定金
				Map<String, Object> map = new HashMap<String, Object>();
				//定金待支付修改下一个状态（全款跳到资源匹配，分期跳到定金已支付）时转账把状态改为支付完成
				//订单提交和车辆已确认时调用,订单提交时传入为02
				if("04".equals(payMethod) && !"02".equals(order.getStatus())){
					map.put("payAmount", order.getAmount01());
					map.put("payStatus", 3);
				}else{//如果修改时不是转账 还给放0
					map.put("payAmount", BigDecimal.ZERO);
					map.put("payStatus", 1);
				//}
				map.put("orderNo", orderNo);
				map.put("payType", "01");
				map.put("payMethod", payMethod);
				map.put("payDate", payDate);
				//能修改的只能是定金也就是amount01，所以直接取params
				map.put("amount", order.getAmount01());
				map.put("updateId", ShiroUtils.getUserId()+"");
				orderDetailDao.updateOrder(map);//费用明细定金更新
			}else{
				if(list.size()>0){
					orderDetailDao.insertBatch(list);//费用明细批量插入
				}
			}*/
			//先删除在插入
			if(detail.size() > 0){
				for(YcejOrderDetailEntity orderDetail : detail){
					orderDetailDao.deleteById(orderDetail.getOrderDetailId());
				}
			}
			if(list.size()>0){
				orderDetailDao.insertBatch(list);//费用明细批量插入
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("处理异常！"+ e.getMessage());
			throw new RRException(e.getMessage());
		}
	}

	@Override
	public List<YcejOrderDetailEntity> query(Map<String, Object> params) {
		/*YcejOrderDetailEntity orderDetail = new YcejOrderDetailEntity();
		List<YcejOrderDetailEntity> detaillist = new ArrayList<YcejOrderDetailEntity>();*/
		//查询费用明细
		List<YcejOrderDetailEntity> list = orderDetailDao.query(params);
		/**if(list.size()>0){
			for(int i=0;i<list.size();i++){
				String payType = list.get(i).getPayType();
				if("01".equals(payType)){
					orderDetail.setAmount01(list.get(i).getAmount());
				}else if("02".equals(payType)){
					orderDetail.setAmount02(list.get(i).getAmount());
				}else if("03".equals(payType)){
					orderDetail.setAmount03(list.get(i).getAmount());
				}else if("04".equals(payType)){
					orderDetail.setAmount04(list.get(i).getAmount());
				}else if("05".equals(payType)){
					orderDetail.setAmount05(list.get(i).getAmount());
				}else if("06".equals(payType)){
					orderDetail.setAmount06(list.get(i).getAmount());
				}else if("07".equals(payType)){
					orderDetail.setAmount07(list.get(i).getAmount());
				}else if("08".equals(payType)){
					orderDetail.setAmount08(list.get(i).getAmount());
				}
				YcejOrderDetailEntity arry = list.get(i);
				orderDetail.setOrderDetailId(arry.getOrderDetailId());
				orderDetail.setOrderNo(arry.getOrderNo());
				orderDetail.setPayDate(arry.getPayDate());
				orderDetail.setPayMethod(arry.getPayMethod());
				detaillist.add(orderDetail);
			}
		}*/
		return list;
	}

	@Override
	public void updateByOrder(Map<String, Object> params) {
	}
	
	private Map<String, Object> amountByOrder(YcejOrderEntity order) {
		//实体接收后，使用map
		Map<String, Object> params = new HashMap<String, Object>();
		if(order.getAmount01() != null)  params.put("amount01", order.getAmount01());
		if(order.getAmount02() != null)  params.put("amount02", order.getAmount02());
		if(order.getAmount03() != null)  params.put("amount03", order.getAmount03());
		if(order.getAmount04() != null)  params.put("amount04", order.getAmount04());
		if(order.getAmount05() != null)  params.put("amount05", order.getAmount05());
		if(order.getAmount06() != null)  params.put("amount06", order.getAmount06());
		if(order.getAmount07() != null)  params.put("amount07", order.getAmount07());
		if(order.getAmount08() != null)  params.put("amount08", order.getAmount08());
		if(order.getAmount09() != null)  params.put("amount09", order.getAmount09());
		if(order.getAmount10() != null)  params.put("amount10", order.getAmount10());
		if(order.getAmount11() != null)  params.put("amount11", order.getAmount11());
		BigDecimal amount07 = (BigDecimal) params.get("amount07");
		BigDecimal amount08 = (BigDecimal) params.get("amount08");
		BigDecimal amount11 = (BigDecimal) params.get("amount11");
		if(amount07 != null && amount08 != null && amount11 != null){
			if(amount11.compareTo(amount07.add(amount08)) != 0){
				throw new RRException("交强和商业之和不等于保险金额，请重试");
			}
		}
		return params;
	}
	
	private List<YcejOrderDetailEntity> orderDetail(Map<String, Object> params,YcejOrderEntity order) {
		List<YcejOrderDetailEntity> list = new ArrayList<YcejOrderDetailEntity>();
		String type = "";
		String paytype = "";
		String payMethod = "";
		String payDate = "";
		BigDecimal amount =  BigDecimal.ZERO;
		String orderNo = "";
		try {
			//YcejOrderEntity order = (YcejOrderEntity) params.get("order");
			orderNo = order.getOrderNo();
			payMethod =order.getPayMethod();
			payDate = order.getPayDate();
			BigDecimal amount07 = BigDecimal.ZERO;
			BigDecimal amount08 = BigDecimal.ZERO;
			BigDecimal amount01 = BigDecimal.ZERO;
			for (Entry<String, Object> entry : params.entrySet()) {
				YcejOrderDetailEntity orderDetail = new YcejOrderDetailEntity();
				String key = entry.getKey();
				String value =  entry.getValue()+"";
				if(key.contains("amount")){
					if(key.equals("amount01")){//定金
						orderDetail.setPayType("01");
						amount01 = new BigDecimal(value);
						type="定金";
					}else if(key.equals("amount02")){
						orderDetail.setPayType("02");
						type="首付";
					}else if(key.equals("amount03")){
						orderDetail.setPayType("03");
						type="上牌";
					}else if(key.equals("amount04")){
						orderDetail.setPayType("04");
						type="尾款";
					}else if(key.equals("amount05")){
						orderDetail.setPayType("05");
						type="代缴购置税";
					}else if(key.equals("amount06")){
						orderDetail.setPayType("06");
						type="续保押金";
					}else if(key.equals("amount07")){
						orderDetail.setPayType("07");
						type="商业险";
						amount07 = new BigDecimal(value);
					}else if(key.equals("amount08")){
						orderDetail.setPayType("08");
						type="交强险";
						amount08 = new BigDecimal(value);
					}else if(key.equals("amount09")){
						orderDetail.setPayType("09");
						type="优壹车保证金";
					}else if(key.equals("amount10")){
						orderDetail.setPayType("10");
						type="优壹车手续费";
					}else if(key.equals("amount11")){
						orderDetail.setPayType("11");
						type="保险";
					}else{
						orderDetail.setPayType("");
					}
					if(StringUtils.isNotBlank(value)){
						orderDetail.setAmount(new BigDecimal(value));
					}else{
						orderDetail.setAmount(BigDecimal.ZERO);
					}
					orderDetail.setInsertId(ShiroUtils.getUserId()+"");
					orderDetail.setInsertTime(DateUtils.getDate());
					orderDetail.setUpdateTime(DateUtils.getDate());
					if(StringUtils.isNotBlank(payDate) && !"null".equals(payDate)){
						orderDetail.setPayDate(DateUtils.stringToDate(payDate,"yyyy-MM-dd"));
					}
					orderDetail.setOrderNo(orderNo);
					if(StringUtils.isNotBlank(payMethod)){
						orderDetail.setPayMethod(payMethod);
					}
					/*if("04".equals(payMethod) && !"02".equals(order.getStatus())){//转账时直接放入需支付金额，状态为已支付
						orderDetail.setPayStatu(3);
						orderDetail.setPayAmount(orderDetail.getAmount());
					}else{*/
						orderDetail.setPayStatu(1);
						orderDetail.setPayAmount(BigDecimal.ZERO);
						
					//}
					list.add(orderDetail);
					paytype += type + ",";
					//循环加出来的金额包括商业外出和交强外出
					amount = amount.add(new BigDecimal(value));
				}
			}
			amount = amount.subtract(amount07).subtract(amount08);
			//支付明细处理
			if(paytype.length()>0){  
				paytype = (",".equals(paytype.substring(paytype.length() - 1) )) ? paytype.substring(0, paytype.length() - 1) : paytype;
			}
			//小于0 时说明费用明细未存入值，支付明细也不处理了
			if(list.size()>0){
				params.clear();
				params.put("status", order.getStatus());
				params.put("type", paytype);
				params.put("amount", amount);
				params.put("amount01", amount01);
				params.put("orderNo", orderNo);
				params.put("payMethod", payMethod);
				params.put("payDate", payDate);
				params.put("id", order.getId());
				orderPaydetailService.savePayDetail(params);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("支付明细处理异常！"+ e.getMessage());
			throw new RRException("支付明细处理异常"+e.getMessage());
		}
	}
	/**
	 *根据 全款 ，分期或者优壹车  确定订单状态流转，并保存费用明细与支付明细 
	 */
	@Override
	@Transactional
	public void newcheck(YcejOrderEntity order) {
		int sellType = order.getSellType();
		String status = "";
		if(sellType == 1){//全款
			status = "08";
		}else if(sellType == 2 || sellType == 3){//分期或者优壹车
			status = "09";
		}else{
			throw new RRException("不存在的购买方式，请稍后在试！");
		}
		order.setStatus(status);
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			//优惠金额处理 add by 20181030 begin
			SalePromotionEntity promotion = order.getPromotion();
			String saleId = promotion.getSaleId();
			String zpId = promotion.getZpId();
			params.put("orderId", order.getId());
			params.put("saleId", saleId);
			params.put("zpId", zpId);
			salePromotionService.updateBySaleId(params);
			//优惠金额处理 add by 20181030 end
			
			saveDetail(order);//保存支付明细与费用明细
			
			params.put("status", status);
			params.put("id", order.getId());
			params.put("commercialIfout", order.getCommercialIfout());
			ycejOrderService.updateOrder(params);//订单状态流转
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("提交失败！"+ e.getMessage());
			throw new RRException("提交失败！"+e.getMessage());
		}
	}
	
	/**
	 * 修改未支付完成的支付明细与费用明细
	 * @param params
	 */
	@Override
	public void updateDetail(Map<String, Object> params) {
		//刷卡和转账结合情况
		String id = params.get("id")+"";
		String payDate = params.get("payDate")+"";
		YcejOrderEntity order = ycejOrderService.selectById(id);
		if("03".equals(order.getStatus()) ){ //订单状态为定金待支付或者车辆已确认
			params.put("orderNo", order.getOrderNo());
			params.put("updateId", ShiroUtils.getUserId()+"");
			params.put("payStatu", 3);//转账时直接修改状态为支付完成
			params.put("payDate", payDate);
			params.put("payType", "定金");
			params.put("Type", "01");
			//费用明细的修改
			orderDetailDao.updateDetail(params);
			//支付明细的修改.
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("order_no",  order.getOrderNo());
			List<OrderPaydetailEntity> list = orderPaydetailService.selectByMap(map);
			BigDecimal pay = BigDecimal.ZERO;
			if(list.size() > 0){
				for(OrderPaydetailEntity orderpay : list){
					BigDecimal payAmount = orderpay.getPayAmount();
					BigDecimal amount = orderpay.getAmount();
					Integer payStatu = orderpay.getPayStatu();
					if(payStatu == 1){
						pay = amount ;
					}
					if(payStatu == 2){
						pay = payAmount;
					}
				}
				params.put("payAmount", pay);
			}
			orderPaydetailService.updatePayDetail(params);
		}
	}

	/**
	 * 修改未支付完成的支付明细与费用明细
	 * @param params
	 */
	@Override
	public void modifyDetail(YcejOrderEntity order) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<OrderPaydetailEntity> payList = order.getOrderPayList();
		BigDecimal payAmount = BigDecimal.ZERO;
		params.put("orderNo", order.getOrderNo());
		List<OrderPaydetailEntity> payDetail = orderPayDetailDao.queryPayDetail(params);
		if(payList != null && payList.size() > 0){
			for(OrderPaydetailEntity orderPay : payList){
				payAmount = payAmount.add(orderPay.getAmount());
			}
		}
		Integer isDisposable = order.getIsDisposable();
		params.put("updateId", ShiroUtils.getUserId()+"");
		BigDecimal amount = payDetail.get(0).getAmount();
		if(isDisposable == 1){//一次性付款      
			if(payAmount.compareTo(amount) != 0){//取已生成的支付明细和转账明细的合计金额
				throw new RRException("转账金额不等于支付金额，请修改！");
			}
			for(OrderPaydetailEntity orderPay : payList){
				params.put("payDate", orderPay.getPayDate());
				params.put("payMethod", orderPay.getPayMethod());
				params.put("payStatu", 3);//转账时直接修改状态为支付完成
				params.put("payAmount", orderPay.getAmount());
				//支付明细处理
				orderPaydetailService.updatePayDetail(params);
				if(orderPay.getAmount().compareTo(amount) < 0){// < 0  b大
					params.put("amount", payDetail.get(0).getAmount());
					params.put("payType", payDetail.get(0).getPayType());
					params.put("payAmount", amount.subtract(orderPay.getAmount()));
					repaymentDao.insertOrderPay(params);
					amount = amount.subtract(orderPay.getAmount());
				}
				if(amount.compareTo(new BigDecimal("0")) == 0){
					break;
				}
			}
			//费用明细处理     转账直接处理支付完成
			orderDetailDao.updateDetail(params);
		}else{
			for(OrderPaydetailEntity orderPay : payList){
				params.put("payDate", orderPay.getPayDate());
				params.put("payMethod", orderPay.getPayMethod());
				params.put("payStatu", 3);//转账时直接修改状态为支付完成
				params.put("payAmount", orderPay.getAmount());
				if("03".equals(order.getStatus())){
					params.put("payType", "定金");
					params.put("Type", "01");
				}
				if("08".equals(order.getStatus()) || "09".equals(order.getStatus())){
					params.put("paytype", "定金");
					params.put("type", "01");
				}
				orderPaydetailService.updatePayDetail(params);
				if(orderPay.getAmount().compareTo(amount) < 0){// < 0  b大
					OrderPaydetailEntity orderPayDetail = orderPaydetailService.selectOne(new EntityWrapper<OrderPaydetailEntity>().addFilter("order_no = '" + order.getOrderNo() + "' and pay_type != '定金'"));
					if(!"03".equals(order.getStatus())){
						params.put("payType", orderPayDetail.getPayType());
					}
					params.put("amount", payDetail.get(0).getAmount());
					params.put("payAmount", amount.subtract(orderPay.getAmount()));
					repaymentDao.insertOrderPay(params);
					amount = amount.subtract(orderPay.getAmount());
				}
				if(amount.compareTo(new BigDecimal("0")) == 0){
					break;
				}
			}
			//费用明细处理     转账直接处理支付完成
			orderDetailDao.updateDetail(params);
		}
			
			/*params.put("payMethod", order.getPayMethod());
			params.put("type", "01");
			//费用明细的修改
			orderDetailDao.updateDetail(params);
			//支付明细的修改
			params.put("paytype", "定金");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("order_no",  order.getOrderNo());
			List<OrderPaydetailEntity> list = orderPaydetailService.selectByMap(map);
			BigDecimal pay = BigDecimal.ZERO;
			if(list.size() > 0){
				for(OrderPaydetailEntity orderpay : list){
					BigDecimal payAmount = orderpay.getPayAmount();
					BigDecimal amount = orderpay.getAmount();
					Integer payStatu = orderpay.getPayStatu();
					if(payStatu == 1){
						pay = amount ;
					}
					if(payStatu == 2){
						pay = payAmount;
					}
				}
				params.put("payAmount", pay);
			}
			orderPaydetailService.updatePayDetail(params);*/
		//}
	}

}
