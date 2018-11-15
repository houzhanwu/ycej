package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.WarehouseManagementEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author hunk
 * @email 
 * @date 2018-07-24 11:30:47
 */
public interface WarehouseManagementService extends IService<WarehouseManagementEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	List<WarehouseManagementEntity> selectList();
}

