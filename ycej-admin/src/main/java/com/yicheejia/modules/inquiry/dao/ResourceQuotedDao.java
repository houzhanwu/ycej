package com.yicheejia.modules.inquiry.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yicheejia.modules.inquiry.entity.ResourceQuotedEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 资源报价表
 * 
 * @author 
 * @email 
 * @date 2018-10-23 16:44:05
 */
public interface ResourceQuotedDao extends BaseMapper<ResourceQuotedEntity> {

    void updateResourceQuotedById(ResourceQuotedEntity resourceQuotedEntity);

    List<Map<String,Object>> getQuotedList(Pagination page, Map<String,Object> map);
	
}
