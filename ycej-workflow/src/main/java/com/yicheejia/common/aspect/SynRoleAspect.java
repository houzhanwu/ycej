package com.yicheejia.common.aspect;


import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.workflow.service.SynUserService;

/**
 * 同步角色  用户  切面类
 * @author hunk
 *
 */
@Aspect
@Component
public class SynRoleAspect {

    @Resource
    private ProcessEngine engine;
    @Autowired
    private SynUserService synUserService;

    @Pointcut("@annotation(com.yicheejia.common.annotation.SynRole)")
    public void synRolePointCut() {

    }

    @AfterReturning(value = "synRolePointCut()", returning = "result")
    public Object afterReturn(JoinPoint joinPoint, Object result) throws Throwable {
        // 执行方法

        if (result != null) {
            if (result instanceof Map) {
                Map map = (Map)result;
                if (map.containsKey("role")) {
                    Map role = (Map) JSON.parse(new Gson().toJson(map.get("role")));
                    synGrop(role.get("roleName").toString());
                } else {
                    throw new RRException("检查返回参数是否封装：role");
                }
            }
        }

        return result;
    }

    private void synGrop(String id) {
        
        synUserService.saveGroup(id, id);
        
    }

}
