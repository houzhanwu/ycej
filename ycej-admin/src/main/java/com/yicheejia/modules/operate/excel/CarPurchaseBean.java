package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-18 16:43:36
 */
public class CarPurchaseBean {
    @Excel(name = "车辆采购主键")
    private Integer purchId;
    @Excel(name = "采购编号")
    private String purchNo;
    @Excel(name = "供应商主键")
    private String supplierId;
    @Excel(name = "店保信息")
    private Integer storeInfo;
    @Excel(name = "车辆来源")
    private Integer carFrom;
    @Excel(name = "采购总金额")
    private BigDecimal purchAmount;
    @Excel(name = "采购数量")
    private Integer count;
    @Excel(name = "收款开户行")
    private String openingBank;
    @Excel(name = "收款开户支行")
    private String openingBranchbank;
    @Excel(name = "收款账号")
    private String accountNo;
    @Excel(name = "车辆类型")
    private Integer carType;
    @Excel(name = "状态")
    private Integer status;
    @Excel(name = "是否付款")
    private Integer isPay;
    @Excel(name = "采购日期")
    private Date purchDate;
    @Excel(name = "入库日期")
    private Date inboundDate;
    @Excel(name = "入库仓库")
    private Integer warehouse;
    @Excel(name = "创建时间")
    private Date insertTime;
    @Excel(name = "创建人")
    private String insertId;
    @Excel(name = "修改时间")
    private Date updateTime;
    @Excel(name = "修改人")
    private String updateId;
    @Excel(name = "备注")
    private String remark;

    public void setPurchId(Integer purchId) {
        this.purchId = purchId;
    }
    public Integer getPurchId() {
        return purchId;
    }
    public void setPurchNo(String purchNo) {
        this.purchNo = purchNo;
    }
    public String getPurchNo() {
        return purchNo;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public String getSupplierId() {
        return supplierId;
    }
    public void setStoreInfo(Integer storeInfo) {
        this.storeInfo = storeInfo;
    }
    public Integer getStoreInfo() {
        return storeInfo;
    }
    public void setCarFrom(Integer carFrom) {
        this.carFrom = carFrom;
    }
    public Integer getCarFrom() {
        return carFrom;
    }
    public void setPurchAmount(BigDecimal purchAmount) {
        this.purchAmount = purchAmount;
    }
    public BigDecimal getPurchAmount() {
        return purchAmount;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Integer getCount() {
        return count;
    }
    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }
    public String getOpeningBank() {
        return openingBank;
    }
    public void setOpeningBranchbank(String openingBranchbank) {
        this.openingBranchbank = openingBranchbank;
    }
    public String getOpeningBranchbank() {
        return openingBranchbank;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public void setCarType(Integer carType) {
        this.carType = carType;
    }
    public Integer getCarType() {
        return carType;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getStatus() {
        return status;
    }
    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }
    public Integer getIsPay() {
        return isPay;
    }
    public void setPurchDate(Date purchDate) {
        this.purchDate = purchDate;
    }
    public Date getPurchDate() {
        return purchDate;
    }
    public void setInboundDate(Date inboundDate) {
        this.inboundDate = inboundDate;
    }
    public Date getInboundDate() {
        return inboundDate;
    }
    public void setWarehouse(Integer warehouse) {
        this.warehouse = warehouse;
    }
    public Integer getWarehouse() {
        return warehouse;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    public Date getInsertTime() {
        return insertTime;
    }
    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }
    public String getInsertId() {
        return insertId;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
    public String getUpdateId() {
        return updateId;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
}
