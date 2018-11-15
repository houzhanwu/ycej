package com.yicheejia.modules.financialmanage.excel;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class SaleStatisticsBean {

	
		
		
		//------------------------------------------------------------------
		
		/*
		 * 订单编号
		 */
		@Excel(name = "订单编号")
		private String orderNo;
		/*
		 * 所属店铺
		 */
		@Excel(name = "门店")
		private String belongShop;
		/*
		 * 客户姓名
		 */
		@Excel(name = "客户姓名")
		private String customerName;
		/*
		 * 车型
		 */
		@Excel(name = "车型")
		private String carModel;
		/*
		 * 销售类型
		 */
		@Excel(name = "购买方式")
		private String sellType;
		/*
		 * 销售价格（不含税）
		 */
		@Excel(name = "车辆销售")
		private BigDecimal sellPrice;
		/*
		 * 定金支付时间
		 */
		@Excel(name = "定金支付时间",format="yyyy-MM-dd")
		private Date saleTime;
		/*
		 * 定金支付方式
		 */
		@Excel(name = "定金支付方式")
		private String collectionType;
		/*
		 * 定金
		 */
		@Excel(name = "定金")
		private BigDecimal appointMoney;
		/*
		 * 首付支付时间
		 */
		@Excel(name = "首付支付时间",format="yyyy-MM-dd")
		private Date downPayDate;
		/**
		 * 首付款支付方式
		 */
		@Excel(name = "首付支付方式")
		private String downPayMethod;
		/*
		 * 首付
		 */
		@Excel(name = "首付")
		private BigDecimal downPayment;
		
		/*
		 * 上牌费
		 */
		@Excel(name = "上牌费")
		private BigDecimal carLicenseFee;
		/*
		 * 商业外出
		 */
		@Excel(name = "商业外出")
		private BigDecimal comInsurance;
		/*
		 * 保险
		 */
		@Excel(name = "保险")
		private BigDecimal insurance;
		/*
		 * 购置税
		 */
		@Excel(name = "代缴购置税")
		private BigDecimal purchaseTax;
		/*
		 * 续保押金
		 */
		@Excel(name = "续保押金")
		private BigDecimal renewDeposit;
		/*
		 * 交强外出
		 */
		@Excel(name = "交强外出")
		private BigDecimal trafficInsurance;
		/*
		 * 尾款支付时间
		 */
		@Excel(name = "尾款支付时间",format="yyyy-MM-dd")
		private Date finalPayDate;
		/**
		 * 尾款支付方式
		 */
		@Excel(name = "尾款支付方式")
		private String finalPayMethod;
		/*
		 * 尾款
		 */
		@Excel(name = "尾款")
		private BigDecimal finalPayment;
		/*
		 * 优惠金额
		 */
		@Excel(name = "优惠金额")
		private BigDecimal discount;
		/*
		 * 实收金额
		 */
		@Excel(name = "实收金额")
		private BigDecimal amount;
		/*
		 * 合计
		 */
		@Excel(name = "合计")
		private BigDecimal totalMoney;
		/*
		 * 产品方案
		 */
		@Excel(name = "贷款产品方案")
		private String financialPro;
		/*
		 * 裸车贷款金额
		 */
		@Excel(name = "裸车贷款金额")
		private BigDecimal loanAmount;
		/*
		 * 服务费
		 */
		@Excel(name = "贷款服务费")
		private BigDecimal serviceCharge;
		/*
		 * 贷款保险
		 */
		@Excel(name = "贷款保险")
		private BigDecimal loanInsureAmount;
		/*
		 * 交强险保费
		 */
		@Excel(name = "交强险保费")
		private BigDecimal loanTrafficLiabilityInsurance;
		/*
		 * 商业险保费
		 */
		@Excel(name = "商业险保费")
		private BigDecimal loanCommerceInsurance;
		/*
		 * 融资期限
		 */
		@Excel(name = "融资期限")
		private String loanFinanceTime;
		/*
		 * 车船税
		 */
		@Excel(name = "车船税")
		private BigDecimal loanVavt;
		/*
		 * 加装费
		 */
		@Excel(name = "加装费")
		private BigDecimal loanInstallationFee;
		/*
		 * gps费用
		 */
		@Excel(name = "gps费用")
		private BigDecimal gpsFee;
		
		/*
		 * 订单状态
		 */
		@Excel(name = "订单状态")
		private String status;
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
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
		public String getFinancialPro() {
			return financialPro;
		}
		public void setFinancialPro(String financialPro) {
			this.financialPro = financialPro;
		}
		public BigDecimal getSellPrice() {
			return sellPrice;
		}
		public void setSellPrice(BigDecimal sellPrice) {
			this.sellPrice = sellPrice;
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
		public Date getDownPayDate() {
			return downPayDate;
		}
		public void setDownPayDate(Date downPayDate) {
			this.downPayDate = downPayDate;
		}
		public String getDownPayMethod() {
			return downPayMethod;
		}
		public void setDownPayMethod(String downPayMethod) {
			this.downPayMethod = downPayMethod;
		}
		public BigDecimal getFinalPayment() {
			return finalPayment;
		}
		public void setFinalPayment(BigDecimal finalPayment) {
			this.finalPayment = finalPayment;
		}
		public Date getFinalPayDate() {
			return finalPayDate;
		}
		public void setFinalPayDate(Date finalPayDate) {
			this.finalPayDate = finalPayDate;
		}
		public String getFinalPayMethod() {
			return finalPayMethod;
		}
		public void setFinalPayMethod(String finalPayMethod) {
			this.finalPayMethod = finalPayMethod;
		}
		public BigDecimal getCarLicenseFee() {
			return carLicenseFee;
		}
		public void setCarLicenseFee(BigDecimal carLicenseFee) {
			this.carLicenseFee = carLicenseFee;
		}
		public BigDecimal getRenewDeposit() {
			return renewDeposit;
		}
		public void setRenewDeposit(BigDecimal renewDeposit) {
			this.renewDeposit = renewDeposit;
		}
		public BigDecimal getInsurance() {
			return insurance;
		}
		public void setInsurance(BigDecimal insurance) {
			this.insurance = insurance;
		}
		public BigDecimal getComInsurance() {
			return comInsurance;
		}
		public void setComInsurance(BigDecimal comInsurance) {
			this.comInsurance = comInsurance;
		}
		public BigDecimal getTrafficInsurance() {
			return trafficInsurance;
		}
		public void setTrafficInsurance(BigDecimal trafficInsurance) {
			this.trafficInsurance = trafficInsurance;
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
		public BigDecimal getLoanAmount() {
			return loanAmount;
		}
		public void setLoanAmount(BigDecimal loanAmount) {
			this.loanAmount = loanAmount;
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
		public String getCollectionType() {
			return collectionType;
		}
		public void setCollectionType(String collectionType) {
			this.collectionType = collectionType;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
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

	
