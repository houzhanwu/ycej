package com.yicheejia.modules.insure.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 保险公司
 * 
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
@TableName("ycej_insure_company")
public class InsureCompanyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String companyId;
	/**
	 * 保险公司名称
	 */
	private String companyName;
	/**
	 * 联系人
	 */
	private String contacts;
	/**
	 * 联系电话
	 */
	private String tel;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 
	 */
	private String insertId;
	/**
	 * 
	 */
	private Date insertTime;
	/**
	 * 
	 */
	private String updateId;
	/**
	 * 
	 */
	private Date updateTime;

	/**
	 * 设置：主键
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * 获取：主键
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * 设置：保险公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：保险公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：联系人
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	/**
	 * 获取：联系人
	 */
	public String getContacts() {
		return contacts;
	}
	/**
	 * 设置：联系电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：联系电话
	 */
	public String getTel() {
		return tel;
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
	/**
	 * 设置：
	 */
	public void setInsertId(String insertId) {
		this.insertId = insertId;
	}
	/**
	 * 获取：
	 */
	public String getInsertId() {
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
	/**
	 * 设置：
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	/**
	 * 获取：
	 */
	public String getUpdateId() {
		return updateId;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
