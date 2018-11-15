package com.yicheejia.modules.shop.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yicheejia.modules.shop.entity.IntentionCustomerEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 意向客户表
 * 
 * @author cn
 * @email 
 * @date 2018-06-27 15:10:45
 */
public interface IntentionCustomerDao extends BaseMapper<IntentionCustomerEntity> {

	List<IntentionCustomerEntity> selectListPage(Pagination page,Map<String, Object> map);

	void updateCustomer(IntentionCustomerEntity intention);

	List<IntentionCustomerEntity> selectIntentionCust(Map<String, Object> params);

	void batchUpdateCustomer(List<IntentionCustomerEntity> intention);
}
