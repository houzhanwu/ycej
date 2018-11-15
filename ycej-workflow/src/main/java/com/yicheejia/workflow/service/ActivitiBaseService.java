package com.yicheejia.workflow.service;

import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
/**
 * Activiti 常用service
 * @author hunk
 *
 */
public interface ActivitiBaseService {

    
    /**
     * 获取用户当前处于代办人的任务集合 只查user
     * 
     * @param userId
     *            用户
     * @return 任务集合
     */
    public List<Task> getUserHaveTasksUser(String userId);
    
    /**
     * 验证工作流是不是已经停止
     * 
     * @param instanceId
     *            工作流id
     * @return true:已经停止,false:没有停止
     */
    public Boolean validateActiviti(String instanceId);
    /**
     * 完成任务
     * 
     * @param taskId
     *            任务id
     */
    public void completeTask(String taskId) ;
    /**
     * 根据任务id得到Form key
     * @param taskId
     * @return
     */
    public String getFormKey(String taskId);
    /**
     * 根据TaskID 获取 FormID
     * @param taskID
     * @return
     */
    public String getFormIDByTaskID(String taskID) ;
    /**
     * 根据表单ID获取Task
     * 
     * @param formid
     * @return
     */
    public Task getTaskByFormID(String formid);
    /**
     * 根据表单ID获取Task
     * 
     * @param processKey 流程定义key
     * @param formid
     * @return
     */
    public Task getTaskByFormID(String processKey,String formid);
    /**
     * 根据Task 获取 FormID
     * @param task
     * @return
     */
    public String getFormIDByTask(Task task);
    /**
     * 根据Task 获取 ProcessInstance
     * @param task
     * @return
     */
    public ProcessInstance getProcessInstanceByTask(Task task) ;
    /**
     * 获取参数
     * @param executionId
     * @param variableName
     * @param variableClass
     * @return
     */
    public <T> T getVariable(String executionId, String variableName, Class<String> variableClass);
    /**
     * 获取用户当前处于代办人的任务集合 ,根据用户，单据主键查询
     * 
     * @param userId
     *            用户
     * @param billID
     *            业务单据主键
     * @return 任务集合
     */
	public Boolean getUserHaveTasksUser(String userId, String formID);
}
