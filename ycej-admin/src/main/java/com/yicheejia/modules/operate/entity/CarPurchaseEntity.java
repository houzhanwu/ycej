package com.yicheejia.modules.operate.entity;

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
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-07-18 16:43:36
 */
@TableName("ycej_car_purchase")
public class CarPurchaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 车辆采购主键
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String purchId;
	/**
	 * 采购编号
	 */
	private String purchNo;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 供应商主键
	 */
	private String supplierId;
	/**
	 * 店保信息
	 */
	private Integer storeInfo;
	/**
	 * 车辆来源
	 */
	private Integer carFrom;
	/**
	 * 采购总金额
	 */
	private BigDecimal purchAmount;
	/**
	 * 采购数量
	 */
	private Integer count;
	/**
	 * 收款开户行
	 */
	private String openingBank;
	/**
	 * 收款开户支行
	 */
	private String openingBranchbank;
	/**
	 * 收款账号
	 */
	private String accountNo;
	/**
	 * 车辆类型
	 */
	private Integer carType;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 是否付款
	 */
	private Integer isPay;
	/**
	 * 采购日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date purchDate;
	/**
	 * 入库日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date inboundDate;
	/**
	 * 入库仓库
	 */
	private String warehouse;
	/**
	 * 创建时间
	 */
	private Date insertTime;
	/**
	 * 创建人
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
	private String remark;
	/*
	 * 采购明细请求list数据封装
	 */
	@TableField(exist = false)
	private List<PurchaseDetailEntity> detailList;
	/*
	 * 店保信息别名
	 */
	@TableField(exist = false)
	private String storeInfoValue;
	/*
	 * 车辆来源别名
	 */
	@TableField(exist = false)
	private String carFromValue;
	/*
	 * 车辆类型别名
	 */
	@TableField(exist = false)
	private String carTypeValue;
	/*
	 * 状态别名
	 */
	@TableField(exist = false)
	private String statusValue;
	/*
	 * 是否付款别名
	 */
	@TableField(exist = false)
	private String isPayValue;
	/*
	 * 入库仓库别名
	 */
	@TableField(exist = false)
	private String warehouseValue;
	

	/**
	 * 设置：车辆采购主键
	 */
	public void setPurchId(String purchId) {
		this.purchId = purchId;
	}
	/**
	 * 获取：车辆采购主键
	 */
	public String getPurchId() {
		return purchId;
	}
	/**
	 * 设置：采购编号
	 */
	public void setPurchNo(String purchNo) {
		this.purchNo = purchNo;
	}
	/**
	 * 获取：采购编号
	 */
	public String getPurchNo() {
		return purchNo;
	}
	/**
	 * 设置：供应商主键
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：供应商主键
	 */
	public String getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：店保信息
	 */
	public void setStoreInfo(Integer storeInfo) {
		this.storeInfo = storeInfo;
	}
	/**
	 * 获取：店保信息
	 */
	public Integer getStoreInfo() {
		return storeInfo;
	}
	/**
	 * 设置：车辆来源
	 */
	public void setCarFrom(Integer carFrom) {
		this.carFrom = carFrom;
	}
	/**
	 * 获取：车辆来源
	 */
	public Integer getCarFrom() {
		return carFrom;
	}
	/**
	 * 设置：采购总金额
	 */
	public void setPurchAmount(BigDecimal purchAmount) {
		this.purchAmount = purchAmount;
	}
	/**
	 * 获取：采购总金额
	 */
	public BigDecimal getPurchAmount() {
		return purchAmount;
	}
	/**
	 * 设置：采购数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：采购数量
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：收款开户行
	 */
	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}
	/**
	 * 获取：收款开户行
	 */
	public String getOpeningBank() {
		return openingBank;
	}
	/**
	 * 设置：收款开户支行
	 */
	public void setOpeningBranchbank(String openingBranchbank) {
		this.openingBranchbank = openingBranchbank;
	}
	/**
	 * 获取：收款开户支行
	 */
	public String getOpeningBranchbank() {
		return openingBranchbank;
	}
	/**
	 * 设置：收款账号
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * 获取：收款账号
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * 设置：车辆类型
	 */
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	/**
	 * 获取：车辆类型
	 */
	public Integer getCarType() {
		return carType;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：是否付款
	 */
	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}
	/**
	 * 获取：是否付款
	 */
	public Integer getIsPay() {
		return isPay;
	}
	/**
	 * 设置：采购日期
	 */
	public void setPurchDate(Date purchDate) {
		this.purchDate = purchDate;
	}
	/**
	 * 获取：采购日期
	 */
	public Date getPurchDate() {
		return purchDate;
	}
	/**
	 * 设置：入库日期
	 */
	public void setInboundDate(Date inboundDate) {
		this.inboundDate = inboundDate;
	}
	/**
	 * 获取：入库日期
	 */
	public Date getInboundDate() {
		return inboundDate;
	}
	/**
	 * 设置：入库仓库
	 */
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	/**
	 * 获取：入库仓库
	 */
	public String getWarehouse() {
		return warehouse;
	}
	/**
	 * 设置：创建时间
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	/**
	 * 获取：创建人
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
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	public List<PurchaseDetailEntity> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<PurchaseDetailEntity> detailList) {
		this.detailList = detailList;
	}
	public String getStoreInfoValue() {
		return storeInfoValue;
	}
	public void setStoreInfoValue(String storeInfoValue) {
		this.storeInfoValue = storeInfoValue;
	}
	public String getCarFromValue() {
		return carFromValue;
	}
	public void setCarFromValue(String carFromValue) {
		this.carFromValue = carFromValue;
	}
	public String getCarTypeValue() {
		return carTypeValue;
	}
	public void setCarTypeValue(String carTypeValue) {
		this.carTypeValue = carTypeValue;
	}
	public String getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}
	public String getIsPayValue() {
		return isPayValue;
	}
	public void setIsPayValue(String isPayValue) {
		this.isPayValue = isPayValue;
	}
	public String getWarehouseValue() {
		return warehouseValue;
	}
	public void setWarehouseValue(String warehouseValue) {
		this.warehouseValue = warehouseValue;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
}
