package com.yicheejia.modules.financialmanage.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 返佣报表
 * @author 梁占豪
 *
 */
@TableName("ycej_order")
public class YcejFinanceRebateEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * 订单ID
	 */
	@TableId
	private String id;
	/*
	 * 订单编号
	 */
	private String order_no;
	/*
	 * 所属组织（大区）
	 */
	private String area;
	/*
	 * 门店
	 */
	private String shopId;
	/*
	 * 门店属性
	 */
	@TableField(exist = false)
	private Integer shopAttr;
	/*
	 * 融资总额
	 */
	@TableField(exist = false)
	private BigDecimal allCharge;
	/*
	 * 融资期限
	 */
	@TableField(exist = false)
	private String financingDate;
	/*
	 * gps费用
	 */
	@TableField(exist = false)
	private BigDecimal gpsFee;
	/*
	 * 产品方案id
	 */
	@TableField(exist = false)
	private Integer productId;
	/*
	 * 客户姓名
	 */
	private String customerName;
	/*
	 * 车型
	 */
	private String modelId;
	/*
	 * 购买方式
	 */
	private String payWay;
	/*
	 * 出保险金额
	 */
	private BigDecimal insurance;
	/*
	 * 车船税
	 */
	private BigDecimal vehicle_vessel_tax;
	/*
	 * 商业险
	 */
	private BigDecimal comInsurance;
	/*
	 * 税后保险金额
	 */
	private BigDecimal after_tax_insurance;
	/*
	 * 合伙人应得保险返利
	 */
	private BigDecimal insurance_rebate;
	/*
	 * 产品返利
	 */
	private BigDecimal product_rebate;
	/*
	 * 手续费返利
	 */
	private BigDecimal service_rebate;
	/*
	 * 服务费
	 */
	private Integer serviceCharge;
	/*
	 * GPS返利
	 */
	private BigDecimal gps_rebate;
	/*
	 * 合伙人返利
	 */
	private BigDecimal partner_rebate;
	
	
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
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Integer getShopAttr() {
		return shopAttr;
	}
	public void setShopAttr(Integer shopAttr) {
		this.shopAttr = shopAttr;
	}
	public BigDecimal getAllCharge() {
		return allCharge;
	}
	public void setAllCharge(BigDecimal allCharge) {
		this.allCharge = allCharge;
	}
	public String getFinancingDate() {
		return financingDate;
	}
	public void setFinancingDate(String financingDate) {
		this.financingDate = financingDate;
	}
	public BigDecimal getGpsFee() {
		return gpsFee;
	}
	public void setGpsFee(BigDecimal gpsFee) {
		this.gpsFee = gpsFee;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(Integer serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	
	
	
}
