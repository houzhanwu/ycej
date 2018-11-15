package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.SupplierCarbrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 供应商品牌关联信息表
 *
 * @author 
 * @email 
 * @date 2018-10-27 15:51:56
 */
public interface SupplierCarbrandService extends IService<SupplierCarbrandEntity> {

    LayuiPage queryPage(Map<String, Object> params);
    /**
     * 根据供应商id获取车型列表
     * @param columnMap
     * @return
     */
	List<SupplierCarbrandEntity> selectDataByMap(Map<String, Object> columnMap);
}

