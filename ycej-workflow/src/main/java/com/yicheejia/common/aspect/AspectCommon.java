package com.yicheejia.common.aspect;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.aspectj.lang.JoinPoint;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.common.annotation.StartFlow;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
/**
 * 工作流切面处理类
 * @author hunk
 *
 */
public class AspectCommon {

    /**
     * 获取单据IDName
     * 
     * @param startFlow
     */
    public static String getFormIdName(ExecuteTask executeTask) {
        if (executeTask != null && StringUtils.isNotEmpty(executeTask.id())) {
            return executeTask.id();
        } else {
            throw new RRException("系统异常：流程业务 id  为空");
        }
    }

    /**
     * 获取单据IDName
     * 
     * @param startFlow
     */
    public static String getFormIdName(StartFlow startFlow) {
        if (startFlow != null && StringUtils.isNotEmpty(startFlow.id())) {
            return startFlow.id();
        } else {
            throw new RRException("系统异常： 业务ID name 为空");
        }
    }

    /**
     * ExecuteTask获取流程模型
     * 
     * @param startFlow
     * @return
     */
    public static String getProcessKey(ExecuteTask executeTask) {
        if (executeTask != null && StringUtils.isNotEmpty(executeTask.processKey())) {
            return executeTask.processKey();
        } else {
            throw new RRException("系统异常： 流程模型processKey为空");
        }
    }
    
    /**
     * 获取流程模型
     * 
     * @param startFlow
     * @return
     */
    public static String getProcessKey(StartFlow startFlow) {
        if (startFlow != null && StringUtils.isNotEmpty(startFlow.processKey())) {
            return startFlow.processKey();
        } else {
            throw new RRException("系统异常： 流程模型processKey为空");
        }
    }
    /**
     * 获取流程模型参数值
     * 
     * @param startFlow
     * @return
     */
    public static String getCustomerKey(StartFlow startFlow,int i) {
        if (startFlow != null && startFlow.args() != null && startFlow.args().length > 0) {
            return startFlow.args()[i];
        } else {
            return null;
        }
    }

    /**
     * 获取业务单据主键
     * 
     * @param joinPoint
     * @param formIdName
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String getFormID(JoinPoint joinPoint, String formIdName,boolean verify) {
        Object[] args = joinPoint.getArgs();
        String params = new Gson().toJson(args[0]);
        Map map = (Map) JSON.parse(params);
        // 检查是否含有ID
        if (!map.containsKey(formIdName)) {
            if (verify) {
                throw new RRException("无法根据入参获取主键：" + formIdName + "的值");
            } else {
                return null;
            }
        }
        return map.get(formIdName).toString();
    }
    /**
     * 对 args() 参数封装，封装业务参数
     * @param joinPoint
     * @param inputArgs
     * @param vars
     */
    public static void setArgs(JoinPoint joinPoint, String[] inputArgs, Map vars) {
        if (inputArgs != null && inputArgs.length > 0) {
            Object[] args = joinPoint.getArgs();
            String params = new Gson().toJson(args[0]);
            Map map = (Map) JSON.parse(params);
            // 检查是否含有ID
            for (String var : inputArgs) {
                if (map.containsKey(var)) {
                    vars.put(var, map.get(var));
                }
            }
        }
    }

    /**
     * 获取返回参数map
     * 暂时处理 :流分支条件 人员指定
     * 并不是所有参数都回封装到 activiti 变量里面， map 返回得需要增加到 keyMaps
     * 注解里面得 args 不用封装到 keyMaps
     * WfConstants ：  keyMaps = new String[] {ACTION,USER};
     */
    @SuppressWarnings("rawtypes")
    public static Map getVars(Object result) {
        Map varsMap = new HashMap();
        if (result instanceof Map) {
            Map vars = (Map) result;
            String[] keyMaps = WfConstants.keyMaps;
            for (String key : keyMaps) {
                if (vars.containsKey(key)) {
                    varsMap.put(key, vars.get(key));
                }
            }
        }
        
        return varsMap;
    }
    /**
     * 获取用户的username
     * @return
     */
    public static String getUserName() {
    	if (ThreadContext.getSubject() == null) {
    		return WfConstants.AUTO_USER;
    	}
        Map user = (Map) JSON.parse(new Gson().toJson(SecurityUtils.getSubject().getPrincipal()));
        if (user != null && user.containsKey("username")) {
            return user.get("username").toString();
        } else { //为空，取自动柜员，只能为 定时任务等，取不到人员
            return WfConstants.AUTO_USER;
        }
    }

}
