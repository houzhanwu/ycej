package com.yicheejia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.order.entity.OrderPaydetailEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 支付明细表
 *
 * @author fkm
 * @email 
 * @date 2018-07-09 11:16:31
 */
public interface OrderPaydetailService extends IService<OrderPaydetailEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	void savePayDetail(Map<String, Object> params);
	/**
	 * 根据订单号查询支付明细数据
	 * @param params
	 * @return
	 */
	List<OrderPaydetailEntity> queryPaydetail(Map<String, Object> params);
	/**
	 * 修改未支付完成的支付明细与费用明细
	 * @param params
	 */
	void updatePayDetail(Map<String, Object> params);
	/**
	 * 根据关联订单id查询所选择的优惠券
	 * @param id
	 * @return
	 */
	BigDecimal discount(String id);
	/**
	 * 支付转账明细查询
	 * @param params
	 * @return
	 */
	List<OrderPaydetailEntity> queryTransfer(Map<String, Object> params);
	/**
	 * 支付转账明细保存
	 * @param params
	 * @return
	 */
	 void saveTransfer(List<OrderPaydetailEntity> list);
	
}

