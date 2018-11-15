package com.yicheejia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.order.entity.RefundOrderEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * @date 2018-09-25 16:27:42
 */
public interface RefundOrderService extends IService<RefundOrderEntity>  {

	/**
	 * 订单退回
	 * @param params
	 */
	void refundSubmit(Map<String, Object> params);
	/**
	 * 取消订单列表
	 * @param params
	 * @return
	 */
	LayuiPage queryRefundPage(Map<String, Object> params);
	/**
	 * 退款账户信息查询
	 * @param params
	 * @return
	 */
	RefundOrderEntity queryRefund(Map<String, Object> params);
	/**
	 * 财务审核并提交退款信息
	 * @param refundOrder
	 */
	void saveRefundOrder(RefundOrderEntity refundOrder);
}

