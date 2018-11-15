package com.yicheejia.modules.sms.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-10-23 16:20:35
 */
@TableName("ycej_sms")
public class SmsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String smsId;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 短信内容
	 */
	private String content;
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 发送状态
	 */
	private String sResult;
	/**
	 * 短信ID
	 */
	private Long msgid;
	/**
	 * 流水号
	 */
	private String custid;

	/**
	 * 设置：主键
	 */
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
	/**
	 * 获取：主键
	 */
	public String getSmsId() {
		return smsId;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：短信内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：短信内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：发送时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * 获取：发送时间
	 */
	public Date getSendTime() {
		return sendTime;
	}
	/**
	 * 设置：发送状态
	 */
	public void setSResult(String sResult) {
		this.sResult = sResult;
	}
	/**
	 * 获取：发送状态
	 */
	public String getSResult() {
		return sResult;
	}
	/**
	 * 设置：短信ID
	 */
	public void setMsgid(Long msgid) {
		this.msgid = msgid;
	}
	/**
	 * 获取：短信ID
	 */
	public Long getMsgid() {
		return msgid;
	}
	/**
	 * 设置：流水号
	 */
	public void setCustid(String custid) {
		this.custid = custid;
	}
	/**
	 * 获取：流水号
	 */
	public String getCustid() {
		return custid;
	}
}
