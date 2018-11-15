package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-11-02 11:08:40
 */
@TableName("ycej_incoming_detail")
public class IncomingDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日常入库明细表主键
	 */
	@TableId
	private Long id;
	/**
	 * 日常入库表主键
	 */
	private String dailyIncomingId;
	/**
	 * 车辆品牌value值
	 */
	private String carBrand;
	/**
	 * 车辆品牌名称
	 */
	private String carBrandName;
	/**
	 * 厂商value值
	 */
	private String carManufacturer;
	/**
	 * 厂商名称
	 */
	private String carManufacturerName;
	/**
	 * 车系value值
	 */
	private String carSeries;
	/**
	 * 车系名称
	 */
	private String carSeriesName;
	/**
	 * 车型value值
	 */
	private String carModel;
	/**
	 * 车型名称
	 */
	private String carModelName;
	/**
	 * 排量
	 */
	private String carDisplacement;
	/**
	 * 座位数
	 */
	private Integer carSeat;
	/**
	 * 外观value值
	 */
	private Integer appearance;
	/**
	 * 外观颜色
	 */
	private String appearanceColor;
	/**
	 * 车架号
	 */
	private String vin;
	/**
	 * 出厂日期
	 */
	private Date productDate;
	/**
	 * 入库日期
	 */
	private Date inboundDate;
	/**
	 * 操作人
	 */
	private String insertUser;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 设置：日常入库明细表主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：日常入库明细表主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：日常入库表主键
	 */
	public void setDailyIncomingId(String dailyIncomingId) {
		this.dailyIncomingId = dailyIncomingId;
	}
	/**
	 * 获取：日常入库表主键
	 */
	public String getDailyIncomingId() {
		return dailyIncomingId;
	}
	/**
	 * 设置：车辆品牌value值
	 */
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	/**
	 * 获取：车辆品牌value值
	 */
	public String getCarBrand() {
		return carBrand;
	}
	/**
	 * 设置：车辆品牌名称
	 */
	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}
	/**
	 * 获取：车辆品牌名称
	 */
	public String getCarBrandName() {
		return carBrandName;
	}
	/**
	 * 设置：厂商value值
	 */
	public void setCarManufacturer(String carManufacturer) {
		this.carManufacturer = carManufacturer;
	}
	/**
	 * 获取：厂商value值
	 */
	public String getCarManufacturer() {
		return carManufacturer;
	}
	/**
	 * 设置：厂商名称
	 */
	public void setCarManufacturerName(String carManufacturerName) {
		this.carManufacturerName = carManufacturerName;
	}
	/**
	 * 获取：厂商名称
	 */
	public String getCarManufacturerName() {
		return carManufacturerName;
	}
	/**
	 * 设置：车系value值
	 */
	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}
	/**
	 * 获取：车系value值
	 */
	public String getCarSeries() {
		return carSeries;
	}
	/**
	 * 设置：车系名称
	 */
	public void setCarSeriesName(String carSeriesName) {
		this.carSeriesName = carSeriesName;
	}
	/**
	 * 获取：车系名称
	 */
	public String getCarSeriesName() {
		return carSeriesName;
	}
	/**
	 * 设置：车型value值
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	/**
	 * 获取：车型value值
	 */
	public String getCarModel() {
		return carModel;
	}
	/**
	 * 设置：车型名称
	 */
	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}
	/**
	 * 获取：车型名称
	 */
	public String getCarModelName() {
		return carModelName;
	}
	/**
	 * 设置：排量
	 */
	public void setCarDisplacement(String carDisplacement) {
		this.carDisplacement = carDisplacement;
	}
	/**
	 * 获取：排量
	 */
	public String getCarDisplacement() {
		return carDisplacement;
	}
	/**
	 * 设置：座位数
	 */
	public void setCarSeat(Integer carSeat) {
		this.carSeat = carSeat;
	}
	/**
	 * 获取：座位数
	 */
	public Integer getCarSeat() {
		return carSeat;
	}
	/**
	 * 设置：外观value值
	 */
	public void setAppearance(Integer appearance) {
		this.appearance = appearance;
	}
	/**
	 * 获取：外观value值
	 */
	public Integer getAppearance() {
		return appearance;
	}
	/**
	 * 设置：外观颜色
	 */
	public void setAppearanceColor(String appearanceColor) {
		this.appearanceColor = appearanceColor;
	}
	/**
	 * 获取：外观颜色
	 */
	public String getAppearanceColor() {
		return appearanceColor;
	}
	/**
	 * 设置：车架号
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	/**
	 * 获取：车架号
	 */
	public String getVin() {
		return vin;
	}
	/**
	 * 设置：出厂日期
	 */
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	/**
	 * 获取：出厂日期
	 */
	public Date getProductDate() {
		return productDate;
	}
	/**
	 * 设置：入库日期
	 */
	public void setInboundDate(Date inboundDate) {
		this.inboundDate = inboundDate;
	}
	/**
	 * 获取：入库日期
	 */
	public Date getInboundDate() {
		return inboundDate;
	}
	/**
	 * 设置：操作人
	 */
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	/**
	 * 获取：操作人
	 */
	public String getInsertUser() {
		return insertUser;
	}
	/**
	 * 设置：
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：
	 */
	public Date getInsertTime() {
		return insertTime;
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
}
