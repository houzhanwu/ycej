package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.IncomingDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-11-02 11:08:40
 */
public interface IncomingDetailService extends IService<IncomingDetailEntity> {

    LayuiPage queryPage(Map<String, Object> params);
}

