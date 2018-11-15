package com.yicheejia.modules.upload.dao;

import com.yicheejia.modules.upload.entity.YcejFileEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-06 09:24:25
 */
public interface YcejFileDao extends BaseMapper<YcejFileEntity> {

	void insertReturnId(YcejFileEntity ycejFileEntity);
	
}
