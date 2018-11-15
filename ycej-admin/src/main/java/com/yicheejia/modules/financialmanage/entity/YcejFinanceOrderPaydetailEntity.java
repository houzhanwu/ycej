package com.yicheejia.modules.financialmanage.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 费用明细表
 * 
 * @author cn
 * @email 
 * @date 2018-07-08 10:29:31
 */
@TableName("ycej_order_paydetail")
public class YcejFinanceOrderPaydetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String orderPayId;
	/**
	 * 订单编号
	 */
	@TableField(exist = false)
	private String orderNo;
	/**
	 * 订单id
	 */
	@TableField(exist = false)
	private String orderId;
	/**
	 * 门店
	 */
	@TableField(exist = false)
	private String shop;
	/**
	 * 客户姓名
	 */
	@TableField(exist = false)
	private String customer;
	/**
	 * 联系方式
	 */
	@TableField(exist = false)
	private String telephone;
	/**
	 * 车型
	 */
	@TableField(exist = false)
	private String carModel;
	/**
	 * 购买方式
	 */
	@TableField(exist = false)
	private String payWay;
	/**
	 * 支付类型
	 */
	private String payType;
	/**
	 * 交易金额金额
	 */
	private BigDecimal payAmount;
	/**
	 * 支付日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payDate;
	/**
	 * 支付方式
	 */
	private String payMethod;
	/**
	 * 支付状态
	 */
	private String payStatu;
	/**
	 * 支付卡号
	 */
	@TableField(exist = false)
	private String paycardno;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private String insert_id;
	/**
	 * 操作人
	 */
	private String update_id;
	/**
	 * 
	 */
	private String remark;
	/**
	 * 系统参考号
	 */
	private String sys_ref_no;
	/**
	 * 车架号vin
	 */
	private String vin;
	/*
	 * 上牌费
	 */
	@TableField(exist = false)
	private BigDecimal carLicenseFee;
	/*
	 * 续保押金
	 */
	@TableField(exist = false)
	private BigDecimal renewDeposit;
	/*
	 * 手续费
	 */
	@TableField(exist = false)
	private BigDecimal charges;
	/*
	 * 优壹车保证金
	 */
	@TableField(exist = false)
	private BigDecimal carDeposit;
	/*
	 * 保险
	 */
	@TableField(exist = false)
	private BigDecimal insurance;
	/*
	 * 优惠金额
	 */
	@TableField(exist = false)
	private BigDecimal discount;
	/*
	 * 实收金额
	 */
	@TableField(exist = false)
	private BigDecimal amount;
	/*
	 * 金额
	 */
	@TableField(exist = false)
	private BigDecimal amount2;
	/*
	 * 车款
	 */
	@TableField(exist = false)
	private BigDecimal amount3;
	
	
	public String getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(String insert_id) {
		this.insert_id = insert_id;
	}
	/**
	 * 设置：主键
	 */
	public void setOrderPayId(String orderPayId) {
		this.orderPayId = orderPayId;
	}
	/**
	 * 获取：主键
	 */
	public String getOrderPayId() {
		return orderPayId;
	}
	/**
	 * 设置：订单主键
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单主键
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：支付类别
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付类别
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * 设置：支付金额
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	/**
	 * 获取：支付金额
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	/**
	 * 设置：支付日期
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	/**
	 * 获取：支付日期
	 */
	public Date getPayDate() {
		return payDate;
	}
	/**
	 * 设置：支付方式
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	/**
	 * 获取：支付方式
	 */
	public String getPayMethod() {
		return payMethod;
	}
	/**
	 * 设置：支付状态
	 */
	public void setPayStatu(String payStatu) {
		this.payStatu = payStatu;
	}
	/**
	 * 获取：支付状态
	 */
	public String getPayStatu() {
		return payStatu;
	}
	/**
	 * 设置：
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
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
	public String getUpdate_id() {
		return update_id;
	}
	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public BigDecimal getCarLicenseFee() {
		return carLicenseFee;
	}
	public void setCarLicenseFee(BigDecimal carLicenseFee) {
		this.carLicenseFee = carLicenseFee;
	}
	public BigDecimal getRenewDeposit() {
		return renewDeposit;
	}
	public void setRenewDeposit(BigDecimal renewDeposit) {
		this.renewDeposit = renewDeposit;
	}
	public BigDecimal getCharges() {
		return charges;
	}
	public void setCharges(BigDecimal charges) {
		this.charges = charges;
	}
	public BigDecimal getCarDeposit() {
		return carDeposit;
	}
	public void setCarDeposit(BigDecimal carDeposit) {
		this.carDeposit = carDeposit;
	}
	public BigDecimal getInsurance() {
		return insurance;
	}
	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
