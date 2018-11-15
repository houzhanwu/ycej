package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.ProvinceEntity;

import java.util.List;
import java.util.Map;

/**
 * 省份表
 *
 * @author
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
public interface ProvinceService extends IService<ProvinceEntity> {

    LayuiPage queryPage(Map<String, Object> params);

    List<Map<String,Object>> getAllProvince();
}

