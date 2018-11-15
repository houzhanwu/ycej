package com.yicheejia.modules.product.dao;

import com.yicheejia.modules.product.entity.ProductInfoEntity;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-07-16 19:57:23
 */
public interface ProductInfoDao extends BaseMapper<ProductInfoEntity> {

	List<ProductInfoEntity> selectProductList();
	
}
