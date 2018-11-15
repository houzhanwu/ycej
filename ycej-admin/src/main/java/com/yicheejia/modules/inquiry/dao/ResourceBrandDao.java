package com.yicheejia.modules.inquiry.dao;

import com.yicheejia.modules.inquiry.entity.ResourceBrandEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 资源客户品牌表
 * 
 * @author 
 * @email 
 * @date 2018-10-23 16:44:05
 */
public interface ResourceBrandDao extends BaseMapper<ResourceBrandEntity> {

    List<Map<String,Object>> getResourceBrands(Map<String,Object> map);
	
}
