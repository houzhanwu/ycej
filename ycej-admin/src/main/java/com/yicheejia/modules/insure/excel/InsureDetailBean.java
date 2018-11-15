package com.yicheejia.modules.insure.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 保险明细表
 *
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
public class InsureDetailBean {
    @Excel(name = "主键")
    private Integer insureDetailId;
    @Excel(name = "保险主键")
    private Integer insureId;
    @Excel(name = "交强险保单号")
    private String strongNo;
    @Excel(name = "交强险金额")
    private BigDecimal strongAmount;
    @Excel(name = "车船税")
    private BigDecimal vehicleAmount;
    @Excel(name = "交强起期")
    private Date strongBegdate;
    @Excel(name = "交强止期")
    private Date strongEndate;
    @Excel(name = "商业险保单号")
    private String commercialNo;
    @Excel(name = "商业险金额")
    private BigDecimal commercialAmount;
    @Excel(name = "商业起期")
    private Date commercialBegdate;
    @Excel(name = "商业止期")
    private Date commercialEndate;
    @Excel(name = "车损金额")
    private BigDecimal carLoss;
    @Excel(name = "免赔金额")
    private BigDecimal deductibles;
    @Excel(name = "盗抢")
    private BigDecimal robbery;
    @Excel(name = "三责险")
    private BigDecimal threeLiability;
    @Excel(name = "驾驶人责任险")
    private BigDecimal driveAmount;
    @Excel(name = "乘客责任险")
    private BigDecimal passengerAmount;
    @Excel(name = "不计免赔-车损")
    private BigDecimal noCar;
    @Excel(name = "不计免赔-三责")
    private BigDecimal noThree;
    @Excel(name = "不计免赔-驾驶人")
    private BigDecimal noDrive;
    @Excel(name = "不计免赔-乘客")
    private BigDecimal noPassenger;
    @Excel(name = "无法找到第三方特约险")
    private BigDecimal notThird;
    @Excel(name = "划痕险")
    private BigDecimal scratching;
    @Excel(name = "玻璃破碎险")
    private BigDecimal glass;
    @Excel(name = "1国产，2进口")
    private Integer glassIsout;
    @Excel(name = "自燃险")
    private BigDecimal pyrophoricity;
    @Excel(name = "不计免赔-盗抢")
    private BigDecimal noRobbery;
    @Excel(name = "不计免赔-划痕")
    private BigDecimal noScratching;
    @Excel(name = "不计免赔-自燃")
    private BigDecimal noPyrophoricity;
    @Excel(name = "不计免赔-发动机涉水")
    private BigDecimal noWading;
    @Excel(name = "")
    private String insertId;
    @Excel(name = "")
    private Date insertTime;
    @Excel(name = "")
    private String updateId;
    @Excel(name = "")
    private Date updateTime;

    public void setInsureDetailId(Integer insureDetailId) {
        this.insureDetailId = insureDetailId;
    }
    public Integer getInsureDetailId() {
        return insureDetailId;
    }
    public void setInsureId(Integer insureId) {
        this.insureId = insureId;
    }
    public Integer getInsureId() {
        return insureId;
    }
    public void setStrongNo(String strongNo) {
        this.strongNo = strongNo;
    }
    public String getStrongNo() {
        return strongNo;
    }
    public void setStrongAmount(BigDecimal strongAmount) {
        this.strongAmount = strongAmount;
    }
    public BigDecimal getStrongAmount() {
        return strongAmount;
    }
    public void setVehicleAmount(BigDecimal vehicleAmount) {
        this.vehicleAmount = vehicleAmount;
    }
    public BigDecimal getVehicleAmount() {
        return vehicleAmount;
    }
    public void setStrongBegdate(Date strongBegdate) {
        this.strongBegdate = strongBegdate;
    }
    public Date getStrongBegdate() {
        return strongBegdate;
    }
    public void setStrongEndate(Date strongEndate) {
        this.strongEndate = strongEndate;
    }
    public Date getStrongEndate() {
        return strongEndate;
    }
    public void setCommercialNo(String commercialNo) {
        this.commercialNo = commercialNo;
    }
    public String getCommercialNo() {
        return commercialNo;
    }
    public void setCommercialAmount(BigDecimal commercialAmount) {
        this.commercialAmount = commercialAmount;
    }
    public BigDecimal getCommercialAmount() {
        return commercialAmount;
    }
    public void setCommercialBegdate(Date commercialBegdate) {
        this.commercialBegdate = commercialBegdate;
    }
    public Date getCommercialBegdate() {
        return commercialBegdate;
    }
    public void setCommercialEndate(Date commercialEndate) {
        this.commercialEndate = commercialEndate;
    }
    public Date getCommercialEndate() {
        return commercialEndate;
    }
    public void setCarLoss(BigDecimal carLoss) {
        this.carLoss = carLoss;
    }
    public BigDecimal getCarLoss() {
        return carLoss;
    }
    public void setDeductibles(BigDecimal deductibles) {
        this.deductibles = deductibles;
    }
    public BigDecimal getDeductibles() {
        return deductibles;
    }
    public void setRobbery(BigDecimal robbery) {
        this.robbery = robbery;
    }
    public BigDecimal getRobbery() {
        return robbery;
    }
    public void setThreeLiability(BigDecimal threeLiability) {
        this.threeLiability = threeLiability;
    }
    public BigDecimal getThreeLiability() {
        return threeLiability;
    }
    public void setDriveAmount(BigDecimal driveAmount) {
        this.driveAmount = driveAmount;
    }
    public BigDecimal getDriveAmount() {
        return driveAmount;
    }
    public void setPassengerAmount(BigDecimal passengerAmount) {
        this.passengerAmount = passengerAmount;
    }
    public BigDecimal getPassengerAmount() {
        return passengerAmount;
    }
    public void setNoCar(BigDecimal noCar) {
        this.noCar = noCar;
    }
    public BigDecimal getNoCar() {
        return noCar;
    }
    public void setNoThree(BigDecimal noThree) {
        this.noThree = noThree;
    }
    public BigDecimal getNoThree() {
        return noThree;
    }
    public void setNoDrive(BigDecimal noDrive) {
        this.noDrive = noDrive;
    }
    public BigDecimal getNoDrive() {
        return noDrive;
    }
    public void setNoPassenger(BigDecimal noPassenger) {
        this.noPassenger = noPassenger;
    }
    public BigDecimal getNoPassenger() {
        return noPassenger;
    }
    public void setNotThird(BigDecimal notThird) {
        this.notThird = notThird;
    }
    public BigDecimal getNotThird() {
        return notThird;
    }
    public void setScratching(BigDecimal scratching) {
        this.scratching = scratching;
    }
    public BigDecimal getScratching() {
        return scratching;
    }
    public void setGlass(BigDecimal glass) {
        this.glass = glass;
    }
    public BigDecimal getGlass() {
        return glass;
    }
    public void setGlassIsout(Integer glassIsout) {
        this.glassIsout = glassIsout;
    }
    public Integer getGlassIsout() {
        return glassIsout;
    }
    public void setPyrophoricity(BigDecimal pyrophoricity) {
        this.pyrophoricity = pyrophoricity;
    }
    public BigDecimal getPyrophoricity() {
        return pyrophoricity;
    }
    public void setNoRobbery(BigDecimal noRobbery) {
        this.noRobbery = noRobbery;
    }
    public BigDecimal getNoRobbery() {
        return noRobbery;
    }
    public void setNoScratching(BigDecimal noScratching) {
        this.noScratching = noScratching;
    }
    public BigDecimal getNoScratching() {
        return noScratching;
    }
    public void setNoPyrophoricity(BigDecimal noPyrophoricity) {
        this.noPyrophoricity = noPyrophoricity;
    }
    public BigDecimal getNoPyrophoricity() {
        return noPyrophoricity;
    }
    public void setNoWading(BigDecimal noWading) {
        this.noWading = noWading;
    }
    public BigDecimal getNoWading() {
        return noWading;
    }
    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }
    public String getInsertId() {
        return insertId;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    public Date getInsertTime() {
        return insertTime;
    }
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
    public String getUpdateId() {
        return updateId;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
}
