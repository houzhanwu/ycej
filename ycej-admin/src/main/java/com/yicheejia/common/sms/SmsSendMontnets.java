package com.yicheejia.common.sms;

import java.util.Date;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import com.yicheejia.common.utils.HttpsClientUtil;
import com.yicheejia.modules.sms.entity.SmsEntity;
import com.yicheejia.modules.sms.service.impl.SmsServiceImpl;


@Component
public class SmsSendMontnets {

		//日志
	    protected Logger logger = LoggerFactory.getLogger(SmsSendMontnets.class);
		
		private HttpsClientUtil httpsClientUtil;
		
	    public String URL = "http://api01.monyun.cn:7901/sms/v2/std/batch_send";
	    
	    
//	    @Autowired
//	    private static ApplicationContext context;
	    @Autowired
	    private SmsServiceImpl smsServiceImpl;

		public String sendmsg(String mobile,String msg){
			
			logger.info("梦网短信发送Start");
		//	SmsService smsService = (SmsService) context.getBean("smsService");
//			SmsServiceImpl smsService = ContextLoader.getCurrentWebApplicationContext().getBean(SmsServiceImpl.class);
			JSONObject jsobj1 = new JSONObject();
			String zt="接口调用失败";
			try {
				String mobile1 = mobile.replace("[", "");
				String mobiles = mobile1.replace("]", "");
				//短信信息进行GBK编码
				String mytext = java.net.URLEncoder.encode(msg,"GBK");
				//发送账号的apikey 
				jsobj1.put("apikey", "16bec00aac3dcd460ae1ff35eee2fcac");
				//短信接收的手机号：多个手机号请用英文逗号分隔，最大1000个号码
				jsobj1.put("mobile", mobiles);
				//短信内容
				jsobj1.put("content",mytext);
				String result=post(jsobj1);
				String recode = null;
				net.sf.json.JSONObject jsonObject = null;
				
				if(result!=null&&result.length()>0){
					jsonObject = net.sf.json.JSONObject.fromObject(result);
					recode = jsonObject.getString("result");
					if("0".equals(recode)){
						zt="短信接口调用成功"+result;
					}
					else
					{
						zt="短信接口调用失败"+result;
					}
				}else{
					zt="短信接口调用失败"+result;
				}
				
				//将短信插入到数据库中
				SmsEntity sms = new SmsEntity();
				sms.setMobile(mobile);
				sms.setContent(msg);
				sms.setSendTime(new Date());
				sms.setSResult(recode);
				sms.setMsgid(jsonObject.getLong("msgid"));
				if(null != jsonObject.getString("custid")) {
					sms.setCustid(jsonObject.getString("custid"));
				}
				smsServiceImpl.insert(sms);
				
			}catch (Exception e) {
				logger.error("梦网云短信发送失败");
				logger.error(e.getMessage(),e);
			} 
			return zt;
		}
	    public String post(JSONObject json) {

	    	HttpPost post = new HttpPost(URL);
	        post.setHeader("Content-Type", "application/json");
	        post.addHeader("Authorization", "Basic YWRtaW46");
	        net.sf.json.JSONObject result = null;
	        
	        try {

	            StringEntity s = new StringEntity(json.toString(), "utf-8");
	            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
	            post.setEntity(s);

	            // 发送短信请求
	            result = httpsClientUtil.getInstance().sendPostRequest(URL, null, json.toString());
	            
	            logger.debug("result");

	        } catch (Exception e) {
	        	logger.error("请求异常");
	            throw new RuntimeException(e);
	        }

	        return result.toString();
	    }
}
