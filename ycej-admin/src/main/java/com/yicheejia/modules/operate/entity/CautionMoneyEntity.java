package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-10-25 15:47:20
 */
@TableName("ycej_caution_money")
public class CautionMoneyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String cautionId;
	/**
	 * 展车台数
	 */
	private Integer carNumber;
	/**
	 * 保证金总额
	 */
	private BigDecimal allCaution;
	/**
	 * 单台保证金
	 */
	private BigDecimal oneCaution;
	/**
	 * 供应商ID
	 */
	private Long supplierId;
	/**
	 * 状态:1.暂存，2.资源总经理待审核，3.会计待审核，4.运营待审核，5.出纳待审核，6.完成
	 */
	private String status;
	/**
	 * 是否付款:1.是，0.否
	 */
	private Integer isPay;
	/**
	 * 车辆品牌ID
	 */
	private String brandsId;
	/**
	 * 车辆品牌
	 */
	private String carBrands;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 联系人
	 */
	private String supplierContact;
	/**
	 * 联系方式
	 */
	private String supplierMobile;
	/**
	 * 详细地址
	 */
	private String supplierAdd;
	/**
	 * 收款开户行
	 */
	private String dueBank;
	/**
	 * 收款开户支行
	 */
	private String branchBank;
	/**
	 * 收款账号
	 */
	private String supplierBankcard;
	/**
	 * 添加时间
	 */
	private Date insertTime;
	/**
	 * 添加人
	 */
	private String insertId;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 修改人
	 */
	private String updateId;
	/**
	 * 备注
	 */
	private String cauComment;
	/**
	 * 备注 --审核时填写的备注
	 */
	@TableField(exist = false)
	private String remark;
	/**
	 * 供应商类型
	 */
	private String supplierType;
	/**
	 * 车辆类型
	 */
	private String vehicleType;

	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 设置：主键ID
	 */
	public void setCautionId(String cautionId) {
		this.cautionId = cautionId;
	}
	/**
	 * 获取：主键ID
	 */
	public String getCautionId() {
		return cautionId;
	}
	/**
	 * 设置：展车台数
	 */
	public void setCarNumber(Integer carNumber) {
		this.carNumber = carNumber;
	}
	/**
	 * 获取：展车台数
	 */
	public Integer getCarNumber() {
		return carNumber;
	}
	/**
	 * 设置：保证金总额
	 */
	public void setAllCaution(BigDecimal allCaution) {
		this.allCaution = allCaution;
	}
	/**
	 * 获取：保证金总额
	 */
	public BigDecimal getAllCaution() {
		return allCaution;
	}
	/**
	 * 设置：单台保证金
	 */
	public void setOneCaution(BigDecimal oneCaution) {
		this.oneCaution = oneCaution;
	}
	/**
	 * 获取：单台保证金
	 */
	public BigDecimal getOneCaution() {
		return oneCaution;
	}
	/**
	 * 设置：供应商ID
	 */
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：供应商ID
	 */
	public Long getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：状态:1.暂存，2.资源总经理待审核，3.会计待审核，4.运营待审核，5.出纳待审核，6.完成
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态:1.暂存，2.资源总经理待审核，3.会计待审核，4.运营待审核，5.出纳待审核，6.完成
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：是否付款:1.是，0.否
	 */
	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}
	/**
	 * 获取：是否付款:1.是，0.否
	 */
	public Integer getIsPay() {
		return isPay;
	}
	/**
	 * 设置：车辆品牌ID
	 */
	public void setBrandsId(String brandsId) {
		this.brandsId = brandsId;
	}
	/**
	 * 获取：车辆品牌ID
	 */
	public String getBrandsId() {
		return brandsId;
	}
	/**
	 * 设置：车辆品牌
	 */
	public void setCarBrands(String carBrands) {
		this.carBrands = carBrands;
	}
	/**
	 * 获取：车辆品牌
	 */
	public String getCarBrands() {
		return carBrands;
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
	 * 设置：联系方式
	 */
	public void setSupplierMobile(String supplierMobile) {
		this.supplierMobile = supplierMobile;
	}
	/**
	 * 获取：联系方式
	 */
	public String getSupplierMobile() {
		return supplierMobile;
	}
	/**
	 * 设置：详细地址
	 */
	public void setSupplierAdd(String supplierAdd) {
		this.supplierAdd = supplierAdd;
	}
	/**
	 * 获取：详细地址
	 */
	public String getSupplierAdd() {
		return supplierAdd;
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
	 * 设置：收款账号
	 */
	public void setSupplierBankcard(String supplierBankcard) {
		this.supplierBankcard = supplierBankcard;
	}
	/**
	 * 获取：收款账号
	 */
	public String getSupplierBankcard() {
		return supplierBankcard;
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
	 * 设置：修改人
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	/**
	 * 获取：修改人
	 */
	public String getUpdateId() {
		return updateId;
	}
	/**
	 * 设置：备注
	 */
	public void setCauComment(String cauComment) {
		this.cauComment = cauComment;
	}
	/**
	 * 获取：备注
	 */
	public String getCauComment() {
		return cauComment;
	}
}
