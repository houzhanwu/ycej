package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.ShopInformationEntity;

import java.util.Map;

/**
 * 门店信息表
 *
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:28
 */
public interface ShopInformationService extends IService<ShopInformationEntity> {

    LayuiPage queryPage(Map<String, Object> params);
}

