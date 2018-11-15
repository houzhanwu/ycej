package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.SupplierEntity;

import java.util.List;
import java.util.Map;

/**
 * 供应商信息表
 *
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:28
 */
public interface SupplierService extends IService<SupplierEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	List<SupplierEntity> getListForPurchase();
	void insertByCondition(SupplierEntity supplier);

//	List<SupplierEntity> getSupplierListForCaution(Map<String, Object> params);
	LayuiPage getSupplierListForCaution(Map<String, Object> params);
}

