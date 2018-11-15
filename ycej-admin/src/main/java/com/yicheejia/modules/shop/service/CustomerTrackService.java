package com.yicheejia.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.shop.entity.CustomerTrackEntity;

import java.util.Map;

/**
 * 客户跟踪表
 *
 * @author cn
 * @email 
 * @date 2018-07-17 15:06:30
 */
public interface CustomerTrackService extends IService<CustomerTrackEntity> {

    LayuiPage queryPage(Map<String, Object> params);
}

