package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-18 16:43:36
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	void insertReturnId(PurchaseDetailEntity purchaseDetailEntity);
	/**
	 * 根据库存id获取采购明细
	 */
	PurchaseDetailEntity selectByInventoryId(String inventoryId);
}

