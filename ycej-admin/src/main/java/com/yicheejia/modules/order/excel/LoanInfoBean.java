package com.yicheejia.modules.order.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-10 17:32:57
 */
public class LoanInfoBean {
    @Excel(name = "主键")
    private String loanInfoId;
    @Excel(name = "客户名称")
    private String customerName;
    @Excel(name = "身份证号")
    private String idNo;
    @Excel(name = "申请编号")
    private String applyNo;
    @Excel(name = "产品方案ID")
    private String productSolutionId;
    @Excel(name = "融资期限(月)")
    private Integer financeTime;
    @Excel(name = "车款")
    private BigDecimal carPayAmount;
    @Excel(name = "首付金额")
    private BigDecimal firstPayAmount;
    @Excel(name = "服务费")
    private BigDecimal serviceAmount;
    @Excel(name = "GPS费用")
    private BigDecimal gpsFee;
    @Excel(name = "交强险保费")
    private BigDecimal trafficLiabilityInsurance;
    @Excel(name = "商业险保费")
    private BigDecimal commerceInsurance;
    @Excel(name = "车船税")
    private BigDecimal vavt;
    @Excel(name = "购置税")
    private BigDecimal purchaseTax;
    @Excel(name = "加装费")
    private BigDecimal installationFee;
    @Excel(name = "风控最终结果")
    private Integer riskMngRst;
    @Excel(name = "时间")
    private Date createTime;

    public void setLoanInfoId(String loanInfoId) {
        this.loanInfoId = loanInfoId;
    }
    public String getLoanInfoId() {
        return loanInfoId;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getIdNo() {
        return idNo;
    }
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }
    public String getApplyNo() {
        return applyNo;
    }
    public void setProductSolutionId(String productSolutionId) {
        this.productSolutionId = productSolutionId;
    }
    public String getProductSolutionId() {
        return productSolutionId;
    }
    public void setFinanceTime(Integer financeTime) {
        this.financeTime = financeTime;
    }
    public Integer getFinanceTime() {
        return financeTime;
    }
    public void setCarPayAmount(BigDecimal carPayAmount) {
        this.carPayAmount = carPayAmount;
    }
    public BigDecimal getCarPayAmount() {
        return carPayAmount;
    }
    public void setFirstPayAmount(BigDecimal firstPayAmount) {
        this.firstPayAmount = firstPayAmount;
    }
    public BigDecimal getFirstPayAmount() {
        return firstPayAmount;
    }
    public void setServiceAmount(BigDecimal serviceAmount) {
        this.serviceAmount = serviceAmount;
    }
    public BigDecimal getServiceAmount() {
        return serviceAmount;
    }
    public void setGpsFee(BigDecimal gpsFee) {
        this.gpsFee = gpsFee;
    }
    public BigDecimal getGpsFee() {
        return gpsFee;
    }
    public void setTrafficLiabilityInsurance(BigDecimal trafficLiabilityInsurance) {
        this.trafficLiabilityInsurance = trafficLiabilityInsurance;
    }
    public BigDecimal getTrafficLiabilityInsurance() {
        return trafficLiabilityInsurance;
    }
    public void setCommerceInsurance(BigDecimal commerceInsurance) {
        this.commerceInsurance = commerceInsurance;
    }
    public BigDecimal getCommerceInsurance() {
        return commerceInsurance;
    }
    public void setVavt(BigDecimal vavt) {
        this.vavt = vavt;
    }
    public BigDecimal getVavt() {
        return vavt;
    }
    public void setPurchaseTax(BigDecimal purchaseTax) {
        this.purchaseTax = purchaseTax;
    }
    public BigDecimal getPurchaseTax() {
        return purchaseTax;
    }
    public void setInstallationFee(BigDecimal installationFee) {
        this.installationFee = installationFee;
    }
    public BigDecimal getInstallationFee() {
        return installationFee;
    }
    public void setRiskMngRst(Integer riskMngRst) {
        this.riskMngRst = riskMngRst;
    }
    public Integer getRiskMngRst() {
        return riskMngRst;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getCreateTime() {
        return createTime;
    }
}
