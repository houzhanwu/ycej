package com.yicheejia.modules.insure.dao;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yicheejia.modules.insure.entity.InsureDetailEntity;

/**
 * 保险明细表
 * 
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
public interface InsureDetailDao extends BaseMapper<InsureDetailEntity> {

	InsureDetailEntity queryDetail(Map<String, Object> params);

	void updateInsureDetail(InsureDetailEntity insureDetail);
	
}
