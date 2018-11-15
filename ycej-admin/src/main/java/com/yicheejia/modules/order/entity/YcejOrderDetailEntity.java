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
 * @date 2018-06-21 16:27:42
 */
@TableName("ycej_order_detail")
public class YcejOrderDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String orderDetailId;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 支付类别(pay_type)
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
	 * 付款日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date payDate;
	/**
	 * 付款方式
	 */
	private String payMethod;
	/**
	 * 支付状态
	 */
	private Integer payStatu;
	/**
	 * 创建时间
	 */
	private Date insertTime;
	/**
	 * 创建人
	 */
	private String insertId;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 修改人
	 */
	private String updateId;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 付款卡号
	 */
	private String paycardno;
	
	
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
	 * 设置：创建时间
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	/**
	 * 获取：创建人
	 */
	public String getInsertId() {
		return insertId;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：修改人
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	/**
	 * 获取：修改人
	 */
	public String getUpdateId() {
		return updateId;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：付款卡号
	 */
	public void setPaycardno(String paycardno) {
		this.paycardno = paycardno;
	}
	/**
	 * 获取：付款卡号
	 */
	public String getPaycardno() {
		return paycardno;
	}
	
	/**
	 * 新增字段用于前端返回
	 
	@TableField(exist = false)
	private BigDecimal amount01;
	@TableField(exist = false)
	private BigDecimal amount02;
	@TableField(exist = false)
	private BigDecimal amount03;
	@TableField(exist = false)
	private BigDecimal amount04;
	@TableField(exist = false)
	private BigDecimal amount05;
	@TableField(exist = false)
	private BigDecimal amount06;
	@TableField(exist = false)
	private BigDecimal amount07;
	@TableField(exist = false)
	private BigDecimal amount08;
	@TableField(exist = false)
	private BigDecimal amount09;


	public BigDecimal getAmount01() {
		return amount01;
	}
	public void setAmount01(BigDecimal amount01) {
		this.amount01 = amount01;
	}
	public BigDecimal getAmount02() {
		return amount02;
	}
	public void setAmount02(BigDecimal amount02) {
		this.amount02 = amount02;
	}
	public BigDecimal getAmount03() {
		return amount03;
	}
	public void setAmount03(BigDecimal amount03) {
		this.amount03 = amount03;
	}
	public BigDecimal getAmount04() {
		return amount04;
	}
	public void setAmount04(BigDecimal amount04) {
		this.amount04 = amount04;
	}
	public BigDecimal getAmount05() {
		return amount05;
	}
	public void setAmount05(BigDecimal amount05) {
		this.amount05 = amount05;
	}
	public BigDecimal getAmount06() {
		return amount06;
	}
	public void setAmount06(BigDecimal amount06) {
		this.amount06 = amount06;
	}
	public BigDecimal getAmount07() {
		return amount07;
	}
	public void setAmount07(BigDecimal amount07) {
		this.amount07 = amount07;
	}
	public BigDecimal getAmount08() {
		return amount08;
	}
	public void setAmount08(BigDecimal amount08) {
		this.amount08 = amount08;
	}
	public BigDecimal getAmount09() {
		return amount09;
	}
	public void setAmount09(BigDecimal amount09) {
		this.amount09 = amount09;
	}
	*/
}
