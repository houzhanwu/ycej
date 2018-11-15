package com.yicheejia.modules.operate.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class YouYiCheEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 品牌
	 */
	private String carBrand;
	/**
	 * 厂商
	 */
	private String carManufacturer;
	/**
	 * 车系
	 */
	private String carSeries;
	/**
	 * 车型
	 */
	private String carModel;
	/**
	 * 指导价
	 */
	private BigDecimal carPrice;
	/**
	 * 采购价
	 */
	private BigDecimal purchCostPrice;
	/**
	 * 采购发票价
	 */
	private BigDecimal purchInvoicePrice;
	/**
	 * 运管费
	 */
	private BigDecimal transport;
	/**
	 * 交强险
	 */
	private BigDecimal jqxPrice;
	/**
	 * 保险金额(商业)
	 */
	private BigDecimal insurancePrice;
	/**
	 * 车船税
	 */
	private BigDecimal ccs;
	/**
	 * 裸车首付
	 */
	private BigDecimal firstPayment;
	/**
	 * 提车服务费
	 */
	private BigDecimal servicePrice;
	/**
	 * 渠道
	 */
	private String channel; 
	/**
	 * 奖励
	 */
	private BigDecimal reward;
	/**
	 * 业务奖励
	 */
	private BigDecimal businessReward;
	/**
	 * 36期月供
	 */
	private BigDecimal monthlySupply;
	/**
	 * 48期月供
	 */
	private BigDecimal monthlySupplys;
	/**
	 * 购置税
	 */
	private BigDecimal purchaseTax;
	/**
	 * GPS融资额
	 */
	private BigDecimal gpsCharge;
	/**
	 * 座位数
	 */
	private int seatNum;
	/**
	 * 排量
	 */
	private double emissions;
	
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public double getEmissions() {
		return emissions;
	}
	public void setEmissions(double emissions) {
		this.emissions = emissions;
	}
	public BigDecimal getPurchaseTax() {
		return purchaseTax;
	}
	public void setPurchaseTax(BigDecimal purchaseTax) {
		this.purchaseTax = purchaseTax;
	}
	public BigDecimal getGpsCharge() {
		return gpsCharge;
	}
	public void setGpsCharge(BigDecimal gpsCharge) {
		this.gpsCharge = gpsCharge;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarManufacturer() {
		return carManufacturer;
	}
	public void setCarManufacturer(String carManufacturer) {
		this.carManufacturer = carManufacturer;
	}
	public String getCarSeries() {
		return carSeries;
	}
	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public BigDecimal getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(BigDecimal carPrice) {
		this.carPrice = carPrice;
	}
	public BigDecimal getPurchCostPrice() {
		return purchCostPrice;
	}
	public void setPurchCostPrice(BigDecimal purchCostPrice) {
		this.purchCostPrice = purchCostPrice;
	}
	public BigDecimal getPurchInvoicePrice() {
		return purchInvoicePrice;
	}
	public void setPurchInvoicePrice(BigDecimal purchInvoicePrice) {
		this.purchInvoicePrice = purchInvoicePrice;
	}
	public BigDecimal getTransport() {
		return transport;
	}
	public void setTransport(BigDecimal transport) {
		this.transport = transport;
	}
	public BigDecimal getJqxPrice() {
		return jqxPrice;
	}
	public void setJqxPrice(BigDecimal jqxPrice) {
		this.jqxPrice = jqxPrice;
	}
	public BigDecimal getInsurancePrice() {
		return insurancePrice;
	}
	public void setInsurancePrice(BigDecimal insurancePrice) {
		this.insurancePrice = insurancePrice;
	}
	public BigDecimal getCcs() {
		return ccs;
	}
	public void setCcs(BigDecimal ccs) {
		this.ccs = ccs;
	}
	public BigDecimal getFirstPayment() {
		return firstPayment;
	}
	public void setFirstPayment(BigDecimal firstPayment) {
		this.firstPayment = firstPayment;
	}
	public BigDecimal getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public BigDecimal getReward() {
		return reward;
	}
	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}
	public BigDecimal getBusinessReward() {
		return businessReward;
	}
	public void setBusinessReward(BigDecimal businessReward) {
		this.businessReward = businessReward;
	}
	public BigDecimal getMonthlySupply() {
		return monthlySupply;
	}
	public void setMonthlySupply(BigDecimal monthlySupply) {
		this.monthlySupply = monthlySupply;
	}
	public BigDecimal getMonthlySupplys() {
		return monthlySupplys;
	}
	public void setMonthlySupplys(BigDecimal monthlySupplys) {
		this.monthlySupplys = monthlySupplys;
	}

}
