package com.yicheejia.modules.workflow.listener;


import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yicheejia.modules.sys.service.SysUserService;
import com.yicheejia.workflow.service.ActivitiBaseService;

/**
 * 增加用户组Listener
 * @author hunk
 *
 */
@Component("addCandidateListener")
public class AddCandidateListener implements TaskListener{

    private static final long serialVersionUID = -7064187833060355675L;
    
    @Autowired
    SysUserService sysUserService;
    @Autowired
    ActivitiBaseService activitiBaseService;
    

    @Override
    public void notify(DelegateTask delegateTask) {
        
        //当前用户
        delegateTask.getAssignee();
    }

}
