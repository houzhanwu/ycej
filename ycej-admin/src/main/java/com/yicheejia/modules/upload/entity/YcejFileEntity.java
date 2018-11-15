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
 * @date 2018-07-06 09:24:25
 */
@TableName("ycej_file")
public class YcejFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String fileId;
	/**
	 * 
	 */
	private String fileName;
	/**
	 * 
	 */
	private String filePath;
	/**
	 * 
	 */
	private String fileType;
	/**
	 * 
	 */
	private String fileSize;
	/**
	 * 
	 */
	private Integer downFlag;
	/**
	 * 添加人
	 */
	private Integer insertId;
	/**
	 * 添加时间
	 */
	private Date insertTime;
	/**
	 * 修改人
	 */
	private Integer updateId;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 是否启用（0:停用；1:启用）
	 */
	private Integer isEnabled;

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
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取：
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置：
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 获取：
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 设置：
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * 获取：
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * 设置：
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * 获取：
	 */
	public String getFileSize() {
		return fileSize;
	}
	/**
	 * 设置：
	 */
	public void setDownFlag(Integer downFlag) {
		this.downFlag = downFlag;
	}
	/**
	 * 获取：
	 */
	public Integer getDownFlag() {
		return downFlag;
	}
	/**
	 * 设置：添加人
	 */
	public void setInsertId(Integer insertId) {
		this.insertId = insertId;
	}
	/**
	 * 获取：添加人
	 */
	public Integer getInsertId() {
		return insertId;
	}
	/**
	 * 设置：添加时间
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getInsertTime() {
		return insertTime;
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
	 * 设置：是否启用（0:停用；1:启用）
	 */
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * 获取：是否启用（0:停用；1:启用）
	 */
	public Integer getIsEnabled() {
		return isEnabled;
	}
}
