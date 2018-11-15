package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.CarManufacturerEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 厂商表
 * 
 * @author
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
public interface CarManufacturerDao extends BaseMapper<CarManufacturerEntity> {
	
	List<CarManufacturerEntity> carManufacturerList(Page<CarManufacturerEntity> page, HashMap<String, Object> map);
	
	void updateMById(HashMap<String, Object> map);

	List<Map<String, Object>> getCarManufacturers(Map<String, Object> params);
}
