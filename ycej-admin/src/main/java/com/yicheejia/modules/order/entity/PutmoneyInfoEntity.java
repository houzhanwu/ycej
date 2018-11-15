package com.yicheejia.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-10-12 15:12:37
 */
@TableName("ycej_putmoney_info")
public class PutmoneyInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long putmoneyInfoId;
	/**
	 * 付款银行
	 */
	private String paymentBank;
	/**
	 * 付款账号
	 */
	private String paymentAccount;
	/**
	 * 付款总额
	 */
	private BigDecimal paymentTotal;
	/**
	 * 收款银行
	 */
	private String incomeBank;
	/**
	 * 收款账户名
	 */
	private String incomeAccountName;
	/**
	 * 收款账户
	 */
	private String incomeAccountNo;
	/**
	 * 申请编号
	 */
	private String applyNo;
	/**
	 * 客户姓名
	 */
	private String customerName;
	/**
	 * 付款时间
	 */
	private Date payDate;
	/**
	 * 身份证号码
	 */
	private String idcardNo;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 设置：
	 */
	public void setPutmoneyInfoId(Long putmoneyInfoId) {
		this.putmoneyInfoId = putmoneyInfoId;
	}
	/**
	 * 获取：
	 */
	public Long getPutmoneyInfoId() {
		return putmoneyInfoId;
	}
	/**
	 * 设置：付款银行
	 */
	public void setPaymentBank(String paymentBank) {
		this.paymentBank = paymentBank;
	}
	/**
	 * 获取：付款银行
	 */
	public String getPaymentBank() {
		return paymentBank;
	}
	/**
	 * 设置：付款账号
	 */
	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}
	/**
	 * 获取：付款账号
	 */
	public String getPaymentAccount() {
		return paymentAccount;
	}
	/**
	 * 设置：付款总额
	 */
	public void setPaymentTotal(BigDecimal paymentTotal) {
		this.paymentTotal = paymentTotal;
	}
	/**
	 * 获取：付款总额
	 */
	public BigDecimal getPaymentTotal() {
		return paymentTotal;
	}
	/**
	 * 设置：收款银行
	 */
	public void setIncomeBank(String incomeBank) {
		this.incomeBank = incomeBank;
	}
	/**
	 * 获取：收款银行
	 */
	public String getIncomeBank() {
		return incomeBank;
	}
	/**
	 * 设置：收款账户名
	 */
	public void setIncomeAccountName(String incomeAccountName) {
		this.incomeAccountName = incomeAccountName;
	}
	/**
	 * 获取：收款账户名
	 */
	public String getIncomeAccountName() {
		return incomeAccountName;
	}
	/**
	 * 设置：收款账户
	 */
	public void setIncomeAccountNo(String incomeAccountNo) {
		this.incomeAccountNo = incomeAccountNo;
	}
	/**
	 * 获取：收款账户
	 */
	public String getIncomeAccountNo() {
		return incomeAccountNo;
	}
	/**
	 * 设置：申请编号
	 */
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	/**
	 * 获取：申请编号
	 */
	public String getApplyNo() {
		return applyNo;
	}
	/**
	 * 设置：客户姓名
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取：客户姓名
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置：付款时间
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	/**
	 * 获取：付款时间
	 */
	public Date getPayDate() {
		return payDate;
	}
	/**
	 * 设置：身份证号码
	 */
	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}
	/**
	 * 获取：身份证号码
	 */
	public String getIdcardNo() {
		return idcardNo;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
