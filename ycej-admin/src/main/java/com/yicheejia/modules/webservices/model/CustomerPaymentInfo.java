package com.yicheejia.modules.webservices.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerPaymentInfo {
    /**
     * 付款银行
     */
    private String paymentBank;
    /**
     * 付款账户
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
     * 客户名称
     */
    private String customerName;

    /**
     * 付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payDate;

    /**
     * 身份证号
     */
    private String idCardNo;

    public String getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public BigDecimal getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(BigDecimal paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public String getIncomeBank() {
        return incomeBank;
    }

    public void setIncomeBank(String incomeBank) {
        this.incomeBank = incomeBank;
    }

    public String getIncomeAccountName() {
        return incomeAccountName;
    }

    public void setIncomeAccountName(String incomeAccountName) {
        this.incomeAccountName = incomeAccountName;
    }

    public String getIncomeAccountNo() {
        return incomeAccountNo;
    }

    public void setIncomeAccountNo(String incomeAccountNo) {
        this.incomeAccountNo = incomeAccountNo;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }
}
