package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.WarehouseManagementEntity;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author hunk
 * @email 
 * @date 2018-07-24 11:30:47
 */
public interface WarehouseManagementDao extends BaseMapper<WarehouseManagementEntity> {

	List<WarehouseManagementEntity> selectList();
	
}
