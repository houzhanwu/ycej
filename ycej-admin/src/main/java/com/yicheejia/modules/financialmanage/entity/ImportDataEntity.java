package com.yicheejia.modules.financialmanage.entity;

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
 * @date 2018-09-07 16:19:59
 */
@TableName("ycej_import_data")
public class ImportDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 批量id
	 */
	private String batchId;
	/**
	 * 支付明细主键
	 */
	private String payId;
	/**
	 * 终端编号
	 */
	private String terminalNo;
	/**
	 * 交易日期
	 */
	private Date tradeDate;
	/**
	 * 交易金额
	 */
	private BigDecimal tradeMoney;
	/**
	 * 对方户名
	 */
	private String accountName;
	/**
	 * 对方账号
	 */
	private String accountNo;
	/**
	 * 系统参考号
	 */
	private String sysReferenceNumber;
	/**
	 * 银行流水号
	 */
	private String bankSerialNumber;
	/**
	 * 转账备注--导入数据中的备注
	 */
	private String remark;
	/**
	 * 收款备注--确认收款时填的备注
	 */
	private String moneyRemark;
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
	 * 支付类型
	 */
	private String payType;
	/**
	 * 账户类型
	 */
	private String accountType;
	/**
	 * 商户费用
	 */
	private BigDecimal merchantFees;
	/**
	 * 结算金额
	 */
	private BigDecimal settlementAmount;
	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：批量id
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	/**
	 * 获取：批量id
	 */
	public String getBatchId() {
		return batchId;
	}
	/**
	 * 设置：支付明细主键
	 */
	public void setPayId(String payId) {
		this.payId = payId;
	}
	/**
	 * 获取：支付明细主键
	 */
	public String getPayId() {
		return payId;
	}
	/**
	 * 设置：终端编号
	 */
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}
	/**
	 * 获取：终端编号
	 */
	public String getTerminalNo() {
		return terminalNo;
	}
	/**
	 * 设置：交易日期
	 */
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	/**
	 * 获取：交易日期
	 */
	public Date getTradeDate() {
		return tradeDate;
	}
	/**
	 * 设置：交易金额
	 */
	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	/**
	 * 获取：交易金额
	 */
	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}
	/**
	 * 设置：对方户名
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * 获取：对方户名
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * 设置：对方账号
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * 获取：对方账号
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * 设置：系统参考号
	 */
	public void setSysReferenceNumber(String sysReferenceNumber) {
		this.sysReferenceNumber = sysReferenceNumber;
	}
	/**
	 * 获取：系统参考号
	 */
	public String getSysReferenceNumber() {
		return sysReferenceNumber;
	}
	/**
	 * 设置：银行流水号
	 */
	public void setBankSerialNumber(String bankSerialNumber) {
		this.bankSerialNumber = bankSerialNumber;
	}
	/**
	 * 获取：银行流水号
	 */
	public String getBankSerialNumber() {
		return bankSerialNumber;
	}
	/**
	 * 设置：转账备注--导入数据中的备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：转账备注--导入数据中的备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：收款备注--确认收款时填的备注
	 */
	public void setMoneyRemark(String moneyRemark) {
		this.moneyRemark = moneyRemark;
	}
	/**
	 * 获取：收款备注--确认收款时填的备注
	 */
	public String getMoneyRemark() {
		return moneyRemark;
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
	/**
	 * 设置：支付类型
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付类型
	 */
	public String getPayType() {
		return payType;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public BigDecimal getMerchantFees() {
		return merchantFees;
	}
	public void setMerchantFees(BigDecimal merchantFees) {
		this.merchantFees = merchantFees;
	}
	public BigDecimal getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(BigDecimal settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	
}
