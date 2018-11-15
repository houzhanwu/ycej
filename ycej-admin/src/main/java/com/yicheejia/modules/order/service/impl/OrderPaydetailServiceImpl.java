package com.yicheejia.modules.order.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.yicheejia.modules.order.dao.YcejOrderDetailDao;
import com.yicheejia.modules.order.entity.OrderPaydetailEntity;
import com.yicheejia.modules.order.entity.SalePromotionEntity;
import com.yicheejia.modules.order.entity.YcejOrderDetailEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.order.service.OrderPaydetailService;
import com.yicheejia.modules.order.service.SalePromotionService;
import com.yicheejia.modules.order.service.YcejOrderDetailService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.webservices.model.RepaymentForm;


@Service("orderPaydetailService")
public class OrderPaydetailServiceImpl extends ServiceImpl<OrderPaydetailDao, OrderPaydetailEntity> implements OrderPaydetailService {
	@Autowired
	private OrderPaydetailDao orderPayDetailDao;
	@Autowired
	private YcejOrderDetailDao orderDetailDao;
	@Autowired
	private YcejOrderDetailService ycejOrderDetailService;
	@Autowired
	private SalePromotionService salePromotionService;
	
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<OrderPaydetailEntity> page = this.selectPage(
                new Query<OrderPaydetailEntity>(params).getPage(),
                new EntityWrapper<OrderPaydetailEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }
    /**
     * 支付明细生成
     * @param order
     */
    @Override
    public void savePayDetail(Map<String, Object> params) {
    	try{
    		OrderPaydetailEntity orderPay = new OrderPaydetailEntity();
    		String type = params.get("type")+"";
    		String orderNo = params.get("orderNo")+"";
    		String amount = params.get("amount")+"";
    		String payMethod = params.get("payMethod")+"";
    		String payDate = params.get("payDate")+"";
			String status = params.get("status")+"";
			BigDecimal count = new BigDecimal(amount);
			String amount1 = params.get("amount01")+"";
			//修改时另一条支付明细会把定金带进来，这里需要移除掉
			OrderPaydetailEntity payDetail = new OrderPaydetailEntity();
			payDetail.setPayType("定金");
			payDetail.setOrderNo(orderNo);
			OrderPaydetailEntity pay = baseMapper.selectOne(payDetail);
			if(type.contains("定金") && (type.contains("首付") || type.contains("尾款")) && pay != null){
				//YcejOrderDetailEntity detail = ycejOrderDetailService.selectOne(new EntityWrapper<YcejOrderDetailEntity>().addFilter("order_no = '" + orderNo + "' and pay_type = '01'"));
				BigDecimal amount01 = BigDecimal.ZERO;
				amount01 = pay.getAmount();
				count = count.subtract(new BigDecimal(amount1));
				type = type.replace("定金,", "");
			}
			orderPay.setPayType(type);
			//add by 20181030 支付金额减去优惠金额 begin   费用 明细暂时先不处理
			String id = params.get("id")+"";
			BigDecimal discount = this.discount(id);
			orderPay.setAmount(count.subtract(discount));
			//add by 20181030 支付金额减去优惠金额 end
			orderPay.setOrderNo(orderNo);
			if(StringUtils.isNotBlank(payMethod) && !"null".equals(payMethod)){
				orderPay.setPayMethod(payMethod);
			}
			if(StringUtils.isNotBlank(payDate) && !"null".equals(payDate)){
				orderPay.setPayDate(DateUtils.stringToDate(payDate,"yyyy-MM-dd"));
			}
			/*if("04".equals(payMethod) && !"02".equals(status)){//转账
				orderPay.setPayAmount(orderPay.getAmount());
				orderPay.setPayStatu(3);//支付完成
			}else{*/
				orderPay.setPayStatu(1);//待支付
				orderPay.setPayAmount(BigDecimal.ZERO);
			//}
			//支付明细数据 先根据主键与支付费用类型查询是否存在数据，不存在新增，存在走修改
			params.clear();
			params.put("order_no", orderNo);
			params.put("pay_type", type);
			List<OrderPaydetailEntity> paydetail = orderPayDetailDao.selectByMap(params);
			if(paydetail.size()>0){
				orderPay.setUpdateId(ShiroUtils.getUserId()+"");
				orderPayDetailDao.updateByOrder(orderPay);//支付明细修改
			}else{
				orderPay.setInsertId(ShiroUtils.getUserId()+"");
				orderPay.setInsertTime(DateUtils.getDate());
				orderPay.setUpdateTime(DateUtils.getDate());
				orderPayDetailDao.insert(orderPay);//支付明细插入
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RRException("保存异常");
		}
	}
    /**
     * 根据订单号，处理订单页面所需支付明细数据
     */
	@Override
	public List<OrderPaydetailEntity> queryPaydetail(Map<String, Object> params) {
		List<OrderPaydetailEntity> list= baseMapper.queryPaydetail(params);
		return list;
	}
	/**
	 * 修改未支付完成的支付明细与费用明细
	 * @param params
	 */
	@Override
	public void updatePayDetail(Map<String, Object> params) {
		baseMapper.updatePayDetail(params);
	}
	
	/**
	 * 根据关联订单id查询所选择的优惠券
	 * @param id
	 * @return
	 */
	@Override
	public BigDecimal discount(String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("order_id", id);
		List<SalePromotionEntity> list = salePromotionService.selectByMap(params);
		BigDecimal deductionPrice = BigDecimal.ZERO;
		for(SalePromotionEntity salePromotion : list){
			deductionPrice = deductionPrice.add(salePromotion.getDeductionPrice());
		}
		return deductionPrice;
	}
	/**
	 * 支付中转账明细查询
	 */
	@Override
	public List<OrderPaydetailEntity> queryTransfer(Map<String, Object> params) {
		return baseMapper.queryTransfer(params);
	}
	/**
	 * 转账明细保存
	 */
	@Override
	public void saveTransfer(List<OrderPaydetailEntity> list) {
		for(OrderPaydetailEntity pay : list){
			pay.setPayStatu(3);
			pay.setInsertId(ShiroUtils.getUserId()+"");
		}
		baseMapper.saveTransfer(list);
	}
}
