package com.yicheejia.modules.webservices;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.ApiJson;
import com.yicheejia.modules.order.service.RepaymentService;
import com.yicheejia.modules.webservices.model.QueryRepaymentForm;
import com.yicheejia.modules.webservices.service.AppSendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/webService")
@Api(tags="app推送相关")
public class AppSendController {
	@Autowired
    private AppSendService appSendServices;
	
	@PostMapping(value="/applogin",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("app登录修改数据")
	public ApiJson appLogin(HttpServletResponse response,@RequestBody String json){
		System.out.println(json);
		return appSendServices.appLogin(json);
	}
	
	@PostMapping(value="/sendMessage",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("app发送推送")
	public ApiJson sendMessage(HttpServletResponse response,@RequestBody String json){
		return appSendServices.sendMessage(json);
	}
	
	@GetMapping(value="/updateversion",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	@ApiOperation("app返回版本号信息")
	public ApiJson updateversion(HttpServletResponse response){
		return appSendServices.updateversion();
	}
}
