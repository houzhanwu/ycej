package com.yicheejia.modules.operate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 车辆查询
 * @author 梁占豪
 * @date 22018-08-29
 */
@TableName("ycej_inventory")
public class CarQueryEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 库存ID（主键）
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String inventoryId;
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
	 * 外观颜色
	 */
	private String colour;
	/**
	 * 内饰颜色
	 */
	private String interiorColour;
	/**
	 * 供应商id
	 */
	private String supplierId;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 发动机号
	 */
	private String engineNo;
	/**
	 * 车架号
	 */
	private String vin;
	/**
	 * 出厂日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date manufactureDate;
	/**
	 * 入库日期
	 */
	private Date storageDate;
	/**
	 * 门店id
	 */
	private String shopId;
	/**
	 * 门店名称
	 */
	private String shopName;
	/**
	 * 采购发票价
	 */
	private BigDecimal receiptPrice;
	/**
	 * 采购成本价
	 */
	private BigDecimal costPrice;
	/**
	 * 活动销售价
	 */
	private BigDecimal salePrice;
	/**
	 * 车辆来源
	 */
	private String carSource;
	/**
	 * 店保
	 */
	private String dinsurance;
	/**
	 * 店保金额
	 */
	private BigDecimal dinsurancePrice;
	/**
	 * 仓库id
	 */
	private String	warehouseId;
	/**
	 * 仓库名称
	 */
	private String	warehouseName;
	/**
	 * 车辆状态
	 */
	private String	carStatus;
	
	
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getInteriorColour() {
		return interiorColour;
	}
	public void setInteriorColour(String interiorColour) {
		this.interiorColour = interiorColour;
	}
	public Date getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	public Date getStorageDate() {
		return storageDate;
	}
	public void setStorageDate(Date storageDate) {
		this.storageDate = storageDate;
	}
	public BigDecimal getReceiptPrice() {
		return receiptPrice;
	}
	public void setReceiptPrice(BigDecimal receiptPrice) {
		this.receiptPrice = receiptPrice;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public String getCarSource() {
		return carSource;
	}
	public void setCarSource(String carSource) {
		this.carSource = carSource;
	}
	public String getDinsurance() {
		return dinsurance;
	}
	public void setDinsurance(String dinsurance) {
		this.dinsurance = dinsurance;
	}
	public BigDecimal getDinsurancePrice() {
		return dinsurancePrice;
	}
	public void setDinsurancePrice(BigDecimal dinsurancePrice) {
		this.dinsurancePrice = dinsurancePrice;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public String getCarStatus() {
		return carStatus;
	}
	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}
	
	
}
