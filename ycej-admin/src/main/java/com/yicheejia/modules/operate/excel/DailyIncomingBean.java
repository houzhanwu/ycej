package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-11-02 11:08:40
 */
public class DailyIncomingBean {
    @Excel(name = "")
    private Long id;
    @Excel(name = "门店主键")
    private String shopId;
    @Excel(name = "提车供应商")
    private String supplierId;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "工作流程备注")
    private String comment;
    @Excel(name = "状态:暂存1；资源待审核1；车辆待入库2；已完成3")
    private Integer state;
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
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    public String getShopId() {
        return shopId;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public String getSupplierId() {
        return supplierId;
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
    public void setState(Integer state) {
        this.state = state;
    }
    public Integer getState() {
        return state;
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
