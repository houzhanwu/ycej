package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.SupplierEntity;
import com.yicheejia.modules.operate.entity.SupplierFileEntity;
import com.yicheejia.modules.upload.entity.YcejFileEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-07-31 19:23:35
 */
public interface SupplierFileService extends IService<SupplierFileEntity> {

    LayuiPage queryPage(Map<String, Object> params);

    int updateSupplierFile(SupplierFileEntity fileEntity);

    List<SupplierFileEntity> selectBySupplierId(Integer supplierId);

	void insertReturnId(YcejFileEntity ycejFileEntity, Map<String, Object> params);

	List<SupplierFileEntity> queryList(Map<String, Object> params);
	/**
	 * 根据供应商id获取附件列表
	 * @param supplierId
	 * @return
	 */
	List<SupplierFileEntity> selectListBySupplierId(Integer supplierId);
}

