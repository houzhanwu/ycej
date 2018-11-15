package com.yicheejia.workflow.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.task.Comment;
import org.activiti.image.ProcessDiagramGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.workflow.service.ActivitiHistoryService;
import com.yicheejia.workflow.utils.ActivitiUtil;
import com.yicheejia.workflow.vo.HisTaskVO;

@Service
public class ActivitiHistoryServiceImpl implements ActivitiHistoryService {

    @Autowired
    HistoryService historyService;
    @Autowired
    protected ProcessEngine processEngine;
    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;

    protected Logger logger = LoggerFactory.getLogger(ActivitiHistoryServiceImpl.class);

    /**
     * 历史流程实例查看（查找按照某个规则一共执行了多少次流程）
     * 
     * @param processDefinitionKey
     * @throws Exception
     */
    public void queryHistoricProcessInstance(String processDefinitionKey) throws Exception {
        // 获取历史流程实例的查询对象
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
        // 设置查询参数
        historicProcessInstanceQuery
                // 过滤条件
                .processDefinitionKey(processDefinitionKey)
                // 分页条件
                // .listPage(firstResult, maxResults)
                // 排序条件
                .orderByProcessInstanceStartTime().desc();
        // 执行查询
        List<HistoricProcessInstance> hpis = historicProcessInstanceQuery.list();
        // 遍历查看结果
        for (HistoricProcessInstance hpi : hpis) {
            logger.info("pid:" + hpi.getId() + ",");
            logger.info("pdid:" + hpi.getProcessDefinitionId() + ",");
            logger.info("startTime:" + hpi.getStartTime() + ",");
            logger.info("endTime:" + hpi.getEndTime() + ",");
            logger.info("duration:" + hpi.getDurationInMillis() + ",");
            logger.info("vars:" + hpi.getProcessVariables());
        }

    }

    /**
     * 历史任务实例查询
     * 
     * @param processInstanceId
     */
    public List<HisTaskVO> findHistoryTask(String processInstanceId) {
        List<HistoricTaskInstance> list = processEngine.getHistoryService()// 与历史数据（历史表）相关的Service
                .createHistoricTaskInstanceQuery()// 创建历史任务实例查询
                .processInstanceId(processInstanceId)//
                .orderByHistoricTaskInstanceStartTime().asc().list();
        if (list != null && list.size() > 0) {
            List<HisTaskVO> hisTaskList = new ArrayList<HisTaskVO>();
            for (HistoricTaskInstance hisIns : list) {
                HisTaskVO hisVO = new HisTaskVO();
                hisVO.setTaskName(hisIns.getName());
                hisVO.setOperator(hisIns.getOwner());
                hisVO.setOptTime(DateUtils.format(hisIns.getDueDate()));
                List<HistoricVariableInstance> varInstanceList = historyService.createHistoricVariableInstanceQuery()
                        .processInstanceId(hisIns.getId()).list();
                ActivitiUtil.setVars(hisVO, varInstanceList);
                hisTaskList.add(hisVO);
            }
            return hisTaskList;
        }
        
        return null;
    }
    /**
     * 历史任务实例查询
     * 
     * @param businessID 业务单据主键
     */
    public List<HisTaskVO> findHistoryTaskByBusinessID(String processDefinitionKey, String businessID) {
        List<HistoricTaskInstance> list = processEngine.getHistoryService()// 与历史数据（历史表）相关的Service
                .createHistoricTaskInstanceQuery()// 创建历史任务实例查询
                .processDefinitionKey(processDefinitionKey)
                .processInstanceBusinessKey(businessID)//
                .orderByHistoricTaskInstanceStartTime()
                .desc()
                .orderByTaskId()
                .desc()
                .list();
        if (list != null && list.size() > 0) {
            List<HisTaskVO> hisTaskList = new ArrayList<HisTaskVO>();
            for (HistoricTaskInstance hisIns : list) {
                HisTaskVO hisVO = new HisTaskVO();
                hisVO.setTaskName(hisIns.getName());
                hisVO.setOperator(hisIns.getAssignee());
                hisVO.setOptTime(DateUtils.formatTime(hisIns.getEndTime()));
                List<Comment> commentList = taskService.getTaskComments(hisIns.getId());
                if(commentList != null && !commentList.isEmpty()) {
                    hisVO.setRemark(commentList.get(0).getFullMessage());
                }
                hisTaskList.add(hisVO);
            }
            return hisTaskList;
        }
        
        return null;
    }
    

    /**
     * 获取流程图像，已执行节点和流程线高亮显示
     */
    public void getActivitiProccessImage(String processInstanceId) {
        // logger.info("[开始]-获取流程图图像");
        try {
            // 获取历史流程实例
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstanceId).singleResult();

            if (historicProcessInstance == null) {
                 throw new ActivitiException("获取流程实例ID[" + processInstanceId + "]对应的历史流程实例失败！");
            } else {
                // 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
                List<HistoricActivityInstance> historicActivityInstanceList = historyService
                        .createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
                        .orderByHistoricActivityInstanceId().asc().list();

                // 已执行的节点ID集合
                List<String> executedActivityIdList = new ArrayList<String>();
                int index = 1;
                // logger.info("获取已经执行的节点ID");
                for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                    executedActivityIdList.add(activityInstance.getActivityId());
                    index++;
                }
                BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());
                // 已执行的线集合
                List<String> flowIds = new ArrayList<String>();
                // 获取流程走过的线 (getHighLightedFlows是下面的方法)
                flowIds = getHighLightedFlows(bpmnModel, historicActivityInstanceList);
                // 获取流程图图像字符流
                ProcessDiagramGenerator pec = processEngine.getProcessEngineConfiguration()
                        .getProcessDiagramGenerator();
                // 配置字体
                InputStream imageStream = pec.generateDiagram(bpmnModel, "png", executedActivityIdList, flowIds, "宋体",
                        "微软雅黑", "黑体", null, 2.0);
                input(imageStream);
            }
             logger.info("[完成]-获取流程图图像");
        } catch (Exception e) {
             logger.error("【异常】-获取流程图失败！" + e.getMessage());
             throw new ActivitiException("获取流程图失败！" + e.getMessage());
        }
    }
    /**
     * 获取高亮流程
     * @param bpmnModel
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(BpmnModel bpmnModel, 
            List<HistoricActivityInstance> historicActivityInstances) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 24小时制
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId

        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
            // 对历史流程节点进行遍历
            // 得到节点定义的详细信息
            FlowNode activityImpl = (FlowNode) bpmnModel.getMainProcess()
                    .getFlowElement(historicActivityInstances.get(i).getActivityId());
            List<FlowNode> sameStartTimeNodes = new ArrayList<FlowNode>();// 用以保存后续开始时间相同的节点
            FlowNode sameActivityImpl1 = null;

            HistoricActivityInstance activityImpl_ = historicActivityInstances.get(i);// 第一个节点
            HistoricActivityInstance activityImp2_;

            for (int k = i + 1; k <= historicActivityInstances.size() - 1; k++) {
                activityImp2_ = historicActivityInstances.get(k);// 后续第1个节点
                if (activityImpl_.getActivityType().equals("userTask")
                        && activityImp2_.getActivityType().equals("userTask")
                        && df.format(activityImpl_.getStartTime()).equals(df.format(activityImp2_.getStartTime()))){
                    // 都是usertask，且主节点与后续节点的开始时间相同，说明不是真实的后继节点
                } else {
                    sameActivityImpl1 = (FlowNode) bpmnModel.getMainProcess()
                            .getFlowElement(historicActivityInstances.get(k).getActivityId());// 找到紧跟在后面的一个节点
                    break;
                }
            }
            sameStartTimeNodes.add(sameActivityImpl1); // 将后面第一个节点放在时间相同节点的集合里
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);// 后续第二个节点
                if (df.format(activityImpl1.getStartTime()).equals(df.format(activityImpl2.getStartTime()))) {// 如果第一个节点和第二个节点开始时间相同保存
                    FlowNode sameActivityImpl2 = (FlowNode) bpmnModel.getMainProcess()
                            .getFlowElement(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {// 有不相同跳出循环
                    break;
                }
            }
            List<SequenceFlow> pvmTransitions = activityImpl.getOutgoingFlows(); // 取出节点的所有出去的线
            for (SequenceFlow pvmTransition : pvmTransitions) {// 对所有的线进行遍历
                FlowNode pvmActivityImpl = (FlowNode) bpmnModel.getMainProcess()
                        .getFlowElement(pvmTransition.getTargetRef());// 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
    
    private void input(InputStream inputStream) {
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("D:\\1.png");
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
