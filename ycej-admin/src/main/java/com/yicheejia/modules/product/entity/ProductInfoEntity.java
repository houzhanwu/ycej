package com.yicheejia.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author cn
 * @email 
 * @date 2018-07-16 19:57:23
 */
@TableName("ycej_product_info")
public class ProductInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.UUID)
	private String productId;
	/**
	 * 产品大类ID
	 */
	@NotBlank(message="产品大类ID不能为空")
	private String productCategoriesId;
	/**
	 * 产品大类名称
	 */
	 @NotBlank(message="产品大类名称不能为空")
	private String productCategoriesName;
	/**
	 * 产品方案ID
	 */
	 @NotBlank(message="产品方案ID不能为空")
	private String productSolutionId;
	/**
	 * 产品方案名称
	 */
	 @NotBlank(message="产品方案名称不能为空")
	private String productSolutionName;
	/**
	 * 添加人
	 */
	private Integer insertId;
	/**
	 * 添加时间
	 */
	private Date insertTime;
	/**
	 * 修改人
	 */
	private Integer updateId;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 是否启用
	 */
	private Integer isEnabled;
	
	public Integer getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * 设置：主键
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：主键
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置：产品大类ID
	 */
	public void setProductCategoriesId(String productCategoriesId) {
		this.productCategoriesId = productCategoriesId;
	}
	/**
	 * 获取：产品大类ID
	 */
	public String getProductCategoriesId() {
		return productCategoriesId;
	}
	/**
	 * 设置：产品大类名称
	 */
	public void setProductCategoriesName(String productCategoriesName) {
		this.productCategoriesName = productCategoriesName;
	}
	/**
	 * 获取：产品大类名称
	 */
	public String getProductCategoriesName() {
		return productCategoriesName;
	}
	/**
	 * 设置：产品方案ID
	 */
	public void setProductSolutionId(String productSolutionId) {
		this.productSolutionId = productSolutionId;
	}
	/**
	 * 获取：产品方案ID
	 */
	public String getProductSolutionId() {
		return productSolutionId;
	}
	/**
	 * 设置：产品方案名称
	 */
	public void setProductSolutionName(String productSolutionName) {
		this.productSolutionName = productSolutionName;
	}
	/**
	 * 获取：产品方案名称
	 */
	public String getProductSolutionName() {
		return productSolutionName;
	}
	/**
	 * 设置：添加人
	 */
	public void setInsertId(Integer insertId) {
		this.insertId = insertId;
	}
	/**
	 * 获取：添加人
	 */
	public Integer getInsertId() {
		return insertId;
	}
	/**
	 * 设置：添加时间
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * 设置：修改人
	 */
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	/**
	 * 获取：修改人
	 */
	public Integer getUpdateId() {
		return updateId;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
