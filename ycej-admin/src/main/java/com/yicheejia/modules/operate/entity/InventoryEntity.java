package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 库存表
 * 
 * @author lw
 * @email 
 * @date 2018-07-24 16:07:43
 */
@TableName("ycej_inventory")
public class InventoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 库存ID（主键）
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String inventoryId;
	/**
	 * 品牌id
	 */
	private String carBrand;
	/**
	 * 品牌名称
	 */
	@TableField(exist = false)
	private String brandName;
	/**
	 * 厂商
	 */
	private String carManufacturer;
	/**
	 * 厂商名称
	 */
	@TableField(exist = false)
	private String manufacturerName;
	/**
	 * 车系id
	 */
	private String carSeries;
	/**
	 * 车系名称
	 */
	@TableField(exist = false)
	private String seriesName;
	/**
	 * 车型id
	 */
	private String carModel;
	/**
	 * 车型名称
	 */
	@TableField(exist = false)
	private String modelName;
	/**
	 * 车身颜色
	 */
	private String carColour;
	/**
	 * 车身颜色名称
	 */
	@TableField(exist = false)
	private String colourName;
	/**
	 *内饰颜色 
	 */
	private String interiorColor;
	/**
	 *内饰颜色名称
	 */
	@TableField(exist = false)
	private String interiorColorName;
	/**
	 * VIN（车架号）
	 */
	private String vin;
	/**
	 * 发动机号
	 */
	private String engineId;
	/**
	 * 出厂日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date manufactureDate;
	/**
	 * 入库日期Now()
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date storageDate;
	/**
	 * 操作人
	 */
	private Integer insertId;
	/**
	 * 操作人name
	 */
	@TableField(exist = false)
	private String insertName;
	/**
	 * 采购发票价
	 */
	private BigDecimal receiptPrice;
	/**
	 * 采购总成本
	 */
	private BigDecimal costPrice;
	/**
	 * 活动销售价（最终售价）
	 */
	private BigDecimal salePrice;
	/**
	 * 所在门店（取部门ID）
	 */
	private String shopId;
	/**
	 * 车辆来源（1为全款采购车、2为保证金车、3为优壹车）
	 */
	private Integer carSource;
	/**
	 * 店保（1无；2店保交强；3店保全险）
	 */
	private Integer dinsurance;
	/**
	 * 店保金额
	 */
	private BigDecimal dinsurancePrice;
	/**
	 * 供应商ID
	 */
	private Integer supplierId;
	   /**
     * 供应商ID
     */
	@TableField(exist = false)
    private String supplierName;
	/**
	 * 修改人
	 */
	private Integer updateId;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 合格证地址
	 */
	private String certificateAdd;
	/**
	 * 合格证附件主键
	 */
	private String fileId;
	/**
	 * 车辆状态:1在库；2调拨中；3已销售；4已出库；5销售中
	 */
	private Integer carStatus;
	/**
	 * 仓库ID
	 */
	private String warehouseId;
	/**
	 * 仓库名
	 */
	@TableField(exist = false)
	private String warehouseName;
	/**
	 * purchasedetailid
	 */
	@TableField(exist = false)
	private String purchaseDetailId;
	/**
	 * 客户订车采购：0否；1是
	 */
	private String isOrder;	
	/**
	 * 是否专票:0否；1是
	 */
	private String isZhuan;	
	/**
	 * 客户姓名
	 */
	@TableField(exist = false)
	private String customerName;
	/**
	 * 客户手机号
	 */
	@TableField(exist = false)
	private String contactInformation;
	/**
	 * 销售日期
	 */
	@TableField(exist = false)
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	private Date insertTime;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public String getIsZhuan() {
		return isZhuan;
	}
	public void setIsZhuan(String isZhuan) {
		this.isZhuan = isZhuan;
	}
	public String getIsOrder() {
		return isOrder;
	}
	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	/**
	 * 设置：库存ID（主键）
	 */
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	/**
	 * 获取：库存ID（主键）
	 */
	public String getInventoryId() {
		return inventoryId;
	}
	/**
	 * 设置：品牌id
	 */
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	/**
	 * 获取：品牌id
	 */
	public String getCarBrand() {
		return carBrand;
	}
	/**
	 * 设置：厂商
	 */
	public void setCarManufacturer(String carManufacturer) {
		this.carManufacturer = carManufacturer;
	}
	/**
	 * 获取：厂商
	 */
	public String getCarManufacturer() {
		return carManufacturer;
	}
	/**
	 * 设置：车系id
	 */
	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}
	/**
	 * 获取：车系id
	 */
	public String getCarSeries() {
		return carSeries;
	}
	/**
	 * 设置：车型id
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	/**
	 * 获取：车型id
	 */
	public String getCarModel() {
		return carModel;
	}
	/**
	 * 设置：车身颜色
	 */
	public void setCarColour(String carColour) {
		this.carColour = carColour;
	}
	/**
	 * 获取：车身颜色
	 */
	public String getCarColour() {
		return carColour;
	}
	/**
	 * 设置：VIN（车架号）
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	/**
	 * 获取：VIN（车架号）
	 */
	public String getVin() {
		return vin;
	}
	/**
	 * 设置：发动机号
	 */
	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}
	/**
	 * 获取：发动机号
	 */
	public String getEngineId() {
		return engineId;
	}
	/**
	 * 设置：出厂日期
	 */
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	/**
	 * 获取：出厂日期
	 */
	public Date getManufactureDate() {
		return manufactureDate;
	}
	/**
	 * 设置：入库日期Now()
	 */
	public void setStorageDate(Date storageDate) {
		this.storageDate = storageDate;
	}
	/**
	 * 获取：入库日期Now()
	 */
	public Date getStorageDate() {
		return storageDate;
	}
	/**
	 * 设置：操作人
	 */
	public void setInsertId(Integer insertId) {
		this.insertId = insertId;
	}
	/**
	 * 获取：操作人
	 */
	public Integer getInsertId() {
		return insertId;
	}
	/**
	 * 设置：采购发票价
	 */
	public void setReceiptPrice(BigDecimal receiptPrice) {
		this.receiptPrice = receiptPrice;
	}
	/**
	 * 获取：采购发票价
	 */
	public BigDecimal getReceiptPrice() {
		return receiptPrice;
	}
	/**
	 * 设置：采购总成本
	 */
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	/**
	 * 获取：采购总成本
	 */
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	/**
	 * 设置：活动销售价（最终售价）
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * 获取：活动销售价（最终售价）
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	/**
	 * 设置：所在门店（取部门ID）
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：所在门店（取部门ID）
	 */
	public String getShopId() {
		return shopId;
	}
	/**
	 * 设置：车辆来源（1为全款采购车、2为保证金车、3为优壹车）
	 */
	public void setCarSource(Integer carSource) {
		this.carSource = carSource;
	}
	/**
	 * 获取：车辆来源（1为全款采购车、2为保证金车、3为优壹车）
	 */
	public Integer getCarSource() {
		return carSource;
	}
	/**
	 * 设置：店保（1无；2店保交强；3店保全险）
	 */
	public void setDinsurance(Integer dinsurance) {
		this.dinsurance = dinsurance;
	}
	/**
	 * 获取：店保（1无；2店保交强；3店保全险）
	 */
	public Integer getDinsurance() {
		return dinsurance;
	}
	/**
	 * 设置：店保金额
	 */
	public void setDinsurancePrice(BigDecimal dinsurancePrice) {
		this.dinsurancePrice = dinsurancePrice;
	}
	/**
	 * 获取：店保金额
	 */
	public BigDecimal getDinsurancePrice() {
		return dinsurancePrice;
	}
	/**
	 * 设置：供应商ID
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：供应商ID
	 */
	public Integer getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：修改人
	 */
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	/**
	 * 获取：修改人
	 */
	public Integer getUpdateId() {
		return updateId;
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
	 * 设置：合格证地址
	 */
	public void setCertificateAdd(String certificateAdd) {
		this.certificateAdd = certificateAdd;
	}
	/**
	 * 获取：合格证地址
	 */
	public String getCertificateAdd() {
		return certificateAdd;
	}
	/**
	 * 设置：车辆状态:1在库；2调拨中；3已销售；4已出库；5销售中
	 */
	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}
	/**
	 * 获取：车辆状态:1在库；2调拨中；3已销售；4已出库；5销售中
	 */
	public Integer getCarStatus() {
		return carStatus;
	}
	//新增字段用于库存查询   add by fkm  20180724
	@TableField(exist = false)
	private String shopName;//门店名称
	@TableField(exist = false)
	private String carModelName;//车型
	@TableField(exist = false)
	private String carEmissions;//排量
	@TableField(exist = false)
	private String purchDate;//采购日期
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCarModelName() {
		return carModelName;
	}
	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}
	public String getPurchDate() {
		return purchDate;
	}
	public void setPurchDate(String purchDate) {
		this.purchDate = purchDate;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getCarEmissions() {
		return carEmissions;
	}
	public void setCarEmissions(String carEmissions) {
		this.carEmissions = carEmissions;
	}
	public String getInsertName() {
		return insertName;
	}
	public void setInsertName(String insertName) {
		this.insertName = insertName;
	}
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
	public String getInteriorColor() {
		return interiorColor;
	}
	public void setInteriorColor(String interiorColor) {
		this.interiorColor = interiorColor;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
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
	public String getColourName() {
		return colourName;
	}
	public void setColourName(String colourName) {
		this.colourName = colourName;
	}
	public String getInteriorColorName() {
		return interiorColorName;
	}
	public void setInteriorColorName(String interiorColorName) {
		this.interiorColorName = interiorColorName;
	}
	public String getPurchaseDetailId() {
		return purchaseDetailId;
	}
	public void setPurchaseDetailId(String purchaseDetailId) {
		this.purchaseDetailId = purchaseDetailId;
	}
	
}
