package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.PriceQuestionEntity;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-09-28 15:23:41
 */
public interface PriceQuestionService extends IService<PriceQuestionEntity> {

    LayuiPage queryPage(Map<String, Object> params);
}

