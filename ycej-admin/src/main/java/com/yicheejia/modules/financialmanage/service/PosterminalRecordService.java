package com.yicheejia.modules.financialmanage.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.financialmanage.entity.PosterminalRecordEntity;

import java.util.Map;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-12 16:52:12
 */
public interface PosterminalRecordService extends IService<PosterminalRecordEntity> {

    LayuiPage queryPage(Map<String, Object> params);
}

