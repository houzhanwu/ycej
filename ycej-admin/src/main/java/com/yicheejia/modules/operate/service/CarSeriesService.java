package com.yicheejia.modules.operate.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.CarSeriesEntity;

/**
 * 车系表
 *
 * @author  luwen
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
public interface CarSeriesService extends IService<CarSeriesEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	List<CarSeriesEntity> getCarSeriesList(Map<String, Object> params);

	CarSeriesEntity selectById(Integer carSeriesId);

	int selectBySeries(CarSeriesEntity seriesEntity);
}

