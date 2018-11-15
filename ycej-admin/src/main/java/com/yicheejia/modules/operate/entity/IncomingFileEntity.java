package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-11-02 11:08:40
 */
@TableName("ycej_incoming_file")
public class IncomingFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日常入库附件表
	 */
	@TableId
	private Long id;
	/**
	 * 日常入库主键
	 */
	private String dailyIncomingId;
	/**
	 * 图片名字
	 */
	private String imgName;
	/**
	 * 附件主键
	 */
	private String fileId;
	/**
	 * 附件类型
	 */
	private String fileType;
	/**
	 * 
	 */
	private String insertUser;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 文件路径
	 */
	@TableField(exist = false)
	private String filePath;

	/**
	 * 设置：日常入库附件表
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：日常入库附件表
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：日常入库主键
	 */
	public void setDailyIncomingId(String dailyIncomingId) {
		this.dailyIncomingId = dailyIncomingId;
	}
	/**
	 * 获取：日常入库主键
	 */
	public String getDailyIncomingId() {
		return dailyIncomingId;
	}
	/**
	 * 设置：图片名字
	 */
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	/**
	 * 获取：图片名字
	 */
	public String getImgName() {
		return imgName;
	}
	/**
	 * 设置：附件主键
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**
	 * 获取：附件主键
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * 设置：附件类型
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * 获取：附件类型
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * 设置：
	 */
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	/**
	 * 获取：
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
