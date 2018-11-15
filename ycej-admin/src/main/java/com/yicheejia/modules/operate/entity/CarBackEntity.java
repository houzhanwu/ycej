package com.yicheejia.modules.operate.entity;

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
 * @date 2018-10-25 15:47:20
 */
@TableName("ycej_car_back")
public class CarBackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id(主键)
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String backId;
	/**
	 * 调出门店ID
	 */
	private Long backShopid;
	/**
	 * 车辆库存ID
	 */
	private Long inventoryId;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String backComment;

	/**
	 * 设置：id(主键)
	 */
	public void setBackId(String backId) {
		this.backId = backId;
	}
	/**
	 * 获取：id(主键)
	 */
	public String getBackId() {
		return backId;
	}
	/**
	 * 设置：调出门店ID
	 */
	public void setBackShopid(Long backShopid) {
		this.backShopid = backShopid;
	}
	/**
	 * 获取：调出门店ID
	 */
	public Long getBackShopid() {
		return backShopid;
	}
	/**
	 * 设置：车辆库存ID
	 */
	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}
	/**
	 * 获取：车辆库存ID
	 */
	public Long getInventoryId() {
		return inventoryId;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：备注
	 */
	public void setBackComment(String backComment) {
		this.backComment = backComment;
	}
	/**
	 * 获取：备注
	 */
	public String getBackComment() {
		return backComment;
	}
}
