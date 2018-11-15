package com.yicheejia.modules.inquiry.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源客户品牌表
 * 
 * @author 
 * @email 
 * @date 2018-10-23 16:44:05
 */
@TableName("ycej_resource_brand")
public class ResourceBrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long resourceCustomerId;
	/**
	 * 
	 */
	private Long carBrandId;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setResourceCustomerId(Long resourceCustomerId) {
		this.resourceCustomerId = resourceCustomerId;
	}
	/**
	 * 获取：
	 */
	public Long getResourceCustomerId() {
		return resourceCustomerId;
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
}
