package com.yicheejia.modules.inquiry.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.inquiry.entity.ResourceBrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 资源客户品牌表
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:44:05
 */
public interface ResourceBrandService extends IService<ResourceBrandEntity> {

    LayuiPage queryPage(Map<String, Object> params);

    void saveOrUpdate(Long resourceId,List<Long> brandIdList);

    List<Map<String,Object>> getResourceBrands(Map<String,Object> map);
}

