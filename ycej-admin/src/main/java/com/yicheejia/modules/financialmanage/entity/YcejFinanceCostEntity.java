package com.yicheejia.modules.financialmanage.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("ycej_order")
public class YcejFinanceCostEntity implements Serializable {

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
	 * 客户姓名
	 */
	private String customer_name;
	/*
	 * 销售日期
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
	private String sell_type;
	/*
	 * 总成本
	 */
	@TableField(exist = false)
	private BigDecimal total_cost;
	/*
	 * 裸车采购价
	 */
	@TableField(exist = false)
	private BigDecimal nakedCar_purchase_price;
	/*
	 * 车辆发票价
	 */
	private BigDecimal invoice_price;
	/*
	 * gps费用
	 */
	@TableField(exist = false)
	private BigDecimal gpsFee;
	/*
	 * 上牌费
	 */
	@TableField(exist = false)
	private BigDecimal carLicenseFee;
	/*
	 * 渠道奖励
	 */
	@TableField(exist = false)
	private BigDecimal awardChannel;
	/*
	 * 业务奖励
	 */
	@TableField(exist = false)
	private BigDecimal businessRewards;
	/*
	 * 购置税
	 */
	@TableField(exist = false)
	private BigDecimal purchaseTax;
	/*
	 * 运管费
	 */
	@TableField(exist = false)
	private BigDecimal pipeFee;
	/*
	 * 商业险
	 */
	@TableField(exist = false)
	private BigDecimal comInsurance;
	/*
	 * 返保险
	 */
	@TableField(exist = false)
	private BigDecimal returnedInsurance;
	/*
	 * 返GPS
	 */
	@TableField(exist = false)
	private BigDecimal returnedGps;
	/*
	 * 订单状态
	 */
	private String status;
	/*
	 * 服务费
	 */
	private Integer serviceCharge;
	/*
	 * 产品方案
	 */
	@TableField(exist = false)
	private String financialPro;
	/*
	 * 裸车贷款金额
	 */
	@TableField(exist = false)
	private BigDecimal loanAmount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
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
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
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
	public String getSell_type() {
		return sell_type;
	}
	public void setSell_type(String sell_type) {
		this.sell_type = sell_type;
	}
	public BigDecimal getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(BigDecimal total_cost) {
		this.total_cost = total_cost;
	}
	public BigDecimal getNakedCar_purchase_price() {
		return nakedCar_purchase_price;
	}
	public void setNakedCar_purchase_price(BigDecimal nakedCar_purchase_price) {
		this.nakedCar_purchase_price = nakedCar_purchase_price;
	}
	public BigDecimal getInvoice_price() {
		return invoice_price;
	}
	public void setInvoice_price(BigDecimal invoice_price) {
		this.invoice_price = invoice_price;
	}
	public BigDecimal getGpsFee() {
		return gpsFee;
	}
	public void setGpsFee(BigDecimal gpsFee) {
		this.gpsFee = gpsFee;
	}
	public BigDecimal getCarLicenseFee() {
		return carLicenseFee;
	}
	public void setCarLicenseFee(BigDecimal carLicenseFee) {
		this.carLicenseFee = carLicenseFee;
	}
	public BigDecimal getAwardChannel() {
		return awardChannel;
	}
	public void setAwardChannel(BigDecimal awardChannel) {
		this.awardChannel = awardChannel;
	}
	public BigDecimal getBusinessRewards() {
		return businessRewards;
	}
	public void setBusinessRewards(BigDecimal businessRewards) {
		this.businessRewards = businessRewards;
	}
	public BigDecimal getPurchaseTax() {
		return purchaseTax;
	}
	public void setPurchaseTax(BigDecimal purchaseTax) {
		this.purchaseTax = purchaseTax;
	}
	public BigDecimal getPipeFee() {
		return pipeFee;
	}
	public void setPipeFee(BigDecimal pipeFee) {
		this.pipeFee = pipeFee;
	}
	public BigDecimal getComInsurance() {
		return comInsurance;
	}
	public void setComInsurance(BigDecimal comInsurance) {
		this.comInsurance = comInsurance;
	}
	public BigDecimal getReturnedInsurance() {
		return returnedInsurance;
	}
	public void setReturnedInsurance(BigDecimal returnedInsurance) {
		this.returnedInsurance = returnedInsurance;
	}
	public BigDecimal getReturnedGps() {
		return returnedGps;
	}
	public void setReturnedGps(BigDecimal returnedGps) {
		this.returnedGps = returnedGps;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Integer getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(Integer serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getFinancialPro() {
		return financialPro;
	}
	public void setFinancialPro(String financialPro) {
		this.financialPro = financialPro;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	
	
	
}
