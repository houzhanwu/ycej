package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.ShopInformationDao;
import com.yicheejia.modules.operate.entity.ShopInformationEntity;
import com.yicheejia.modules.operate.service.ShopInformationService;


@Service("shopInformationService")
public class ShopInformationServiceImpl extends ServiceImpl<ShopInformationDao, ShopInformationEntity> implements ShopInformationService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<ShopInformationEntity> page = this.selectPage(
                new Query<ShopInformationEntity>(params).getPage(),
                new EntityWrapper<ShopInformationEntity>()
        );

        return new LayuiPage(page.getRecords(),page.getTotal());
    }

}
