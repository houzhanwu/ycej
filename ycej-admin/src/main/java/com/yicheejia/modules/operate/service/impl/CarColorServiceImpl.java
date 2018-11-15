package com.yicheejia.modules.operate.service.impl;

import com.yicheejia.modules.operate.dao.CarColorDao;
import com.yicheejia.modules.operate.entity.CarColorEntity;
import com.yicheejia.modules.operate.service.CarColorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;




@Service("carColorService")
public class CarColorServiceImpl extends ServiceImpl<CarColorDao, CarColorEntity> implements CarColorService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Object color = params.get("color");
        EntityWrapper<CarColorEntity> wrapper = new EntityWrapper<>();
        if(color != null && !"".equals(color.toString())){
            wrapper.like("color",color.toString());
        }
        wrapper.addFilter("decoration_color is null");
        Page<CarColorEntity> page = this.selectPage(
                new Query<CarColorEntity>(params).getPage(),
                wrapper
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

    @Override
    public LayuiPage queryInnerPage(Map<String, Object> params) {
        Object color = params.get("color");
        EntityWrapper<CarColorEntity> wrapper = new EntityWrapper<>();
        if(color != null && !"".equals(color.toString())){
            wrapper.like("decoration_color",color.toString());
        }
        wrapper.addFilter("color is null");
        Page<CarColorEntity> page = this.selectPage(
                new Query<CarColorEntity>(params).getPage(),
                wrapper
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

    @Override
    public int queryExistByCarColor(CarColorEntity carColorEntity) {
        String color = carColorEntity.getColor().trim();
        EntityWrapper<CarColorEntity> wrapper = new EntityWrapper<CarColorEntity>();
        wrapper.addFilter("color ="+"'"+color+"'");
        int count = baseMapper.selectCount(wrapper);

        return count;
    }

    @Override
    public int queryExistDecorationColor(CarColorEntity carColorEntity) {
        String color = carColorEntity.getDecorationColor().trim();
        EntityWrapper<CarColorEntity> wrapper = new EntityWrapper<CarColorEntity>();
        wrapper.addFilter("decoration_color ="+"'"+color+"'");
        int count = baseMapper.selectCount(wrapper);

        return count;
    }

    @Override
    public List<Map<String, Object>> getAllColors() {
        return baseMapper.selectAllColors();
    }

    @Override
    public List<Map<String, Object>> getAllDecorationColors() {
        return baseMapper.selectAllDecorationColors();
    }

}
