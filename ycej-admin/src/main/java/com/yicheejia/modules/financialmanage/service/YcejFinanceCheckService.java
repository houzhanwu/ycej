package com.yicheejia.modules.financialmanage.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceCheckEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author liangzhanhao
 * @email zr_liangzhanhao@163.com
 * @date 2018-06-24 10:54:54
 */
public interface YcejFinanceCheckService extends IService<YcejFinanceCheckEntity> {

    PageUtils queryPage(Map<String, Object> params);

	PageUtils queryDetailPage(Map<String, Object> params);

	void checkByIds(List<Integer> asList);

	void returnByIds(List<Integer> asList);

	void accountCheckByIds(List<Integer> asList);

	List<YcejFinanceCheckEntity> getDataForExcel(List<Integer> list);

	List<Map<String, Object>> getDataForExcel2(List<Integer> asList);
}

