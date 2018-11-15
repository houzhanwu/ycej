package com.yicheejia.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.modules.shop.entity.IntentionCustomerEntity;

import java.util.List;
import java.util.Map;

/**
 * 意向客户表
 *
 * @author cn
 * @email 
 * @date 2018-06-27 15:10:45
 */
public interface IntentionCustomerService extends IService<IntentionCustomerEntity> {

    LayuiPage queryPage(Map<String, Object> params);
    /**
     * 订单财务确认时修改意向客户订单信息
     */
	void updateCustomer(IntentionCustomerEntity intention);
	/**
	 * 根据传入参数查询意向客户信息
	 * @param params
	 * @return
	 */
	List<IntentionCustomerEntity> selectIntentionCust(Map<String, Object> params);

	int selectByIntentionCustomer(IntentionCustomerEntity intentionCustomer);
	/**
     * 订单财务确认时修改意向客户订单信息
     */
	void updateCustomer(List<IntentionCustomerEntity> intention);
}

