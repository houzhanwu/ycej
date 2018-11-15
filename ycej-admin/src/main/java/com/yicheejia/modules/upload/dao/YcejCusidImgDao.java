package com.yicheejia.modules.upload.dao;

import com.yicheejia.modules.upload.entity.YcejCusidImgEntity;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-06 09:24:03
 */
public interface YcejCusidImgDao extends BaseMapper<YcejCusidImgEntity> {

	void insertReturnId(YcejCusidImgEntity ycejCusidImgEntity);

	List<YcejCusidImgEntity> selectAllPic(String customerId);
	
}
