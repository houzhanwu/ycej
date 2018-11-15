package com.yicheejia.modules.order.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yicheejia.modules.order.entity.YcejOrderDetailEntity;

/**
 * @date 2018-06-21 16:27:42
 */
public interface YcejOrderDetailDao extends BaseMapper<YcejOrderDetailEntity> {

	void insertBatch(List<YcejOrderDetailEntity> list);

	List<YcejOrderDetailEntity> query(Map<String, Object> params);

	void updateOrder(Map<String, Object> params);

	void batchUpdate(List<YcejOrderDetailEntity> list);

	void updateDetail(Map<String, Object> params);
	
}
