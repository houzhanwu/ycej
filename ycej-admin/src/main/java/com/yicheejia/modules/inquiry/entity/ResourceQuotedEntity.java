package com.yicheejia.modules.inquiry.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 资源报价表
 * 
 * @author 
 * @email 
 * @date 2018-10-23 16:44:05
 */
@TableName("ycej_resource_quoted")
public class ResourceQuotedEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 报价id
	 */
	@TableId(type = IdType.ID_WORKER)
	private Long quotedId;
	/**
	 * 
	 */
	private Long carBrandId;
	/**
	 * 品牌
	 */
	private String carBrand;
	/**
	 * 
	 */
	private Long carManufacturerId;
	/**
	 * 厂商
	 */
	private String carManufacturer;
	/**
	 * 
	 */
	private Long carSeriesId;
	/**
	 * 车系
	 */
	private String carSeries;
	/**
	 * 
	 */
	private Long carModelId;
	/**
	 * 车型
	 */
	private String carModel;
	/**
	 * 外观颜色
	 */
	private Long carAppearanceColor;
	/**
	 * 排量
	 */
	private Double carEmissions;
	/**
	 * 供应价
	 */
	private BigDecimal offerPrice;
	/**
	 * 资源名称
	 */
	private String resourceName;
	/**
	 * 报价时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date quotedTime;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 报价用户id
	 */
	private Long resourceId;

	/**
	 * 报价历史记录
	 */
	@TableField(exist = false)
	private List<?> histories;

	public List<?> getHistories() {
		return histories;
	}

	public void setHistories(List<?> histories) {
		this.histories = histories;
	}

	public Long getQuotedId() {
		return quotedId;
	}

	public void setQuotedId(Long quotedId) {
		this.quotedId = quotedId;
	}

	/**
	 * 设置：
	 */
	public void setCarBrandId(Long carBrandId) {
		this.carBrandId = carBrandId;
	}
	/**
	 * 获取：
	 */
	public Long getCarBrandId() {
		return carBrandId;
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
	 * 设置：
	 */
	public void setCarManufacturerId(Long carManufacturerId) {
		this.carManufacturerId = carManufacturerId;
	}
	/**
	 * 获取：
	 */
	public Long getCarManufacturerId() {
		return carManufacturerId;
	}
	/**
	 * 设置：厂商
	 */
	public void setCarManufacturer(String carManufacturer) {
		this.carManufacturer = carManufacturer;
	}
	/**
	 * 获取：厂商
	 */
	public String getCarManufacturer() {
		return carManufacturer;
	}
	/**
	 * 设置：
	 */
	public void setCarSeriesId(Long carSeriesId) {
		this.carSeriesId = carSeriesId;
	}
	/**
	 * 获取：
	 */
	public Long getCarSeriesId() {
		return carSeriesId;
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
	 * 设置：
	 */
	public void setCarModelId(Long carModelId) {
		this.carModelId = carModelId;
	}
	/**
	 * 获取：
	 */
	public Long getCarModelId() {
		return carModelId;
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

	public Long getCarAppearanceColor() {
		return carAppearanceColor;
	}

	public void setCarAppearanceColor(Long carAppearanceColor) {
		this.carAppearanceColor = carAppearanceColor;
	}

	/**
	 * 设置：排量
	 */
	public void setCarEmissions(Double carEmissions) {
		this.carEmissions = carEmissions;
	}
	/**
	 * 获取：排量
	 */
	public Double getCarEmissions() {
		return carEmissions;
	}
	/**
	 * 设置：供应价
	 */
	public void setOfferPrice(BigDecimal offerPrice) {
		this.offerPrice = offerPrice;
	}
	/**
	 * 获取：供应价
	 */
	public BigDecimal getOfferPrice() {
		return offerPrice;
	}
	/**
	 * 设置：资源名称
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	/**
	 * 获取：资源名称
	 */
	public String getResourceName() {
		return resourceName;
	}
	/**
	 * 设置：报价时间
	 */
	public void setQuotedTime(Date quotedTime) {
		this.quotedTime = quotedTime;
	}
	/**
	 * 获取：报价时间
	 */
	public Date getQuotedTime() {
		return quotedTime;
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

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

}
