package com.yicheejia.modules.order.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动代金券信息表
 *
 * @author 
 * @email 
 * @date 2018-10-26 13:44:25
 */
public class SalePromotionBean {
    @Excel(name = "ID(主键)")
    private Long saleId;
    @Excel(name = "订单编号")
    private String saleCode;
    @Excel(name = "购买人姓名")
    private String saleContact;
    @Excel(name = "购买人手机号")
    private String saleMobile;
    @Excel(name = "地址")
    private String saleAdd;
    @Excel(name = "商品总金额")
    private BigDecimal goodsPrice;
    @Excel(name = "抵扣金额")
    private BigDecimal deductionPrice;
    @Excel(name = "有效期止")
    private Date endDate;
    @Excel(name = "支付方式")
    private String payType;
    @Excel(name = "支付金额")
    private BigDecimal payMoney;
    @Excel(name = "购买方式")
    private String buyType;
    @Excel(name = "支付流水号")
    private String payNumber;
    @Excel(name = "名称")
    private String saleName;
    @Excel(name = "买家留言")
    private String saleComment;
    @Excel(name = "添加人")
    private String insertId;
    @Excel(name = "添加时间")
    private Date insertTime;

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
    public Long getSaleId() {
        return saleId;
    }
    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode;
    }
    public String getSaleCode() {
        return saleCode;
    }
    public void setSaleContact(String saleContact) {
        this.saleContact = saleContact;
    }
    public String getSaleContact() {
        return saleContact;
    }
    public void setSaleMobile(String saleMobile) {
        this.saleMobile = saleMobile;
    }
    public String getSaleMobile() {
        return saleMobile;
    }
    public void setSaleAdd(String saleAdd) {
        this.saleAdd = saleAdd;
    }
    public String getSaleAdd() {
        return saleAdd;
    }
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }
    public void setDeductionPrice(BigDecimal deductionPrice) {
        this.deductionPrice = deductionPrice;
    }
    public BigDecimal getDeductionPrice() {
        return deductionPrice;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getPayType() {
        return payType;
    }
    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }
    public BigDecimal getPayMoney() {
        return payMoney;
    }
    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }
    public String getBuyType() {
        return buyType;
    }
    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }
    public String getPayNumber() {
        return payNumber;
    }
    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }
    public String getSaleName() {
        return saleName;
    }
    public void setSaleComment(String saleComment) {
        this.saleComment = saleComment;
    }
    public String getSaleComment() {
        return saleComment;
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
}
