package com.yicheejia.modules.operate.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class InsuranceCalculatorEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 车辆销售价
	 */
	private BigDecimal carPrice;
	/**
	 * 座位数
	 */
	private String seatNum;
	/**
	 * 排量
	 */
	private String emissions;
	/**
	 * 车损险
	 */
	private String clss;
	/**
	 * 三责
	 */
	private String dsz;
	/**
	 * 盗抢险
	 */
	private String dq;
	/**
	 * 无法找到第三方特约险
	 */
	private String noOther;
	/**
	 * 乘员责任险（司机）
	 */
	private String cyzrDriver;
	/**
	 * 乘员责任险（乘客）
	 */
	private String cyzrPassenger;
	/**
	 * 玻璃破碎险
	 */
	private String blps;
	/**
	 * 车身划痕险
	 */
	private String cshh;
	/**
	 * 不计免赔
	 */
	private String bjmp;
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
	
	
	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
	public BigDecimal getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(BigDecimal carPrice) {
		this.carPrice = carPrice;
	}
	public String getEmissions() {
		return emissions;
	}
	public void setEmissions(String emissions) {
		this.emissions = emissions;
	}
	public String getClss() {
		return clss;
	}
	public void setClss(String clss) {
		this.clss = clss;
	}
	public String getDsz() {
		return dsz;
	}
	public void setDsz(String dsz) {
		this.dsz = dsz;
	}
	public String getDq() {
		return dq;
	}
	public void setDq(String dq) {
		this.dq = dq;
	}
	public String getNoOther() {
		return noOther;
	}
	public void setNoOther(String noOther) {
		this.noOther = noOther;
	}
	public String getCyzrDriver() {
		return cyzrDriver;
	}
	public void setCyzrDriver(String cyzrDriver) {
		this.cyzrDriver = cyzrDriver;
	}
	public String getCyzrPassenger() {
		return cyzrPassenger;
	}
	public void setCyzrPassenger(String cyzrPassenger) {
		this.cyzrPassenger = cyzrPassenger;
	}
	public String getBlps() {
		return blps;
	}
	public void setBlps(String blps) {
		this.blps = blps;
	}
	public String getCshh() {
		return cshh;
	}
	public void setCshh(String cshh) {
		this.cshh = cshh;
	}
	public String getBjmp() {
		return bjmp;
	}
	public void setBjmp(String bjmp) {
		this.bjmp = bjmp;
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
	
}

