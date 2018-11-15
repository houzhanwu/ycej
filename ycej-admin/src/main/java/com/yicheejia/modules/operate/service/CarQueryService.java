package com.yicheejia.modules.operate.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.CarQueryEntity;

public interface CarQueryService extends IService<CarQueryEntity> {

	LayuiPage queryPage(Map<String, Object> params);

}
