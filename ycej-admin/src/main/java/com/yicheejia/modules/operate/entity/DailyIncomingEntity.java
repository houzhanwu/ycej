package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-11-02 11:08:40
 */
@TableName("ycej_daily_incoming")
public class DailyIncomingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 门店主键
	 */
	private String shopId;
	/**
	 * 提车供应商
	 */
	private String supplierId;
	/**
	 * 提车供应商名称
	 */
	@TableField(exist = false)
	private String supplierName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 工作流程备注
	 */
	private String comment;
	/**
	 * 状态:暂存1；资源待审核1；车辆待入库2；已完成3
	 */
	private Integer state;
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
	 * 门店名称
	 */
	@TableField(exist = false)
	private String shopName;
	/**
	 * 门店状态
	 */
	@TableField(exist = false)
	private String shopState;
	/**
	 * 门店属性
	 */
	@TableField(exist = false)
	private String shopAttr;
	/**
	 * 城市经理
	 */
	@TableField(exist = false)
	private String cityManager;
	/**
	 * 门店地址
	 */
	@TableField(exist = false)
	private String shopAddress;
	/**
	 * 店长姓名
	 */
	@TableField(exist = false)
	private String shopManager;
	/**
	 * 联系方式
	 */
	@TableField(exist = false)
	private String phone;
	/**
	 * 身份证号码
	 */
	@TableField(exist = false)
	private String idCard;
	/**
	 * 车辆品牌id
	 */
	private String carBrand;
	/**
	 * 车辆品牌名称
	 */
	private String carBrandName;
	/**
	 * 厂商id
	 */
	private String carManufacturer;
	/**
	 * 厂商名称
	 */
	private String carManufacturerName;
	/**
	 * 车系id
	 */
	private String carSeries;
	/**
	 * 车系名称
	 */
	private String carSeriesName;
	/**
	 * 车型id
	 */
	private String carModel;
	/**
	 * 车型名称
	 */
	private String carModelName;
	/**
	 * 排量
	 */
	private String carDisplacement;
	/**
	 * 座位数
	 */
	private Integer carSeat;
	/**
	 * 颜色id
	 */
	private String appearance;
	/**
	 * 颜色名称
	 */
	private String appearanceColor;
	/**
	 * 车架号
	 */
	private String vin;
	/**
	 * 出厂日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date productDate;
	/**
	 * 入库日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date inboundDate;
	
	

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：门店主键
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：门店主键
	 */
	public String getShopId() {
		return shopId;
	}
	/**
	 * 设置：提车供应商
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：提车供应商
	 */
	public String getSupplierId() {
		return supplierId;
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
	 * 设置：工作流程备注
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：工作流程备注
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：状态:暂存1；资源待审核1；车辆待入库2；已完成3
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态:暂存1；资源待审核1；车辆待入库2；已完成3
	 */
	public Integer getState() {
		return state;
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
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopState() {
		return shopState;
	}
	public void setShopState(String shopState) {
		this.shopState = shopState;
	}
	public String getShopAttr() {
		return shopAttr;
	}
	public void setShopAttr(String shopAttr) {
		this.shopAttr = shopAttr;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarBrandName() {
		return carBrandName;
	}
	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getCityManager() {
		return cityManager;
	}
	public void setCityManager(String cityManager) {
		this.cityManager = cityManager;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopManager() {
		return shopManager;
	}
	public void setShopManager(String shopManager) {
		this.shopManager = shopManager;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCarManufacturer() {
		return carManufacturer;
	}
	public void setCarManufacturer(String carManufacturer) {
		this.carManufacturer = carManufacturer;
	}
	public String getCarManufacturerName() {
		return carManufacturerName;
	}
	public void setCarManufacturerName(String carManufacturerName) {
		this.carManufacturerName = carManufacturerName;
	}
	public String getCarSeries() {
		return carSeries;
	}
	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}
	public String getCarSeriesName() {
		return carSeriesName;
	}
	public void setCarSeriesName(String carSeriesName) {
		this.carSeriesName = carSeriesName;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getCarModelName() {
		return carModelName;
	}
	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}
	public String getCarDisplacement() {
		return carDisplacement;
	}
	public void setCarDisplacement(String carDisplacement) {
		this.carDisplacement = carDisplacement;
	}
	public Integer getCarSeat() {
		return carSeat;
	}
	public void setCarSeat(Integer carSeat) {
		this.carSeat = carSeat;
	}
	public String getAppearance() {
		return appearance;
	}
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	public String getAppearanceColor() {
		return appearanceColor;
	}
	public void setAppearanceColor(String appearanceColor) {
		this.appearanceColor = appearanceColor;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public Date getInboundDate() {
		return inboundDate;
	}
	public void setInboundDate(Date inboundDate) {
		this.inboundDate = inboundDate;
	}
	
}
