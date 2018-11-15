package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.CarColorEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 汽车外观颜色表
 * 
 * @author 
 * @email 
 * @date 2018-08-30 15:46:19
 */
public interface CarColorDao extends BaseMapper<CarColorEntity> {

    List<Map<String,Object>> selectAllColors();

    List<Map<String,Object>> selectAllDecorationColors();
}
