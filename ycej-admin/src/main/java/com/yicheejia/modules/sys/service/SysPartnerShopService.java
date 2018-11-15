package com.yicheejia.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.sys.entity.SysPartnerShopEntity;

import java.util.List;
import java.util.Map;

/**
 * 合伙人门店映射表
 *
 * @author cn
 * @email 
 * @date 2018-07-16 10:33:20
 */
public interface SysPartnerShopService extends IService<SysPartnerShopEntity> {

    LayuiPage queryPage(Map<String, Object> params);

    void saveOrUpdate(Long userId,List<Integer> partShopIdList);

    int deleteBatch(Integer[] shopIds);

    List<Integer> queryShopIdList(Long userId);
}

