package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-11-06 18:04:09
 */
public class RefundDepositBean {
    @Excel(name = "")
    private Long id;
    @Excel(name = "供应商主键")
    private String supplierId;
    @Excel(name = "保证金支付主键")
    private String depositPayId;
    @Excel(name = "交接文件")
    private Integer handleFile;
    @Excel(name = "交接时间")
    private Date handleTime;
    @Excel(name = "接收人")
    private String receiveUser;
    @Excel(name = "是否退款")
    private Integer isRefund;
    @Excel(name = "预计退款时间")
    private Date refundTime;
    @Excel(name = "是否退车")
    private Integer isBack;
    @Excel(name = "退车时间")
    private Date backTime;
    @Excel(name = "退车数量")
    private Integer backCarCount;
    @Excel(name = "收款时间")
    private Date receiveTime;
    @Excel(name = "收款金额")
    private BigDecimal receiveAmount;
    @Excel(name = "收款流水号")
    private String receiveSerialNum;
    @Excel(name = "状态：暂存1；资源总经理待审核2；会计待审核3；资源待确认4；出纳待审核5；已完成6")
    private Integer refundState;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "用来保存工作流备注")
    private String comment;
    @Excel(name = "发起人")
    private String insertUser;
    @Excel(name = "")
    private Date insertTime;
    @Excel(name = "")
    private String updateUser;
    @Excel(name = "")
    private Date updateTime;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public String getSupplierId() {
        return supplierId;
    }
    public void setDepositPayId(String depositPayId) {
        this.depositPayId = depositPayId;
    }
    public String getDepositPayId() {
        return depositPayId;
    }
    public void setHandleFile(Integer handleFile) {
        this.handleFile = handleFile;
    }
    public Integer getHandleFile() {
        return handleFile;
    }
    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }
    public Date getHandleTime() {
        return handleTime;
    }
    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }
    public String getReceiveUser() {
        return receiveUser;
    }
    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
    }
    public Integer getIsRefund() {
        return isRefund;
    }
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }
    public Date getRefundTime() {
        return refundTime;
    }
    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }
    public Integer getIsBack() {
        return isBack;
    }
    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }
    public Date getBackTime() {
        return backTime;
    }
    public void setBackCarCount(Integer backCarCount) {
        this.backCarCount = backCarCount;
    }
    public Integer getBackCarCount() {
        return backCarCount;
    }
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
    public Date getReceiveTime() {
        return receiveTime;
    }
    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }
    public BigDecimal getReceiveAmount() {
        return receiveAmount;
    }
    public void setReceiveSerialNum(String receiveSerialNum) {
        this.receiveSerialNum = receiveSerialNum;
    }
    public String getReceiveSerialNum() {
        return receiveSerialNum;
    }
    public void setRefundState(Integer refundState) {
        this.refundState = refundState;
    }
    public Integer getRefundState() {
        return refundState;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }
    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }
    public String getInsertUser() {
        return insertUser;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    public Date getInsertTime() {
        return insertTime;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    public String getUpdateUser() {
        return updateUser;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
}
