package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.CarBrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author luwen
 * @email ${email}
 * @date 2018-07-03 16:35:00
 */
public interface CarBrandService extends IService<CarBrandEntity> {

    List<Map<String, Object>> getCarBrands(Map<String, Object> params);

	LayuiPage queryPage(Map<String, Object> params);

	int selectByBrand(CarBrandEntity carBrand);
}

