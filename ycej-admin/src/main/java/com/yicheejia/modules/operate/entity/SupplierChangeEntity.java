package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 供应商变更信息表
 * 
 * @author 
 * @email 
 * @date 2018-10-27 15:51:56
 */
@TableName("ycej_supplier_change")
public class SupplierChangeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 供应商变更ID（主键）
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
	 * 联系人
	 */
	private String supplierContact;
	/**
	 * 联系电话
	 */
	private String supplierMoblie;
	/**
	 * 地址
	 */
	private String supplierAdd;
	/**
	 * 
	 */
	private Integer provinceId;
	/**
	 * 
	 */
	private String provinceName;
	/**
	 * 省市县信息编号（市ID）
	 */
	private Integer cityId;
	/**
	 * 
	 */
	private String cityName;
	/**
	 * 保证金金额
	 */
	private BigDecimal supplierBail;
	/**
	 * 提供展车数量
	 */
	private Integer supplierCarnum;
	/**
	 * 供应商收款账号
	 */
	private String supplierBankcard;
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
	 * 备注
	 */
	private String comment;
	/**
	 * 收款开户行code
	 */
	private Integer dueBankCode;
	/**
	 * 收款开户行
	 */
	private String dueBank;
	/**
	 * 收款开户支行
	 */
	private String branchBank;
	/**
	 * 供应商状态:1,暂存，2，待审核，3，已审核
	 */
	private Integer supplierState;
	/**
	 * 供应商类型
	 */
	private Integer supplierType;
	/**
	 * 车辆类型
	 */
	private Integer vehicleType;
	/**
	 * 车辆品牌
	 */
	private String carBrand;
	/**
	 * 车辆品牌名称
	 */
	private String carBrandName;
	/**
	 * 收款账号类型
	 */
	private Integer dueAccountType;
	/**
	 * 收款账号名称
	 */
	private String dueAccountName;

	/**
	 * 设置：供应商变更ID（主键）
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：供应商变更ID（主键）
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
	 * 设置：联系人
	 */
	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}
	/**
	 * 获取：联系人
	 */
	public String getSupplierContact() {
		return supplierContact;
	}
	/**
	 * 设置：联系电话
	 */
	public void setSupplierMoblie(String supplierMoblie) {
		this.supplierMoblie = supplierMoblie;
	}
	/**
	 * 获取：联系电话
	 */
	public String getSupplierMoblie() {
		return supplierMoblie;
	}
	/**
	 * 设置：地址
	 */
	public void setSupplierAdd(String supplierAdd) {
		this.supplierAdd = supplierAdd;
	}
	/**
	 * 获取：地址
	 */
	public String getSupplierAdd() {
		return supplierAdd;
	}
	/**
	 * 设置：
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * 获取：
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * 设置：省市县信息编号（市ID）
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：省市县信息编号（市ID）
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * 设置：
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * 获取：
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * 设置：保证金金额
	 */
	public void setSupplierBail(BigDecimal supplierBail) {
		this.supplierBail = supplierBail;
	}
	/**
	 * 获取：保证金金额
	 */
	public BigDecimal getSupplierBail() {
		return supplierBail;
	}
	/**
	 * 设置：提供展车数量
	 */
	public void setSupplierCarnum(Integer supplierCarnum) {
		this.supplierCarnum = supplierCarnum;
	}
	/**
	 * 获取：提供展车数量
	 */
	public Integer getSupplierCarnum() {
		return supplierCarnum;
	}
	/**
	 * 设置：供应商收款账号
	 */
	public void setSupplierBankcard(String supplierBankcard) {
		this.supplierBankcard = supplierBankcard;
	}
	/**
	 * 获取：供应商收款账号
	 */
	public String getSupplierBankcard() {
		return supplierBankcard;
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
	/**
	 * 设置：备注
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：备注
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：收款开户行code
	 */
	public void setDueBankCode(Integer dueBankCode) {
		this.dueBankCode = dueBankCode;
	}
	/**
	 * 获取：收款开户行code
	 */
	public Integer getDueBankCode() {
		return dueBankCode;
	}
	/**
	 * 设置：收款开户行
	 */
	public void setDueBank(String dueBank) {
		this.dueBank = dueBank;
	}
	/**
	 * 获取：收款开户行
	 */
	public String getDueBank() {
		return dueBank;
	}
	/**
	 * 设置：收款开户支行
	 */
	public void setBranchBank(String branchBank) {
		this.branchBank = branchBank;
	}
	/**
	 * 获取：收款开户支行
	 */
	public String getBranchBank() {
		return branchBank;
	}
	/**
	 * 设置：供应商状态:1,暂存，2，待审核，3，已审核
	 */
	public void setSupplierState(Integer supplierState) {
		this.supplierState = supplierState;
	}
	/**
	 * 获取：供应商状态:1,暂存，2，待审核，3，已审核
	 */
	public Integer getSupplierState() {
		return supplierState;
	}
	/**
	 * 设置：供应商类型
	 */
	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}
	/**
	 * 获取：供应商类型
	 */
	public Integer getSupplierType() {
		return supplierType;
	}
	/**
	 * 设置：车辆类型
	 */
	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}
	/**
	 * 获取：车辆类型
	 */
	public Integer getVehicleType() {
		return vehicleType;
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
	 * 设置：收款账号类型
	 */
	public void setDueAccountType(Integer dueAccountType) {
		this.dueAccountType = dueAccountType;
	}
	/**
	 * 获取：收款账号类型
	 */
	public Integer getDueAccountType() {
		return dueAccountType;
	}
	/**
	 * 设置：收款账号名称
	 */
	public void setDueAccountName(String dueAccountName) {
		this.dueAccountName = dueAccountName;
	}
	/**
	 * 获取：收款账号名称
	 */
	public String getDueAccountName() {
		return dueAccountName;
	}
}
