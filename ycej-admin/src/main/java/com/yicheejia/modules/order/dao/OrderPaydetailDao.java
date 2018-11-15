package com.yicheejia.modules.order.dao;

import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.order.entity.OrderPaydetailEntity;
import com.yicheejia.modules.order.entity.YcejOrderDetailEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 支付明细表
 * 
 * @author fkm
 * @email 
 * @date 2018-07-09 11:16:31
 */
public interface OrderPaydetailDao extends BaseMapper<OrderPaydetailEntity> {

	void updateByOrder(OrderPaydetailEntity orderDetail);

	List<OrderPaydetailEntity> queryPaydetail(Map<String, Object> params);

	void updatePayDetail(Map<String, Object> params);
	/**
	 * 转账明细查询
	 * @param params
	 * @return
	 */
	List<OrderPaydetailEntity> queryTransfer(Map<String, Object> params);

	void saveTransfer(List<OrderPaydetailEntity> list);

	List<OrderPaydetailEntity> queryPayDetail(Map<String, Object> params);
	
}
