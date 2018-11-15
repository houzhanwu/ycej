package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yicheejia.modules.inquiry.entity.ResourceQuotedEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-09-28 15:23:41
 */
@TableName("ycej_price_question")
public class PriceQuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 提问门店
	 */
	private String shopId;
	/**
	 * 提问人
	 */
	private String questioner;
	/**
	 * 提问时间
	 */
	private Date questionTime;
	/**
	 * 客户姓名
	 */
	private String customer;
	/**
	 * 联系方式
	 */
	private String phone;
	/**
	 * 提问内容
	 */
	private String question;
	/**
	 * 回复人
	 */
	private String replier;
	/**
	 * 回复时间
	 */
	private Date replyTime;
	/**
	 * 车价来源
	 */
	private String priceFrom;
	/**
	 * 回复内容
	 */
	private String reply;
	/**
	 * 报价id
	 */
	private Long quotedId;

	@TableField(exist = false)
	private ResourceQuotedEntity quotedEntity;
	/**
	 * 品牌ID
	 */
	private String carBrand;
	/**
	 * 品牌名称
	 */
	private String carBrandName;
	/**
	 * 厂商ID
	 */
	private String carManufacturer;
	/**
	 * 厂商名称
	 */
	private String carManufacturerName;
	/**
	 * 车系ID
	 */
	private String carSeries;
	/**
	 * 车系名称
	 */
	private String carSeriesName;
	/**
	 * 车型ID
	 */
	private String carModel;
	/**
	 * 车型名称
	 */
	private String carModelName;
	/**
	 * 排量
	 */
	private String displacement;
	/**
	 * 外观颜色ID
	 */
	private String appearance;
	/**
	 * 外观颜色名称
	 */
	private String appearanceColour;
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
	 * 设置：提问门店
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：提问门店
	 */
	public String getShopId() {
		return shopId;
	}
	/**
	 * 设置：提问人
	 */
	public void setQuestioner(String questioner) {
		this.questioner = questioner;
	}
	/**
	 * 获取：提问人
	 */
	public String getQuestioner() {
		return questioner;
	}
	/**
	 * 设置：提问时间
	 */
	public void setQuestionTime(Date questionTime) {
		this.questionTime = questionTime;
	}
	/**
	 * 获取：提问时间
	 */
	public Date getQuestionTime() {
		return questionTime;
	}
	/**
	 * 设置：客户姓名
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * 获取：客户姓名
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * 设置：联系方式
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系方式
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：提问内容
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * 获取：提问内容
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * 设置：回复人
	 */
	public void setReplier(String replier) {
		this.replier = replier;
	}
	/**
	 * 获取：回复人
	 */
	public String getReplier() {
		return replier;
	}
	/**
	 * 设置：回复时间
	 */
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	/**
	 * 获取：回复时间
	 */
	public Date getReplyTime() {
		return replyTime;
	}
	/**
	 * 设置：车价来源
	 */
	public void setPriceFrom(String priceFrom) {
		this.priceFrom = priceFrom;
	}
	/**
	 * 获取：车价来源
	 */
	public String getPriceFrom() {
		return priceFrom;
	}
	/**
	 * 设置：回复内容
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}
	/**
	 * 获取：回复内容
	 */
	public String getReply() {
		return reply;
	}

	public Long getQuotedId() {
		return quotedId;
	}

	public void setQuotedId(Long quotedId) {
		this.quotedId = quotedId;
	}

	public ResourceQuotedEntity getQuotedEntity() {
		return quotedEntity;
	}

	public void setQuotedEntity(ResourceQuotedEntity quotedEntity) {
		this.quotedEntity = quotedEntity;
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
	public String getDisplacement() {
		return displacement;
	}
	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}
	public String getAppearance() {
		return appearance;
	}
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	public String getAppearanceColour() {
		return appearanceColour;
	}
	public void setAppearanceColour(String appearanceColour) {
		this.appearanceColour = appearanceColour;
	}
	
}
