package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商变更信息表
 *
 * @author 
 * @email 
 * @date 2018-10-27 15:51:56
 */
public class SupplierChangeBean {
    @Excel(name = "供应商变更ID（主键）")
    private Integer id;
    @Excel(name = "供应商ID")
    private String supplierId;
    @Excel(name = "供应商名称")
    private String supplierName;
    @Excel(name = "联系人")
    private String supplierContact;
    @Excel(name = "联系电话")
    private String supplierMoblie;
    @Excel(name = "地址")
    private String supplierAdd;
    @Excel(name = "")
    private Integer provinceId;
    @Excel(name = "")
    private String provinceName;
    @Excel(name = "省市县信息编号（市ID）")
    private Integer cityId;
    @Excel(name = "")
    private String cityName;
    @Excel(name = "保证金金额")
    private BigDecimal supplierBail;
    @Excel(name = "提供展车数量")
    private Integer supplierCarnum;
    @Excel(name = "供应商收款账号")
    private String supplierBankcard;
    @Excel(name = "添加人")
    private String insertUser;
    @Excel(name = "添加时间")
    private Date insertTime;
    @Excel(name = "修改人")
    private String updateUser;
    @Excel(name = "修改时间")
    private Date updateTime;
    @Excel(name = "备注")
    private String comment;
    @Excel(name = "收款开户行code")
    private Integer dueBankCode;
    @Excel(name = "收款开户行")
    private String dueBank;
    @Excel(name = "收款开户支行")
    private String branchBank;
    @Excel(name = "供应商状态:1,暂存，2，待审核，3，已审核")
    private Integer supplierState;
    @Excel(name = "供应商类型")
    private Integer supplierType;
    @Excel(name = "车辆类型")
    private Integer vehicleType;
    @Excel(name = "车辆品牌")
    private String carBrand;
    @Excel(name = "车辆品牌名称")
    private String carBrandName;
    @Excel(name = "收款账号类型")
    private Integer dueAccountType;
    @Excel(name = "收款账号名称")
    private String dueAccountName;

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public String getSupplierId() {
        return supplierId;
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
    public void setSupplierMoblie(String supplierMoblie) {
        this.supplierMoblie = supplierMoblie;
    }
    public String getSupplierMoblie() {
        return supplierMoblie;
    }
    public void setSupplierAdd(String supplierAdd) {
        this.supplierAdd = supplierAdd;
    }
    public String getSupplierAdd() {
        return supplierAdd;
    }
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
    public Integer getProvinceId() {
        return provinceId;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public String getProvinceName() {
        return provinceName;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getCityName() {
        return cityName;
    }
    public void setSupplierBail(BigDecimal supplierBail) {
        this.supplierBail = supplierBail;
    }
    public BigDecimal getSupplierBail() {
        return supplierBail;
    }
    public void setSupplierCarnum(Integer supplierCarnum) {
        this.supplierCarnum = supplierCarnum;
    }
    public Integer getSupplierCarnum() {
        return supplierCarnum;
    }
    public void setSupplierBankcard(String supplierBankcard) {
        this.supplierBankcard = supplierBankcard;
    }
    public String getSupplierBankcard() {
        return supplierBankcard;
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
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }
    public void setDueBankCode(Integer dueBankCode) {
        this.dueBankCode = dueBankCode;
    }
    public Integer getDueBankCode() {
        return dueBankCode;
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
    public void setSupplierState(Integer supplierState) {
        this.supplierState = supplierState;
    }
    public Integer getSupplierState() {
        return supplierState;
    }
    public void setSupplierType(Integer supplierType) {
        this.supplierType = supplierType;
    }
    public Integer getSupplierType() {
        return supplierType;
    }
    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }
    public Integer getVehicleType() {
        return vehicleType;
    }
    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
    public String getCarBrand() {
        return carBrand;
    }
    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }
    public String getCarBrandName() {
        return carBrandName;
    }
    public void setDueAccountType(Integer dueAccountType) {
        this.dueAccountType = dueAccountType;
    }
    public Integer getDueAccountType() {
        return dueAccountType;
    }
    public void setDueAccountName(String dueAccountName) {
        this.dueAccountName = dueAccountName;
    }
    public String getDueAccountName() {
        return dueAccountName;
    }
}
