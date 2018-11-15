package com.yicheejia.modules.order.dao;

import com.yicheejia.modules.order.entity.LoanInfoEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-07-10 17:32:57
 */
public interface LoanInfoDao extends BaseMapper<LoanInfoEntity> {
	/**
	 * 根据订单主键查询贷款信息
	 * @param params
	 * @return
	 */
	List<LoanInfoEntity> selectLoaninfo(Map<String, Object> params);
	
}
