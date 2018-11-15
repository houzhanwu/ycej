package com.yicheejia.modules.inquiry.dao;

import com.yicheejia.modules.inquiry.entity.ResourceCustomerEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 资源客户表
 * 
 * @author 
 * @email 
 * @date 2018-10-23 16:27:35
 */
public interface ResourceCustomerDao extends BaseMapper<ResourceCustomerEntity> {

    ResourceCustomerEntity queryByUserMobile(ResourceCustomerEntity resourceCustomerEntity);
}
