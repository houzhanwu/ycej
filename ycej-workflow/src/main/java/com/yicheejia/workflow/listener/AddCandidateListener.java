package com.yicheejia.workflow.listener;

import java.util.List;
import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.yicheejia.workflow.service.ActivitiBaseService;

/**
 * 增加用户组Listener
 * @author hunk
 * 缺陷：无法被String bean管理
 */
public class AddCandidateListener implements TaskListener{

    private static final long serialVersionUID = -7064187833060355675L;
    
    @Autowired
    ActivitiBaseService activitiBaseService;
    

    @Override
    public void notify(DelegateTask delegateTask) {
        
        //当前用户
        delegateTask.deleteCandidateGroup("");
        delegateTask.getCandidates();
        Set<IdentityLink> set = delegateTask.getCandidates();
        for (IdentityLink identityLink : set) {
            String roleName = identityLink.getGroupId();
            String formId = activitiBaseService.getFormIDByTaskID(identityLink.getTaskId());
        }
        delegateTask.getAssignee();
        
    }

}
