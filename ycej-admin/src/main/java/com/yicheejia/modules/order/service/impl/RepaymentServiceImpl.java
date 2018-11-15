package com.yicheejia.modules.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.ApiJson;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.JsonUtil;
import com.yicheejia.modules.order.dao.RepaymentDao;
import com.yicheejia.modules.order.dao.YcejOrderDetailDao;
import com.yicheejia.modules.order.entity.RepaymentEntity;
import com.yicheejia.modules.order.entity.YcejOrderDetailEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.order.service.RepaymentService;
import com.yicheejia.modules.order.service.YcejOrderService;
import com.yicheejia.modules.webservices.model.QueryRepaymentForm;
import com.yicheejia.modules.webservices.model.RepaymentForm;

@Service("RepaymentService")
public class RepaymentServiceImpl extends ServiceImpl<RepaymentDao, RepaymentEntity> implements RepaymentService{
	@Autowired
	private RepaymentDao repaymentDao;
	@Autowired
	private YcejOrderDetailDao orderDetailDao;
	@Autowired
	private YcejOrderService ycejOrderService;
	//日志
    protected Logger logger = LoggerFactory.getLogger(RepaymentService.class);
	
	public  static String  key = "FC45931950DB5AA5E513F338A3327BE02299F0333729D05F";
	private final static String privateKey="afa66b7ccfb74724a8bdfb281a2ac66a";
	private final static String privateKey1="E1A52F92150BB765B1DC937A0AB5667A5AF60C25";
	@Override
	public ApiJson queryRepayment(QueryRepaymentForm form) {
		ApiJson j = new ApiJson();
		//首先验证签名
		String signature=form.getSignature();
		String md5= JsonUtil.dispose(form);
		md5=md5.substring(0,md5.lastIndexOf("&"))+privateKey;
				
		md5=JsonUtil.MD5(md5);
		logger.info("md5==="+md5);
		if(!md5.equals(signature)){
			j.setError("加密方式错误！");
			return j;
		}
		String orderNo = form.getOrder_no();
		String shopId = form.getShop_id();
		Map map = new HashMap();
		map.put("orderNo", orderNo);
		map.put("shopId", shopId);
		List<RepaymentEntity> list = repaymentDao.queryOrderPayDetail(map);
		if(list.size()>0){
			BigDecimal pay_sum = BigDecimal.ZERO;
			String order_no = "";
			StringBuilder sb = new StringBuilder() ;
			RepaymentEntity repay = new RepaymentEntity();
			for(int i=0;i<list.size();i++){
				 BigDecimal sumAmount = BigDecimal.ZERO;
				 order_no = list.get(0).getOrderNo();//订单号
				 String  payDetail = list.get(i).getPayDetail();//支付类别
				 BigDecimal paySum = list.get(i).getPaySum();//已支付金额
				 BigDecimal sum = list.get(i).getSum();//需支付金额
				 //pay_sum = paySum == null ? BigDecimal.ZERO : pay_sum.add(paySum);
				// pay_sum = pay_sum.add(paySum);//合并已支付金额
				Integer payStatu = list.get(i).getPayStatu();
				if(payStatu != null && payStatu == 1){
					sumAmount = sum;
				} 
				if(payStatu != null && payStatu == 2){
					sumAmount = paySum;
				}
				 pay_sum = pay_sum.add(sumAmount);
				 //sumAmount = sumAmount.add(sum);//合并需支付金额
				 if(!sb.toString().contains(payDetail)){
					 sb.append(payDetail).append(",");
				 }
			}
			repay.setOrderNo(order_no);
			repay.setPayDetail(sb.substring(0,sb.length()-1));
			repay.setPaySum(pay_sum);//返回刷卡金额=支付金额合计，需支付金额每次不变
			List<RepaymentEntity> payment = new ArrayList<RepaymentEntity>();
			payment.add(repay);
			j.setObject(payment);
		}else{
			j.setError("未查询出相关订单信息，请确认！");
		}
		return j;
	}

	@Override
	@Transactional
	public ApiJson repayment(RepaymentForm form) {
		ApiJson j = new ApiJson();
		try {
			
			String payType = form.getPay_type();
			String cardNo = form.getPay_cardNo();
			//首先验证签名
			String signature=form.getSignature();
			String md5= JsonUtil.dispose(form);
			md5=md5.substring(0,md5.lastIndexOf("&"))+privateKey;
			
			md5=JsonUtil.MD5(md5);
			logger.info("md5==="+md5);
			if(!md5.equals(signature)){
				j.setError("加密方式错误！");
				return j;
			}
			
			if("01".equals(payType) && StringUtils.isEmpty(cardNo)){//刷卡时需填入卡号
				j.setError("刷卡卡号不能为空或错误！");
			}
			Map<String,Object> map = new HashMap<String, Object>();
			String orderNo = form.getOrder_no();
			String shopId = form.getShop_id();
			String payDate = form.getPay_date();
			String sysRefNo = form.getOut_trade_no();
			BigDecimal payAmount = form.getPay_amount();
			/*if(orderNo.length()>4){
				orderNo = orderNo.substring(orderNo.length()-4);
			}*/
			map.put("orderNo", orderNo);
			map.put("shopId", shopId);
			List<RepaymentEntity> list = repaymentDao.queryOrderPayDetail(map);//支付查询
			List<RepaymentEntity> list1 = repaymentDao.queryOrderDetail(map);//费用明细查询
			if("01".equals(payType)){
				map.put("cardNo", cardNo);
			}
			map.put("payDate", payDate);
			map.put("payMethod", payType);//支付方式
			Boolean flag = true;//是否要新插入数据标识
			//支付明细处理
			if(list.size()>0){
				BigDecimal amount = BigDecimal.ZERO;
				BigDecimal pay_amount = BigDecimal.ZERO;
				for(RepaymentEntity pay : list){//正常情况只有一条数据
					BigDecimal	sum = pay.getSum();//sum需支付金额不会变，主要处理刷卡金额大于需支付金额，未处理定金和首付款/尾款同时收情况
					Integer payStatu = pay.getPayStatu();
					BigDecimal	paysum = pay.getPaySum();
					if(payStatu != null && payStatu == 1){
						amount = sum;
					}
					if(payStatu != null && payStatu == 2){
						amount = paysum;
					}
				}
				//amount = amount.add(payS);
				//小于应缴金额处理：修改原有数据状态为支付完成，新增一条同样数据，状态为支付中，并且应缴金额 = 应缴金额-付款金额=剩余金额    amount不变
				//1.如果刷卡金额小于需付金额直接放入刷卡金额，并新增一条部分支付，放入金额=需支付金额-刷卡金额
				//2.如果刷卡金额等于需付金额放入刷卡金额
				//3.如果刷卡金额大于需支付总额,刷卡金额-需支付金额 = 反写定金
				if(payAmount.compareTo(amount)>0){
					for(RepaymentEntity pay : list){
						String pay_type = pay.getPayDetail();
						BigDecimal sum = pay.getSum();
						pay_amount = payAmount.subtract(sum);
						map.put("payType", pay_type);
						map.put("payStatu", 3);
						map.put("payAmount", sum);
						map.put("sysRefNo", sysRefNo);
						//支付明细数据修改
						repaymentDao.upOrderPayDetail(map);
						map.put("orderNo",pay.getOrderNo() );
					}
						//多余的金额按照定金插入,同时插入费用明细
						//map.clear();
						map.put("payType", "定金");
						map.put("payStatu", 3);
						map.put("payAmount", pay_amount);
						map.put("sysRefNo", sysRefNo);
						map.put("amount", pay_amount);
						repaymentDao.insertPay(map);//支付明细新增定金
						//费用明细先不处理
/*						YcejOrderDetailEntity orderDetail = new YcejOrderDetailEntity();
						orderDetail.setAmount(payAmount);
						orderDetail.setPayAmount(payAmount);
						orderDetail.setInsertTime(DateUtils.getDate());
						orderDetail.setUpdateTime(DateUtils.getDate());
						orderDetail.setPayType("01");
						orderDetail.setPayDate(DateUtils.getDate());
						orderDetail.setPayStatu(3);
						orderDetail.setPayMethod(payType);
						if("01".equals(payType)){//pos刷卡填入卡号
							orderDetail.setPaycardno(cardNo);
						}
						orderDetailDao.insert(orderDetail);*///费用明细新增定金
					
				}
				if(payAmount.compareTo(amount)<=0 && payAmount.compareTo(BigDecimal.ZERO) > 0){
					for(RepaymentEntity pay : list){
						String pay_type = pay.getPayDetail();
						BigDecimal paySum = BigDecimal.ZERO;
						String order_no = pay.getOrderNo();
						BigDecimal sum = pay.getSum();
						BigDecimal pay_sum = pay.getPaySum();
						if(payAmount.compareTo(amount)<0){
							paySum = amount.subtract(payAmount);
							//pay_sum = payAmount;
							flag = false;
						}
						map.put("payType", pay_type);//这个支付类别按取出数据放入
						map.put("payStatu", 3);
						map.put("payAmount", payAmount);
						map.put("sysRefNo", sysRefNo);
						//支付明细数据修改
						repaymentDao.upOrderPayDetail(map);
						if(!flag){
							map.clear();
							map.put("payAmount", paySum);
							map.put("orderNo", order_no);
							map.put("payType", pay_type);
							map.put("amount", sum);
							repaymentDao.insertOrderPay(map);
							break;
						}
					}
				}
			}
			//费用明细处理
			payAmount = form.getPay_amount();
			if(list1.size()>0){
				List<YcejOrderDetailEntity> orderDetaillist = new ArrayList<YcejOrderDetailEntity>();
				//根据支付查询的数据对刷款按顺序总额进行匹配
				for(RepaymentEntity pay : list1){
					YcejOrderDetailEntity orderDetail = new YcejOrderDetailEntity();
					String pay_type = pay.getPayDetail();
					BigDecimal pay_sum = pay.getPaySum();
					BigDecimal sum = pay.getSum();
					BigDecimal paySum =  BigDecimal.ZERO;
					String order_no = pay.getOrderNo();
					paySum = sum.subtract(pay_sum);//拿需支付金额减去支付金额
					//超过应缴金额与等于的不做处理 payamount 放入amount
					//小于应缴金额处理：状态改为支付中，并且应缴金额payamount = 应缴金额-付款金额=剩余金额 
					if(payAmount.compareTo(paySum)>0){
						payAmount = payAmount.subtract(pay_sum);
						orderDetail.setPayStatu(3);
						orderDetail.setPayAmount(sum);
					}else if(payAmount.compareTo(paySum)==0 ){//后期有业务在处理，先这样写
						payAmount = payAmount.subtract(pay_sum);
						orderDetail.setPayStatu(3);
						orderDetail.setPayAmount(sum);
					}else if(payAmount.compareTo(new BigDecimal("0"))>0 && payAmount.compareTo(paySum)<0){
						payAmount = payAmount.subtract(pay_sum);
						orderDetail.setPayStatu(2);
						orderDetail.setPayAmount(payAmount.add(pay_sum));
					}
					//orderDetail.setUpdateId(ShiroUtils.getUserId()+"");
					Date date = DateUtils.parse(payDate.substring(0,8),"yyyyMMdd");
					orderDetail.setPayDate(date);
					orderDetail.setOrderNo(order_no);
					orderDetail.setPayMethod(payType);
					
					orderDetail.setPayType(pay_type);
					if(StringUtils.isNotBlank(cardNo)){
						orderDetail.setPaycardno(cardNo);
					}
					orderDetaillist.add(orderDetail);
				}
				//费用明细数据修改
				orderDetailDao.batchUpdate(orderDetaillist);
				
				//支付待定金的时候：全款修改订单状态到资源匹配，分期修改至定金已支付
				/*Map<String, Object> params = new HashMap<String, Object>();
				String id = list1.get(0).getId();
				YcejOrderEntity order = ycejOrderService.selectById(id);
				String status = order.getStatus();
				if(StringUtils.isNotBlank(status) && "03".equals(status)){ //定金待支付时支付完毕后 修改订单状态，后面首付款/尾款不处理
					Integer sellType = order.getSellType();
					if(sellType == 1 ){
						params.put("status", "05");//全款修改为资源匹配
					}else if(sellType == 2 || sellType == 3){
						params.put("status", "04");//分期或者优壹车修改为定金已支付
					}
					params.put("id", order.getId());
					//只执行一次工作流 
					Map<String, Object> params1 = new HashMap<String, Object>();
					if (!params1.containsKey(order.getId())) {
						//处理pos更换情况，刷卡时不在触发订单状态修改 add by 20180823
						//ycejOrderService.updateOrderByPayment(params);
						//params1.put(order.getId(), order.getId());
					} 
				}*/
			}
		  	//20181012 新增刷卡完毕，如果支付完成后，把订单状态修改为已支付
			updateOrderByPayment(form);
		} catch (Exception e) {
			e.printStackTrace();
			j.setError("数据处理异常！");
			logger.error("数据处理异常："+e.getMessage());
			throw new RRException("数据处理异常!");
		}
		return j;
	}
	
	
	
	private void updateOrderByPayment(RepaymentForm form) {
		String order_no = form.getOrder_no();
		String shopId = form.getShop_id();
		Map<String,Object> map = new HashMap<String, Object>();
		String[] flag = WfConstants.status; 
		map.put("orderNo", order_no);
		map.put("shopId", shopId);
		List<RepaymentEntity> list = repaymentDao.queryOrderPayDetail(map);//支付查询
		if(list != null && list.size() > 0){
		}else{//如果不存在说明都已经处理完毕
			try {
				List<RepaymentEntity> list1 = repaymentDao.queryDetail(map);//支付查询
				String status = list1.get(0).getStatus();
				String orderNo = list1.get(0).getOrderNo();
				if(Arrays.asList(flag).contains(status)){
					String stat = "";
					YcejOrderEntity order = ycejOrderService.selectOne(new EntityWrapper<YcejOrderEntity>().addFilter("order_no = '" + orderNo + "'"));
					if("03".equals(status)){
						stat = "05";
						/*Integer sellType = order.getSellType();
						if(sellType ==  1){//全款跳过风控
							stat = "05";
						}
						if(sellType ==  2){//分期，不考虑优壹车无定金，在运营审核提交后流转到资源匹配
							stat = "04";
						}*/
					}
					if("08".equals(status) || "09".equals(status)){
						stat = "10";
					}
					/*if("09".equals(status)){
						stat = "091";
					}*/
					map.clear();
					map.put("orderNo", orderNo);
					map.put("status", stat);
					map.put("id", order.getId());
					Map<String, Object> params = new HashMap<String, Object>();
					if (!params.containsKey(orderNo)) {
						ycejOrderService.updateOrderByPayment(map);
						//baseMapper.updateOrder(map);
						params.put(orderNo, orderNo);
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("状态修改异常：" + e.getMessage());
				throw new RRException("状态修改异常!");
			}
		}
	}

	public static void main(String[] args) {
		BigDecimal b1 = new BigDecimal("10");
		String str = "order_no=280003&shop_id=13&pay_type=2&pay_date=20180702151553&pay_amount=0.01&sign_type=MD5afa66b7ccfb74724a8bdfb281a2ac66a";
		String md = JsonUtil.MD5(str);
		System.out.println("11111="+md);
	}
}
