package com.yicheejia.modules.order.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-10-12 15:12:37
 */
public class PutmoneyInfoBean {
    @Excel(name = "")
    private Long putmoneyInfoId;
    @Excel(name = "付款银行")
    private String paymentBank;
    @Excel(name = "付款账号")
    private String paymentAccount;
    @Excel(name = "付款总额")
    private BigDecimal paymentTotal;
    @Excel(name = "收款银行")
    private String incomeBank;
    @Excel(name = "收款账户名")
    private String incomeAccountName;
    @Excel(name = "收款账户")
    private String incomeAccountNo;
    @Excel(name = "申请编号")
    private String applyNo;
    @Excel(name = "客户姓名")
    private String customerName;
    @Excel(name = "付款时间")
    private Date payDate;
    @Excel(name = "身份证号码")
    private String idcardNo;
    @Excel(name = "创建时间")
    private Date createTime;

    public void setPutmoneyInfoId(Long putmoneyInfoId) {
        this.putmoneyInfoId = putmoneyInfoId;
    }
    public Long getPutmoneyInfoId() {
        return putmoneyInfoId;
    }
    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank;
    }
    public String getPaymentBank() {
        return paymentBank;
    }
    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }
    public String getPaymentAccount() {
        return paymentAccount;
    }
    public void setPaymentTotal(BigDecimal paymentTotal) {
        this.paymentTotal = paymentTotal;
    }
    public BigDecimal getPaymentTotal() {
        return paymentTotal;
    }
    public void setIncomeBank(String incomeBank) {
        this.incomeBank = incomeBank;
    }
    public String getIncomeBank() {
        return incomeBank;
    }
    public void setIncomeAccountName(String incomeAccountName) {
        this.incomeAccountName = incomeAccountName;
    }
    public String getIncomeAccountName() {
        return incomeAccountName;
    }
    public void setIncomeAccountNo(String incomeAccountNo) {
        this.incomeAccountNo = incomeAccountNo;
    }
    public String getIncomeAccountNo() {
        return incomeAccountNo;
    }
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }
    public String getApplyNo() {
        return applyNo;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
    public Date getPayDate() {
        return payDate;
    }
    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }
    public String getIdcardNo() {
        return idcardNo;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getCreateTime() {
        return createTime;
    }
}
