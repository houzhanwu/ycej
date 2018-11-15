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
public class IncomingDetailBean {
    @Excel(name = "日常入库明细表主键")
    private Long id;
    @Excel(name = "日常入库表主键")
    private String dailyIncomingId;
    @Excel(name = "车辆品牌value值")
    private String carBrand;
    @Excel(name = "车辆品牌名称")
    private String carBrandName;
    @Excel(name = "厂商value值")
    private String carManufacturer;
    @Excel(name = "厂商名称")
    private String carManufacturerName;
    @Excel(name = "车系value值")
    private String carSeries;
    @Excel(name = "车系名称")
    private String carSeriesName;
    @Excel(name = "车型value值")
    private String carModel;
    @Excel(name = "车型名称")
    private String carModelName;
    @Excel(name = "排量")
    private String carDisplacement;
    @Excel(name = "座位数")
    private Integer carSeat;
    @Excel(name = "外观value值")
    private Integer appearance;
    @Excel(name = "外观颜色")
    private String appearanceColor;
    @Excel(name = "车架号")
    private String vin;
    @Excel(name = "出厂日期")
    private Date productDate;
    @Excel(name = "入库日期")
    private Date inboundDate;
    @Excel(name = "操作人")
    private String insertUser;
    @Excel(name = "")
    private Date insertTime;
    @Excel(name = "备注")
    private String remark;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setDailyIncomingId(String dailyIncomingId) {
        this.dailyIncomingId = dailyIncomingId;
    }
    public String getDailyIncomingId() {
        return dailyIncomingId;
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
    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }
    public String getCarManufacturer() {
        return carManufacturer;
    }
    public void setCarManufacturerName(String carManufacturerName) {
        this.carManufacturerName = carManufacturerName;
    }
    public String getCarManufacturerName() {
        return carManufacturerName;
    }
    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }
    public String getCarSeries() {
        return carSeries;
    }
    public void setCarSeriesName(String carSeriesName) {
        this.carSeriesName = carSeriesName;
    }
    public String getCarSeriesName() {
        return carSeriesName;
    }
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    public String getCarModel() {
        return carModel;
    }
    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }
    public String getCarModelName() {
        return carModelName;
    }
    public void setCarDisplacement(String carDisplacement) {
        this.carDisplacement = carDisplacement;
    }
    public String getCarDisplacement() {
        return carDisplacement;
    }
    public void setCarSeat(Integer carSeat) {
        this.carSeat = carSeat;
    }
    public Integer getCarSeat() {
        return carSeat;
    }
    public void setAppearance(Integer appearance) {
        this.appearance = appearance;
    }
    public Integer getAppearance() {
        return appearance;
    }
    public void setAppearanceColor(String appearanceColor) {
        this.appearanceColor = appearanceColor;
    }
    public String getAppearanceColor() {
        return appearanceColor;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public String getVin() {
        return vin;
    }
    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }
    public Date getProductDate() {
        return productDate;
    }
    public void setInboundDate(Date inboundDate) {
        this.inboundDate = inboundDate;
    }
    public Date getInboundDate() {
        return inboundDate;
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
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
}
