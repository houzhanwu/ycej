package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.AllotEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 调拨记录表
 * 
 * @author lw
 * @email 
 * @date 2018-07-18 16:37:00
 */
public interface AllotDao extends BaseMapper<AllotEntity> {

	void updateByVinTo(AllotEntity allot);

	void updateByVinFrom(AllotEntity allot);

	void updateallot(AllotEntity allot);

	void updateCarStatus(AllotEntity allot);

	void updateWarehouseAndShop(AllotEntity allot);

	void updateWarehouseToShop(AllotEntity allot);

	void updateShopToShop(AllotEntity allot);

	List<AllotEntity> allotList(Page<AllotEntity> page, Map<String, Object> params);

	String queryShopId(String allotId);
	
	AllotEntity selectById(String allotId);
	/*
	 * 车辆查询中根据Vin获取调拨信息
	 */
	List<AllotEntity> selectByVin(Map<String, Object> map);

}
