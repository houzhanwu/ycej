package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 省份表
 * 
 * @author
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
@TableName("ycej_province")
public class ProvinceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 省份ID
	 */
	@TableId
	private Integer provinceId;
	/**
	 * 省份名
	 */
	private String provinceName;
	/**
	 * 是否启用
	 */
	private Integer isEnabled;

	/**
	 * 设置：省份ID
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：省份ID
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：省份名
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * 获取：省份名
	 */
	public String getProvinceName() {
		return provinceName;
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
