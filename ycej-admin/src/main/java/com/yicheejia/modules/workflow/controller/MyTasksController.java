package com.yicheejia.modules.workflow.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.service.SysUserService;
import com.yicheejia.modules.workflow.entity.MyTasks;
import com.yicheejia.workflow.service.ActivitiBaseService;
import com.yicheejia.workflow.service.ActivitiHistoryService;
import com.yicheejia.workflow.vo.HisTaskVO;
/**
 * 代办工作 
 * @author hunk
 * @date 2018年7月19日
 */
@RestController
@RequestMapping("/tasks/mytasks")
public class MyTasksController {

    @Autowired
    ActivitiBaseService activitiBaseService;
    @Autowired
    ActivitiHistoryService activitiHistoryService;
    @Autowired
    SysUserService sysUserService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPage list(@RequestParam Map<String, Object> params) {

        String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
        List<Task> tasks = activitiBaseService.getUserHaveTasksUser(userId);
        List<MyTasks> list = new ArrayList<MyTasks>();
        //封装代办任务
        getMyTasks(tasks, list);
        //封装用户账号
        List userList = new ArrayList();
        for (MyTasks his : list) {
            userList.add(his.getApplyUser());
        }
        //获取用户名
        Map<String,String> map = getUserName(userList);
        for (MyTasks his : list) {
            if (map.containsKey(his.getApplyUser())) {
                his.setApplyUser(map.get(his.getApplyUser()));
            }
        }
    
    	LayuiPage layuiPage = new LayuiPage(list,list.size());
        return layuiPage;
    }

    
    /**
     * 通过 业务单据主键查询
     * @param id 业务单据主键
     * @return
     */
    @RequestMapping("/taskinfo/{id}-{proKey}")
    public LayuiPage hisTaskInfo(@PathVariable("id") String id,@PathVariable("proKey") String proKey) {
        List<HisTaskVO>  list = activitiHistoryService.findHistoryTaskByBusinessID(proKey,id);
        if (list != null && list.size() > 0) {
            List userList = new ArrayList<>();
            for (HisTaskVO his : list) {
                userList.add(his.getOperator());
            }
            //通过账号获取用户名
            Map<String,String> map = getUserName(userList);
            for (HisTaskVO his : list) {
                if (map.containsKey(his.getOperator())) {
                    his.setOperator(map.get(his.getOperator()));
                }
            }
        } else {
            list = new ArrayList<HisTaskVO>();
        }
        
        LayuiPage layuiPage = new LayuiPage(list,list.size());
        return layuiPage;
    }
    /**
     * 获取用户名Map
     * @param userList
     * @return
     */
    private Map getUserName(List userList) {
        
        return sysUserService.getUserName(userList);
        
    }
    /**
     * 代办任务封装
     * @param tasks
     * @param list
     * @return
     */
    private List<MyTasks> getMyTasks(List<Task> tasks,List<MyTasks> list) {
        for (Task task : tasks) {
            task.getFormKey();
            String formUrl = task.getFormKey();
            //可以查询优化整合，
            String formID = activitiBaseService.getFormIDByTask(task);
            ProcessInstance pro = activitiBaseService.getProcessInstanceByTask(task);
            String customerName = activitiBaseService.getVariable(pro.getId(), WfConstants.customerName, String.class);
            MyTasks myTasks = new MyTasks();
            myTasks.setTaskID(task.getId());
            myTasks.setCustomerName(customerName);
            myTasks.setFormID(formID);
            myTasks.setFormUrl(formUrl);
            myTasks.setType(pro.getProcessDefinitionKey());
            myTasks.setName(task.getName());
            myTasks.setApplyUser(pro.getStartUserId());
            myTasks.setApplyTime(DateUtils.formatTime(pro.getStartTime()));
            myTasks.setCheckResult(formID);
            myTasks.setInstanceId(task.getProcessInstanceId());
            myTasks.toString();
            list.add(myTasks);
        }
        return list;
    }
    

}
