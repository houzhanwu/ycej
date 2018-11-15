package com.yicheejia.common.aspect;

import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.workflow.service.ActivitiBusinessService;
/**
 * 执行任务
 * @author hunk
 * @date 2018年6月29日
 */
@Aspect
@Component
public class ExecuteTaskAspect {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private IdentityService identityService;
    @Resource
    private TaskService taskService;
    
    
    @Autowired
    private ActivitiBusinessService activitiBusinessService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.yicheejia.common.annotation.ExecuteTask)")
    public void executeTaskPointCut() {

    }

    @AfterReturning(value = "executeTaskPointCut()", returning = "result")
    public Object afterReturn(JoinPoint joinPoint, Object result) throws Throwable {

        logger.info("executeTaskPointCut()注解工作流启动");
        if (result != null) {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();
                ExecuteTask executeTask = method.getAnnotation(ExecuteTask.class);
                // 获取业务ID名
                String idName = AspectCommon.getFormIdName(executeTask);
                // 流程模型 key
                String processKey = AspectCommon.getProcessKey(executeTask);
                // 获取业务单据主键
                String formid = AspectCommon.getFormID(joinPoint,idName,true);
                //获取用户名
                String username = AspectCommon.getUserName();
                // 参数的处理
                Map vars = AspectCommon.getVars(result);
                AspectCommon.setArgs(joinPoint, executeTask.args(), vars);
                logger.info("executeTaskPointCut()注解调用服务");
                activitiBusinessService.executeTaskByID(processKey,formid, username, vars);
        }

        return result;
    }



}
