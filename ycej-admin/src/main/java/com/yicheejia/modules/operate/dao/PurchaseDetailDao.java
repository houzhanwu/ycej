package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.PurchaseDetailEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-07-18 16:43:36
 */
public interface PurchaseDetailDao extends BaseMapper<PurchaseDetailEntity> {

	void insertReturnId(PurchaseDetailEntity purchaseDetailEntity);

	PurchaseDetailEntity selectByInventoryId(String inventoryId);
	
}
