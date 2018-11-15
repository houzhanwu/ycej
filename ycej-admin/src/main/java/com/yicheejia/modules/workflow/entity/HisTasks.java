package com.yicheejia.modules.workflow.entity;

/**
 * 任务
 * @author hunk
 *
 */
public class HisTasks {
    
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 时间
     */
    private String optTime;
    /**
     * 审核结果
     */
    private String remark;
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String getOptTime() {
        return optTime;
    }
    public void setOptTime(String optTime) {
        this.optTime = optTime;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
    

}
