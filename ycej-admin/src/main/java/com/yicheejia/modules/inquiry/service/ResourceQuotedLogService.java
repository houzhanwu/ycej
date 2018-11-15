package com.yicheejia.modules.inquiry.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.inquiry.entity.ResourceQuotedLogEntity;

import java.util.Map;

/**
 * 资源报价历史记录表
 *
 * @author 
 * @email 
 * @date 2018-10-29 17:42:28
 */
public interface ResourceQuotedLogService extends IService<ResourceQuotedLogEntity> {

    LayuiPage queryPage(Map<String, Object> params);
}

