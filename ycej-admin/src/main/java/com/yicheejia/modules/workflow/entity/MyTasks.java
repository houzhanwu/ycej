package com.yicheejia.modules.workflow.entity;

/**
 * 任务
 * @author hunk
 *
 */
public class MyTasks {
    
    /**
     * 任务ID
     */
    private String  taskID;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务类型
     */
    private String type;
    /**
     * 表单Url
     */
    private String formUrl;
    /**
     * 表单主键
     */
    private String formID;
    /**
     * 申请人
     */
    private String applyUser;
    /**
     * 申请时间
     */
    private String applyTime;
    /**
     * 申请人
     */
    private String lastUser;
    /**
     * 申请时间
     */
    private String lastTime;
    /**
     * 审核人
     */
    private String checkUser;
    /**
     * 审核时间
     */
    private String checkTime;
    /**
     * 审核结果
     */
    private String checkResult;
    /**
     * 流程实例ID
     */
    private String instanceId;
    
    /**
     * 客户名称
     */
    private String customerName;
    
    public String getTaskID() {
        return taskID;
    }
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getApplyUser() {
        return applyUser;
    }
    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }
    public String getApplyTime() {
        return applyTime;
    }
    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }
    public String getCheckUser() {
        return checkUser;
    }
    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }
    public String getCheckTime() {
        return checkTime;
    }
    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
    public String getCheckResult() {
        return checkResult;
    }
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
    public String getFormUrl() {
        return formUrl;
    }
    public void setFormUrl(String formUrl) {
        this.formUrl = formUrl;
    }
    public String getFormID() {
        return formID;
    }
    public void setFormID(String formID) {
        this.formID = formID;
    }
    public String getInstanceId() {
        return instanceId;
    }
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
    public String getLastUser() {
        return lastUser;
    }
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    public String getLastTime() {
        return lastTime;
    }
    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    @Override
    public String toString() {
        return "MyTasks [taskID=" + taskID + ", name=" + name + ", type=" + type + ", formUrl=" + formUrl + ", formID="
                + formID + ", applyUser=" + applyUser + ", applyTime=" + applyTime + ", lastUser=" + lastUser
                + ", lastTime=" + lastTime + ", checkUser=" + checkUser + ", checkTime=" + checkTime + ", checkResult="
                + checkResult + ", instanceId=" + instanceId + "]";
    }


    
    

}
