package com.yicheejia.modules.order.dao;

import com.yicheejia.modules.order.entity.PutmoneyInfoEntity;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-10-12 15:12:37
 */
public interface PutmoneyInfoDao extends BaseMapper<PutmoneyInfoEntity> {
	/**
     * 根据条件获取放款信息
     */
	PutmoneyInfoEntity selectInfoByCondition(Map<String, Object> params);
	
}
