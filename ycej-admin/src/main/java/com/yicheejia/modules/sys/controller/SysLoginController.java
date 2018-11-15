/**
 *
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.yicheejia.modules.sys.controller;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import net.sf.json.JSONObject;

import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.utils.ApiJson;
import com.yicheejia.common.utils.HttpsClientUtil;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.form.SysLoginForm;
import com.yicheejia.modules.sys.service.SysCaptchaService;
import com.yicheejia.modules.sys.service.SysUserService;
import com.yicheejia.modules.sys.service.SysUserTokenService;
import com.yicheejia.modules.webservices.service.AppSendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关
 * 
 *
 *
 * @date 2016年11月10日 下午1:15:31
 */
@RestController
@Api(tags="系统登陆接口")
public class SysLoginController extends AbstractController{
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysCaptchaService sysCaptchaService;
	@Autowired 
	private AppSendService appSendService;
	//是否开启验证码
    @Value("${ycej.captcha.open}")
    private boolean capOpen;

	@ApiOperation("获取验证码")
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid)throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}
	
	/**
	 * 登录
	 */
	@ApiOperation("登陆")
	@PostMapping("/sys/login")
	public Map<String, Object> login(@RequestBody SysLoginForm form) {
		boolean captchaFlag = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
		if(capOpen && !captchaFlag){
			return R.error(501, "验证码不正确");
		}

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getUserId());
		//判断是否为APP端登录，是发送设备id
		if (StringUtils.isNotBlank(form.getClientId())) {
			ApiJson json=getJsonData(String.valueOf(user.getUserId()),form.getClientId());
			if (json.getMsgcode()!=100) {
				//判断返回值
				return R.error("登录失败");
			}
		}
		return r;
	}


	/**
	 * 退出
	 */
	@ApiOperation("退出")
	@PostMapping("/sys/logout")
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.ok();
	}
	
	//推送，发送设备id
	@SysLog("APP登录调用服务发送设备号")
	private ApiJson getJsonData(String userId,String clientId){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId",userId);
		jsonObject.put("clientId", clientId);
		Map<String, String> map = new HashMap<>();
//		JSONObject json=HttpsClientUtil.getInstance().sendPostRequest(url, map, jsonObject.toString());
		ApiJson json = appSendService.appLogin(jsonObject.toString());
		return json;
	}
	
}
