package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.ProvinceDao;
import com.yicheejia.modules.operate.entity.ProvinceEntity;
import com.yicheejia.modules.operate.service.ProvinceService;


@Service("provinceService")
public class ProvinceServiceImpl extends ServiceImpl<ProvinceDao, ProvinceEntity> implements ProvinceService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<ProvinceEntity> page = this.selectPage(
                new Query<ProvinceEntity>(params).getPage(),
                new EntityWrapper<ProvinceEntity>()
        );

        return new LayuiPage(page.getRecords(),page.getTotal());
    }

    @Override
    public List<Map<String, Object>> getAllProvince() {
        return baseMapper.getAllProvince();
    }

}
