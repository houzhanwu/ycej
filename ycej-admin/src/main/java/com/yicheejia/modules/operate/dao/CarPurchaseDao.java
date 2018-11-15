package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.CarPurchaseEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-07-18 16:43:36
 */
public interface CarPurchaseDao extends BaseMapper<CarPurchaseEntity> {

	void insertReturnId(CarPurchaseEntity carPurchase);

	void updateCarPurchase(Map<String, Object> params);

	List<CarPurchaseEntity> queryCarPurchase(Page<CarPurchaseEntity> page, Map<String, Object> params);

	CarPurchaseEntity selectDetailById(String purch_id);
	
}
