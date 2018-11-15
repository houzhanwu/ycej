package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.SupplierCarbrandEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 供应商品牌关联信息表
 * 
 * @author 
 * @email 
 * @date 2018-10-27 15:51:56
 */
public interface SupplierCarbrandDao extends BaseMapper<SupplierCarbrandEntity> {

	List<SupplierCarbrandEntity> selectDataByMap(Map<String, Object> columnMap);
	
}
