package com.yicheejia.modules.upload.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.upload.entity.YcejCusidImgEntity;
import com.yicheejia.modules.upload.service.YcejCusidImgService;
import com.yicheejia.modules.upload.service.YcejFileService;

/**
 * 附件预览
 * @author 梁占豪
 */
@RestController
@RequestMapping("preview")
public class PreviewController {
	@Autowired
	private YcejCusidImgService ycejCusidImgService;
	/*
	 * 根据用户ID获取该用户下所有的附件
	 */
	@RequestMapping("/customer")
	@RequiresPermissions("financialmanage:ycejorderdetail:list")
	public LayuiPage getCustomerList(@RequestBody String customerId){
		List<YcejCusidImgEntity> list =ycejCusidImgService.selectAllPic(customerId);
		LayuiPage page=new LayuiPage(list, list.size());
		return page;
	}
}
