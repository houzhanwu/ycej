package com.yicheejia.modules.financialmanage.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单费用明细表
 * 
 * @author cn
 * @email 
 * @date 2018-07-10 08:52:57
 */
@TableName("ycej_order_detail")
public class YcejFinanceOrderDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String orderDetailId;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 门店
	 */
	private String shop;
	/**
	 * 客户姓名
	 */
	private String customer;
	/**
	 * 联系方式
	 */
	private String telephone;
	/**
	 * 车型
	 */
	private String carModel;
	/**
	 * 购买方式
	 */
	private String payWay;
	/**
	 * 支付类别(pay_type)
	 */
	private String payType;
	/**
	 * 订单金额
	 */
	private BigDecimal amount;
	/**
	 * 交易金额
	 */
	private BigDecimal payAmount;
	/**
	 * 交易方式
	 */
	private String payMethod;
	/**
	 * 剩余金额
	 */
	private BigDecimal balance;
	/**
	 * 付款日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date payDate;
	

	/**
	 * 设置：主键
	 */
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	/**
	 * 获取：主键
	 */
	public String getOrderDetailId() {
		return orderDetailId;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单编号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：支付类别(pay_type)
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付类别(pay_type)
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * 设置：已支付金额
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	/**
	 * 获取：已支付金额
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	/**
	 * 设置：需支付金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：需支付金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：付款日期
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	/**
	 * 获取：付款日期
	 */
	public Date getPayDate() {
		return payDate;
	}
	/**
	 * 设置：付款方式
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	/**
	 * 获取：付款方式
	 */
	public String getPayMethod() {
		return payMethod;
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
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	
}
