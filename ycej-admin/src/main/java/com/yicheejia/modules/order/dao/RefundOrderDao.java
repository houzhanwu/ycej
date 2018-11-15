package com.yicheejia.modules.order.dao;

import com.yicheejia.modules.order.entity.RefundOrderEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 退款信息表
 * 
 * @author fkm
 * @email 
 * @date 2018-09-26 15:02:44
 */
public interface RefundOrderDao extends BaseMapper<RefundOrderEntity> {
	
	List<YcejOrderEntity> queryRefundOrder(Page<YcejOrderEntity> page, Map<String, Object> params);
	/**
	 * 退款账户信息查询
	 * @param params
	 * @return
	 */
	RefundOrderEntity queryRefund(Map<String, Object> params);
	/**
	 * 退款信息修改
	 * @param refundOrder
	 */
	void updateRefundOrder(RefundOrderEntity refundOrder);
}
