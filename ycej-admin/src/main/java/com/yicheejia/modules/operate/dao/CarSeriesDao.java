package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.CarSeriesEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 车系表
 * 
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
public interface CarSeriesDao extends BaseMapper<CarSeriesEntity> {

	List<CarSeriesEntity> carSeriesList(Page<CarSeriesEntity> page, HashMap<String, Object> map);

	List<CarSeriesEntity> getCarSeriesList(Map<String, Object> params);
	
}
