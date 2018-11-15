package com.yicheejia.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @date 2018-06-21 16:27:42
 */
@TableName("ycej_order")
public class YcejOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 门店id
	 */
	private String shopId;
	/**
	 * 库存ID
	 */
	private String inventoryId;
	/**
	 * 车型ID
	 */
	private String carModelId;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 销售价
	 */
	private BigDecimal sellPrice;
	/**
	 * 销售类型
	 */
	private Integer sellType;
	/**
	 * 交强险是否外出
	 */
	private Integer insuranceIfout;
	/**
	 * 商业是否外出
	 */
	private Integer commercialIfout;
	/**
	 * 是否店保
	 */
	private Integer insureInshop;
	/**
	 * 客户姓名
	 */
	private String customerName;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 联系方式
	 */
	private String contactInformation;
	/**
	 * 证件类型
	 */
	private Integer idType;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 外观颜色
	 */
	private String appearanceColour;
	/**
	 * 内饰颜色
	 */
	private String trimColour;
	/**
	 * 排量
	 */
	private String displacement;
	/**
	 * 订单来源
	 */
	private Integer orderSource;
	/**
	 * 支付总额
	 */
	private BigDecimal payTotle;
	/**
	 * 发票价格
	 */
	private BigDecimal invoicePrice;
	/**
	 * 融资总额
	 */
	private BigDecimal factTotle;
	/**
	 * 是否收到合格证书
	 */
	private Integer isqualified;
	/**
	 * 订单状态
	 */
	private String status;
	/**
	 * 车辆确认时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date confirmTime;
	/**
	 * 车辆交接时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date handoverTime;
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
	/**
	 * 是否一次性付款
	 */
	private Integer isDisposable;
	
	public Integer getIsDisposable() {
		return isDisposable;
	}
	public void setIsDisposable(Integer isDisposable) {
		this.isDisposable = isDisposable;
	}
	
	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：门店id
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：门店id
	 */
	public String getShopId() {
		return shopId;
	}
	/**
	 * 设置：库存ID
	 */
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	/**
	 * 获取：库存ID
	 */
	public String getInventoryId() {
		return inventoryId;
	}
	/**
	 * 设置：车型ID
	 */
	public void setCarModelId(String carModelId) {
		this.carModelId = carModelId;
	}
	/**
	 * 获取：车型ID
	 */
	public String getCarModelId() {
		return carModelId;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：订单编号
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：销售价
	 */
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	/**
	 * 获取：销售价
	 */
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	/**
	 * 设置：销售类型
	 */
	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}
	/**
	 * 获取：销售类型
	 */
	public Integer getSellType() {
		return sellType;
	}
	/**
	 * 设置：交强险是否外出
	 */
	public void setInsuranceIfout(Integer insuranceIfout) {
		this.insuranceIfout = insuranceIfout;
	}
	/**
	 * 获取：交强险是否外出
	 */
	public Integer getInsuranceIfout() {
		return insuranceIfout;
	}
	/**
	 * 设置：商业是否外出
	 */
	public void setCommercialIfout(Integer commercialIfout) {
		this.commercialIfout = commercialIfout;
	}
	/**
	 * 获取：商业是否外出
	 */
	public Integer getCommercialIfout() {
		return commercialIfout;
	}
	/**
	 * 设置：是否店保
	 */
	public void setInsureInshop(Integer insureInshop) {
		this.insureInshop = insureInshop;
	}
	/**
	 * 获取：是否店保
	 */
	public Integer getInsureInshop() {
		return insureInshop;
	}
	/**
	 * 设置：客户姓名
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取：客户姓名
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：联系方式
	 */
	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	/**
	 * 获取：联系方式
	 */
	public String getContactInformation() {
		return contactInformation;
	}
	/**
	 * 设置：证件类型
	 */
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	/**
	 * 获取：证件类型
	 */
	public Integer getIdType() {
		return idType;
	}
	/**
	 * 设置：证件号码
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	/**
	 * 获取：证件号码
	 */
	public String getIdNo() {
		return idNo;
	}
	/**
	 * 设置：外观颜色
	 */
	public void setAppearanceColour(String appearanceColour) {
		this.appearanceColour = appearanceColour;
	}
	/**
	 * 获取：外观颜色
	 */
	public String getAppearanceColour() {
		return appearanceColour;
	}
	/**
	 * 设置：内饰颜色
	 */
	public void setTrimColour(String trimColour) {
		this.trimColour = trimColour;
	}
	/**
	 * 获取：内饰颜色
	 */
	public String getTrimColour() {
		return trimColour;
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
	 * 设置：订单来源
	 */
	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}
	/**
	 * 获取：订单来源
	 */
	public Integer getOrderSource() {
		return orderSource;
	}
	/**
	 * 设置：支付总额
	 */
	public void setPayTotle(BigDecimal payTotle) {
		this.payTotle = payTotle;
	}
	/**
	 * 获取：支付总额
	 */
	public BigDecimal getPayTotle() {
		return payTotle;
	}
	/**
	 * 设置：发票价格
	 */
	public void setInvoicePrice(BigDecimal invoicePrice) {
		this.invoicePrice = invoicePrice;
	}
	/**
	 * 获取：发票价格
	 */
	public BigDecimal getInvoicePrice() {
		return invoicePrice;
	}
	/**
	 * 设置：融资总额
	 */
	public void setFactTotle(BigDecimal factTotle) {
		this.factTotle = factTotle;
	}
	/**
	 * 获取：融资总额
	 */
	public BigDecimal getFactTotle() {
		return factTotle;
	}
	/**
	 * 设置：是否收到合格证书
	 */
	public void setIsqualified(Integer isqualified) {
		this.isqualified = isqualified;
	}
	/**
	 * 获取：是否收到合格证书
	 */
	public Integer getIsqualified() {
		return isqualified;
	}
	/**
	 * 设置：订单状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态
	 */
	public String getStatus() {
		return status;
	}
	
	public Date getHandoverTime() {
		return handoverTime;
	}
	public void setHandoverTime(Date handoverTime) {
		this.handoverTime = handoverTime;
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
	
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	@TableField(exist = false)
	private SalePromotionEntity promotion;
	
	@TableField(exist = false)
	private String shopName;//门店名称
	@TableField(exist = false)
	private String shopNo;//门店编码
	@TableField(exist = false)
	private String carModel;//车型
	@TableField(exist = false)
	private String carSeries;//车系
	@TableField(exist = false)
	private String carBrand;//品牌
	@TableField(exist = false)
	private String VIN;//车架号
	@TableField(exist = false)
	private String engineId;//发动机号
	@TableField(exist = false)
	private String carManufacturer;//厂商
	
	@TableField(exist = false)
	private String insuranceId;//交强
	@TableField(exist = false)
	private String commercialId;//商业
	
	
	@TableField(exist = false)
	private String payDate;
	@TableField(exist = false)
	private String payMethod;
	@TableField(exist = false)
	private BigDecimal amount01;
	@TableField(exist = false)
	private BigDecimal amount02;
	@TableField(exist = false)
	private BigDecimal amount03;
	@TableField(exist = false)
	private BigDecimal amount04;
	@TableField(exist = false)
	private BigDecimal amount05;
	@TableField(exist = false)
	private BigDecimal amount06;
	@TableField(exist = false)
	private BigDecimal amount07;
	@TableField(exist = false)
	private BigDecimal amount08;
	@TableField(exist = false)
	private BigDecimal amount09;
	@TableField(exist = false)
	private BigDecimal amount10;
	@TableField(exist = false)
	private BigDecimal amount11;
	//转账明细接收
	@TableField(exist = false)
	private List<OrderPaydetailEntity>  orderPayList;
	
	public List<OrderPaydetailEntity> getOrderPayList() {
		return orderPayList;
	}
	public void setOrderDetail(List<OrderPaydetailEntity> orderPayList) {
		this.orderPayList = orderPayList;
	}
	public SalePromotionEntity getPromotion() {
		return promotion;
	}
	public void setPromotion(SalePromotionEntity promotion) {
		this.promotion = promotion;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public BigDecimal getAmount01() {
		return amount01;
	}
	public void setAmount01(BigDecimal amount01) {
		this.amount01 = amount01;
	}
	public BigDecimal getAmount02() {
		return amount02;
	}
	public void setAmount02(BigDecimal amount02) {
		this.amount02 = amount02;
	}
	public BigDecimal getAmount03() {
		return amount03;
	}
	public void setAmount03(BigDecimal amount03) {
		this.amount03 = amount03;
	}
	public BigDecimal getAmount04() {
		return amount04;
	}
	public void setAmount04(BigDecimal amount04) {
		this.amount04 = amount04;
	}
	public BigDecimal getAmount05() {
		return amount05;
	}
	public void setAmount05(BigDecimal amount05) {
		this.amount05 = amount05;
	}
	public BigDecimal getAmount06() {
		return amount06;
	}
	public void setAmount06(BigDecimal amount06) {
		this.amount06 = amount06;
	}
	public BigDecimal getAmount07() {
		return amount07;
	}
	public void setAmount07(BigDecimal amount07) {
		this.amount07 = amount07;
	}
	public BigDecimal getAmount08() {
		return amount08;
	}
	public void setAmount08(BigDecimal amount08) {
		this.amount08 = amount08;
	}
	public BigDecimal getAmount09() {
		return amount09;
	}
	public void setAmount09(BigDecimal amount09) {
		this.amount09 = amount09;
	}
	public BigDecimal getAmount10() {
		return amount10;
	}
	public void setAmount10(BigDecimal amount10) {
		this.amount10 = amount10;
	}
	public BigDecimal getAmount11() {
		return amount11;
	}
	public void setAmount11(BigDecimal amount11) {
		this.amount11 = amount11;
	}
	
	public String getCarManufacturer() {
		return carManufacturer;
	}
	public void setCarManufacturer(String carManufacturer) {
		this.carManufacturer = carManufacturer;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getCarSeries() {
		return carSeries;
	}
	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String VIN) {
		this.VIN = VIN;
	}
	public String getEngineId() {
		return engineId;
	}
	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}
	public String getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
	public String getCommercialId() {
		return commercialId;
	}
	public void setCommercialId(String commercialId) {
		this.commercialId = commercialId;
	}
	
	
}
