package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.InventoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 库存表
 *
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:28
 */
public interface InventoryService extends IService<InventoryEntity> {

    LayuiPage queryPage(Map<String, Object> params);

    LayuiPage getInventoryByShopId(Map<String, Object> params);
	/**
	 * 订单资源匹配返回库存信息
	 * @param params
	 * @return
	 */
	LayuiPage selectInventory(Map<String, Object> params);
	/*
	 * 采购调用，新增数据并返回主键
	 */
	void insertReturnId(InventoryEntity inventoryEntity);

	List<InventoryEntity> selectByWareHouse(Long id);
	
	void updateinventory(InventoryEntity inventoryEntity);
	 /**
     * 根据库存id获取库存信息 --用于采购明细 车辆库存查询
     */
	InventoryEntity selectDataForCarPurchaseById(Map<String, Object> params);

	InventoryEntity selectByinventoryId(String inventoryId);
	/**
	 * 库存新增数据 查询 ---不是走采购流程
	 * @param id
	 * @return
	 */
	InventoryEntity selectByCondition(String id);
}

