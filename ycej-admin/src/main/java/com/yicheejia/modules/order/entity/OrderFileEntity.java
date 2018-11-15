package com.yicheejia.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author fkm
 * @email 
 * @date 2018-07-11 14:16:58
 */
@TableName("ycej_order_file")
public class OrderFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String orderFileId;
	/**
	 * 图片名称
	 */
	private String imgName;
	/**
	 * 附件主键
	 */
	private String fileId;
	/**
	 * 添加人
	 */
	private Integer insertId;
	/**
	 * 添加时间
	 */
	private Date insertTime;
	/**
	 * 业务类型
	 */
	private String businessType;
	/**
	 * 业务主键
	 */
	private String orderId;
	/**
	 * 文件类型
	 */
	private String fileType;

	/**
	 * 设置：主键
	 */
	public void setOrderFileId(String orderFileId) {
		this.orderFileId = orderFileId;
	}
	/**
	 * 获取：主键
	 */
	public String getOrderFileId() {
		return orderFileId;
	}
	/**
	 * 设置：图片名称
	 */
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	/**
	 * 获取：图片名称
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
	 * 设置：业务类型
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	/**
	 * 获取：业务类型
	 */
	public String getBusinessType() {
		return businessType;
	}
	/**
	 * 设置：业务主键
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：业务主键
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 设置：文件类型
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * 获取：文件类型
	 */
	public String getFileType() {
		return fileType;
	}
	@TableField(exist = false)
	private String fileName;
	@TableField(exist = false)
	private String filePath;

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
