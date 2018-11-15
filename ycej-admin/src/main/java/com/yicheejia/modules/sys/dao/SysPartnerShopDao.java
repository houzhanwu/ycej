package com.yicheejia.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yicheejia.modules.sys.entity.SysPartnerShopEntity;

import java.util.List;

/**
 * 合伙人门店映射表
 * 
 * @author cn
 * @email 
 * @date 2018-07-16 10:33:20
 */
public interface SysPartnerShopDao extends BaseMapper<SysPartnerShopEntity> {

    List<Integer> queryPartnerShopIdList(Long userId);

    int deleteBatch(Integer[] shopIds);
	
}
