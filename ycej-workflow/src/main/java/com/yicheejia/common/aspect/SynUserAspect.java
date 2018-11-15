package com.yicheejia.common.aspect;


import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yicheejia.common.exception.RRException;
import com.yicheejia.workflow.service.SynUserService;

/**
 * 同步用户
 * @author hunk
 * @date 2018年6月30日
 */
@Aspect
@Component
public class SynUserAspect {

    @Resource
    private ProcessEngine engine;
    @Autowired
    private SynUserService synUserService;

    @Pointcut("@annotation(com.yicheejia.common.annotation.SynUser)")
    public void synUserPointCut() {

    }

    @AfterReturning(value = "synUserPointCut()", returning = "result")
    public Object afterReturn(JoinPoint joinPoint, Object result) throws Throwable {
        // 执行方法

        if (result != null) {
            if (result instanceof Map) {
                Map map = (Map) result;
                if (!map.containsKey("user")) {
                    throw new RRException("返回参数无法获取用户信息：user！");
                }
                String userID = map.get("user").toString();
                synUser(userID);
                if (map.containsKey("role")) {
                    String role = map.get("role").toString();
                    String roles[] = role.split("&");
                    for (String string : roles) {
                        if (StringUtils.isNotEmpty(string)) {
                            saveRelation(userID, string);
                        }
                    }
                } else {
                    throw new RRException("用户无分配角色！");
                }
            }
        }

        return result;
    }

    private void synUser(String id) {
        
        synUserService.saveUser(id);
        
    }
    
    private void saveRelation(String userID, String groupID) {
        
        synUserService.saveRelation(userID,groupID);
        
    }
    
    

}
