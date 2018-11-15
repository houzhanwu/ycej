package com.yicheejia.modules.financialmanage.excel;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class CostStatisticsBean {

	
	/*
	 * 订单编号
	 */
	@Excel(name = "订单编号")
	private String order_no;
	/*
	 * 所属店铺
	 */
	@Excel(name = "所属店铺")
	private String belongShop;
	/*
	 * 客户姓名
	 */
	@Excel(name = "客户姓名")
	private String customer_name;
	/*
	 * 销售日期
	 */
	@Excel(name = "销售日期",format="yyyy-MM-dd")
	private Date saleTime;
	/*
	 * 车型
	 */
	@Excel(name = "车型")
	private String carModel;
	/*
	 * 销售类型
	 */
	@Excel(name = "销售类型")
	private String sell_type;
	/*
	 * 总成本
	 */
	@Excel(name = "总成本")
	private BigDecimal total_cost;
	/*
	 * 裸车采购价
	 */
	@Excel(name = "裸车采购价")
	private BigDecimal nakedCar_purchase_price;
	/*
	 * 车辆发票价
	 */
	@Excel(name = "车辆发票价")
	private BigDecimal invoice_price;
	/*
	 * 贷款产品方案
	 */
	@Excel(name = "贷款产品方案")
	private String financialPro;
	/*
	 * 裸车贷款金额
	 */
	@Excel(name = "裸车贷款金额")
	private BigDecimal loanAmount;
	/*
	 * gps费用
	 */
	@Excel(name = "gps费用")
	private BigDecimal gpsFee;
	/*
	 * 上牌费
	 */
	@Excel(name = "上牌费")
	private BigDecimal carLicenseFee;
	/*
	 * 渠道奖励
	 */
	@Excel(name = "渠道奖励")
	private BigDecimal awardChannel;
	/*
	 * 业务奖励
	 */
	@Excel(name = "业务奖励")
	private BigDecimal businessRewards;
	/*
	 * 购置税
	 */
	@Excel(name = "购置税")
	private BigDecimal purchaseTax;
	/*
	 * 运管费
	 */
	@Excel(name = "运管费")
	private BigDecimal pipeFee;
	/*
	 * 商业险
	 */
	@Excel(name = "商业险")
	private BigDecimal comInsurance;
	/*
	 * 返保险
	 */
	@Excel(name = "返保险")
	private BigDecimal returnedInsurance;
	/*
	 * 返GPS
	 */
	@Excel(name = "返GPS")
	private BigDecimal returnedGps;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
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
