package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.CautionMoneyEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-10-25 15:47:20
 */
public interface CautionMoneyDao extends BaseMapper<CautionMoneyEntity> {

	void submitCaution(CautionMoneyEntity cautionMoney);

	void updateCaution(CautionMoneyEntity cautionMoney);

	List<CautionMoneyEntity> selectCautionPage(Page<CautionMoneyEntity> page, Map<String, Object> params);
	
}
