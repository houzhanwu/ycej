package com.yicheejia.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户跟踪表
 * 
 * @author cn
 * @email 
 * @date 2018-07-17 15:06:30
 */
@TableName("ycej_customer_track")
public class CustomerTrackEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 意向客户id
	 */
	private Integer customerId;
	/**
	 * 跟踪人
	 */
	private String trackPerson;
	/**
	 * 跟踪时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date trackTime;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 设置：意向客户id
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：意向客户id
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * 设置：跟踪人
	 */
	public void setTrackPerson(String trackPerson) {
		this.trackPerson = trackPerson;
	}
	/**
	 * 获取：跟踪人
	 */
	public String getTrackPerson() {
		return trackPerson;
	}
	/**
	 * 设置：跟踪时间
	 */
	public void setTrackTime(Date trackTime) {
		this.trackTime = trackTime;
	}
	/**
	 * 获取：跟踪时间
	 */
	public Date getTrackTime() {
		return trackTime;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
