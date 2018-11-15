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

package com.yicheejia.common.aspect;

import com.google.gson.Gson;
import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.utils.HttpContextUtils;
import com.yicheejia.common.utils.IPUtils;
import com.yicheejia.modules.sys.entity.SysLogEntity;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.service.SysLogService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ThreadContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志，切面处理类
 *
 *
 * @since 1.3.0 2017-03-08
 */
@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("@annotation(com.yicheejia.common.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time, result);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time,Object result) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = new Gson().toJson(args[0]);
			sysLog.setParams(params);
			//来源单据类型 ，主键
			String id = syslog.id();
			if (StringUtils.isNotEmpty(id)) {
			    String billId = AspectCommon.getFormID(joinPoint, id, false);
			    sysLog.setSourceBillId(billId);
			    sysLog.setSourceBillType(syslog.billType());
			}
			
		}catch (Exception e){

		}
		if( RequestContextHolder.getRequestAttributes() == null){
			sysLog.setIp("");
		}else{
			//获取request
			HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
			//设置IP地址
			sysLog.setIp(IPUtils.getIpAddr(request));
		}

		//用户名
		if (ThreadContext.getSubject() == null) {
			sysLog.setUsername(WfConstants.AUTO_USER);
    	}else{
    		String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
    		sysLog.setUsername(username);
    	}

		sysLog.setTime(time);
		sysLog.setCreateDate(new Date());
		//保存系统日志
		sysLogService.insert(sysLog);
	}
}
