package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.CarModelEntity;

import java.util.List;
import java.util.Map;

/**
 * 车型表
 *
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
public interface CarModelService extends IService<CarModelEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	CarModelEntity selectById(Integer carModelId);

	List<CarModelEntity> getCarModelList(Map<String, Object> params);
	/**
	 * 新增车型
	 */
	void insertCarModel(CarModelEntity carModelEntity);

	int selectByCarModel(CarModelEntity carModelEntity);
}

