package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.CarManufacturerEntity;

import java.util.List;
import java.util.Map;

/**
 * 厂商
 *
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
public interface CarManufacturerService extends IService<CarManufacturerEntity> {

    LayuiPage queryPage(Map<String, Object> params);
    
	boolean updateById(CarManufacturerEntity carManufacturer);

	List<Map<String, Object>> getCarManufacturers(Map<String, Object> params);

	int selectByManufacturer(CarManufacturerEntity manufacturerEntity);
}

