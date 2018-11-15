package com.yicheejia.modules.inquiry.entity;



import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 资源客户表
 * 
 * @author 
 * @email 
 * @date 2018-10-23 16:27:35
 */
@TableName("ycej_resource_customer")
public class ResourceCustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.ID_WORKER)
	private Long resourceId;
	/**
	 * 资源名称
	 */
	private String resourceName;
	/**
	 * 
	 */
	private Integer provinceId;
	/**
	 * 省
	 */
	private String provinceName;
	/**
	 * 
	 */
	private Integer cityId;
	/**
	 * 市
	 */
	private String cityName;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date insTime;
	/**
	 * 创建用户
	 */
	private String insUser;
	/**
	 * 更新时间
	 */
	private Date updTime;
	/**
	 * 更新用户
	 */
	private String updUser;

	/**
	 * 销售品牌ID列表
	 */
	@TableField(exist = false)
	private List<Long> sellBrands;

	@TableField(exist = false)
	private List<Map<String,Object>> brandList;

	/**
	 * 销售品牌名称列表
	 */
	@TableField(exist = false)
	private List<String> sellBrandNames;

	public List<Long> getSellBrands() {
		return sellBrands;
	}

	public void setSellBrands(List<Long> sellBrands) {
		this.sellBrands = sellBrands;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * 设置：资源名称
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	/**
	 * 获取：资源名称
	 */
	public String getResourceName() {
		return resourceName;
	}
	/**
	 * 设置：
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：省
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * 获取：省
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * 设置：
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * 设置：市
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * 获取：市
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * 设置：联系人
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * 获取：联系人
	 */
	public String getContact() {
		return contact;
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
	 * 设置：登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：登录密码
	 */
	public String getPassword() {
		return password;
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
	 * 设置：创建时间
	 */
	public void setInsTime(Date insTime) {
		this.insTime = insTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getInsTime() {
		return insTime;
	}
	/**
	 * 设置：创建用户
	 */
	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
	/**
	 * 获取：创建用户
	 */
	public String getInsUser() {
		return insUser;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getUpdUser() {
		return updUser;
	}

	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}

	public List<String> getSellBrandNames() {
		return sellBrandNames;
	}

	public void setSellBrandNames(List<String> sellBrandNames) {
		this.sellBrandNames = sellBrandNames;
	}

	public List<Map<String, Object>> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<Map<String, Object>> brandList) {
		this.brandList = brandList;
	}
}
