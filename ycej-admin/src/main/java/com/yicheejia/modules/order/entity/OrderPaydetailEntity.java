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
 * 支付明细表
 * 
 * @author fkm
 * @email 
 * @date 2018-07-09 11:16:31
 */
@TableName("ycej_order_paydetail")
public class OrderPaydetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String orderPayId;
	/**
	 * 订单主键
	 */
	private String orderNo;
	/**
	 * 支付类别，
	 */
	private String payType;
	/**
	 * 已支付金额
	 */
	private BigDecimal payAmount;
	/**
	 * 需支付金额
	 */
	private BigDecimal amount;
	/**
	 * 支付日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date payDate;
	/**
	 * 支付方式，微信支付宝
	 */
	private String payMethod;
	/**
	 * 支付状态
	 */
	private Integer payStatu;
	/**
	 * 支付卡号
	 */
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
	 * 操作人
	 */
	private String insertId;
	/**
	 * 
	 */
	private String remark;
	
	private String sysRefNo;
	
	private String updateId;
	
	public String getSysRefNo() {
		return sysRefNo;
	}
	public void setSysRefNo(String sysRefNo) {
		this.sysRefNo = sysRefNo;
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
	 * 设置：支付类别，
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付类别，
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
	 * 设置：支付方式，微信支付宝
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	/**
	 * 获取：支付方式，微信支付宝
	 */
	public String getPayMethod() {
		return payMethod;
	}
	/**
	 * 设置：支付状态
	 */
	public void setPayStatu(Integer payStatu) {
		this.payStatu = payStatu;
	}
	/**
	 * 获取：支付状态
	 */
	public Integer getPayStatu() {
		return payStatu;
	}
	/**
	 * 设置：支付卡号
	 */
	public void setPaycardno(String paycardno) {
		this.paycardno = paycardno;
	}
	/**
	 * 获取：支付卡号
	 */
	public String getPaycardno() {
		return paycardno;
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
	 * 设置：操作人
	 */
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	/**
	 * 获取：操作人
	 */
	public String getInsertId() {
		return insertId;
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
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
	@TableField(exist = false)
	private String shopNo;
	@TableField(exist = false)
	private String shopName;

	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
}
