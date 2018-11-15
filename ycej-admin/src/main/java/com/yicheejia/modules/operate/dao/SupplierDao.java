package com.yicheejia.modules.operate.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yicheejia.modules.operate.entity.SupplierEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 供应商信息表
 * 
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:28
 */
public interface SupplierDao extends BaseMapper<SupplierEntity> {

	List<SupplierEntity> getListForPurchase();

	List<SupplierEntity> getSupplierListPage(Pagination page, Map<String, Object> map);

	void insertByCondition(SupplierEntity supplier);

//	List<SupplierEntity> getSupplierListForCaution(Map<String, Object> params);

	List<SupplierEntity> getSupplierListForCaution(Page<SupplierEntity> page, Map<String, Object> params);
	
}
