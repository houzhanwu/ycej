package com.yicheejia.modules.operate.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class CalculatorEntity implements Serializable {
		private static final long serialVersionUID = 1L;
		
		/**
		 * 门店属性
		 */
		private Integer shopAttr;
		/**
		 * 车辆售价
		 */
		private double salePrice;
		/**
		 * 产品方案ID
		 */
		private String productId;
		/**
		 * 	融资期限24期1；36期2
		 */
		private String financingDate;
		/**
		 * 金融服务费融资额
		 */
		private Integer serviceCharge;
		/**
		 * GPS融资额
		 */
		private Integer gpsCharge;
		/**
		 * 排量
		 */
		private double emissions;
		/**
		 * 座位数
		 */
		private Integer seatNum;
		/**
		 * 第三方责任险 0:未选择；1:30万；2:50万；3:100万
		 */
		private Integer third;
		/**
		 * 玻璃破碎险0:未选择；1:国内；2:进口
		 */
		private Integer glass;
		/**
		 *  车辆损失险
		 */
		private Integer lose;
		/**
		 * 乘员责任险(驾驶人)
		 */
		private Integer driver;
		/**
		 * 乘员责任险(乘客)
		 */
		private Integer passenger;
		/**
		 * 盗抢险
		 */
		private Integer robbery;
		/**
		 * 车身划痕险
		 */
		private Integer scratch;
		/**
		 * 基本险不计免赔
		 */
		private Integer noCompensate;
		/**
		 * 保险金额(交强)
		 */
		private BigDecimal jqxPrice;
		/**
		 * 保险金额(商业)
		 */
		private BigDecimal insurancePrice;
		/**
		 * 保险返利
		 */
		private BigDecimal insuranceRebate;
		/**
		 * 金融返利
		 */
		private BigDecimal financeRebate;
		/**
		 * 返利总金额
		 */
		private BigDecimal allRebate;
		/**
		 * 融资总额
		 */
		private BigDecimal allCharge;
		/**
		 * 商业险返利 insuranceRe 
		 */
		private BigDecimal insuranceRe;
		/**
		 * 金融产品返利
		 */
		private BigDecimal productRebate;
		/**
		 * GPS返利
		 */
		private BigDecimal gpsRe;
		
		public BigDecimal getGpsRe() {
			return gpsRe;
		}
		public void setGpsRe(BigDecimal gpsRe) {
			this.gpsRe = gpsRe;
		}
		public BigDecimal getProductRebate() {
			return productRebate;
		}
		public void setProductRebate(BigDecimal productRebate) {
			this.productRebate = productRebate;
		}
		public BigDecimal getInsuranceRe() {
			return insuranceRe;
		}
		public void setInsuranceRe(BigDecimal insuranceRe) {
			this.insuranceRe = insuranceRe;
		}
		public BigDecimal getAllCharge() {
			return allCharge;
		}
		public void setAllCharge(BigDecimal allCharge) {
			this.allCharge = allCharge;
		}
		public Integer getShopAttr() {
			return shopAttr;
		}
		public void setShopAttr(Integer shopAttr) {
			this.shopAttr = shopAttr;
		}
		public double getSalePrice() {
			return salePrice;
		}
		public void setSalePrice(double salePrice) {
			this.salePrice = salePrice;
		}
		public String getProductId() {
			return productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
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
		public Integer getGpsCharge() {
			return gpsCharge;
		}
		public void setGpsCharge(Integer gpsCharge) {
			this.gpsCharge = gpsCharge;
		}
		public double getEmissions() {
			return emissions;
		}
		public void setEmissions(double emissions) {
			this.emissions = emissions;
		}
		public Integer getSeatNum() {
			return seatNum;
		}
		public void setSeatNum(Integer seatNum) {
			this.seatNum = seatNum;
		}
		public Integer getThird() {
			return third;
		}
		public void setThird(Integer third) {
			this.third = third;
		}
		public Integer getGlass() {
			return glass;
		}
		public void setGlass(Integer glass) {
			this.glass = glass;
		}
		public Integer getLose() {
			return lose;
		}
		public void setLose(Integer lose) {
			this.lose = lose;
		}
		public Integer getDriver() {
			return driver;
		}
		public void setDriver(Integer driver) {
			this.driver = driver;
		}
		public Integer getPassenger() {
			return passenger;
		}
		public void setPassenger(Integer passenger) {
			this.passenger = passenger;
		}
		public Integer getRobbery() {
			return robbery;
		}
		public void setRobbery(Integer robbery) {
			this.robbery = robbery;
		}
		public Integer getScratch() {
			return scratch;
		}
		public void setScratch(Integer scratch) {
			this.scratch = scratch;
		}
		public Integer getNoCompensate() {
			return noCompensate;
		}
		public void setNoCompensate(Integer noCompensate) {
			this.noCompensate = noCompensate;
		}
		public BigDecimal getInsurancePrice() {
			return insurancePrice;
		}
		public void setInsurancePrice(BigDecimal insurancePrice) {
			this.insurancePrice = insurancePrice;
		}
		public BigDecimal getInsuranceRebate() {
			return insuranceRebate;
		}
		public void setInsuranceRebate(BigDecimal insuranceRebate) {
			this.insuranceRebate = insuranceRebate;
		}
		public BigDecimal getFinanceRebate() {
			return financeRebate;
		}
		public void setFinanceRebate(BigDecimal financeRebate) {
			this.financeRebate = financeRebate;
		}
		public BigDecimal getAllRebate() {
			return allRebate;
		}
		public void setAllRebate(BigDecimal allRebate) {
			this.allRebate = allRebate;
		}
		public BigDecimal getJqxPrice() {
			return jqxPrice;
		}
		public void setJqxPrice(BigDecimal jqxPrice) {
			this.jqxPrice = jqxPrice;
		}
}
