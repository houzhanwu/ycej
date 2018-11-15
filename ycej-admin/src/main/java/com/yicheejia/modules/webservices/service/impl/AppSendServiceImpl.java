package com.yicheejia.modules.webservices.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.google.gson.JsonObject;
import com.yicheejia.common.utils.ApiJson;
import com.yicheejia.common.utils.JsonUtil;
import com.yicheejia.common.utils.NumberUtil;
import com.yicheejia.modules.sys.dao.SysUserDao;
import com.yicheejia.modules.sys.entity.SysDictEntity;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.service.SysDictService;
import com.yicheejia.modules.sys.service.SysUserService;
import com.yicheejia.modules.webservices.service.AppSendService;

@Service("appSendServices")
public class AppSendServiceImpl extends ServiceImpl<SysUserDao,SysUserEntity> implements AppSendService{
	 //日志
    public static Logger log = LoggerFactory.getLogger(AppSendServiceImpl.class); 
	@Autowired
	private SysUserService sysUserService;
	private String appId = "Vnra0Q6kyv6gcHaoJJ1l32";//个推appId
	private String appKey = "PEhVRzOtFy9VdSmoUoIR46";//个推appkey
	private String masterSecret = "OwkTmgsfOd6pcBzsRVBS52";//个推masterSecret服务端调用验证
	private String url = "https://sdk.open.api.igexin.com/apiex.htm";//个推调用地址
	@Autowired 
	private SysDictService sysDictService;

	@Override
	public ApiJson appLogin(String request) {
		log.info("app登录调用添加或更新设备id接口开始！参数为："+request);
		Map<String,Object> map = JsonUtil.jsonToMap(request, false);
		Long userId = NumberUtil.longOf(map.get("userId"),0L);//账号
		String clientId = NumberUtil.stringOf(map.get("clientId")).trim();//设备id\
//		List<Long>list=new ArrayList<Long>();
//		list.add(userId);
		ApiJson apiJson=new ApiJson();
		SysUserEntity sysUserEntity  = sysUserService.selectById(userId);
		if(sysUserEntity==null){
			apiJson.setError("查无此人");
			return apiJson;
		}
		try {
			if(sysUserEntity.getClientid()==null||"".equals(sysUserEntity.getClientid())){
				//第一次从app登录   无设备id
				sysUserEntity.setClientid(clientId);
			}else{
				if(!clientId.equals(sysUserEntity.getClientid())){
					sysUserEntity.setClientid(clientId);
				}
			}
			sysUserService.updateBySql(clientId,userId);
			apiJson.setMsg("设备id保存成功");
			log.info("app登录调用添加或更新设备id接口成功！参数为："+request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			apiJson.setMsg("设备id保存失败");
			log.error("app登录调用添加或更新设备id接口失败！参数为："+request);
		}
		return apiJson;
	}
	

	@Override
	public ApiJson sendMessage(String request) {
		log.info("app调用发送推送接口开始！参数为："+request);
		Map<String,Object>map=JsonUtil.jsonToMap(request, true);
		String ss=""+map.get("userId");//账号
		ss=ss.replace("[","").replace("]", "").replace("\"","");
		String[]list=ss.split(",");
//		Long userId = NumberUtil.longOf(map.get("userId"),0L);//账号
		String titleText = NumberUtil.stringOf(map.get("titleText")).trim();//标题
		String describe = NumberUtil.stringOf(map.get("describe")).trim();//内容描述
		String url = NumberUtil.stringOf(map.get("url")).trim();//其他浏览器跳转url
		ApiJson apiJson=new ApiJson();
		String msg="";
		for(String userId:list){
			SysUserEntity entity=sysUserService.selectById(userId);
			if(entity==null||entity.getClientid()==null||"".equals(entity.getClientid())){
				msg=msg+"userId为:"+userId +"的用户 不存在 或者该用户clientid不存在";
				log.info("userId为:"+userId +"的用户 不存在 或者该用户clientid不存在");
			}else{
				String clientId=entity.getClientid();
				msg=msg+push(userId,clientId,titleText,describe,url)+"\r		";
			}
		}
		log.info("app调用接口结束：接口返回信息为:"+msg);
		apiJson.setMsg(msg);
		return apiJson;
	}

	private String push(String userId,String clientId, String titleText, String describe, String url2) {
		if("".equals(clientId)){
			return "userid:"+userId+" 用户无绑定设备id";
		}
		IGtPush push = new IGtPush(url, appKey, masterSecret);
		LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle(titleText);
        template.setText(describe);
        template.setUrl(url2);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(clientId);
        IPushResult ret = null;
        try {
        	ret = push.pushMessageToSingle(message, target);
            log.info("个推推送接口调用成功！返回信息："+ret.getResponse().toString());
		} catch (Exception e) {
			log.error("个推推送接口调用异常！");
		}
		return ret.getResponse().toString();
	}


	@Override
	public ApiJson updateversion() {
		log.info("app调用更新接口开始！");
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("type", "app_version");
		ApiJson apiJson=new ApiJson();
		try {
			List<SysDictEntity> sysDictEntitys=sysDictService.queryValue(map);
			SysDictEntity sysDictEntity=null;
			if(sysDictEntitys!=null&&sysDictEntitys.size()!=0){
				sysDictEntity=sysDictEntitys.get(0);
			}
			JsonObject obj=new JsonObject();
			obj.addProperty("version",sysDictEntity.getCode());
			obj.addProperty("url",sysDictEntity.getValue());
			apiJson.setMsg(obj.toString());
			log.info("app调用更新接口成功 返回参数："+apiJson.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			apiJson.setError(e.getMessage());
			log.info("app调用更新接口失败 返回参数：请查看字典表type=app_version是否存在;"+apiJson.getMsg());
		}
		return apiJson;
	}
}
