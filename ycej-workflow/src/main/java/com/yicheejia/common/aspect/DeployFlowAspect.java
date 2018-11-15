package com.yicheejia.common.aspect;


import javax.annotation.Resource;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 流程部署
 * @author hunk
 *
 */
@Aspect
@Component
public class DeployFlowAspect {

    @Resource
    private ProcessEngine engine;

    @Pointcut("@annotation(com.yicheejia.common.annotation.DeployFlow)")
    public void deployPointCut() {

    }

    @AfterReturning(value = "deployPointCut()", returning = "result")
    public Object afterReturn(JoinPoint joinPoint, Object result) throws Throwable {
        deploy(result,"");
        return result;
    }
    /**
     * 流程部署
     * @param result
     * @param name
     */
    private void deploy(Object result,String name) {
        RepositoryService rs = engine.getRepositoryService();
        // rs.createDeployment().addClasspathResource("classpath:/processes/vacation.bpmn").deploy();
        Deployment dep = rs.createDeployment().addClasspathResource("processes/OrderForm_26.bpmn20.xml").deploy();
        ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
        if (pd == null) {
            throw new ActivitiException("流程部署失败！" + name);
        } else {
            pd.getName();
        }
       
    }

}
