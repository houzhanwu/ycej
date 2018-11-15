package com.yicheejia.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yicheejia.common.validator.group.AddGroup;
import com.yicheejia.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 意向客户表
 *
 * @author cn
 * @email
 * @date 2018-07-18 14:03:20
 */
@TableName("ycej_intention_customer")
public class IntentionCustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 客户id
	 */
	@TableId
	private Integer customerId;
	/**
	 * 门店id
	 */
	@NotNull(message="门店不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Integer shopId;

	@TableField(exist = false)
	private String shopName;
	/**
	 * 门店编号
	 */
	private String shopNo;
	/**
	 * 客户姓名
	 */
	@NotNull(message="姓名不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String customerName;
	/**
	 * 0：女，1：男
	 */
	@NotNull(message="性别不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Integer sex;
	/**
	 * 身份证号
	 */
	private String idCardNo;
	/**
	 * 客户手机号
	 */
	private String customerTel;
	/**
	 * 联系地址
	 */
	private String address;
	/**
	 * 意向品牌
	 */
	private String carBrandName;
	/**
	 * 意向车系
	 */
	private String carSeriesName;
	/**
	 * 意向车型
	 */
	private String carTypeName;
	/**
	 * 意向等级：H（一周内）、A（一个月内）、B（三个月内）；C（半年内）
	 */
	@NotNull(message="意向等级不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String level;
	/**
	 * 其他联系人
	 */
	private String otherPerson;
	/**
	 * 其他联系方式
	 */
	private String otherTel;
	/**
	 * 是否成交:0,未成交 1，已成交
	 */
	private Integer dealed;
	/**
	 * 到店日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date arriveTime;
	/**
	 * 购买方式：1，全款，2，分期，3，优壹车
	 */
	private Integer payWay;
	/**
	 * 创建人
	 */
	private String insUser;
	/**
	 * 创建时间
	 */
	private Date insTime;
	/**
	 * 修改人
	 */
	private String updUser;
	/**
	 * 修改时间
	 */
	private Date updTime;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 最后跟踪日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date trackDate;
	/**
	 * 厂商
	 */
	private String carFirmName;

	@NotNull(message="意向品牌不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Integer carBrand;

	@NotNull(message="意向厂商不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Integer carFirm;

	@NotNull(message="意向车系不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Integer carSeries;

	@NotNull(message="意向车型不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private Integer carType;

	private String orderNo;

	private Integer intentionCustomerState;

	/**
	 * 设置：客户id
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：客户id
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * 设置：门店id
	 */
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：门店id
	 */
	public Integer getShopId() {
		return shopId;
	}
	/**
	 * 设置：门店编号
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	/**
	 * 获取：门店编号
	 */
	public String getShopNo() {
		return shopNo;
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
	 * 设置：0：女，1：男
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：0：女，1：男
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：客户手机号
	 */
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	/**
	 * 获取：客户手机号
	 */
	public String getCustomerTel() {
		return customerTel;
	}
	/**
	 * 设置：联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：联系地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置：意向等级：H（一周内）、A（一个月内）、B（三个月内）；C（半年内）
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取：意向等级：H（一周内）、A（一个月内）、B（三个月内）；C（半年内）
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置：其他联系人
	 */
	public void setOtherPerson(String otherPerson) {
		this.otherPerson = otherPerson;
	}
	/**
	 * 获取：其他联系人
	 */
	public String getOtherPerson() {
		return otherPerson;
	}
	/**
	 * 设置：其他联系方式
	 */
	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel;
	}
	/**
	 * 获取：其他联系方式
	 */
	public String getOtherTel() {
		return otherTel;
	}
	/**
	 * 设置：是否成交:0,未成交 1，已成交
	 */
	public void setDealed(Integer dealed) {
		this.dealed = dealed;
	}
	/**
	 * 获取：是否成交:0,未成交 1，已成交
	 */
	public Integer getDealed() {
		return dealed;
	}
	/**
	 * 设置：到店日期
	 */
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	/**
	 * 获取：到店日期
	 */
	public Date getArriveTime() {
		return arriveTime;
	}
	/**
	 * 设置：购买方式：1，全款，2，分期，3，优壹车
	 */
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	/**
	 * 获取：购买方式：1，全款，2，分期，3，优壹车
	 */
	public Integer getPayWay() {
		return payWay;
	}
	/**
	 * 设置：创建人
	 */
	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
	/**
	 * 获取：创建人
	 */
	public String getInsUser() {
		return insUser;
	}
	/**
	 * 设置：创建时间
	 */
	public void setInsTime(Date insTime) {
		this.insTime = insTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getInsTime() {
		return insTime;
	}
	/**
	 * 设置：修改人
	 */
	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}
	/**
	 * 获取：修改人
	 */
	public String getUpdUser() {
		return updUser;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdTime() {
		return updTime;
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

	public Date getTrackDate() {
		return trackDate;
	}

	public void setTrackDate(Date trackDate) {
		this.trackDate = trackDate;
	}

	public String getCarBrandName() {
		return carBrandName;
	}

	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}

	public String getCarSeriesName() {
		return carSeriesName;
	}

	public void setCarSeriesName(String carSeriesName) {
		this.carSeriesName = carSeriesName;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public String getCarFirmName() {
		return carFirmName;
	}

	public void setCarFirmName(String carFirmName) {
		this.carFirmName = carFirmName;
	}

	public Integer getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}

	public Integer getCarFirm() {
		return carFirm;
	}

	public void setCarFirm(Integer carFirm) {
		this.carFirm = carFirm;
	}

	public Integer getCarSeries() {
		return carSeries;
	}

	public void setCarSeries(Integer carSeries) {
		this.carSeries = carSeries;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getIntentionCustomerState() {
		return intentionCustomerState;
	}

	public void setIntentionCustomerState(Integer intentionCustomerState) {
		this.intentionCustomerState = intentionCustomerState;
	}
}
