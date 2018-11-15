package com.yicheejia.modules.shop.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 成交客户
 *
 * @author cn
 * @email 
 * @date 2018-07-11 14:00:11
 */
public class CustomerBean {
    @Excel(name = "客户id")
    private Integer customerId;
    @Excel(name = "客户姓名")
    private String customerName;
    @Excel(name = "客户手机号")
    private String customerTel;
    @Excel(name = "客户身份证号")
    private String cardNo;
    @Excel(name = "购买车型")
    private String carType;
    @Excel(name = "购买日期",format = "yyyy-MM-dd")
    private Date buyDate;
    @Excel(name = "车辆颜色")
    private String carColor;
    @Excel(name = "购买方式")
    private Integer buyManner;
    @Excel(name = "交车日期",format = "yyyy-MM-dd")
    private Date handoverTime;
    @Excel(name = "交车门店")
    private String handoverShop;
    @Excel(name = "保险金额")
    private BigDecimal insuranceSum;
    @Excel(name = "保险生效日期",format = "yyyy-MM-dd")
    private Date insuranceDate;
    @Excel(name = "续保押金")
    private BigDecimal insuranceDiposit;
    @Excel(name = "分期年限")
    private Integer instalmentYear;
    @Excel(name = "创建时间",format = "yyyy-MM-dd HH:mm:ss")
    private Date insTime;
    @Excel(name = "创建人")
    private String insUser;
    @Excel(name = "修改时间",format = "yyyy-MM-dd HH:mm:ss")
    private Date updTime;
    @Excel(name = "修改人")
    private String updUser;
    @Excel(name = "备注")
    private String remark;


    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }
    public String getCustomerTel() {
        return customerTel;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getCarType() {
        return carType;
    }
    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
    public Date getBuyDate() {
        return buyDate;
    }
    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }
    public String getCarColor() {
        return carColor;
    }
    public void setBuyManner(Integer buyManner) {
        this.buyManner = buyManner;
    }
    public Integer getBuyManner() {
        return buyManner;
    }
    public void setHandoverTime(Date handoverTime) {
        this.handoverTime = handoverTime;
    }
    public Date getHandoverTime() {
        return handoverTime;
    }
    public void setHandoverShop(String handoverShop) {
        this.handoverShop = handoverShop;
    }
    public String getHandoverShop() {
        return handoverShop;
    }
    public void setInsuranceSum(BigDecimal insuranceSum) {
        this.insuranceSum = insuranceSum;
    }
    public BigDecimal getInsuranceSum() {
        return insuranceSum;
    }
    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }
    public Date getInsuranceDate() {
        return insuranceDate;
    }
    public void setInsuranceDiposit(BigDecimal insuranceDiposit) {
        this.insuranceDiposit = insuranceDiposit;
    }
    public BigDecimal getInsuranceDiposit() {
        return insuranceDiposit;
    }
    public void setInstalmentYear(Integer instalmentYear) {
        this.instalmentYear = instalmentYear;
    }
    public Integer getInstalmentYear() {
        return instalmentYear;
    }
    public void setInsTime(Date insTime) {
        this.insTime = insTime;
    }
    public Date getInsTime() {
        return insTime;
    }
    public void setInsUser(String insUser) {
        this.insUser = insUser;
    }
    public String getInsUser() {
        return insUser;
    }
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
    public Date getUpdTime() {
        return updTime;
    }
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }
    public String getUpdUser() {
        return updUser;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }

}
