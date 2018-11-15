package com.yicheejia.modules.shop.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yicheejia.modules.shop.entity.CustomerEntity;
import com.yicheejia.modules.shop.entity.IntentionCustomerEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * 成交客户
 * 
 * @author cn
 * @email 
 * @date 2018-07-02 09:15:15
 */
public interface CustomerDao extends BaseMapper<CustomerEntity> {

    List<Map<String,Object>> customerManageListPage(Pagination page,Map<String, Object> map);

    Map<String,Object> getCusManageInfoById(Integer customerId);

	void updateCustomer(CustomerEntity customer );

	int updateCustomerByCardNo(CustomerEntity customerEntity);

	List<CustomerEntity> selectCustByCard(Map<String, Object> params);

	Map<String,Object> getCustomerInfoById(Integer customerId);

}
