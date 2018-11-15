package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.DailyIncomingEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-11-02 11:08:40
 */
public interface DailyIncomingDao extends BaseMapper<DailyIncomingEntity> {

	List<DailyIncomingEntity> selectAllPage(Page<DailyIncomingEntity> page, Map<String, Object> params);
	
}
