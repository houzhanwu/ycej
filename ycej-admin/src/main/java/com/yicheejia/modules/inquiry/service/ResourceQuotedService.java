package com.yicheejia.modules.inquiry.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.inquiry.entity.ResourceQuotedEntity;

import java.util.Map;

/**
 * 资源报价表
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:44:05
 */
public interface ResourceQuotedService extends IService<ResourceQuotedEntity> {

    LayuiPage queryPage(Map<String, Object> params);

    void updateResourceQuotedById(ResourceQuotedEntity resourceQuotedEntity);

}

