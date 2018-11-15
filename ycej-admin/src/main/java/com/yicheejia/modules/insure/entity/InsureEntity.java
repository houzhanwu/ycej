package com.yicheejia.modules.insure.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 保险
 * 
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
@TableName("ycej_insure")
public class InsureEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String insureId;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 交强保险公司编号
	 */
	private String insuranceId;
	/**
	 * 商业保险公司编号
	 */
	private String commercialId;
	/**
	 * 保险金额
	 */
	private BigDecimal premium;
	/**
	 * 理赔金额
	 */
	private BigDecimal claims;
	/**
	 * 出保时间
	 */
	private Date insureTime;
	/**
	 * 出保次数
	 */
	private Integer insureNum;
	/**
	 * 出险情况
	 */
	private String insure;
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
	
	private String flag;
	

	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * 设置：主键
	 */
	public void setInsureId(String insureId) {
		this.insureId = insureId;
	}
	/**
	 * 获取：主键
	 */
	public String getInsureId() {
		return insureId;
	}
	/**
	 * 设置：订单号
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单号
	 */
	
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 设置：交强保险公司编号
	 */
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
	/**
	 * 获取：交强保险公司编号
	 */
	public String getInsuranceId() {
		return insuranceId;
	}
	
	public String getCommercialId() {
		return commercialId;
	}
	public void setCommercialId(String commercialId) {
		this.commercialId = commercialId;
	}
	/**
	 * 设置：保险金额
	 */
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	/**
	 * 获取：保险金额
	 */
	public BigDecimal getPremium() {
		return premium;
	}
	/**
	 * 设置：理赔金额
	 */
	public void setClaims(BigDecimal claims) {
		this.claims = claims;
	}
	/**
	 * 获取：理赔金额
	 */
	public BigDecimal getClaims() {
		return claims;
	}
	/**
	 * 设置：出保时间
	 */
	public void setInsureTime(Date insureTime) {
		this.insureTime = insureTime;
	}
	/**
	 * 获取：出保时间
	 */
	public Date getInsureTime() {
		return insureTime;
	}
	/**
	 * 设置：出保次数
	 */
	public void setInsureNum(Integer insureNum) {
		this.insureNum = insureNum;
	}
	/**
	 * 获取：出保次数
	 */
	public Integer getInsureNum() {
		return insureNum;
	}
	/**
	 * 设置：出险情况
	 */
	public void setInsure(String insure) {
		this.insure = insure;
	}
	/**
	 * 获取：出险情况
	 */
	public String getInsure() {
		return insure;
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
	
}
