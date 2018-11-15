package com.yicheejia.modules.financialmanage.excel;

import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class YcejFinanceRebateBean {

	/*
	 * 订单编号
	 */
	@Excel(name = "订单编号")
	private String order_no;
	/*
	 * 门店
	 */
	@Excel(name = "门店")
	private String shopId;
	/*
	 * 客户姓名
	 */
	@Excel(name = "客户姓名")
	private String customerName;
	/*
	 * 车型
	 */
	@Excel(name = "车型")
	private String modelId;
	/*
	 * 购买方式
	 */
	@Excel(name = "购买方式")
	private String payWay;
	/*
	 * 出保险金额
	 */
	@Excel(name = "出保险金额")
	private BigDecimal insurance;
	/*
	 * 车船税
	 */
	@Excel(name = "车船税")
	private BigDecimal vehicle_vessel_tax;
	/*
	 * 商业险
	 */
	@Excel(name = "商业险")
	private BigDecimal comInsurance;
	/*
	 * 税后保险金额
	 */
	@Excel(name = "税后保险金额")
	private BigDecimal after_tax_insurance;
	/*
	 * 合伙人应得保险返利
	 */
	@Excel(name = "合伙人应得保险返利")
	private BigDecimal insurance_rebate;
	/*
	 * 产品返利
	 */
	@Excel(name = "产品返利")
	private BigDecimal product_rebate;
	/*
	 * 手续费返利
	 */
	@Excel(name = "手续费返利")
	private BigDecimal service_rebate;
	/*
	 * GPS返利
	 */
	@Excel(name = "GPS返利")
	private BigDecimal gps_rebate;
	/*
	 * 合伙人返利
	 */
	@Excel(name = "合伙人返利")
	private BigDecimal partner_rebate;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public BigDecimal getInsurance() {
		return insurance;
	}
	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}
	public BigDecimal getVehicle_vessel_tax() {
		return vehicle_vessel_tax;
	}
	public void setVehicle_vessel_tax(BigDecimal vehicle_vessel_tax) {
		this.vehicle_vessel_tax = vehicle_vessel_tax;
	}
	public BigDecimal getComInsurance() {
		return comInsurance;
	}
	public void setComInsurance(BigDecimal comInsurance) {
		this.comInsurance = comInsurance;
	}
	public BigDecimal getAfter_tax_insurance() {
		return after_tax_insurance;
	}
	public void setAfter_tax_insurance(BigDecimal after_tax_insurance) {
		this.after_tax_insurance = after_tax_insurance;
	}
	public BigDecimal getInsurance_rebate() {
		return insurance_rebate;
	}
	public void setInsurance_rebate(BigDecimal insurance_rebate) {
		this.insurance_rebate = insurance_rebate;
	}
	public BigDecimal getProduct_rebate() {
		return product_rebate;
	}
	public void setProduct_rebate(BigDecimal product_rebate) {
		this.product_rebate = product_rebate;
	}
	public BigDecimal getService_rebate() {
		return service_rebate;
	}
	public void setService_rebate(BigDecimal service_rebate) {
		this.service_rebate = service_rebate;
	}
	public BigDecimal getGps_rebate() {
		return gps_rebate;
	}
	public void setGps_rebate(BigDecimal gps_rebate) {
		this.gps_rebate = gps_rebate;
	}
	public BigDecimal getPartner_rebate() {
		return partner_rebate;
	}
	public void setPartner_rebate(BigDecimal partner_rebate) {
		this.partner_rebate = partner_rebate;
	}
	
}
