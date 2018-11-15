package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 供应商品牌关联信息表
 *
 * @author 
 * @email 
 * @date 2018-10-27 15:51:56
 */
public class SupplierCarbrandBean {
    @Excel(name = "供应商品牌ID（主键）")
    private Integer id;
    @Excel(name = "供应商ID")
    private String supplierId;
    @Excel(name = "供应商名称")
    private String supplierName;
    @Excel(name = "车辆品牌")
    private String carBrand;
    @Excel(name = "车辆品牌名称")
    private String carBrandName;
    @Excel(name = "添加人")
    private String insertUser;
    @Excel(name = "添加时间")
    private Date insertTime;
    @Excel(name = "修改人")
    private String updateUser;
    @Excel(name = "修改时间")
    private Date updateTime;

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
