package com.yicheejia.modules.financialmanage.dao;

import com.yicheejia.modules.financialmanage.entity.ImportDataEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-09-07 16:19:59
 */
public interface ImportDataDao extends BaseMapper<ImportDataEntity> {

	List<ImportDataEntity> selectPaymentDetail(Map<String, Object> params);
	/**
	 * 银盛总账
	 */
	Map<String, Object> selectTotalForPos(Map<String, Object> params);
	/**
	 * 根据账户类型获取银盛总账明细
	 */
	Map<String, Object> selectTotalForPosByAccountType(Map<String, Object> params);
	/**
	 * 从数据库查询导入pos明细
	 */
	List<ImportDataEntity> selectListByCondition(Map<String, Object> params);
	/**
	 * 银行转账 确认收款页面 导入数据流水总账
	 */
	Map<String, Object> selectBankTotalByImport(Map<String, Object> params);
	
}
