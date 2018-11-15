package com.yicheejia.workflow.service;

import java.util.List;


import com.yicheejia.workflow.vo.HisTaskVO;

/**
 * Activiti history查询常用service
 * 
 * @author hunk
 * @date 2018年6月31日
 */
public interface ActivitiHistoryService {
    /**
     * 历史流程实例查看（查找按照某个规则一共执行了多少次流程）
     * @param processDefinitionKey
     * @throws Exception
     */
    public void queryHistoricProcessInstance(String processDefinitionKey) throws Exception;
    
    /**
     * 历史任务实例查询
     * @param processInstanceId
     * @return 
     */
    public List<HisTaskVO> findHistoryTask(String processInstanceId) ;
    /**
     * 获取流程图像，已执行节点和流程线高亮显示
     */
    public void getActivitiProccessImage(String pProcessInstanceId);
    /**
     * 历史任务实例查询
     * @param processDefinitionKey 流程定义key
     * @param businessID 业务单据主键
     */
    public List<HisTaskVO> findHistoryTaskByBusinessID(String processDefinitionKey,String businessID);
}
