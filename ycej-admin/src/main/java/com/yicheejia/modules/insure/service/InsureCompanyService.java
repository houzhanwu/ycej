package com.yicheejia.modules.insure.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.insure.entity.InsureCompanyEntity;

import java.util.Map;

/**
 * 保险公司
 *
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
public interface InsureCompanyService extends IService<InsureCompanyEntity> {

    LayuiPage queryPage(Map<String, Object> params);
}

