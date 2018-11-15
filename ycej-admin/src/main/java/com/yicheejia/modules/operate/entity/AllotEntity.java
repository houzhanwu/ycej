package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 调拨记录表
 * 
 * @author lw
 * @email 
 * @date 2018-07-18 16:37:00
 */
@TableName("ycej_allot")
public class AllotEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 调拨ID(主键)
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String allotId;
	/**
	 * VIN
	 */
	private String vin;
	/**
	 * VIN list
	 */
	@TableField(exist = false)
	private List<String> vinList;
	/**
	 * 调拨日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date allotDate;
	/**
	 * 调拨人
	 */
	private String allotName;
	/**
	 * 调拨人手机号 
	 */
	private String allotMobile;
	/**
	 * 调出门店ID
	 */
	private String allotFrom;
	/**
	 * 调入门店ID
	 */
	private String allotTo;
	/**
	 * 调出门店name
	 */
	@TableField(exist = false)
	private String allotFromName;
	/**
	 * 调入门店name
	 */
	@TableField(exist = false)
	private String allotToName;
	/**
	 * 随车物品
	 */
	private String allotWith;
	/**
	 * 车况
	 */
	private String carCondition;
	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 合格证去向（0：随车；1：邮寄）
	 */
	private Integer certificate;
	/**
	 * 邮寄日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date mailDate;
	/**
	 * 快递公司
	 */
	private String expressCompany;
	/**
	 * 快递单号
	 */
	private String courierNumber;
	/**
	 * 调出确认0没有；1已确认
	 */
	private Integer allotFromConfirm;
	/**
	 * 调出确认人
	 */
	private String allotFromConfirmId;
	/**
	 * 调入方确认时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date allotFromConfirmDate;
	/**
	 * 调入确认0没有；1已确认
	 */
	private Integer allotToConfirm;
	/**
	 * 调入确认人
	 */
	private String allotToConfirmId;
	/**
	 * 调出方确认时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date allotToConfirmDate;
	/**
	 * 调拨状态1.暂存；2.已提交；3.调出方已确认；4.已完成
	 */
	private Integer allotStatus;
	/**
	 * 门店ID
	 */
	@TableField(exist = false)
	private String shopId;
	/**
	 * 车库ID
	 */
	@TableField(exist = false)
	private String warehouseId;
	/**
	 * 调出方类型：0门店；1仓库
	 */
	private Integer type;
	/**
	 * 数据插入时间
	 */
	private Date insertTime;
	/**
	 * 是否收到车辆合格证:收到1；未收到0
	 */
	private Integer isReceived;
	/**
	 * 附件主键
	 */
	private String fileId;
	/**
	 * 文件路径
	 */
	private String vinPhotoAdd;
	
	public String getVinPhotoAdd() {
		return vinPhotoAdd;
	}
	public void setVinPhotoAdd(String vinPhotoAdd) {
		this.vinPhotoAdd = vinPhotoAdd;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public Integer getIsReceived() {
		return isReceived;
	}
	public void setIsReceived(Integer isReceived) {
		this.isReceived = isReceived;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	//	/**
//	 * 车辆状态
//	 */
//	private Integer carStatus;
//
//	public Integer getCarStatus() {
//		return carStatus;
//	}
//	public void setCarStatus(Integer carStatus) {
//		this.carStatus = carStatus;
//	}
	public Integer getAllotStatus() {
		return allotStatus;
	}
	public void setAllotStatus(Integer allotStatus) {
		this.allotStatus = allotStatus;
	}
	public Integer getAllotFromConfirm() {
		return allotFromConfirm;
	}
	public void setAllotFromConfirm(Integer allotFromConfirm) {
		this.allotFromConfirm = allotFromConfirm;
	}
	public String getAllotFromConfirmId() {
		return allotFromConfirmId;
	}
	public void setAllotFromConfirmId(String allotFromConfirmId) {
		this.allotFromConfirmId = allotFromConfirmId;
	}
	public Date getAllotFromConfirmDate() {
		return allotFromConfirmDate;
	}
	public void setAllotFromConfirmDate(Date allotFromConfirmDate) {
		this.allotFromConfirmDate = allotFromConfirmDate;
	}
	public Integer getAllotToConfirm() {
		return allotToConfirm;
	}
	public void setAllotToConfirm(Integer allotToConfirm) {
		this.allotToConfirm = allotToConfirm;
	}
	public String getAllotToConfirmId() {
		return allotToConfirmId;
	}
	public void setAllotToConfirmId(String allotToConfirmId) {
		this.allotToConfirmId = allotToConfirmId;
	}
	public Date getAllotToConfirmDate() {
		return allotToConfirmDate;
	}
	public void setAllotToConfirmDate(Date allotToConfirmDate) {
		this.allotToConfirmDate = allotToConfirmDate;
	}
	/**
	 * 设置：调拨ID(主键)
	 */
	public void setAllotId(String allotId) {
		this.allotId = allotId;
	}
	/**
	 * 获取：调拨ID(主键)
	 */
	public String getAllotId() {
		return allotId;
	}
	/**
	 * 设置：VIN
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	/**
	 * 获取：VIN
	 */
	public String getVin() {
		return vin;
	}
	/**
	 * 设置：调拨日期
	 */
	public void setAllotDate(Date allotDate) {
		this.allotDate = allotDate;
	}
	/**
	 * 获取：调拨日期
	 */
	public Date getAllotDate() {
		return allotDate;
	}
	/**
	 * 设置：调拨人
	 */
	public void setAllotName(String allotName) {
		this.allotName = allotName;
	}
	/**
	 * 获取：调拨人
	 */
	public String getAllotName() {
		return allotName;
	}
	/**
	 * 设置：调拨人手机号 
	 */
	public void setAllotMobile(String allotMobile) {
		this.allotMobile = allotMobile;
	}
	/**
	 * 获取：调拨人手机号 
	 */
	public String getAllotMobile() {
		return allotMobile;
	}
	/**
	 * 设置：调出门店ID
	 */
	public void setAllotFrom(String allotFrom) {
		this.allotFrom = allotFrom;
	}
	/**
	 * 获取：调出门店ID
	 */
	public String getAllotFrom() {
		return allotFrom;
	}
	/**
	 * 设置：调入门店ID
	 */
	public void setAllotTo(String allotTo) {
		this.allotTo = allotTo;
	}
	/**
	 * 获取：调入门店ID
	 */
	public String getAllotTo() {
		return allotTo;
	}
	/**
	 * 设置：随车物品
	 */
	public void setAllotWith(String allotWith) {
		this.allotWith = allotWith;
	}
	/**
	 * 获取：随车物品
	 */
	public String getAllotWith() {
		return allotWith;
	}
	/**
	 * 设置：车况
	 */
	public void setCarCondition(String carCondition) {
		this.carCondition = carCondition;
	}
	/**
	 * 获取：车况
	 */
	public String getCarCondition() {
		return carCondition;
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
	 * 设置：合格证去向（0：随车；1：邮寄）
	 */
	public void setCertificate(Integer certificate) {
		this.certificate = certificate;
	}
	/**
	 * 获取：合格证去向（0：随车；1：邮寄）
	 */
	public Integer getCertificate() {
		return certificate;
	}
	/**
	 * 设置：邮寄日期
	 */
	public void setMailDate(Date mailDate) {
		this.mailDate = mailDate;
	}
	/**
	 * 获取：邮寄日期
	 */
	public Date getMailDate() {
		return mailDate;
	}
	/**
	 * 设置：快递公司
	 */
	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}
	/**
	 * 获取：快递公司
	 */
	public String getExpressCompany() {
		return expressCompany;
	}
	/**
	 * 设置：快递单号
	 */
	public void setCourierNumber(String courierNumber) {
		this.courierNumber = courierNumber;
	}
	/**
	 * 获取：快递单号
	 */
	public String getCourierNumber() {
		return courierNumber;
	}
	public List<String> getVinList() {
		return vinList;
	}
	public void setVinList(List<String> vinList) {
		this.vinList = vinList;
	}
	public String getAllotFromName() {
		return allotFromName;
	}
	public void setAllotFromName(String allotFromName) {
		this.allotFromName = allotFromName;
	}
	public String getAllotToName() {
		return allotToName;
	}
	public void setAllotToName(String allotToName) {
		this.allotToName = allotToName;
	}
	
	//调拨明细数据
	/**
	 * 库存ID
	 */
	@TableField(exist = false)
	private String inventoryId;
	/**
	 * 品牌id
	 */
	@TableField(exist = false)
	private String carBrand;
	/**
	 * 厂商
	 */
	@TableField(exist = false)
	private String carManufacturer;
	/**
	 * 车系id
	 */
	@TableField(exist = false)
	private String carSeries;
	/**
	 * 车型id
	 */
	@TableField(exist = false)
	private String carModel;
	/**
	 * 车身颜色
	 */
	@TableField(exist = false)
	private String carColour;
	/**
	 * 排量
	 */
	@TableField(exist = false)
	private String carEmissions;
	/**
	 * 采购总成本
	 */
	@TableField(exist = false)
	private BigDecimal costPrice;
	/**
	 * 活动销售价（最终售价）
	 */
	@TableField(exist = false)
	private BigDecimal salePrice;
	/**
	 * 采购发票价
	 */
	@TableField(exist = false)
	private BigDecimal receiptPrice;
	/**
	 * 发动机号
	 */
	@TableField(exist = false)
	private String engineId;
	/**
	 * 合格证地址
	 */
	@TableField(exist = false)
	private String certificateAdd;
	/**
	 * 车辆状态:1在库；2调拨中；3已销售；4已出库；5销售中
	 */
	@TableField(exist = false)
	private Integer carStatus;

	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarManufacturer() {
		return carManufacturer;
	}
	public void setCarManufacturer(String carManufacturer) {
		this.carManufacturer = carManufacturer;
	}
	public String getCarSeries() {
		return carSeries;
	}
	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getCarColour() {
		return carColour;
	}
	public void setCarColour(String carColour) {
		this.carColour = carColour;
	}
	public String getCarEmissions() {
		return carEmissions;
	}
	public void setCarEmissions(String carEmissions) {
		this.carEmissions = carEmissions;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public BigDecimal getReceiptPrice() {
		return receiptPrice;
	}
	public void setReceiptPrice(BigDecimal receiptPrice) {
		this.receiptPrice = receiptPrice;
	}
	public String getEngineId() {
		return engineId;
	}
	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}
	public String getCertificateAdd() {
		return certificateAdd;
	}
	public void setCertificateAdd(String certificateAdd) {
		this.certificateAdd = certificateAdd;
	}
	public Integer getCarStatus() {
		return carStatus;
	}
	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}
}
