package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 调拨记录表
 *
 * @author lw
 * @email 
 * @date 2018-07-18 16:37:00
 */
public class AllotBean {
    @Excel(name = "调拨ID(主键)")
    private Integer allotId;
    @Excel(name = "VIN")
    private String vin;
    @Excel(name = "调拨日期")
    private Date allotDate;
    @Excel(name = "调拨人")
    private String allotName;
    @Excel(name = "调拨人手机号 ")
    private Integer allotMobile;
    @Excel(name = "调出门店ID")
    private Integer allotFrom;
    @Excel(name = "调入门店ID")
    private Integer allotTo;
    @Excel(name = "随车物品")
    private String allotWith;
    @Excel(name = "车况")
    private String carCondition;
    @Excel(name = "备注")
    private String comment;
    @Excel(name = "合格证去向（0：随车；1：邮寄）")
    private Integer certificate;
    @Excel(name = "邮寄日期")
    private Date mailDate;
    @Excel(name = "快递公司")
    private String expressCompany;
    @Excel(name = "快递单号")
    private String courierNumber;

    public void setAllotId(Integer allotId) {
        this.allotId = allotId;
    }
    public Integer getAllotId() {
        return allotId;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public String getVin() {
        return vin;
    }
    public void setAllotDate(Date allotDate) {
        this.allotDate = allotDate;
    }
    public Date getAllotDate() {
        return allotDate;
    }
    public void setAllotName(String allotName) {
        this.allotName = allotName;
    }
    public String getAllotName() {
        return allotName;
    }
    public void setAllotMobile(Integer allotMobile) {
        this.allotMobile = allotMobile;
    }
    public Integer getAllotMobile() {
        return allotMobile;
    }
    public void setAllotFrom(Integer allotFrom) {
        this.allotFrom = allotFrom;
    }
    public Integer getAllotFrom() {
        return allotFrom;
    }
    public void setAllotTo(Integer allotTo) {
        this.allotTo = allotTo;
    }
    public Integer getAllotTo() {
        return allotTo;
    }
    public void setAllotWith(String allotWith) {
        this.allotWith = allotWith;
    }
    public String getAllotWith() {
        return allotWith;
    }
    public void setCarCondition(String carCondition) {
        this.carCondition = carCondition;
    }
    public String getCarCondition() {
        return carCondition;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }
    public void setCertificate(Integer certificate) {
        this.certificate = certificate;
    }
    public Integer getCertificate() {
        return certificate;
    }
    public void setMailDate(Date mailDate) {
        this.mailDate = mailDate;
    }
    public Date getMailDate() {
        return mailDate;
    }
    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }
    public String getExpressCompany() {
        return expressCompany;
    }
    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }
    public String getCourierNumber() {
        return courierNumber;
    }
}
