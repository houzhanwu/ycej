package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-07-18 16:43:36
 */
@TableName("ycej_purchase_detail")
public class PurchaseDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 采购明细主键
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String purchDetailId;
	/**
	 * 采购单主键
	 */
	private String purchId;
	/**
	 * 品牌主键
	 */
	private String brandId;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 厂商主键
	 */
	private String manufacturerId;
	/**
	 * 厂商名称
	 */
	private String manufacturerName;
	/**
	 * 车系主键
	 */
	private String seriesId;
	/**
	 * 车系名称
	 */
	private String seriesName;
	/**
	 * 车型主键
	 */
	private String modelId;
	/**
	 * 车型名称
	 */
	private String modelName;
	/**
	 * 外观颜色id
	 */
	private String colour;
	/**
	 * 外观颜色名称
	 */
	private String colourName;
	/**
	 * 内饰颜色id
	 */
	private String interiorColor;
	/**
	 * 内饰颜色名称
	 */
	private String interiorColorName;
	/**
	 * 排量
	 */
	private String displacement;
	/**
	 * 采购成本价
	 */
	private BigDecimal purchCostPrice;
	/**
	 * 建议销售价
	 */
	private BigDecimal sugSalePrice;
	/**
	 * 采购发票价
	 */
	private BigDecimal purchInvoicePrice;
	/**
	 * 出厂日期
	 */
	@JsonFormat(pattern = "yyyy-MM",timezone="GMT+8")
	private Date productDate;
	/**
	 * 车架号
	 */
	private String vin;
	/**
	 * 发动机号
	 */
	private String engineNo;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 附件主键
	 */
	private String fileId;
	/**
	 * 库存主键
	 */
	private String inventoryId;
	/*
	 * 标识，用来判断是新增还是修改，1是新增，否则为修改
	 */
	@TableField(exist = false)
	private String addPurchaseDetail;
	/**
	 * 设置：采购明细主键
	 */
	public void setPurchDetailId(String purchDetailId) {
		this.purchDetailId = purchDetailId;
	}
	/**
	 * 获取：采购明细主键
	 */
	public String getPurchDetailId() {
		return purchDetailId;
	}
	/**
	 * 设置：品牌主键
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取：品牌主键
	 */
	public String getBrandId() {
		return brandId;
	}
	/**
	 * 设置：厂商主键
	 */
	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	/**
	 * 获取：厂商主键
	 */
	public String getManufacturerId() {
		return manufacturerId;
	}
	/**
	 * 设置：车系主键
	 */
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}
	/**
	 * 获取：车系主键
	 */
	public String getSeriesId() {
		return seriesId;
	}
	/**
	 * 设置：车型主键
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	/**
	 * 获取：车型主键
	 */
	public String getModelId() {
		return modelId;
	}
	/**
	 * 设置：外观颜色
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
	/**
	 * 获取：外观颜色
	 */
	public String getColour() {
		return colour;
	}
	/**
	 * 设置：排量
	 */
	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}
	/**
	 * 获取：排量
	 */
	public String getDisplacement() {
		return displacement;
	}
	/**
	 * 设置：采购成本价
	 */
	public void setPurchCostPrice(BigDecimal purchCostPrice) {
		this.purchCostPrice = purchCostPrice;
	}
	/**
	 * 获取：采购成本价
	 */
	public BigDecimal getPurchCostPrice() {
		return purchCostPrice;
	}
	/**
	 * 设置：建议销售价
	 */
	public void setSugSalePrice(BigDecimal sugSalePrice) {
		this.sugSalePrice = sugSalePrice;
	}
	/**
	 * 获取：建议销售价
	 */
	public BigDecimal getSugSalePrice() {
		return sugSalePrice;
	}
	/**
	 * 设置：采购发票价
	 */
	public void setPurchInvoicePrice(BigDecimal purchInvoicePrice) {
		this.purchInvoicePrice = purchInvoicePrice;
	}
	/**
	 * 获取：采购发票价
	 */
	public BigDecimal getPurchInvoicePrice() {
		return purchInvoicePrice;
	}
	/**
	 * 设置：出厂日期
	 */
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	/**
	 * 获取：出厂日期
	 */
	public Date getProductDate() {
		return productDate;
	}
	/**
	 * 设置：车架号
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	/**
	 * 获取：车架号
	 */
	public String getVin() {
		return vin;
	}
	/**
	 * 设置：发动机号
	 */
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	/**
	 * 获取：发动机号
	 */
	public String getEngineNo() {
		return engineNo;
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
	public String getPurchId() {
		return purchId;
	}
	public void setPurchId(String purchId) {
		this.purchId = purchId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getAddPurchaseDetail() {
		return addPurchaseDetail;
	}
	public void setAddPurchaseDetail(String addPurchaseDetail) {
		this.addPurchaseDetail = addPurchaseDetail;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getColourName() {
		return colourName;
	}
	public void setColourName(String colourName) {
		this.colourName = colourName;
	}
	public String getInteriorColor() {
		return interiorColor;
	}
	public void setInteriorColor(String interiorColor) {
		this.interiorColor = interiorColor;
	}
	public String getInteriorColorName() {
		return interiorColorName;
	}
	public void setInteriorColorName(String interiorColorName) {
		this.interiorColorName = interiorColorName;
	}
	
	
}
