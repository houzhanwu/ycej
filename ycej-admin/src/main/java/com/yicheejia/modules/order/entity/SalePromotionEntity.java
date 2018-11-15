package com.yicheejia.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 活动代金券信息表
 * 
 * @author 
 * @email 
 * @date 2018-10-26 13:44:25
 */
@TableName("ycej_sale_promotion")
public class SalePromotionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID(主键)
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String saleId;
	/**
	 * 订单编号
	 */
	private String saleCode;
	/**
	 * 购买人姓名
	 */
	private String saleContact;
	/**
	 * 购买人手机号
	 */
	private String saleMobile;
	/**
	 * 地址
	 */
	private String saleAdd;
	/**
	 * 商品总金额
	 */
	private BigDecimal goodsPrice;
	/**
	 * 抵扣金额
	 */
	private BigDecimal deductionPrice;
	/**
	 * 有效期止
	 */
	private Date endDate;
	/**
	 * 支付方式
	 */
	private String payType;
	/**
	 * 支付金额
	 */
	private BigDecimal payMoney;
	/**
	 * 购买方式
	 */
	private String buyType;
	/**
	 * 支付流水号
	 */
	private String payNumber;
	/**
	 * 名称
	 */
	private String saleName;
	/**
	 * 买家留言
	 */
	private String saleComment;
	/**
	 * 添加人
	 */
	private String insertId;
	/**
	 * 添加时间
	 */
	private Date insertTime;
	/**
	 * 是否使用：0未使用；1已使用
	 */
	private String isUsed;
	/**
	 * 订单ID
	 */
	private String orderId;
	
	
	@TableField(exist = false)
	private String zpId;
	
	public String getZpId() {
		return zpId;
	}
	public void setZpId(String zpId) {
		this.zpId = zpId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	/**
	 * 设置：ID(主键)
	 */
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	/**
	 * 获取：ID(主键)
	 */
	public String getSaleId() {
		return saleId;
	}
	/**
	 * 设置：订单编号
	 */
	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}
	/**
	 * 获取：订单编号
	 */
	public String getSaleCode() {
		return saleCode;
	}
	/**
	 * 设置：购买人姓名
	 */
	public void setSaleContact(String saleContact) {
		this.saleContact = saleContact;
	}
	/**
	 * 获取：购买人姓名
	 */
	public String getSaleContact() {
		return saleContact;
	}
	/**
	 * 设置：购买人手机号
	 */
	public void setSaleMobile(String saleMobile) {
		this.saleMobile = saleMobile;
	}
	/**
	 * 获取：购买人手机号
	 */
	public String getSaleMobile() {
		return saleMobile;
	}
	/**
	 * 设置：地址
	 */
	public void setSaleAdd(String saleAdd) {
		this.saleAdd = saleAdd;
	}
	/**
	 * 获取：地址
	 */
	public String getSaleAdd() {
		return saleAdd;
	}
	/**
	 * 设置：商品总金额
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	/**
	 * 获取：商品总金额
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	/**
	 * 设置：抵扣金额
	 */
	public void setDeductionPrice(BigDecimal deductionPrice) {
		this.deductionPrice = deductionPrice;
	}
	/**
	 * 获取：抵扣金额
	 */
	public BigDecimal getDeductionPrice() {
		return deductionPrice;
	}
	/**
	 * 设置：有效期止
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：有效期止
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：支付方式
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付方式
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * 设置：支付金额
	 */
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	/**
	 * 获取：支付金额
	 */
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	/**
	 * 设置：购买方式
	 */
	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}
	/**
	 * 获取：购买方式
	 */
	public String getBuyType() {
		return buyType;
	}
	/**
	 * 设置：支付流水号
	 */
	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}
	/**
	 * 获取：支付流水号
	 */
	public String getPayNumber() {
		return payNumber;
	}
	/**
	 * 设置：名称
	 */
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	/**
	 * 获取：名称
	 */
	public String getSaleName() {
		return saleName;
	}
	/**
	 * 设置：买家留言
	 */
	public void setSaleComment(String saleComment) {
		this.saleComment = saleComment;
	}
	/**
	 * 获取：买家留言
	 */
	public String getSaleComment() {
		return saleComment;
	}
	/**
	 * 设置：添加人
	 */
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	/**
	 * 获取：添加人
	 */
	public String getInsertId() {
		return insertId;
	}
	/**
	 * 设置：添加时间
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getInsertTime() {
		return insertTime;
	}
}
