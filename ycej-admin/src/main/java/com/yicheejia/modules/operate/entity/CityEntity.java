package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 地级市表
 * 
 * @author
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
@TableName("ycej_city")
public class CityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 市ID
	 */
	@TableId
	private Integer cityId;
	/**
	 * 市名
	 */
	private String cityName;
	/**
	 * 所属省ID
	 */
	private Integer provinceId;
	/**
	 * 区号
	 */
	private Integer cityAreacode;
	/**
	 * 是否启用
	 */
	private Integer isEnabled;

	/**
	 * 设置：市ID
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：市ID
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * 设置：市名
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * 获取：市名
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * 设置：所属省ID
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：所属省ID
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：区号
	 */
	public void setCityAreacode(Integer cityAreacode) {
		this.cityAreacode = cityAreacode;
	}
	/**
	 * 获取：区号
	 */
	public Integer getCityAreacode() {
		return cityAreacode;
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
