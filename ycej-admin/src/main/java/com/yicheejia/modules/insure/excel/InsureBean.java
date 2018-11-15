package com.yicheejia.modules.insure.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 保险
 *
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
public class InsureBean {
    @Excel(name = "主键")
    private Integer insureId;
    @Excel(name = "订单号")
    private Integer orderId;
    @Excel(name = "保险公司编号")
    private Integer companyId;
    @Excel(name = "保险金额")
    private BigDecimal premium;
    @Excel(name = "理赔金额")
    private BigDecimal claims;
    @Excel(name = "出保时间")
    private Date insureTime;
    @Excel(name = "出保次数")
    private Integer insureNum;
    @Excel(name = "出险情况")
    private String insure;
    @Excel(name = "")
    private String insertId;
    @Excel(name = "")
    private Date insertTime;
    @Excel(name = "")
    private String updateId;
    @Excel(name = "")
    private Date updateTime;

    public void setInsureId(Integer insureId) {
        this.insureId = insureId;
    }
    public Integer getInsureId() {
        return insureId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }
    public BigDecimal getPremium() {
        return premium;
    }
    public void setClaims(BigDecimal claims) {
        this.claims = claims;
    }
    public BigDecimal getClaims() {
        return claims;
    }
    public void setInsureTime(Date insureTime) {
        this.insureTime = insureTime;
    }
    public Date getInsureTime() {
        return insureTime;
    }
    public void setInsureNum(Integer insureNum) {
        this.insureNum = insureNum;
    }
    public Integer getInsureNum() {
        return insureNum;
    }
    public void setInsure(String insure) {
        this.insure = insure;
    }
    public String getInsure() {
        return insure;
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
