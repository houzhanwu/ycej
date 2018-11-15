package com.yicheejia.modules.financialmanage.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-07-12 16:52:12
 */
@TableName("ycej_posterminal_record")
public class PosterminalRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * POS终端号变更记录
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String postRecordId;
	/**
	 * 门店主键
	 */
	private String shopId;
	/**
	 * 终端编号
	 */
	private String terminalNum;
	/**
	 * 变更人
	 */
	private String updUser;
	/**
	 * 变更日期
	 */
	private Date updTime;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 设置：POS终端号变更记录
	 */
	public void setPostRecordId(String postRecordId) {
		this.postRecordId = postRecordId;
	}
	/**
	 * 获取：POS终端号变更记录
	 */
	public String getPostRecordId() {
		return postRecordId;
	}
	/**
	 * 设置：门店主键
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：门店主键
	 */
	public String getShopId() {
		return shopId;
	}
	/**
	 * 设置：终端编号
	 */
	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}
	/**
	 * 获取：终端编号
	 */
	public String getTerminalNum() {
		return terminalNum;
	}
	/**
	 * 设置：变更人
	 */
	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}
	/**
	 * 获取：变更人
	 */
	public String getUpdUser() {
		return updUser;
	}
	/**
	 * 设置：变更日期
	 */
	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}
	/**
	 * 获取：变更日期
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
}
