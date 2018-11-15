package com.yicheejia.modules.product.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.product.entity.ProductInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-16 19:57:23
 */
public interface ProductInfoService extends IService<ProductInfoEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	List<ProductInfoEntity> selectProductList();
}

