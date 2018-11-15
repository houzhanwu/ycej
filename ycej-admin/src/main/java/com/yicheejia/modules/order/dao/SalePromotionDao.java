package com.yicheejia.modules.order.dao;

import com.yicheejia.modules.order.entity.SalePromotionEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 活动代金券信息表
 * 
 * @author 
 * @email 
 * @date 2018-10-26 13:44:25
 */
public interface SalePromotionDao extends BaseMapper<SalePromotionEntity> {

	List<SalePromotionEntity> querySalePromotionList(Page<SalePromotionEntity> page, Map<String, Object> params);

	void insertSalePromotionList(List<SalePromotionEntity> list);

	void updateBySaleId(Map<String, Object> params);

	List<Map<String, Object>> getPromotionList(Map<String, Object> params);

	List<Map<String, Object>> getZPPromotionList(Map<String, Object> params);

	List<Map<String, Object>> getPromotionListByOrderId(Map<String, Object> params);
	
}
