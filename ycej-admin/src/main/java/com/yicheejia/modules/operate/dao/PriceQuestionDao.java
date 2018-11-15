package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.PriceQuestionEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-09-28 15:23:41
 */
public interface PriceQuestionDao extends BaseMapper<PriceQuestionEntity> {

	List<PriceQuestionEntity> selectAllPage(Page<PriceQuestionEntity> page, Map<String, Object> params);
	
}
