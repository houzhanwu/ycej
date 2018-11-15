package com.yicheejia.modules.financialmanage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("ycej_order")
public class YcejSaleStatisticsEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	/*
	 * 订单ID
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/*
	 * 订单编号
	 */
	@TableField(exist = false)
	private String orderNo;
	/*
	 * 所属组织
	 */
	@TableField(exist = false)
	private String area;
	/*
	 * 所属店铺
	 */
	@TableField(exist = false)
	private String belongShop;
	/*
	 * 定金支付时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@TableField(exist = false)
	private Date saleTime;
	/*
	 * 车型
	 */
	@TableField(exist = false)
	private String carModel;
	/*
	 * 销售类型
	 */
	@TableField(exist = false)
	private String sellType;
	/*
	 * 客户姓名
	 */
	@TableField(exist = false)
	private String customerName;
	/*
	 * 产品方案
	 */
	@TableField(exist = false)
	private String financialPro;
	/*
	 * 销售价格（不含税）
	 */
	@TableField(exist = false)
	private BigDecimal sellPrice;
	/*
	 * 定金
	 */
	@TableField(exist = false)
	private BigDecimal appointMoney;
	/*
	 * 首付
	 */
	@TableField(exist = false)
	private BigDecimal downPayment;
	/*
	 * 首付支付时间
	 */
	@TableField(exist = false)
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date downPayDate;
	/**
	 * 首付款支付方式
	 */
	@TableField(exist=false)
	private String downPayMethod;
	/*
	 * 尾款
	 */
	@TableField(exist = false)
	private BigDecimal finalPayment;
	/*
	 * 尾款支付时间
	 */
	@TableField(exist = false)
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date finalPayDate;
	/**
	 * 尾款支付方式
	 */
	@TableField(exist=false)
	private String finalPayMethod;
	/*
	 * 上牌费
	 */
	@TableField(exist = false)
	private BigDecimal carLicenseFee;
	/*
	 * 续保押金
	 */
	@TableField(exist = false)
	private BigDecimal renewDeposit;
	/*
	 * 保险
	 */
	@TableField(exist = false)
	private BigDecimal insurance;
	/*
	 * 商业外出
	 */
	@TableField(exist = false)
	private BigDecimal comInsurance;
	/*
	 * 交强外出
	 */
	@TableField(exist = false)
	private BigDecimal trafficInsurance;
	/*
	 * 购置税
	 */
	@TableField(exist = false)
	private BigDecimal purchaseTax;
	/*
	 * 服务费
	 */
	@TableField(exist = false)
	private BigDecimal serviceCharge;
	/*
	 * gps费用
	 */
	@TableField(exist = false)
	private BigDecimal gpsFee;
	/*
	 * 裸车贷款金额
	 */
	@TableField(exist = false)
	private BigDecimal loanAmount;
	/*
	 * 贷款服务费
	 */
	@TableField(exist = false)
	private BigDecimal loanServiceAmount;
	/*
	 * 贷款保险
	 */
	@TableField(exist = false)
	private BigDecimal loanInsureAmount;
	/*
	 * 车船税
	 */
	@TableField(exist = false)
	private BigDecimal loanVavt;
	/*
	 * 加装费
	 */
	@TableField(exist = false)
	private BigDecimal loanInstallationFee;
	/*
	 * 交强险保费
	 */
	@TableField(exist = false)
	private BigDecimal loanTrafficLiabilityInsurance;
	/*
	 * 商业险保费
	 */
	@TableField(exist = false)
	private BigDecimal loanCommerceInsurance;
	/*
	 * 店保信息
	 */
	@TableField(exist = false)
	private String shopProtectedDetail;
	/*
	 * 融资期限
	 */
	@TableField(exist = false)
	private String loanFinanceTime;
	/*
	 * 收款时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@TableField(exist = false)
	private Date collectionTime;
	/*
	 * 定金支付方式
	 */
	@TableField(exist = false)
	private String collectionType;
	/*
	 * 订单状态
	 */
	private String status;
	/*
	 * 备用字段1
	 */
	@TableField(exist = false)
	private String ext1;
	/*
	 * 备用字段2
	 */
	@TableField(exist = false)
	private String ext2;
	/*
	 * 门店编号
	 */
	@TableField(exist = false)
	private String shopNo;
	/*
	 * 优惠金额
	 */
	@TableField(exist = false)
	private BigDecimal discount;
	/*
	 * 实收金额
	 */
	@TableField(exist = false)
	private BigDecimal amount;
	/*
	 * 合计
	 */
	@TableField(exist = false)
	private BigDecimal totalMoney;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBelongShop() {
		return belongShop;
	}
	public void setBelongShop(String belongShop) {
		this.belongShop = belongShop;
	}
	public Date getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	
	public String getFinancialPro() {
		return financialPro;
	}
	public void setFinancialPro(String financialPro) {
		this.financialPro = financialPro;
	}
	
	public BigDecimal getAppointMoney() {
		return appointMoney;
	}
	public void setAppointMoney(BigDecimal appointMoney) {
		this.appointMoney = appointMoney;
	}
	public BigDecimal getDownPayment() {
		return downPayment;
	}
	public void setDownPayment(BigDecimal downPayment) {
		this.downPayment = downPayment;
	}
	public BigDecimal getRenewDeposit() {
		return renewDeposit;
	}
	public void setRenewDeposit(BigDecimal renewDeposit) {
		this.renewDeposit = renewDeposit;
	}
	public BigDecimal getCarLicenseFee() {
		return carLicenseFee;
	}
	public void setCarLicenseFee(BigDecimal carLicenseFee) {
		this.carLicenseFee = carLicenseFee;
	}
	public BigDecimal getComInsurance() {
		return comInsurance;
	}
	public void setComInsurance(BigDecimal comInsurance) {
		this.comInsurance = comInsurance;
	}
	public BigDecimal getPurchaseTax() {
		return purchaseTax;
	}
	public void setPurchaseTax(BigDecimal purchaseTax) {
		this.purchaseTax = purchaseTax;
	}
	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public BigDecimal getGpsFee() {
		return gpsFee;
	}
	public void setGpsFee(BigDecimal gpsFee) {
		this.gpsFee = gpsFee;
	}
	public String getShopProtectedDetail() {
		return shopProtectedDetail;
	}
	public void setShopProtectedDetail(String shopProtectedDetail) {
		this.shopProtectedDetail = shopProtectedDetail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public BigDecimal getFinalPayment() {
		return finalPayment;
	}
	public void setFinalPayment(BigDecimal finalPayment) {
		this.finalPayment = finalPayment;
	}
	public Date getCollectionTime() {
		return collectionTime;
	}
	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}
	public String getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}
	
	public String getDownPayMethod() {
		return downPayMethod;
	}
	public void setDownPayMethod(String downPayMethod) {
		this.downPayMethod = downPayMethod;
	}
	public String getFinalPayMethod() {
		return finalPayMethod;
	}
	public void setFinalPayMethod(String finalPayMethod) {
		this.finalPayMethod = finalPayMethod;
	}
	public BigDecimal getInsurance() {
		return insurance;
	}
	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}
	public BigDecimal getTrafficInsurance() {
		return trafficInsurance;
	}
	public void setTrafficInsurance(BigDecimal trafficInsurance) {
		this.trafficInsurance = trafficInsurance;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public BigDecimal getLoanServiceAmount() {
		return loanServiceAmount;
	}
	public void setLoanServiceAmount(BigDecimal loanServiceAmount) {
		this.loanServiceAmount = loanServiceAmount;
	}
	public BigDecimal getLoanInsureAmount() {
		return loanInsureAmount;
	}
	public void setLoanInsureAmount(BigDecimal loanInsureAmount) {
		this.loanInsureAmount = loanInsureAmount;
	}
	public BigDecimal getLoanVavt() {
		return loanVavt;
	}
	public void setLoanVavt(BigDecimal loanVavt) {
		this.loanVavt = loanVavt;
	}
	public BigDecimal getLoanInstallationFee() {
		return loanInstallationFee;
	}
	public void setLoanInstallationFee(BigDecimal loanInstallationFee) {
		this.loanInstallationFee = loanInstallationFee;
	}
	public BigDecimal getLoanTrafficLiabilityInsurance() {
		return loanTrafficLiabilityInsurance;
	}
	public void setLoanTrafficLiabilityInsurance(BigDecimal loanTrafficLiabilityInsurance) {
		this.loanTrafficLiabilityInsurance = loanTrafficLiabilityInsurance;
	}
	public BigDecimal getLoanCommerceInsurance() {
		return loanCommerceInsurance;
	}
	public void setLoanCommerceInsurance(BigDecimal loanCommerceInsurance) {
		this.loanCommerceInsurance = loanCommerceInsurance;
	}
	public String getLoanFinanceTime() {
		return loanFinanceTime;
	}
	public void setLoanFinanceTime(String loanFinanceTime) {
		this.loanFinanceTime = loanFinanceTime;
	}
	public Date getDownPayDate() {
		return downPayDate;
	}
	public void setDownPayDate(Date downPayDate) {
		this.downPayDate = downPayDate;
	}
	public Date getFinalPayDate() {
		return finalPayDate;
	}
	public void setFinalPayDate(Date finalPayDate) {
		this.finalPayDate = finalPayDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSellType() {
		return sellType;
	}
	public void setSellType(String sellType) {
		this.sellType = sellType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
}
