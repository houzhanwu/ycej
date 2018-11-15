package com.yicheejia.modules.inquiry.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 资源报价历史记录表
 * 
 * @author 
 * @email 
 * @date 2018-10-29 17:42:28
 */
@TableName("ycej_resource_quoted_log")
public class ResourceQuotedLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 历史记录id
	 */
	@TableId(type = IdType.ID_WORKER)
	private Long logId;
	/**
	 * 供应价
	 */
	private BigDecimal offerPrice;
	/**
	 * 报价时间
	 */
	private Date quotedTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 报价ID
	 */
	private Long quotedId;

	/**
	 * 设置：历史记录id
	 */
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	/**
	 * 获取：历史记录id
	 */
	public Long getLogId() {
		return logId;
	}
	/**
	 * 设置：供应价
	 */
	public void setOfferPrice(BigDecimal offerPrice) {
		this.offerPrice = offerPrice;
	}
	/**
	 * 获取：供应价
	 */
	public BigDecimal getOfferPrice() {
		return offerPrice;
	}
	/**
	 * 设置：报价时间
	 */
	public void setQuotedTime(Date quotedTime) {
		this.quotedTime = quotedTime;
	}
	/**
	 * 获取：报价时间
	 */
	public Date getQuotedTime() {
		return quotedTime;
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
	 * 设置：报价ID
	 */
	public void setQuotedId(Long quotedId) {
		this.quotedId = quotedId;
	}
	/**
	 * 获取：报价ID
	 */
	public Long getQuotedId() {
		return quotedId;
	}
}
