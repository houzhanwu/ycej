package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 车型表
 * 
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
@TableName("ycej_car_model")
public class CarModelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 车型ID（主键）
	 */
	@TableId
	private Integer carModelId;
	/**
	 * 车系ID（对应车系表的主键）
	 */
	private Integer carSeriesId;
	/**
	 * 品牌
	 */
	@TableField(exist = false)
	private String carBrand;
	/**
	 * 厂商
	 */
	@TableField(exist = false)
	private String carManufacturer;
	/**
	 * 车系
	 */
	@TableField(exist = false)
	private String carSeries;
	/**
	 * 车型
	 */
	private String carModel;
	/**
	 * 添加人userName
	 */
	private String insertId;
	/**
	 * 添加人name
	 */
	@TableField(exist = false)
	private String insertName;
	/**
	 * 添加时间
	 */
	private Date insertTime;
	/**
	 * 修改人
	 */
	private String updateId;
	/**
	 * 修改人name
	 */
	@TableField(exist = false)
	private String updateName;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 是否启用（0:停用；1:启用）
	 */
	private Integer isEnabled;
	/**
	 * 指导价
	 */
	private BigDecimal guidePrice;
	/**
	 * 排量
	 */
	private String carEmissions;
	/**
	 * 座位数
	 */
	private Integer seatNum;
	
	public String getInsertName() {
		return insertName;
	}
	public void setInsertName(String insertName) {
		this.insertName = insertName;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	/**
	 * 设置：车型ID（主键）
	 */
	public void setCarModelId(Integer carModelId) {
		this.carModelId = carModelId;
	}
	/**
	 * 获取：车型ID（主键）
	 */
	public Integer getCarModelId() {
		return carModelId;
	}
	/**
	 * 设置：车系ID（对应车系表的主键）
	 */
	public void setCarSeriesId(Integer carSeriesId) {
		this.carSeriesId = carSeriesId;
	}
	/**
	 * 获取：车系ID（对应车系表的主键）
	 */
	public Integer getCarSeriesId() {
		return carSeriesId;
	}
	/**
	 * 获取：车系
	 */
	public String getCarSeries() {
		return carSeries;
	}
	/**
	 * 设置：车系
	 */
	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
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
	 * 设置：添加人
	 */
	public void setInsertId(String string) {
		this.insertId = string;
	}
	/**
	 * 获取：添加人
	 */
	public String getInsertId() {
		return insertId;
	}
	/**
	 * 设置：添加时间
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * 设置：修改人
	 */
	public void setUpdateId(String string) {
		this.updateId = string;
	}
	/**
	 * 获取：修改人
	 */
	public String getUpdateId() {
		return updateId;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：是否启用（0:停用；1:启用）
	 */
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * 获取：是否启用（0:停用；1:启用）
	 */
	public Integer getIsEnabled() {
		return isEnabled;
	}
	/**
	 * 设置：指导价
	 */
	public void setGuidePrice(BigDecimal guidePrice) {
		this.guidePrice = guidePrice;
	}
	/**
	 * 获取：指导价
	 */
	public BigDecimal getGuidePrice() {
		return guidePrice;
	}
	/**
	 * 设置：排量
	 */
	public void setCarEmissions(String carEmissions) {
		this.carEmissions = carEmissions;
	}
	/**
	 * 获取：排量
	 */
	public String getCarEmissions() {
		return carEmissions;
	}
	/**
	 * 设置：座位数
	 */
	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}
	/**
	 * 获取：座位数
	 */
	public Integer getSeatNum() {
		return seatNum;
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
}
