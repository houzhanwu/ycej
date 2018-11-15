package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.CautionHistoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-10-25 15:47:20
 */
public interface CautionHistoryService extends IService<CautionHistoryEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	List<CautionHistoryEntity> selectListById(String cautionId);
}

