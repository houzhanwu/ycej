package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 汽车外观颜色表
 * 
 * @author 
 * @email 
 * @date 2018-08-30 15:46:19
 */
@TableName("ycej_car_color")
public class CarColorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 外观颜色
	 */
	private String color;
	/**
	 * 内饰颜色
	 */
	private String decorationColor;
	/**
	 * 创建人
	 */
	private String insUser;
	/**
	 * 创建日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date insTime;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：外观颜色
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * 获取：外观颜色
	 */
	public String getColor() {
		return color;
	}
	/**
	 * 设置：创建人
	 */
	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
	/**
	 * 获取：创建人
	 */
	public String getInsUser() {
		return insUser;
	}
	/**
	 * 设置：创建日期
	 */
	public void setInsTime(Date insTime) {
		this.insTime = insTime;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getInsTime() {
		return insTime;
	}

	public String getDecorationColor() {
		return decorationColor;
	}

	public void setDecorationColor(String decorationColor) {
		this.decorationColor = decorationColor;
	}
}
