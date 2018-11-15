package com.yicheejia.modules.financialmanage.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceCostEntity;

public interface YcejFinanceCostService extends IService<YcejFinanceCostEntity>{

	LayuiPage queryPage(Map<String, Object> params);

	List<YcejFinanceCostEntity> selectExportData(Long[] ids);

}
