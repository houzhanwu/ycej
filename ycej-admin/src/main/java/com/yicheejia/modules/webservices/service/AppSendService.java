package com.yicheejia.modules.webservices.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.ApiJson;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.webservices.model.QueryRepaymentForm;

public interface AppSendService extends IService<SysUserEntity>{
	public ApiJson appLogin(String request);
	public ApiJson sendMessage(String request);
	public ApiJson updateversion();
}
