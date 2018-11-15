package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.modules.operate.entity.YouYiCheEntity;

public interface YouYiCheService extends IService<YouYiCheEntity>{
	
	YouYiCheEntity calculate (YouYiCheEntity youYiCheEntity);
	
}
