package com.yicheejia.modules.order.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付明细表
 *
 * @author fkm
 * @email 
 * @date 2018-07-09 11:16:31
 */
public class OrderPaydetailBean {
    @Excel(name = "主键")
    private Long orderPayId;
    @Excel(name = "订单主键")
    private String orderNo;
    @Excel(name = "支付类别")
    private Integer payType;
    @Excel(name = "已支付金额")
    private BigDecimal payAmount;
    @Excel(name = "需支付金额")
    private BigDecimal amount;
    @Excel(name = "支付日期")
    private Date payDate;
    @Excel(name = "支付方式，微信支付宝")
    private Integer payMethod;
    @Excel(name = "支付状态")
    private Integer payStatu;
    @Excel(name = "支付卡号")
    private String paycardno;
    @Excel(name = "")
    private Date insertTime;
    @Excel(name = "")
    private Date updateTime;
    @Excel(name = "操作人")
    private String insertId;
    @Excel(name = "")
    private String remark;

    public void setOrderPayId(Long orderPayId) {
        this.orderPayId = orderPayId;
    }
    public Long getOrderPayId() {
        return orderPayId;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    public Integer getPayType() {
        return payType;
    }
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
    public BigDecimal getPayAmount() {
        return payAmount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
    public Date getPayDate() {
        return payDate;
    }
    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }
    public Integer getPayMethod() {
        return payMethod;
    }
    public void setPayStatu(Integer payStatu) {
        this.payStatu = payStatu;
    }
    public Integer getPayStatu() {
        return payStatu;
    }
    public void setPaycardno(String paycardno) {
        this.paycardno = paycardno;
    }
    public String getPaycardno() {
        return paycardno;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    public Date getInsertTime() {
        return insertTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }
    public String getInsertId() {
        return insertId;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
}
