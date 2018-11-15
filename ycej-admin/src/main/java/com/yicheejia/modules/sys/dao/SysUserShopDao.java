package com.yicheejia.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yicheejia.modules.sys.entity.SysUserShopEntity;

import java.util.List;

/**
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-06-28 10:05:45
 */
public interface SysUserShopDao extends BaseMapper<SysUserShopEntity> {

    List<Integer> queryShopIdList(Long userId);

    int deleteBatch(Integer[] shopIds);
	
}
