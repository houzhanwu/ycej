package com.yicheejia.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 成交客户
 * 
 * @author cn
 * @email 
 * @date 2018-07-02 09:15:15
 */
@TableName("ycej_customer")
public class CustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 客户id
	 */
	@TableId
	private Integer customerId;
	/**
	 * 客户姓名
	 */
	private String customerName;
	/**
	 * 客户手机号
	 */
	private String customerTel;
	/**
	 * 客户身份证号
	 */
	private String cardNo;
	/**
	 * 购买车型
	 */
	private String carType;
	/**
	 * 购买日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date buyDate;
	/**
	 * 车辆颜色
	 */
	private String carColor;
	/**
	 * 购买方式
	 */
	private Integer buyManner;
	/**
	 * 交车日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date handoverTime;
	/**
	 * 交车门店
	 */
	private String handoverShop;
	/**
	 * 保险金额
	 */
	private BigDecimal insuranceSum;
	/**
	 * 保险生效日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date insuranceDate;
	/**
	 * 续保押金
	 */
	private BigDecimal insuranceDiposit;
	/**
	 * 分期年限
	 */
	private Integer instalmentYear;
	/**
	 * 创建时间
	 */
	private Date insTime;
	/**
	 * 创建人
	 */
	private String insUser;
	/**
	 * 修改时间
	 */
	private Date updTime;
	/**
	 * 修改人
	 */
	private String updUser;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 门店id
	 */
	private Integer shopId;
	/**
	 * 客户状态
	 */
	private Integer customerState;

	@TableField(exist = false)
	private String carModel;

	@TableField(exist = false)
	private String handOverShopName;


	/**
	 * 设置：客户id
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：客户id
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * 设置：客户姓名
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取：客户姓名
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置：客户手机号
	 */
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	/**
	 * 获取：客户手机号
	 */
	public String getCustomerTel() {
		return customerTel;
	}
	/**
	 * 设置：客户身份证号
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * 获取：客户身份证号
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * 设置：购买车型
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}
	/**
	 * 获取：购买车型
	 */
	public String getCarType() {
		return carType;
	}
	/**
	 * 设置：购买日期
	 */
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	/**
	 * 获取：购买日期
	 */
	public Date getBuyDate() {
		return buyDate;
	}
	/**
	 * 设置：车辆颜色
	 */
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	/**
	 * 获取：车辆颜色
	 */
	public String getCarColor() {
		return carColor;
	}
	/**
	 * 设置：购买方式
	 */
	public void setBuyManner(Integer buyManner) {
		this.buyManner = buyManner;
	}
	/**
	 * 获取：购买方式
	 */
	public Integer getBuyManner() {
		return buyManner;
	}
	/**
	 * 设置：交车日期
	 */
	public void setHandoverTime(Date handoverTime) {
		this.handoverTime = handoverTime;
	}
	/**
	 * 获取：交车日期
	 */
	public Date getHandoverTime() {
		return handoverTime;
	}
	/**
	 * 设置：交车门店
	 */
	public void setHandoverShop(String handoverShop) {
		this.handoverShop = handoverShop;
	}
	/**
	 * 获取：交车门店
	 */
	public String getHandoverShop() {
		return handoverShop;
	}
	/**
	 * 设置：保险金额
	 */
	public void setInsuranceSum(BigDecimal insuranceSum) {
		this.insuranceSum = insuranceSum;
	}
	/**
	 * 获取：保险金额
	 */
	public BigDecimal getInsuranceSum() {
		return insuranceSum;
	}
	/**
	 * 设置：保险生效日期
	 */
	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}
	/**
	 * 获取：保险生效日期
	 */
	public Date getInsuranceDate() {
		return insuranceDate;
	}
	/**
	 * 设置：续保押金
	 */
	public void setInsuranceDiposit(BigDecimal insuranceDiposit) {
		this.insuranceDiposit = insuranceDiposit;
	}
	/**
	 * 获取：续保押金
	 */
	public BigDecimal getInsuranceDiposit() {
		return insuranceDiposit;
	}
	/**
	 * 设置：分期年限
	 */
	public void setInstalmentYear(Integer instalmentYear) {
		this.instalmentYear = instalmentYear;
	}
	/**
	 * 获取：分期年限
	 */
	public Integer getInstalmentYear() {
		return instalmentYear;
	}
	/**
	 * 设置：创建时间
	 */
	public void setInsTime(Date insTime) {
		this.insTime = insTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getInsTime() {
		return insTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
	/**
	 * 获取：创建人
	 */
	public String getInsUser() {
		return insUser;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdTime() {
		return updTime;
	}
	/**
	 * 设置：修改人
	 */
	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}
	/**
	 * 获取：修改人
	 */
	public String getUpdUser() {
		return updUser;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：门店id
	 */
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：门店id
	 */
	public Integer getShopId() {
		return shopId;
	}

	public Integer getCustomerState() {
		return customerState;
	}

	public void setCustomerState(Integer customerState) {
		this.customerState = customerState;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getHandOverShopName() {
		return handOverShopName;
	}

	public void setHandOverShopName(String handOverShopName) {
		this.handOverShopName = handOverShopName;
	}
}
