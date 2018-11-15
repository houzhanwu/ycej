package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.CarBrandEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author luwen
 * @email ${email}
 * @date 2018-07-03 16:35:00
 */
public interface CarBrandDao extends BaseMapper<CarBrandEntity> {
	
	List<Map<String,Object>> getCarBrands(Map<String, Object> params);
	
}
