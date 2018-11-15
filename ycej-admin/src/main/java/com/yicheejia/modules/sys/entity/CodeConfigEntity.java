package com.yicheejia.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hunk
 * @email 
 * @date 2018-07-22 16:55:33
 */
@TableName("ycej_code_config")
public class CodeConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键:SerialType增加与主键一致
	 */
	@TableId(type = IdType.INPUT)
	private String codeConfigId;
	/**
	 * 常量
	 */
	private String constant;
	/**
	 * 流水日期
	 */
	private String serialDate;
	/**
	 * 流水长度
	 */
	private Integer serialLength;
	/**
	 * 流水值
	 */
	private Integer serialValue;

	/**
	 * 设置：主键
	 */
	public void setCodeConfigId(String codeConfigId) {
		this.codeConfigId = codeConfigId;
	}
	/**
	 * 获取：主键
	 */
	public String getCodeConfigId() {
		return codeConfigId;
	}
	/**
	 * 设置：常量
	 */
	public void setConstant(String constant) {
		this.constant = constant;
	}
	/**
	 * 获取：常量
	 */
	public String getConstant() {
		return constant;
	}
	/**
	 * 设置：流水日期
	 */
	public void setSerialDate(String serialDate) {
		this.serialDate = serialDate;
	}
	/**
	 * 获取：流水日期
	 */
	public String getSerialDate() {
		return serialDate;
	}
	/**
	 * 设置：流水长度
	 */
	public void setSerialLength(Integer serialLength) {
		this.serialLength = serialLength;
	}
	/**
	 * 获取：流水长度
	 */
	public Integer getSerialLength() {
		return serialLength;
	}
	/**
	 * 设置：流水值
	 */
	public void setSerialValue(Integer serialValue) {
		this.serialValue = serialValue;
	}
	/**
	 * 获取：流水值
	 */
	public Integer getSerialValue() {
		return serialValue;
	}
}
