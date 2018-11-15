package com.yicheejia.modules.financialmanage.dao;

import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceCostEntity;

public interface YcejFinanceCostDao extends BaseMapper<YcejFinanceCostEntity> {

	List<YcejFinanceCostEntity> selectAllPage(Page<YcejFinanceCostEntity> page, HashMap<Object, Object> map);

	List<YcejFinanceCostEntity> selectExportData(List<Long> list);

}
