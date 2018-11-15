package com.yicheejia.modules.financialmanage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.financialmanage.entity.MoneyConfirmEntity;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceOrderPaydetailEntity;
import com.yicheejia.modules.order.entity.OrderPaydetailEntity;

/**
 * 费用明细表
 *
 * @author cn
 * @email 
 * @date 2018-07-08 10:29:31
 */
public interface YcejFinanceOrderPaydetailService extends IService<YcejFinanceOrderPaydetailEntity> {

	LayuiPage queryPage(Map<String, Object> params);
    /*
     * T+1到账确认
     */
	void checkBatchIds(List<String> asList,String remark);
	/*
	 * 导入POS流水,展示确认收款页面
	 */
	Map<String, Object> inportPos(MultipartFile file,Map<String, Object> params, HttpServletRequest request);
	/*
	 * 导入银行统一模板数据
	 */
	Map<String, Object> inportZcard(MultipartFile file,Map<String, Object> params, HttpServletRequest request);
	/*
	 * 导入建行流水，账目核对
	 */
	Map<String, Object> inportJcard(MultipartFile file, HttpServletRequest request);
	/*
	 * 导出
	 */
	List<YcejFinanceOrderPaydetailEntity> selectExportData(List<String> list);
	/**
	 *  根据支付时间、支付方式获取总账明细
	 */
	List<Map<String, Object>> selectTotalDetail(Map<String, Object> params);
	/**
	 *  根据支付时间、支付方式获取收款总账
	 */
	Map<String, Object> selectTotal(Map<String, Object> params);
	/**
	 * 根据用户筛选条件在导入页面展示收款总账
	 */
	Map<String, Object> selectTotalForImport(Map<String, Object> params);
	/**
	 * Pos确认收款
	 * @return 
	 */
	Map<String, Object> posMoneyConfirm(MoneyConfirmEntity moneyConfirmEntity);
	/**
	 * 
	 * @param moneyConfirmEntity
	 * @return 
	 */
	Map<String, Object> bankMoneyConfirm(MoneyConfirmEntity moneyConfirmEntity);
	/**
	 * 银行转账页面进行名字搜索查询
	 */
	LayuiPage queryPageForQueryName(Map<String, Object> params);
	
	/**
	 * pos确认页面确认过后重载数据
	 */
	Map<String, Object> selectReloadPosData(Map<String, Object> params);
	/**
	 * 银行确认页面确认过后重载数据
	 */
	Map<String, Object> selectReloadBankData(Map<String, Object> params);
}

