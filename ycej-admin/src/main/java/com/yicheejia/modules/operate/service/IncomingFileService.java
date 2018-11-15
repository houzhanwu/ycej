package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.IncomingFileEntity;
import com.yicheejia.modules.upload.entity.YcejFileEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-11-02 11:08:40
 */
public interface IncomingFileService extends IService<IncomingFileEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	void insertReturnId(YcejFileEntity ycejFileEntity, Map<String, Object> params);
	
	List<IncomingFileEntity> queryList(Map<String, Object> params);
}

