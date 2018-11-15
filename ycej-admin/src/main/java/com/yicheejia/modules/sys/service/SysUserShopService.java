package com.yicheejia.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.modules.sys.entity.SysUserShopEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-06-28 10:05:45
 */
public interface SysUserShopService extends IService<SysUserShopEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveOrUpdate(Long userId, List<Integer> shopIdList);

    List<Integer> queryShopIdList(Long userId);

    int deleteBatch(Integer[] shopIds);
}

