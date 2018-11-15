package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.RefundDepositEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-11-06 18:04:09
 */
public interface RefundDepositDao extends BaseMapper<RefundDepositEntity> {

	List<RefundDepositEntity> selectAllPage(Page<RefundDepositEntity> page, Map<String, Object> params);
	
}
