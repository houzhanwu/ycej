package com.yicheejia.modules.upload.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.modules.upload.entity.YcejCusidImgEntity;
import com.yicheejia.modules.upload.entity.YcejFileEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-06 09:24:03
 */
public interface YcejCusidImgService extends IService<YcejCusidImgEntity> {

    PageUtils queryPage(Map<String, Object> params);

	void insertReturnId(YcejFileEntity ycejFileEntity,String customerId);
	List<YcejCusidImgEntity> selectAllPic(String customerId);

}

