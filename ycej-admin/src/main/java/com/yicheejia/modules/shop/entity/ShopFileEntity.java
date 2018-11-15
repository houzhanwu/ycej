package com.yicheejia.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-07-18 19:34:48
 */
@TableName("ycej_shop_file")
public class ShopFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer shopId;
	/**
	 * 
	 */
	private Integer frontFileId;

	private Integer backFileId;
	/**
	 * 
	 */
	private Integer fileType;
	/**
	 * 身份证正面
	 */
	private String frontImg;
	/**
	 * 身份证反面
	 */
	private String backImg;
	/**
	 * 
	 */
	private Date insTime;
	/**
	 * 
	 */
	private String insUser;

	/**
	 * 设置：
	 */
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：
	 */
	public Integer getShopId() {
		return shopId;
	}

	public Integer getFrontFileId() {
		return frontFileId;
	}

	public void setFrontFileId(Integer frontFileId) {
		this.frontFileId = frontFileId;
	}

	public Integer getBackFileId() {
		return backFileId;
	}

	public void setBackFileId(Integer backFileId) {
		this.backFileId = backFileId;
	}

	/**
	 * 设置：
	 */
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	/**
	 * 获取：
	 */
	public Integer getFileType() {
		return fileType;
	}
	/**
	 * 设置：
	 */
	public void setInsTime(Date insTime) {
		this.insTime = insTime;
	}
	/**
	 * 获取：
	 */
	public Date getInsTime() {
		return insTime;
	}
	/**
	 * 设置：
	 */
	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
	/**
	 * 获取：
	 */
	public String getInsUser() {
		return insUser;
	}

	public String getFrontImg() {
		return frontImg;
	}

	public void setFrontImg(String frontImg) {
		this.frontImg = frontImg;
	}

	public String getBackImg() {
		return backImg;
	}

	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}
}
