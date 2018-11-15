package com.yicheejia.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.modules.shop.entity.CustomerEntity;
import com.yicheejia.modules.shop.entity.IntentionCustomerEntity;
import com.yicheejia.modules.webservices.model.CustomerVO;

import java.util.List;
import java.util.Map;

/**
 * 成交客户
 *
 * @author cn
 * @email 
 * @date 2018-06-27 15:10:45
 */
public interface CustomerService extends IService<CustomerEntity> {

    LayuiPage queryPage(Map<String, Object> params);
    
    void saveCustomerInfo(CustomerVO customerVO);

    LayuiPage selectCustomerManageList(Map<String,Object> map);

    Map<String,Object> getCusManageInfoById(Integer customerId);
    /**
     * 修改风控客户为成交客户
     *
     */
	void updateCustomer(CustomerEntity customer);

	int updateCustomerByCardNo(CustomerEntity customerEntity);
	/**
	 * 根据身份证号查询风控客户
	 * @param params
	 * @return
	 */
	List<CustomerEntity> selectCustByCard(Map<String, Object> params);

	Map<String,Object> getCustomerInfoById(Integer customerId);
	
}

