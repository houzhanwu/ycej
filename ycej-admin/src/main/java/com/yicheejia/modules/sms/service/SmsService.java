package com.yicheejia.modules.sms.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.sms.entity.SmsEntity;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:20:35
 */
public interface SmsService extends IService<SmsEntity> {

    LayuiPage queryPage(Map<String, Object> params);
}

