package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.IncomingFileEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-11-02 11:08:40
 */
public interface IncomingFileDao extends BaseMapper<IncomingFileEntity> {

	List<IncomingFileEntity> queryList(Map<String, Object> params);
	
}
