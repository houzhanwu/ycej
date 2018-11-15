package com.yicheejia.modules.insure.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yicheejia.modules.insure.entity.InsureDetailEntity;
import com.yicheejia.modules.insure.entity.InsureEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;

/**
 * 保险
 * 
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
public interface InsureDao extends BaseMapper<InsureEntity> {

	List<YcejOrderEntity> queryInsure(Page<YcejOrderEntity> page, Map<String, Object> params);

	YcejOrderEntity queryInsure(Map<String, Object> params);

	void updateInsure(InsureEntity insure);

}
