package com.yicheejia.modules.shop.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 意向客户表
 *
 * @author cn
 * @email
 * @date 2018-07-18 14:03:20
 */
public class IntentionCustomerBean {
    @Excel(name = "客户id")
    private Integer customerId;
    @Excel(name = "门店id")
    private Integer shopId;
    @Excel(name = "门店编号")
    private String shopNo;
    @Excel(name = "客户姓名")
    private String customerName;
    @Excel(name = "0：女，1：男")
    private Integer sex;
    @Excel(name = "客户手机号")
    private String customerTel;
    @Excel(name = "联系地址")
    private String address;
    @Excel(name = "意向品牌")
    private String carBrand;
    @Excel(name = "意向车系")
    private String carSeries;
    @Excel(name = "意向车型")
    private String carType;
    @Excel(name = "意向等级：H（一周内）、A（一个月内）、B（三个月内）；C（半年内）")
    private String level;
    @Excel(name = "其他联系人")
    private String otherPerson;
    @Excel(name = "其他联系方式")
    private String otherTel;
    @Excel(name = "是否成交:0,未成交 1，已成交")
    private Integer dealed;
    @Excel(name = "到店日期")
    private Date arriveTime;
    @Excel(name = "购买方式：1，全款，2，分期，3，优壹车")
    private Integer payWay;
    @Excel(name = "创建人")
    private String insUser;
    @Excel(name = "创建时间")
    private Date insTime;
    @Excel(name = "修改人")
    private String updUser;
    @Excel(name = "修改时间")
    private Date updTime;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "最后跟踪日期")
    private Date trackDate;
    @Excel(name = "厂商")
    private String carFirm;

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public Integer getShopId() {
        return shopId;
    }
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }
    public String getShopNo() {
        return shopNo;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getSex() {
        return sex;
    }
    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }
    public String getCustomerTel() {
        return customerTel;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
    public String getCarBrand() {
        return carBrand;
    }
    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }
    public String getCarSeries() {
        return carSeries;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getCarType() {
        return carType;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getLevel() {
        return level;
    }
    public void setOtherPerson(String otherPerson) {
        this.otherPerson = otherPerson;
    }
    public String getOtherPerson() {
        return otherPerson;
    }
    public void setOtherTel(String otherTel) {
        this.otherTel = otherTel;
    }
    public String getOtherTel() {
        return otherTel;
    }
    public void setDealed(Integer dealed) {
        this.dealed = dealed;
    }
    public Integer getDealed() {
        return dealed;
    }
    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }
    public Date getArriveTime() {
        return arriveTime;
    }
    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }
    public Integer getPayWay() {
        return payWay;
    }
    public void setInsUser(String insUser) {
        this.insUser = insUser;
    }
    public String getInsUser() {
        return insUser;
    }
    public void setInsTime(Date insTime) {
        this.insTime = insTime;
    }
    public Date getInsTime() {
        return insTime;
    }
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }
    public String getUpdUser() {
        return updUser;
    }
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
    public Date getUpdTime() {
        return updTime;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }

    public Date getTrackDate() {
        return trackDate;
    }

    public void setTrackDate(Date trackDate) {
        this.trackDate = trackDate;
    }

    public String getCarFirm() {
        return carFirm;
    }

    public void setCarFirm(String carFirm) {
        this.carFirm = carFirm;
    }
}
