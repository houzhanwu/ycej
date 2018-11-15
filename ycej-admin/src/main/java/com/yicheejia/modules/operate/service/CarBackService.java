package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.CarBackEntity;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-10-25 15:47:20
 */
public interface CarBackService extends IService<CarBackEntity> {

    LayuiPage queryPage(Map<String, Object> params);
}

