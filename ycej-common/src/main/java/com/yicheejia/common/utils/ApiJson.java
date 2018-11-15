package com.yicheejia.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * ApiJson请求时返回的JSON对象
 * 
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(value={"success","close","type","refrsh"})
public class ApiJson {

	/** 提示信息 */
	@JsonView(AjaxView.class)
	private String msg;
	/** 数据请求状态码(100:成功,101：失败,102:登录超时) */
	@JsonView(AjaxView.class)
	private Integer msgcode = 100;
	/** 返回对象 */
	@JsonView(AjaxView.class)
	private Object object;
	/** 成功标识 */
	private Boolean success = true;
	private Boolean close = true;// 是否关闭窗口
	private String type;
	private Boolean refrsh = true;// 是否刷新父页面
	private String  signature ;//签名
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getMsg() {
		return msg;
	}

	public ApiJson setMsg(String msg) {
		this.msg = msg;
		this.msgcode = 100;
		this.setSuccess(true);
		this.type = "success";
		return this;
	}

	public ApiJson setError(String msg) {
		this.msg = msg;
		this.msgcode = 101;
		this.type = "error";
		this.setSuccess(false);
		this.setClose(false);
		return this;
	}

	public Integer getMsgcode() {
		return msgcode;
	}

	public void setMsgcode(Integer msgcode) {
		this.msgcode = msgcode;
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject() {
		return (T) object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Boolean getClose() {
		return close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getRefrsh() {
		return refrsh;
	}

	public void setRefrsh(Boolean refrsh) {
		this.refrsh = refrsh;
	}

}