package com.yicheejia.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;


/**
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-07-10 17:32:57
 */
@TableName("ycej_loan_info")
public class LoanInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String loanInfoId;
	/**
	 * 客户名称
	 */
	@NotBlank(message="客户名称不能为空")
	private String customerName;
	/**
	 * 身份证号
	 */
	@NotBlank(message="身份证号不能为空")
	private String idNo;
	/**
	 * 申请编号
	 */
	@NotBlank(message="申请编号不能为空")
	private String applyNo;
	/**
	 * 产品方案ID
	 */
	@NotBlank(message="产品方案ID不能为空")
	private String productSolutionId;
	/**
	 * 融资期限(月)
	 */
	@NotNull(message = "融资期限 financeTime (月)不能为空")
	@Min(value = 0,message = "融资期限financeTime(月)必须大于0")
	private Integer financeTime;
	/**
	 * 车款
	 */
	@DecimalMin(value = "0.00",message = "车款必须大于0")
	private BigDecimal carPayAmount;
	/**
	 * 首付金额
	 */
	private BigDecimal firstPayAmount;
	/**
	 * 服务费
	 */
	private BigDecimal serviceAmount;
	/**
	 * GPS费用
	 */
	private BigDecimal gpsFee;
	/**
	 * 交强险保费
	 */
	private BigDecimal trafficLiabilityInsurance;
	/**
	 * 商业险保费
	 */
	private BigDecimal commerceInsurance;
	/**
	 * 车船税
	 */
	private BigDecimal vavt;
	/**
	 * 购置税
	 */
	private BigDecimal purchaseTax;
	/**
	 * 加装费
	 */
	private BigDecimal installationFee;
	/**
	 * 风控最终结果
	 */
    @NotNull(message = "风控最终结果不能为空  1 ： 通过 ，2 ： 拒绝，3 ： 核准退回 ")
	@Range(min = 1, max = 3, message = "风控最终结果 1 ： 通过 ，2 ： 拒绝,3:核准退回")
	private Integer riskMngRst;
	/**
	 * 时间
	 */
	private Date createTime;
	/**
	 * 订单主键
	 */
	private String orderId;
	
	/**
	 * 设置：主键
	 */
	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}
	/**
	 * 获取：主键
	 */
	public String getLoanInfoId() {
		return loanInfoId;
	}
	/**
	 * 设置：客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取：客户名称
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置：身份证号
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	/**
	 * 获取：身份证号
	 */
	public String getIdNo() {
		return idNo;
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
	 * 设置：产品方案ID
	 */
	public void setProductSolutionId(String productSolutionId) {
		this.productSolutionId = productSolutionId;
	}
	/**
	 * 获取：产品方案ID
	 */
	public String getProductSolutionId() {
		return productSolutionId;
	}
	/**
	 * 设置：融资期限(月)
	 */
	public void setFinanceTime(Integer financeTime) {
		this.financeTime = financeTime;
	}
	/**
	 * 获取：融资期限(月)
	 */
	public Integer getFinanceTime() {
		return financeTime;
	}
	/**
	 * 设置：车款
	 */
	public void setCarPayAmount(BigDecimal carPayAmount) {
		this.carPayAmount = carPayAmount;
	}
	/**
	 * 获取：车款
	 */
	public BigDecimal getCarPayAmount() {
		return carPayAmount;
	}
	/**
	 * 设置：首付金额
	 */
	public void setFirstPayAmount(BigDecimal firstPayAmount) {
		this.firstPayAmount = firstPayAmount;
	}
	/**
	 * 获取：首付金额
	 */
	public BigDecimal getFirstPayAmount() {
		return firstPayAmount;
	}
	/**
	 * 设置：服务费
	 */
	public void setServiceAmount(BigDecimal serviceAmount) {
		this.serviceAmount = serviceAmount;
	}
	/**
	 * 获取：服务费
	 */
	public BigDecimal getServiceAmount() {
		return serviceAmount;
	}
	/**
	 * 设置：GPS费用
	 */
	public void setGpsFee(BigDecimal gpsFee) {
		this.gpsFee = gpsFee;
	}
	/**
	 * 获取：GPS费用
	 */
	public BigDecimal getGpsFee() {
		return gpsFee;
	}
	/**
	 * 设置：交强险保费
	 */
	public void setTrafficLiabilityInsurance(BigDecimal trafficLiabilityInsurance) {
		this.trafficLiabilityInsurance = trafficLiabilityInsurance;
	}
	/**
	 * 获取：交强险保费
	 */
	public BigDecimal getTrafficLiabilityInsurance() {
		return trafficLiabilityInsurance;
	}
	/**
	 * 设置：商业险保费
	 */
	public void setCommerceInsurance(BigDecimal commerceInsurance) {
		this.commerceInsurance = commerceInsurance;
	}
	/**
	 * 获取：商业险保费
	 */
	public BigDecimal getCommerceInsurance() {
		return commerceInsurance;
	}
	/**
	 * 设置：车船税
	 */
	public void setVavt(BigDecimal vavt) {
		this.vavt = vavt;
	}
	/**
	 * 获取：车船税
	 */
	public BigDecimal getVavt() {
		return vavt;
	}
	/**
	 * 设置：购置税
	 */
	public void setPurchaseTax(BigDecimal purchaseTax) {
		this.purchaseTax = purchaseTax;
	}
	/**
	 * 获取：购置税
	 */
	public BigDecimal getPurchaseTax() {
		return purchaseTax;
	}
	/**
	 * 设置：加装费
	 */
	public void setInstallationFee(BigDecimal installationFee) {
		this.installationFee = installationFee;
	}
	/**
	 * 获取：加装费
	 */
	public BigDecimal getInstallationFee() {
		return installationFee;
	}
	/**
	 * 设置：风控最终结果
	 */
	public void setRiskMngRst(Integer riskMngRst) {
		this.riskMngRst = riskMngRst;
	}
	/**
	 * 获取：风控最终结果
	 */
	public Integer getRiskMngRst() {
		return riskMngRst;
	}
	/**
	 * 设置：时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
    public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 产品方案名称
	 */
	@TableField(exist = false)
	private String productSolutionName;
	/**
	 * 贷款金额
	 * @return
	 */
	@TableField(exist = false)
	private BigDecimal amount;
	/**
	 * 保险金额
	 */
	@TableField(exist = false)
	private BigDecimal insureAmount;
	
	public String getProductSolutionName() {
		return productSolutionName;
	}
	public void setProductSolutionName(String productSolutionName) {
		this.productSolutionName = productSolutionName;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getInsureAmount() {
		return insureAmount;
	}
	public void setInsureAmount(BigDecimal insureAmount) {
		this.insureAmount = insureAmount;
	}
	@Override
    public String toString() {
        return "LoanInfoEntity [loanInfoId=" + loanInfoId + ", customerName=" + customerName + ", idNo=" + idNo
                + ", applyNo=" + applyNo + ", productSolutionId=" + productSolutionId + ", financeTime=" + financeTime
                + ", carPayAmount=" + carPayAmount + ", firstPayAmount=" + firstPayAmount + ", serviceAmount="
                + serviceAmount + ", gpsFee=" + gpsFee + ", trafficLiabilityInsurance=" + trafficLiabilityInsurance
                + ", commerceInsurance=" + commerceInsurance + ", vavt=" + vavt + ", purchaseTax=" + purchaseTax
                + ", installationFee=" + installationFee + ", riskMngRst=" + riskMngRst + ", createTime=" + createTime
                + "]";
    }
	
	
}
