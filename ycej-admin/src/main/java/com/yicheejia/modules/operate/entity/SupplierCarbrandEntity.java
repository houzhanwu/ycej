package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 供应商品牌关联信息表
 * 
 * @author 
 * @email 
 * @date 2018-10-27 15:51:56
 */
@TableName("ycej_supplier_carbrand")
public class SupplierCarbrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 供应商品牌ID（主键）
	 */
	@TableId
	private Integer id;
	/**
	 * 供应商ID
	 */
	private String supplierId;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 车辆品牌
	 */
	private String carBrand;
	/**
	 * 车辆品牌名称
	 */
	private String carBrandName;
	/**
	 * 添加人
	 */
	private String insertUser;
	/**
	 * 添加时间
	 */
	private Date insertTime;
	/**
	 * 修改人
	 */
	private String updateUser;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 车辆品牌名称--for 多选品牌
	 */
	@TableField(exist = false)
	private String name;
	/**
	 * 车辆品牌value值--for 多选品牌
	 */
	@TableField(exist = false)
	private String value;
	
	/**
	 * 设置：供应商品牌ID（主键）
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：供应商品牌ID（主键）
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：供应商ID
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：供应商ID
	 */
	public String getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：供应商名称
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * 获取：供应商名称
	 */
	public String getSupplierName() {
		return supplierName;
	}
	/**
	 * 设置：车辆品牌
	 */
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	/**
	 * 获取：车辆品牌
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
	 * 设置：添加人
	 */
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	/**
	 * 获取：添加人
	 */
	public String getInsertUser() {
		return insertUser;
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
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：修改人
	 */
	public String getUpdateUser() {
		return updateUser;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
