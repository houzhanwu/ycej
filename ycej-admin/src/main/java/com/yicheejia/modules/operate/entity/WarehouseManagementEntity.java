package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hunk
 * @email 
 * @date 2018-07-24 11:30:47
 */
@TableName("ycej_warehouse_management")
public class WarehouseManagementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/**
	 * 仓库名称
	 */
	@ApiModelProperty(value = "仓库名称")
	private String warehouseName;
	/**
	 * 联系人
	 */
	private String linkman;
	/**
	 * 联系方式
	 */
	private String contactInformation;
	/**
	 * 详细地址
	 */
	private String detailedAddress;
	/**
	 * 备注
	 */
	private String remarks;
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
	/*
	 * 删除标识
	 */
	private Integer deleteFlag;

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
	 * 设置：仓库名称
	 */
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	/**
	 * 获取：仓库名称
	 */
	public String getWarehouseName() {
		return warehouseName;
	}
	/**
	 * 设置：联系人
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	/**
	 * 获取：联系人
	 */
	public String getLinkman() {
		return linkman;
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
	 * 设置：详细地址
	 */
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	/**
	 * 获取：详细地址
	 */
	public String getDetailedAddress() {
		return detailedAddress;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
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
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}
