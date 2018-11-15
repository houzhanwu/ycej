package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.SupplierChangeEntity;

import java.util.List;
import java.util.Map;

/**
 * 供应商变更信息表
 *
 * @author 
 * @email 
 * @date 2018-10-27 15:51:56
 */
public interface SupplierChangeService extends IService<SupplierChangeEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	List<SupplierChangeEntity> selectAllPage(Map<String, Object> params);
}

