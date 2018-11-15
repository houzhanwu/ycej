package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 车系表
 * 
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
@TableName("ycej_car_series")
public class CarSeriesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 车系ID（主键）
	 */
	@TableId
	private Integer carSeriesId;
	/**
	 * 厂商ID（外键）
	 */
	private Integer carManufacturerId;
	/**
	 * 品牌
	 */
	@TableField(exist = false)
	private String carBrand;
	/**
	 * 品牌Id
	 */
	@TableField(exist = false)
	private String carBrandId;
	/**
	 * 厂商
	 */
	@TableField(exist = false)
	private String carManufacturer;
	/**
	 * 车系
	 */
	private String carSeries;
	/**
	 * 添加人
	 */
	private String insertId;
	/**
	 * 添加时间
	 */
	private Date insertTime;
	/**
	 * 修改人
	 */
	private String updateId;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 是否启用（0:停用；1:启用）
	 */
	private Integer isEnabled;

	public String getCarBrandId() {
		return carBrandId;
	}
	public void setCarBrandId(String carBrandId) {
		this.carBrandId = carBrandId;
	}
	/**
	 * 设置：车系ID（主键）
	 */
	public void setCarSeriesId(Integer carSeriesId) {
		this.carSeriesId = carSeriesId;
	}
	/**
	 * 获取：车系ID（主键）
	 */
	public Integer getCarSeriesId() {
		return carSeriesId;
	}
	/**
	 * 设置：品牌ID（外键）
	 */
	public void setCarManufacturerId(Integer carManufacturerId) {
		this.carManufacturerId = carManufacturerId;
	}
	/**
	 * 获取：品牌ID（外键）
	 */
	public Integer getCarManufacturerId() {
		return carManufacturerId;
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
	 * 获取：品牌
	 */
	public String getCarBrand() {
		return carBrand;
	}
	/**
	 * 设置：品牌
	 */
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	/**
	 * 获取：厂商名
	 */
	public String getCarManufacturer() {
		return carManufacturer;
	}
	/**
	 * 设置：厂商名
	 */
	public void setCarManufacturer(String carManufacturer) {
		this.carManufacturer = carManufacturer;
	}
	
}
