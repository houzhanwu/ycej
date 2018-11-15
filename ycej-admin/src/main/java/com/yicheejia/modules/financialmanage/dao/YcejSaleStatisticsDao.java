package com.yicheejia.modules.financialmanage.dao;

import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yicheejia.modules.financialmanage.entity.YcejSaleStatisticsEntity;

public interface YcejSaleStatisticsDao  extends BaseMapper<YcejSaleStatisticsEntity>{

	List<YcejSaleStatisticsEntity> selectAllPage(Page<YcejSaleStatisticsEntity> page, HashMap<Object, Object> map);


	List<YcejSaleStatisticsEntity> selectExportData(List<Long> list);

}
