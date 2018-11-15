package com.yicheejia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.order.entity.PutmoneyInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-10-12 15:12:37
 */
public interface PutmoneyInfoService extends IService<PutmoneyInfoEntity> {

    LayuiPage queryPage(Map<String, Object> params);
    /**
     * 根据条件获取放款信息
     * @param params
     * @return
     */
	PutmoneyInfoEntity selectInfoByCondition(Map<String, Object> params);
}

