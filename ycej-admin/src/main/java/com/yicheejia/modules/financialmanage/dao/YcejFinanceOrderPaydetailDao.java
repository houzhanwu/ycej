package com.yicheejia.modules.financialmanage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceOrderPaydetailEntity;
import com.yicheejia.modules.order.entity.OrderPaydetailEntity;

/**
 * 费用明细表
 * 
 * @author cn
 * @email 
 * @date 2018-07-08 10:29:31
 */
public interface YcejFinanceOrderPaydetailDao extends BaseMapper<YcejFinanceOrderPaydetailEntity> {

	List<YcejFinanceOrderPaydetailEntity> selectAllPage(Page<YcejFinanceOrderPaydetailEntity> page, Map<String, Object> map);

	void checkBatchIds(List<String> asList);

	List<YcejFinanceOrderPaydetailEntity> selectAll();

	List<YcejFinanceOrderPaydetailEntity> selectAllCard();

	Map<String, Object> selectOrderPayAndWaterPay(String long1);

	void updateStatusForOne(String orderPayId);

	List<YcejFinanceOrderPaydetailEntity> selectExportData(List<String> list);

	void updateStateByIds(Map<String, Object> hmMap);
	/**
	 * 根据支付时间、支付方式获取总账明细
	 */
	List<Map<String, Object>> selectTotalDetail(Map<String, Object> params);
	/**
	 * 根据用户筛选条件在导入页面展示收款总账
	 */
	Map<String, Object> selectTotalForImport(Map<String, Object> params);
	/**
	 * 根据支付时间、支付方式获取收款总账
	 */
	Map<String, Object> selectTotal(Map<String, Object> params);
	/**
	 * 根据条件获取支付明细列表
	 */
	List<YcejFinanceOrderPaydetailEntity> selectListByCondition(Map<String, Object> params);
	/**
	 * 单条更新支付明细状态
	 */
	void updateOneStateByCondition(Map<String, Object> map);
	/**
	 * 银行转账 展示系统收款总账
	 */
	List<Map<String, Object>> selectBankTotal(Map<String, Object> params);
	/**
	 * 导入银行转账页面展示支付明细
	 */
	List<YcejFinanceOrderPaydetailEntity> selectDetailListForImport(Map<String, Object> params);
	/**
	 * 银行转账页面进行名字搜索查询
	 */
	List<YcejFinanceOrderPaydetailEntity> selectListForQueryName(Page<YcejFinanceOrderPaydetailEntity> page,
			Map<String, Object> params);
	/**
	 * Pos页面系统收款总账明细
	 */
	List<Map<String, Object>> selectPosTotalDetailByPayMethod(Map<String, Object> params);
	/**
	 * POS确认页面获取收款总账
	 * @param params
	 * @return
	 */
	Map<String, Object> selectTotalForPos(Map<String, Object> params);

	
}
