package com.yicheejia.common.aspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yicheejia.common.annotation.StartFlow;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.workflow.service.ActivitiBusinessService;

/**
 * 开始流程切面
 * 
 * @author hunk
 *
 */
@Aspect
@Component
public class StartFlowAspect {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private IdentityService identityService;
    @Resource
    private TaskService taskService;
    @Autowired
    private ActivitiBusinessService activitiBusinessService;

    @Pointcut("@annotation(com.yicheejia.common.annotation.StartFlow)")
    public void startFlowPointCut() {

    }

    @AfterReturning(value = "startFlowPointCut()", returning = "result")
    public Object afterReturn(JoinPoint joinPoint, Object result) throws Throwable {

        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            StartFlow startFlow = method.getAnnotation(StartFlow.class);
            // 获取业务ID名
            String idName = AspectCommon.getFormIdName(startFlow);
            // 流程模型 key
            String processKey = AspectCommon.getProcessKey(startFlow);
            // 获取业务单据主键
            String formid = AspectCommon.getFormID(joinPoint, idName, true);
            // 获取用户名
            String username = AspectCommon.getUserName();
            // 获取客户名称
//            String customerId = AspectCommon.getCustomerKey(startFlow, 0);
//            String customerName = AspectCommon.getFormID(joinPoint, customerId, false);
            // 参数的处理
            Map vars = AspectCommon.getVars(result);
//            if (StringUtils.isNotEmpty(customerName)) {
//                vars.put(WfConstants.customerName, customerName);
//            }
            AspectCommon.setArgs(joinPoint, startFlow.args(), vars);
            startFlow(username, processKey, formid, vars);
        } catch (Exception e) {
            throw new Exception("流程发起异常:" + e.getMessage());
        }

        return result;
    }

    /**
     * 开始流程
     * @param userName
     * @return
     * @throws Throwable  
     */
    public Object startFlow(String userName, String processKey, String formid, Map vars) throws Throwable {

        identityService.setAuthenticatedUserId(userName);
        /** 判断流程是否存在，不存在则开启流程 */
        ProcessInstance rpi = runtimeService.createProcessInstanceQuery()// 创建流程实例查询对象
                .processInstanceBusinessKey(formid, processKey).singleResult();
        if (rpi == null) {
            // 开始流程
            Map<String, Object> map = new HashMap<String, Object>();
            //客户名称增加
            if (vars.containsKey(WfConstants.customerName) ) {
                map.put(WfConstants.customerName, vars.get(WfConstants.customerName));
            }
            if (vars.containsKey(WfConstants.supplierName)) {
            	 map.put(WfConstants.customerName, vars.get(WfConstants.supplierName));
            }
            rpi = runtimeService.startProcessInstanceByKey(processKey, formid, map);
        }
        //执行流程  增加processKey
        activitiBusinessService.executeTaskByID(processKey,formid, userName, vars);
        return true;
    }

}
