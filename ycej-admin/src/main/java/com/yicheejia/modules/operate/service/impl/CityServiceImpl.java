package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.CityDao;
import com.yicheejia.modules.operate.entity.CityEntity;
import com.yicheejia.modules.operate.service.CityService;


@Service("cityService")
public class CityServiceImpl extends ServiceImpl<CityDao, CityEntity> implements CityService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<CityEntity> page = this.selectPage(
                new Query<CityEntity>(params).getPage(),
                new EntityWrapper<CityEntity>()
        );

        return new LayuiPage(page.getRecords(),page.getTotal());
    }

    @Override
    public List<Map<String, Object>> getCityListByProvinceId(int provinceId) {
        return baseMapper.getCitiesByProvinceId(provinceId);
    }

    @Override
    public List<Map<String, Object>> getAllCities() {
        return baseMapper.getAllCities();
    }

    /**
     * 多表查询分页测试
     * @return
     */
    @Override
    public LayuiPage pageQuery() {
        Page<Map<String,Object>> page = new Page<>(1,10);
        page.setRecords(baseMapper.pageQueryTest(page,16));
        return new LayuiPage(page.getRecords(),page.getTotal());
    }

}
