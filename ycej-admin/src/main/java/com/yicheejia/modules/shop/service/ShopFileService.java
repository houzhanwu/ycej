package com.yicheejia.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.shop.entity.ShopFileEntity;

import java.util.Map;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-18 19:34:48
 */
public interface ShopFileService extends IService<ShopFileEntity> {

    LayuiPage queryPage(Map<String, Object> params);

    ShopFileEntity selectByShopId(Integer shopId);

    int updateByShopId(ShopFileEntity shopFileEntity);
}

