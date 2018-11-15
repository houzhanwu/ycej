package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.CarModelEntity;
import com.yicheejia.modules.operate.entity.InventoryEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 车型表
 * 
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
public interface CarModelDao extends BaseMapper<CarModelEntity> {

	List<CarModelEntity> carModelList(Page<CarModelEntity> page, HashMap<String, Object> map);

	CarModelEntity getCarModelInfo(Integer carModelId);

	List<CarModelEntity> getCarModelList(Map<String, Object> params);

	void insert(InventoryEntity inventoryEntity);

	List<String> selectModelList(String carSeriesId);

}
