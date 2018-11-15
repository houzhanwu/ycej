package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-10-25 15:47:20
 */
public class CautionMoneyBean {
    @Excel(name = "主键ID")
    private Long cautionId;
    @Excel(name = "展车台数")
    private Integer carNumber;
    @Excel(name = "保证金总额")
    private BigDecimal allCaution;
    @Excel(name = "单台保证金")
    private BigDecimal oneCaution;
    @Excel(name = "供应商ID")
    private Long supplierId;
    @Excel(name = "状态:1.暂存，2.资源总经理待审核，3.会计待审核，4.运营待审核，5.出纳待审核，6.完成")
    private String status;
    @Excel(name = "是否付款:1.是，0.否")
    private Integer isPay;
    @Excel(name = "车辆品牌ID")
    private String brandsId;
    @Excel(name = "车辆品牌")
    private String carBrands;
    @Excel(name = "供应商名称")
    private String supplierName;
    @Excel(name = "联系人")
    private String supplierContact;
    @Excel(name = "联系方式")
    private String supplierMobile;
    @Excel(name = "详细地址")
    private String supplierAdd;
    @Excel(name = "收款开户行")
    private String dueBank;
    @Excel(name = "收款开户支行")
    private String branchBank;
    @Excel(name = "收款账号")
    private String supplierBankcard;
    @Excel(name = "添加时间")
    private Date insertTime;
    @Excel(name = "添加人")
    private String insertId;
    @Excel(name = "修改时间")
    private Date updateTime;
    @Excel(name = "修改人")
    private String updateId;
    @Excel(name = "备注")
    private String cauComment;

    public void setCautionId(Long cautionId) {
        this.cautionId = cautionId;
    }
    public Long getCautionId() {
        return cautionId;
    }
    public void setCarNumber(Integer carNumber) {
        this.carNumber = carNumber;
    }
    public Integer getCarNumber() {
        return carNumber;
    }
    public void setAllCaution(BigDecimal allCaution) {
        this.allCaution = allCaution;
    }
    public BigDecimal getAllCaution() {
        return allCaution;
    }
    public void setOneCaution(BigDecimal oneCaution) {
        this.oneCaution = oneCaution;
    }
    public BigDecimal getOneCaution() {
        return oneCaution;
    }
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
    public Long getSupplierId() {
        return supplierId;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }
    public Integer getIsPay() {
        return isPay;
    }
    public void setBrandsId(String brandsId) {
        this.brandsId = brandsId;
    }
    public String getBrandsId() {
        return brandsId;
    }
    public void setCarBrands(String carBrands) {
        this.carBrands = carBrands;
    }
    public String getCarBrands() {
        return carBrands;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }
    public String getSupplierContact() {
        return supplierContact;
    }
    public void setSupplierMobile(String supplierMobile) {
        this.supplierMobile = supplierMobile;
    }
    public String getSupplierMobile() {
        return supplierMobile;
    }
    public void setSupplierAdd(String supplierAdd) {
        this.supplierAdd = supplierAdd;
    }
    public String getSupplierAdd() {
        return supplierAdd;
    }
    public void setDueBank(String dueBank) {
        this.dueBank = dueBank;
    }
    public String getDueBank() {
        return dueBank;
    }
    public void setBranchBank(String branchBank) {
        this.branchBank = branchBank;
    }
    public String getBranchBank() {
        return branchBank;
    }
    public void setSupplierBankcard(String supplierBankcard) {
        this.supplierBankcard = supplierBankcard;
    }
    public String getSupplierBankcard() {
        return supplierBankcard;
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
    public void setCauComment(String cauComment) {
        this.cauComment = cauComment;
    }
    public String getCauComment() {
        return cauComment;
    }
}
