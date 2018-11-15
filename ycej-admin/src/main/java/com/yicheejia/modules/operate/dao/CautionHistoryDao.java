package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.CautionHistoryEntity;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-10-25 15:47:20
 */
public interface CautionHistoryDao extends BaseMapper<CautionHistoryEntity> {

	List<CautionHistoryEntity> selectListById(String cautionId);
	
}
