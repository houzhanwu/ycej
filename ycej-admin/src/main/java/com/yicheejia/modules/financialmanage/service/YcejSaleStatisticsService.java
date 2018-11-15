package com.yicheejia.modules.financialmanage.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.financialmanage.entity.YcejSaleStatisticsEntity;

public interface YcejSaleStatisticsService extends IService<YcejSaleStatisticsEntity> {

	LayuiPage queryPage(Map<String, Object> params);

	List<YcejSaleStatisticsEntity> selectExportData(Long[] ids);

}
