package com.yicheejia.modules.financialmanage.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 
 * @author 梁占豪
 * @email zr_liangzhanhao@163.com
 * @date 2018-06-24 10:54:54
 */
@TableName("ycej_order")
public class YcejFinanceCheckEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 所属店铺
	 */
	@TableField(exist = false)
	private String belongShop;
	/**
	 * 客户姓名
	 */
	private String customer_name;
	/**
	 * 联系方式
	 */
	private String contact_information;
	/**
	 * 品牌
	 */
	@TableField(exist = false)
	private String carBrand;
	/**
	 * 车系
	 */
	@TableField(exist = false)
	private String carSeries;
	/**
	 * 车型
	 */
	@TableField(exist = false)
	private String carModel;
	/**
	 * VIN码（车架号）
	 */
	@TableField(exist = false)
	private String vin;
	/**
	 * 销售类型
	 */
	private Integer sell_type;
	/**
	 * 销售价
	 */
	private BigDecimal sell_price;
	/**
	 * 定金收款方式
	 */
	@TableField(exist = false)
	private int appoint_pay_type;
	/**
	 * 定金收款时间
	 */
	@TableField(exist = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date appoint_time;

	/**
	 * 定金
	 */
	@TableField(exist = false)
	private BigDecimal appoint_money;
	/**
	 * 首付收款方式
	 */
	@TableField(exist = false)
	private int down_pay_type;
	/**
	 * 首付收款时间
	 */
	@TableField(exist = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date down_time;
	/**
	 * 首付金额
	 */
	@TableField(exist = false)
	private BigDecimal down_payment;
	/**
	 * 保险
	 */
	@TableField(exist = false)
	private BigDecimal insurance;
	/**
	 * 续保押金/优壹车保证金
	 */
	@TableField(exist = false)
	private BigDecimal renew_deposit;
	/**
	 * 上牌费
	 */
	@TableField(exist = false)
	private BigDecimal car_license_fee;
	/**
	 * 手续费
	 */
	@TableField(exist = false)
	private BigDecimal handling_charge;
	/**
	 * 店保详情
	 */
	@TableField(exist = false)
	private int shop_protected_detail;
	/**
	 * 店保金额
	 */
	@TableField(exist = false)
	private BigDecimal shop_protected_fee;
	/**
	 * 合计金额=
	 */
	@TableField(exist = false)
	private BigDecimal total_money;
	/**
	 * 订单状态
	 */
	private String status;
	
	

	public BigDecimal getAppoint_money() {
		return appoint_money;
	}
	public void setAppoint_money(BigDecimal appoint_money) {
		this.appoint_money = appoint_money;
	}
	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：所属店铺
	 */
	public void setBelongShop(String belongShop) {
		this.belongShop = belongShop;
	}
	/**
	 * 获取：所属店铺
	 */
	public String getBelongShop() {
		return belongShop;
	}
	/**
	 * 设置：品牌
	 */
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	/**
	 * 获取：品牌
	 */
	public String getCarBrand() {
		return carBrand;
	}
	/**
	 * 设置：车系
	 */
	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}
	/**
	 * 获取：车系
	 */
	public String getCarSeries() {
		return carSeries;
	}
	/**
	 * 设置：车型
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	/**
	 * 获取：车型
	 */
	public String getCarModel() {
		return carModel;
	}
	/**
	 * 设置：订单状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态
	 */
	public String getStatus() {
		return status;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public int getAppoint_pay_type() {
		return appoint_pay_type;
	}
	public void setAppoint_pay_type(int appoint_pay_type) {
		this.appoint_pay_type = appoint_pay_type;
	}
	public Date getAppoint_time() {
		return appoint_time;
	}
	public void setAppoint_time(Date appoint_time) {
		this.appoint_time = appoint_time;
	}
	public int getDown_pay_type() {
		return down_pay_type;
	}
	public void setDown_pay_type(int down_pay_type) {
		this.down_pay_type = down_pay_type;
	}
	public Date getDown_time() {
		return down_time;
	}
	public void setDown_time(Date down_time) {
		this.down_time = down_time;
	}
	public BigDecimal getDown_payment() {
		return down_payment;
	}
	public void setDown_payment(BigDecimal down_payment) {
		this.down_payment = down_payment;
	}
	public BigDecimal getInsurance() {
		return insurance;
	}
	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
	}
	public BigDecimal getCar_license_fee() {
		return car_license_fee;
	}
	public void setCar_license_fee(BigDecimal car_license_fee) {
		this.car_license_fee = car_license_fee;
	}
	public BigDecimal getHandling_charge() {
		return handling_charge;
	}
	public void setHandling_charge(BigDecimal handling_charge) {
		this.handling_charge = handling_charge;
	}
	public int getShop_protected_detail() {
		return shop_protected_detail;
	}
	public void setShop_protected_detail(int shop_protected_detail) {
		this.shop_protected_detail = shop_protected_detail;
	}
	public BigDecimal getShop_protected_fee() {
		return shop_protected_fee;
	}
	public void setShop_protected_fee(BigDecimal shop_protected_fee) {
		this.shop_protected_fee = shop_protected_fee;
	}
	public BigDecimal getTotal_money() {
		return total_money;
	}
	public void setTotal_money(BigDecimal total_money) {
		this.total_money = total_money;
	}
	public BigDecimal getSell_price() {
		return sell_price;
	}
	public void setSell_price(BigDecimal sell_price) {
		this.sell_price = sell_price;
	}
	public Integer getSell_type() {
		return sell_type;
	}
	public void setSell_type(Integer sell_type) {
		this.sell_type = sell_type;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getContact_information() {
		return contact_information;
	}
	public void setContact_information(String contact_information) {
		this.contact_information = contact_information;
	}
	public BigDecimal getRenew_deposit() {
		return renew_deposit;
	}
	public void setRenew_deposit(BigDecimal renew_deposit) {
		this.renew_deposit = renew_deposit;
	}
	
	
}
