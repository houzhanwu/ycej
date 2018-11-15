package com.yicheejia.modules.order.dao;

import com.yicheejia.modules.order.entity.OrderFileEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author fkm
 * @email 
 * @date 2018-07-11 14:16:58
 */
public interface OrderFileDao extends BaseMapper<OrderFileEntity> {

	void insertReturnId(OrderFileEntity OrderFileEntity);

	List<OrderFileEntity> queryList(Map<String, Object> params);
	
}
