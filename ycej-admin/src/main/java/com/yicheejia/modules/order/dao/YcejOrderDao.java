package com.yicheejia.modules.order.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yicheejia.modules.order.entity.YcejOrderEntity;

/**
 * 
 * @date 2018-06-21 16:27:42
 */
public interface YcejOrderDao extends BaseMapper<YcejOrderEntity> {
	List<YcejOrderEntity> queryOrder(Pagination page ,Map<String, Object> params);

	void updateOrder(Map<String, Object> params);

	/**
	 * @param orderlist
	 */
	void submitService(List<YcejOrderEntity> orderlist);
	/**
	 * POS刷卡修改订单状态
	 * @param params
	 */
	void updateOrderByPay(Map<String, Object> params);
	/**
	 * 优壹车拆分--获取列表
	 * @param page
	 * @param params
	 * @return
	 */
	List<YcejOrderEntity> queryCheapCarOrder(Page<YcejOrderEntity> page, Map<String, Object> params);
}
