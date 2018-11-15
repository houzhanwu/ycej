package com.yicheejia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.order.entity.YcejOrderDetailEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;

import java.util.List;
import java.util.Map;

/**
 *
 * @date 2018-06-21 16:27:42
 */
public interface YcejOrderDetailService extends IService<YcejOrderDetailEntity> {

	LayuiPage queryPage(Map<String, Object> params);
	/**
	 * 插入费用明细表并同步插入支付明细
	 * @param ycejOrderDetail
	 */
	void saveDetail(YcejOrderEntity order);
	/**
	 * 根据订单主键查询出费用明细
	 * @param params
	 * @return
	 */
	List<YcejOrderDetailEntity> query(Map<String, Object> params);
	/**
	 * 根据订单主键更新费用明细并同步更新支付明细
	 * @param params
	 */
	void updateByOrder(Map<String, Object> params);
	/**
	 * 车辆已确认时填写的首付款或者尾款以及状态的更新
	 * @param order
	 */
	void newcheck(YcejOrderEntity order);
	/**
	 * 修改未支付完成的支付明细与费用明细
	 * @param params
	 */
	void updateDetail(Map<String, Object> params);
	/**
	 * 修改未支付完成的支付明细与费用明细
	 * @param params
	 */
	void modifyDetail(YcejOrderEntity order);
}

