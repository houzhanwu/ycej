package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存表
 *
 * @author 
 * @email 
 * @date 2018-07-24 16:07:43
 */
public class InventoryBean {
    @Excel(name = "库存ID（主键）")
    private String inventoryId;
    @Excel(name = "品牌id")
    private String carBrand;
    @Excel(name = "厂商")
    private String carManufacturer;
    @Excel(name = "车系id")
    private String carSeries;
    @Excel(name = "车型id")
    private String carModel;
    @Excel(name = "车身颜色")
    private String carColour;
    @Excel(name = "VIN（车架号）")
    private String vin;
    @Excel(name = "发动机号")
    private String engineId;
    @Excel(name = "出厂日期")
    private Date manufactureDate;
    @Excel(name = "入库日期Now()")
    private Date storageDate;
    @Excel(name = "操作人")
    private Integer insertId;
    @Excel(name = "采购发票价")
    private BigDecimal receiptPrice;
    @Excel(name = "采购总成本")
    private BigDecimal costPrice;
    @Excel(name = "活动销售价（最终售价）")
    private BigDecimal salePrice;
    @Excel(name = "所在门店（取部门ID）")
    private Integer shopId;
    @Excel(name = "车辆来源（1为全款采购车、2为保证金车、3为优壹车）")
    private Integer carSource;
    @Excel(name = "店保（1无；2店保交强；3店保全险）")
    private Integer dinsurance;
    @Excel(name = "店保金额")
    private BigDecimal dinsurancePrice;
    @Excel(name = "供应商ID")
    private Integer supplierId;
    @Excel(name = "修改人")
    private Integer updateId;
    @Excel(name = "修改时间")
    private Date updateTime;
    @Excel(name = "备注")
    private String comment;
    @Excel(name = "合格证地址")
    private String certificateAdd;
    @Excel(name = "车辆状态:1在库；2调拨中；3已销售；4已出库；5销售中")
    private Integer carStatus;

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }
    public String getInventoryId() {
        return inventoryId;
    }
    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
    public String getCarBrand() {
        return carBrand;
    }
    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }
    public String getCarManufacturer() {
        return carManufacturer;
    }
    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }
    public String getCarSeries() {
        return carSeries;
    }
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    public String getCarModel() {
        return carModel;
    }
    public void setCarColour(String carColour) {
        this.carColour = carColour;
    }
    public String getCarColour() {
        return carColour;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public String getVin() {
        return vin;
    }
    public void setEngineId(String engineId) {
        this.engineId = engineId;
    }
    public String getEngineId() {
        return engineId;
    }
    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }
    public Date getManufactureDate() {
        return manufactureDate;
    }
    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }
    public Date getStorageDate() {
        return storageDate;
    }
    public void setInsertId(Integer insertId) {
        this.insertId = insertId;
    }
    public Integer getInsertId() {
        return insertId;
    }
    public void setReceiptPrice(BigDecimal receiptPrice) {
        this.receiptPrice = receiptPrice;
    }
    public BigDecimal getReceiptPrice() {
        return receiptPrice;
    }
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
    public BigDecimal getCostPrice() {
        return costPrice;
    }
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
    public BigDecimal getSalePrice() {
        return salePrice;
    }
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public Integer getShopId() {
        return shopId;
    }
    public void setCarSource(Integer carSource) {
        this.carSource = carSource;
    }
    public Integer getCarSource() {
        return carSource;
    }
    public void setDinsurance(Integer dinsurance) {
        this.dinsurance = dinsurance;
    }
    public Integer getDinsurance() {
        return dinsurance;
    }
    public void setDinsurancePrice(BigDecimal dinsurancePrice) {
        this.dinsurancePrice = dinsurancePrice;
    }
    public BigDecimal getDinsurancePrice() {
        return dinsurancePrice;
    }
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
    public Integer getSupplierId() {
        return supplierId;
    }
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }
    public Integer getUpdateId() {
        return updateId;
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
    public void setCertificateAdd(String certificateAdd) {
        this.certificateAdd = certificateAdd;
    }
    public String getCertificateAdd() {
        return certificateAdd;
    }
    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }
    public Integer getCarStatus() {
        return carStatus;
    }
}
