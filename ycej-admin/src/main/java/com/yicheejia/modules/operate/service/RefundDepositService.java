package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.RefundDepositEntity;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-11-06 18:04:09
 */
public interface RefundDepositService extends IService<RefundDepositEntity> {

    LayuiPage queryPage(Map<String, Object> params);
}

