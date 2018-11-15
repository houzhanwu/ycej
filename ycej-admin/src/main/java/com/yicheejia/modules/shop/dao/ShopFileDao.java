package com.yicheejia.modules.shop.dao;

import com.yicheejia.modules.shop.entity.ShopFileEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-07-18 19:34:48
 */
public interface ShopFileDao extends BaseMapper<ShopFileEntity> {

    ShopFileEntity selectByShopId(Integer shopId);

    int updateByShopId(ShopFileEntity shopFileEntity);
	
}
