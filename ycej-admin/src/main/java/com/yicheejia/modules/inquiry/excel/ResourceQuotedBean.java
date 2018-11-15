package com.yicheejia.modules.inquiry.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资源报价表
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:44:05
 */
public class ResourceQuotedBean {
    @Excel(name = "报价id")
    private Long quotedId;
    @Excel(name = "")
    private Long carBrandId;
    @Excel(name = "品牌")
    private String carBrand;
    @Excel(name = "")
    private Long carManufacturerId;
    @Excel(name = "厂商")
    private String carManufacturer;
    @Excel(name = "")
    private Long carSeriesId;
    @Excel(name = "车系")
    private String carSeries;
    @Excel(name = "")
    private Long carModelId;
    @Excel(name = "车型")
    private String carModel;
    @Excel(name = "")
    private String carAppearanceColor;
    @Excel(name = "排量")
    private Double carEmissions;
    @Excel(name = "供应价")
    private BigDecimal offerPrice;
    @Excel(name = "资源名称")
    private String resourceName;
    @Excel(name = "报价时间")
    private Date quotedTime;
    @Excel(name = "创建时间")
    private Date insertTime;
    @Excel(name = "")
    private String insertUser;
    @Excel(name = "备注")
    private String remark;

    public void setQuotedId(Long quotedId) {
        this.quotedId = quotedId;
    }
    public Long getQuotedId() {
        return quotedId;
    }
    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }
    public Long getCarBrandId() {
        return carBrandId;
    }
    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
    public String getCarBrand() {
        return carBrand;
    }
    public void setCarManufacturerId(Long carManufacturerId) {
        this.carManufacturerId = carManufacturerId;
    }
    public Long getCarManufacturerId() {
        return carManufacturerId;
    }
    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }
    public String getCarManufacturer() {
        return carManufacturer;
    }
    public void setCarSeriesId(Long carSeriesId) {
        this.carSeriesId = carSeriesId;
    }
    public Long getCarSeriesId() {
        return carSeriesId;
    }
    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }
    public String getCarSeries() {
        return carSeries;
    }
    public void setCarModelId(Long carModelId) {
        this.carModelId = carModelId;
    }
    public Long getCarModelId() {
        return carModelId;
    }
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    public String getCarModel() {
        return carModel;
    }
    public void setCarAppearanceColor(String carAppearanceColor) {
        this.carAppearanceColor = carAppearanceColor;
    }
    public String getCarAppearanceColor() {
        return carAppearanceColor;
    }
    public void setCarEmissions(Double carEmissions) {
        this.carEmissions = carEmissions;
    }
    public Double getCarEmissions() {
        return carEmissions;
    }
    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }
    public BigDecimal getOfferPrice() {
        return offerPrice;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setQuotedTime(Date quotedTime) {
        this.quotedTime = quotedTime;
    }
    public Date getQuotedTime() {
        return quotedTime;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    public Date getInsertTime() {
        return insertTime;
    }
    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }
    public String getInsertUser() {
        return insertUser;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
}
