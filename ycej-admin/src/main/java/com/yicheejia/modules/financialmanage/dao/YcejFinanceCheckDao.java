package com.yicheejia.modules.financialmanage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceCheckEntity;

/**
 * 
 * 
 * @author liangzhanhao
 * @email zr_liangzhanhao@163.com
 * @date 2018-06-24 10:54:54
 */
public interface YcejFinanceCheckDao extends BaseMapper<YcejFinanceCheckEntity> {

	List<YcejFinanceCheckEntity> selectAllPage(Page<YcejFinanceCheckEntity> page,HashMap<Object, Object> map);

	List<YcejFinanceCheckEntity> selectDetailPage(Page<YcejFinanceCheckEntity> page, HashMap<Object, Object> map);

	void checkByIds(HashMap<String, Object> map);

	void returnByIds(HashMap<String, Object> map);

	void accountCheckByIds(HashMap<String, Object> map);
	List<YcejFinanceCheckEntity> getDataForExcel(List<Integer> asList);
	List<Map<String, Object>> getDataForExcel2(List<Integer> asList);
}
