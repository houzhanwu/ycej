package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.CarColorEntity;

import java.util.List;
import java.util.Map;

/**
 * 汽车外观颜色表
 *
 * @author 
 * @email 
 * @date 2018-08-30 15:46:19
 */
public interface CarColorService extends IService<CarColorEntity> {

    LayuiPage queryPage(Map<String, Object> params);

    LayuiPage queryInnerPage(Map<String, Object> params);

    int queryExistByCarColor(CarColorEntity carColorEntity);

    int queryExistDecorationColor(CarColorEntity carColorEntity);

    List<Map<String,Object>> getAllColors();

    List<Map<String,Object>> getAllDecorationColors();
}

