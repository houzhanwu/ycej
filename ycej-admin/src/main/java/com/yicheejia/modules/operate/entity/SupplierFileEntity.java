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
 * @date 2018-07-31 19:23:35
 */
@TableName("ycej_supplier_file")
public class SupplierFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private String supplierFileId;
	/**
	 * 供应商id
	 */
	private Integer supplierId;
	/**
	 * 文件表id
	 */
	private Integer fileId;
	/**
	 * 营业执照路径
	 */
	private String licenseImg;
	/**
	 * 
	 */
	private Date insTime;
	/**
	 * 
	 */
	private String insUser;
	/**
	 * 附件类型
	 */
	private String fileType;
	/**
	 * 文件路径
	 */
	@TableField(exist = false)
	private String filePath;
	/**
	 * 设置：供应商id
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：供应商id
	 */
	public Integer getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：文件表id
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	/**
	 * 获取：文件表id
	 */
	public Integer getFileId() {
		return fileId;
	}
	/**
	 * 设置：营业执照路径
	 */
	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}
	/**
	 * 获取：营业执照路径
	 */
	public String getLicenseImg() {
		return licenseImg;
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
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getSupplierFileId() {
		return supplierFileId;
	}
	public void setSupplierFileId(String supplierFileId) {
		this.supplierFileId = supplierFileId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
