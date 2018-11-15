package com.yicheejia.modules.operate.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yicheejia.modules.operate.entity.SupplierFileEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-07-31 19:23:35
 */
public interface SupplierFileDao extends BaseMapper<SupplierFileEntity> {

    int updateSupplierFile(SupplierFileEntity fileEntity);

    List<SupplierFileEntity> selectBySupplierId(Integer supplierId);

	List<SupplierFileEntity> queryList(Map<String, Object> params);

	List<SupplierFileEntity> selectListBySupplierId(Integer supplierId);
	
}
