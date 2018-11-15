package com.yicheejia.modules.shop.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.shop.dao.ShopFileDao;
import com.yicheejia.modules.shop.entity.ShopFileEntity;
import com.yicheejia.modules.shop.service.ShopFileService;


@Service("shopFileService")
public class ShopFileServiceImpl extends ServiceImpl<ShopFileDao, ShopFileEntity> implements ShopFileService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<ShopFileEntity> page = this.selectPage(
                new Query<ShopFileEntity>(params).getPage(),
                new EntityWrapper<ShopFileEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

    @Override
    public ShopFileEntity selectByShopId(Integer shopId) {
        return baseMapper.selectByShopId(shopId);
    }

    @Override
    public int updateByShopId(ShopFileEntity shopFileEntity) {
        return baseMapper.updateByShopId(shopFileEntity);
    }

}
