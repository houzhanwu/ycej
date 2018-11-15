package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.CarBackDao;
import com.yicheejia.modules.operate.entity.CarBackEntity;
import com.yicheejia.modules.operate.service.CarBackService;


@Service("carBackService")
public class CarBackServiceImpl extends ServiceImpl<CarBackDao, CarBackEntity> implements CarBackService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<CarBackEntity> page = this.selectPage(
                new Query<CarBackEntity>(params).getPage(),
                new EntityWrapper<CarBackEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

}
