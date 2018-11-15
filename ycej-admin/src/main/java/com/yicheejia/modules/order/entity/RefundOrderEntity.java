package com.yicheejia.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 退款信息表
 * 
 * @author fkm
 * @email 
 * @date 2018-09-26 15:02:44
 */
@TableName("ycej_refund_order")
public class RefundOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 订单id
	 */
	private String orderId;
	/**
	 * 退款账号
	 */
	private String accountNumber;
	/**
	 * 退款银行id
	 */
	private String accountBank;
	/**
	 * 退款银行
	 */
	private String accountBankname;
	/**
	 * 支付方式
	 */
	private String payMethod;
	/**
	 * 退款金额
	 */
	private BigDecimal payAmount;
	/**
	 * 退款时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date refundDate;
	/**
	 * 退款人
	 */
	private String refundId;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 
	 */
	private String insertId;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private String updateId;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：订单id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 设置：退款账号
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * 获取：退款账号
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * 设置：退款银行id
	 */
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	/**
	 * 获取：退款银行id
	 */
	public String getAccountBank() {
		return accountBank;
	}
	/**
	 * 设置：退款银行
	 */
	public void setAccountBankname(String accountBankname) {
		this.accountBankname = accountBankname;
	}
	/**
	 * 获取：退款银行
	 */
	public String getAccountBankname() {
		return accountBankname;
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
	 * 设置：退款金额
	 */
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	/**
	 * 获取：退款金额
	 */
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	/**
	 * 设置：退款时间
	 */
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	/**
	 * 获取：退款时间
	 */
	public Date getRefundDate() {
		return refundDate;
	}
	/**
	 * 设置：退款人
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	/**
	 * 获取：退款人
	 */
	public String getRefundId() {
		return refundId;
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
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	/**
	 * 获取：
	 */
	public String getInsertId() {
		return insertId;
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
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	/**
	 * 获取：
	 */
	public String getUpdateId() {
		return updateId;
	}
	@TableField(exist = false)
	private String remark;//备注
	@TableField(exist = false)
	private String status;//订单状态
	@TableField(exist = false)
	private String userName;//
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
