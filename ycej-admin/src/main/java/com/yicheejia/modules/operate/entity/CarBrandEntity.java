package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author luwen
 * @email ${email}
 * @date 2018-07-03 16:35:00
 */
@TableName("ycej_car_brand")
public class CarBrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer carBrandId;
	/**
	 * 品牌
	 */
	private String carBrand;
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
	 * 是否启用
	 */
	private Integer isEnabled;

	/**
	 * 设置：
	 */
	public void setCarBrandId(Integer carBrandId) {
		this.carBrandId = carBrandId;
	}
	/**
	 * 获取：
	 */
	public Integer getCarBrandId() {
		return carBrandId;
	}
	/**
	 * 设置：
	 */
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	/**
	 * 获取：
	 */
	public String getCarBrand() {
		return carBrand;
	}
	/**
	 * 设置：添加人
	 */
	public void setInsertId(String insertId) {
		this.insertId = insertId;
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
	 * 设置：
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	/**
	 * 获取：
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
	 * 设置：是否启用
	 */
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * 获取：是否启用
	 */
	public Integer getIsEnabled() {
		return isEnabled;
	}
}
