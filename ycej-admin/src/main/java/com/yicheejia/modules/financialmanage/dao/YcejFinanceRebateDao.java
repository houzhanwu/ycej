package com.yicheejia.modules.financialmanage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceRebateEntity;

public interface YcejFinanceRebateDao extends BaseMapper<YcejFinanceRebateEntity> {

	List<YcejFinanceRebateEntity> selectAllPage(Page<YcejFinanceRebateEntity> page, Map<String, Object> map);

	List<YcejFinanceRebateEntity> selectExportData(List<Long> list);

	List<YcejFinanceRebateEntity> selectAllShopPage(Page<YcejFinanceRebateEntity> page, Map<String, Object> map);

}
