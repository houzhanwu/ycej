package com.yicheejia.modules.inquiry.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.inquiry.entity.ResourceCustomerEntity;

import java.util.Map;

/**
 * 资源客户表
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:27:35
 */
public interface ResourceCustomerService extends IService<ResourceCustomerEntity> {

    LayuiPage queryPage(Map<String, Object> params);

    ResourceCustomerEntity queryByUserMobile(ResourceCustomerEntity resourceCustomerEntity);
}

