package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.SupplierChangeEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 供应商变更信息表
 * 
 * @author 
 * @email 
 * @date 2018-10-27 15:51:56
 */
public interface SupplierChangeDao extends BaseMapper<SupplierChangeEntity> {

	List<SupplierChangeEntity> selectAllPage(Map<String, Object> params);
	
}
