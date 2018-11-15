package com.yicheejia.modules.operate.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.InventoryEntity;

/**
 * 库存表
 * 
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:28
 */
public interface InventoryDao extends BaseMapper<InventoryEntity> {

	List<InventoryEntity> getInventoryByShopId(Page<InventoryEntity> page,Map<String, Object> params);

	List<InventoryEntity> selectInventory(Page<InventoryEntity> page, Map<String, Object> params);

	void insertReturnId(InventoryEntity inventoryEntity);

	List<InventoryEntity> inventoryList(Page<InventoryEntity> page, Map<String, Object> params);

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
