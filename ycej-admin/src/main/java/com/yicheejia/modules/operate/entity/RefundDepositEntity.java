package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-11-06 18:04:09
 */
@TableName("ycej_refund_deposit")
public class RefundDepositEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 供应商主键
	 */
	private String supplierId;
	/**
	 * 保证金支付主键
	 */
	private String depositPayId;
	/**
	 * 交接文件
	 */
	private String handleFile;
	/**
	 * 交接时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date handleTime;
	/**
	 * 接收人
	 */
	private String receiveUser;
	/**
	 * 是否退款
	 */
	private Integer isRefund;
	/**
	 * 预计退款时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date refundTime;
	/**
	 * 是否退车
	 */
	private Integer isBack;
	/**
	 * 退车时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date backTime;
	/**
	 * 退车数量
	 */
	private Integer backCarCount;
	/**
	 * 收款时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date receiveTime;
	/**
	 * 收款金额
	 */
	private BigDecimal receiveAmount;
	/**
	 * 收款流水号
	 */
	private String receiveSerialNum;
	/**
	 * 状态：暂存1；资源总经理待审核2；会计待审核3；资源待确认4；出纳待审核5；已完成6
	 */
	private Integer refundState;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 用来保存工作流备注
	 */
	private String comment;
	/**
	 * 发起人
	 */
	private String insertUser;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 
	 */
	private String updateUser;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 供应商名称
	 */
	@TableField(exist = false)
	private String supplierName;
	/**
	 * 供应商类型
	 */
	@TableField(exist = false)
	private String supplierType;
	/**
	 * 车辆类型
	 */
	@TableField(exist = false)
	private String carType;
	/**
	 * 保证金额
	 */
	@TableField(exist = false)
	private BigDecimal depositMoney;
	/**
	 * 提供展车数量
	 */
	@TableField(exist = false)
	private Integer belowCar;
	

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
	 * 设置：保证金支付主键
	 */
	public void setDepositPayId(String depositPayId) {
		this.depositPayId = depositPayId;
	}
	/**
	 * 获取：保证金支付主键
	 */
	public String getDepositPayId() {
		return depositPayId;
	}
	/**
	 * 设置：交接文件
	 */
	public void setHandleFile(String handleFile) {
		this.handleFile = handleFile;
	}
	/**
	 * 获取：交接文件
	 */
	public String getHandleFile() {
		return handleFile;
	}
	/**
	 * 设置：交接时间
	 */
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	/**
	 * 获取：交接时间
	 */
	public Date getHandleTime() {
		return handleTime;
	}
	/**
	 * 设置：接收人
	 */
	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}
	/**
	 * 获取：接收人
	 */
	public String getReceiveUser() {
		return receiveUser;
	}
	/**
	 * 设置：是否退款
	 */
	public void setIsRefund(Integer isRefund) {
		this.isRefund = isRefund;
	}
	/**
	 * 获取：是否退款
	 */
	public Integer getIsRefund() {
		return isRefund;
	}
	/**
	 * 设置：预计退款时间
	 */
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	/**
	 * 获取：预计退款时间
	 */
	public Date getRefundTime() {
		return refundTime;
	}
	/**
	 * 设置：是否退车
	 */
	public void setIsBack(Integer isBack) {
		this.isBack = isBack;
	}
	/**
	 * 获取：是否退车
	 */
	public Integer getIsBack() {
		return isBack;
	}
	/**
	 * 设置：退车时间
	 */
	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}
	/**
	 * 获取：退车时间
	 */
	public Date getBackTime() {
		return backTime;
	}
	/**
	 * 设置：退车数量
	 */
	public void setBackCarCount(Integer backCarCount) {
		this.backCarCount = backCarCount;
	}
	/**
	 * 获取：退车数量
	 */
	public Integer getBackCarCount() {
		return backCarCount;
	}
	/**
	 * 设置：收款时间
	 */
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	/**
	 * 获取：收款时间
	 */
	public Date getReceiveTime() {
		return receiveTime;
	}
	/**
	 * 设置：收款金额
	 */
	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	/**
	 * 获取：收款金额
	 */
	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}
	/**
	 * 设置：收款流水号
	 */
	public void setReceiveSerialNum(String receiveSerialNum) {
		this.receiveSerialNum = receiveSerialNum;
	}
	/**
	 * 获取：收款流水号
	 */
	public String getReceiveSerialNum() {
		return receiveSerialNum;
	}
	/**
	 * 设置：状态：暂存1；资源总经理待审核2；会计待审核3；资源待确认4；出纳待审核5；已完成6
	 */
	public void setRefundState(Integer refundState) {
		this.refundState = refundState;
	}
	/**
	 * 获取：状态：暂存1；资源总经理待审核2；会计待审核3；资源待确认4；出纳待审核5；已完成6
	 */
	public Integer getRefundState() {
		return refundState;
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
	/**
	 * 设置：用来保存工作流备注
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：用来保存工作流备注
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：发起人
	 */
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	/**
	 * 获取：发起人
	 */
	public String getInsertUser() {
		return insertUser;
	}
	/**
	 * 设置：
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public BigDecimal getDepositMoney() {
		return depositMoney;
	}
	public void setDepositMoney(BigDecimal depositMoney) {
		this.depositMoney = depositMoney;
	}
	public Integer getBelowCar() {
		return belowCar;
	}
	public void setBelowCar(Integer belowCar) {
		this.belowCar = belowCar;
	}
	
}
