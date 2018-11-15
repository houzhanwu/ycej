package com.yicheejia.modules.upload.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-06 09:24:03
 */
@TableName("ycej_cusid_img")
public class YcejCusidImgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String cusImgId;
	/**
	 * 
	 */
	private String imgName;
	/**
	 * 
	 */
	private String fileId;
	/**
	 * 
	 */
	private String businessId;
	/**
	 * 
	 */
	private Integer fileType;
	/**
	 * 
	 */
	private Integer insertId;
	/**
	 * 
	 */
	private Date insertTime;

	/**
	 * 设置：
	 */
	public void setCusImgId(String cusImgId) {
		this.cusImgId = cusImgId;
	}
	/**
	 * 获取：
	 */
	public String getCusImgId() {
		return cusImgId;
	}
	/**
	 * 设置：
	 */
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	/**
	 * 获取：
	 */
	public String getImgName() {
		return imgName;
	}
	/**
	 * 设置：
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**
	 * 获取：
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * 设置：
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	/**
	 * 获取：
	 */
	public String getBusinessId() {
		return businessId;
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
	public void setInsertId(Integer insertId) {
		this.insertId = insertId;
	}
	/**
	 * 获取：
	 */
	public Integer getInsertId() {
		return insertId;
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
}
