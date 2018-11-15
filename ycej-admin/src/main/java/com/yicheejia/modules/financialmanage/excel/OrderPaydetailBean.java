package com.yicheejia.modules.financialmanage.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 费用明细表
 *
 * @author cn
 * @email 
 * @date 2018-07-08 10:29:31
 */
public class OrderPaydetailBean {
    @Excel(name = "订单编号")
    private String orderNo;
    @Excel(name = "门店")
    private String shop;
    @Excel(name = "客户姓名")
    private String customer;
    @Excel(name = "联系方式")
    private String telephone;
    @Excel(name = "车型")
    private String carModel;
    @Excel(name = "VIN码(车架号)")
    private String vin;
    @Excel(name = "购买方式")
    private String payWay;
    @Excel(name = "支付类型")
    private String payType;
    @Excel(name = "金额")
    private BigDecimal amount2;
    @Excel(name = "交易金额")
    private BigDecimal payAmount;
    @Excel(name = "实收金额")
    private BigDecimal amount;
    @Excel(name = "优惠金额")
    private BigDecimal discount;
    @Excel(name = "支付日期",format="yyyy-MM-dd")
    private Date payDate;
    @Excel(name = "支付方式")
    private String payMethod;
    @Excel(name = "支付卡号")
    private String paycardno;
    @Excel(name = "车款")
    private BigDecimal amount3;
    @Excel(name = "保险")
    private BigDecimal insurance;
    @Excel(name = "续保押金")
    private BigDecimal carDeposit;
    @Excel(name = "优壹车保证金")
    private BigDecimal renewDeposit;
    @Excel(name = "上牌费")
    private BigDecimal carLicenseFee;
    @Excel(name = "手续费")
    private BigDecimal charges;
    @Excel(name = "系统参考号")
    private String sys_ref_no;
    @Excel(name = "操作时间",format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @Excel(name = "操作人")
    private String update_id;
    @Excel(name = "支付状态")
    private String payStatu;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getPayStatu() {
		return payStatu;
	}
	public void setPayStatu(String payStatu) {
		this.payStatu = payStatu;
	}
	public String getPaycardno() {
		return paycardno;
	}
	public void setPaycardno(String paycardno) {
		this.paycardno = paycardno;
	}
	public String getSys_ref_no() {
		return sys_ref_no;
	}
	public void setSys_ref_no(String sys_ref_no) {
		this.sys_ref_no = sys_ref_no;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdate_id() {
		return update_id;
	}
	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}
	public BigDecimal getInsurance() {
		return insurance;
	}
	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}
	public BigDecimal getCarDeposit() {
		return carDeposit;
	}
	public void setCarDeposit(BigDecimal carDeposit) {
		this.carDeposit = carDeposit;
	}
	public BigDecimal getCarLicenseFee() {
		return carLicenseFee;
	}
	public void setCarLicenseFee(BigDecimal carLicenseFee) {
		this.carLicenseFee = carLicenseFee;
	}
	public BigDecimal getCharges() {
		return charges;
	}
	public void setCharges(BigDecimal charges) {
		this.charges = charges;
	}
	public BigDecimal getRenewDeposit() {
		return renewDeposit;
	}
	public void setRenewDeposit(BigDecimal renewDeposit) {
		this.renewDeposit = renewDeposit;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getAmount2() {
		return amount2;
	}
	public void setAmount2(BigDecimal amount2) {
		this.amount2 = amount2;
	}
	public BigDecimal getAmount3() {
		return amount3;
	}
	public void setAmount3(BigDecimal amount3) {
		this.amount3 = amount3;
	}
	
    
    
}
