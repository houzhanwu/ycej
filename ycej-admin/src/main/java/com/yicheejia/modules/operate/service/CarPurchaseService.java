package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.operate.entity.CarPurchaseEntity;
import com.yicheejia.modules.operate.entity.PurchDetailList;
import com.yicheejia.modules.operate.entity.PurchaseDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-18 16:43:36
 */
public interface CarPurchaseService extends IService<CarPurchaseEntity> {

    LayuiPage queryPage(Map<String, Object> params);


	void updateCarPurchase(CarPurchaseEntity carPurchase);
	/*
	 * 新增暂存，保存采购单状态为暂存
	 */
	void holeSave(CarPurchaseEntity carPurchase);
	/*
	 * 修改暂存
	 */
	void updateAll(CarPurchaseEntity carPurchase);
	/*
	 * 新增提交，保存采购单状态为财务待审核
	 */
	void insertReturnId(CarPurchaseEntity carPurchase);
	/*
	 * 修改提交，保存采购单状态为财务待审核
	 */
	void tiJiaoUpdate(CarPurchaseEntity carPurchase);


	void putStock(CarPurchaseEntity carPurchase);


	CarPurchaseEntity selectDetailById(String purch_id);


	R stock(CarPurchaseEntity carPurchase);
}

