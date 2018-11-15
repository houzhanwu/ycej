package com.yicheejia.workflow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.workflow.service.ActivitiBaseService;
import com.yicheejia.workflow.service.ActivitiBusinessService;



/**
 * activit 业务处理类
 * @author hunk
 *
 */
@Service
public class ActivitiBusinessServiceImpl implements ActivitiBusinessService {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ActivitiBaseService activitiBaseService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 通过表单ID执行任务
     * @param formid
     * @param userName
     * @param vars
     * @return
     */
    @Transactional
    public Object executeTaskByID(String processKey,String formid, String userName, Map vars) {

        Task task = activitiBaseService.getTaskByFormID(processKey, formid);
        if (task == null) {
        	throw new RRException("流程："+processKey+":"+formid+"为空！");
        }
        // 查询任务是否已经存在，是否已经被领取 ，领取和执行放在一起
        if (task.getAssignee() == null) {
            // 领取任务
            taskService.claim(task.getId(), userName);
            //增加批注审批意见
            if (vars.containsKey(WfConstants.REMARK)) {
                taskService.addComment(task.getId(),task.getProcessInstanceId(),vars.get(WfConstants.REMARK).toString());
                vars.remove(WfConstants.REMARK);
            }
            taskService.complete(task.getId(), vars);
            logger.info("executeTaskPointCut()注解工作任务完成");
        }
        
        return true;

    }

}
