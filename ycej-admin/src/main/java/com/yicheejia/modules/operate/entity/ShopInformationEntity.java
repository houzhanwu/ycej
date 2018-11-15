package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 门店信息表
 * 
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:28
 */
@TableName("ycej_shop_information")
public class ShopInformationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 门店ID
	 */
	@TableId
	private Integer shopId;
	/**
	 * 店名
	 */
	private String shopName;
	/**
	 * 店长姓名
	 */
	private String shopownerName;
	/**
	 * 店长电话
	 */
	private Integer shopownerMobile;
	/**
	 * 身份证号
	 */
	private String shopownerIdcard;
	/**
	 * 店面地址
	 */
	private String shopAdd;
	/**
	 * 1建设中、2试营业、3正常营业、4关闭（退网）
	 */
	private Integer shopStatus;
	/**
	 * 0乡镇；1县城
	 */
	private Integer shopProperty;
	/**
	 * Pos机编号
	 */
	private Integer posId;
	/**
	 * 门店省份ID
	 */
	private Integer provinceId;
	/**
	 * 门店市ID
	 */
	private Integer cityId;

	/**
	 * 设置：门店ID
	 */
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：门店ID
	 */
	public Integer getShopId() {
		return shopId;
	}
	/**
	 * 设置：店名
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**
	 * 获取：店名
	 */
	public String getShopName() {
		return shopName;
	}
	/**
	 * 设置：店长姓名
	 */
	public void setShopownerName(String shopownerName) {
		this.shopownerName = shopownerName;
	}
	/**
	 * 获取：店长姓名
	 */
	public String getShopownerName() {
		return shopownerName;
	}
	/**
	 * 设置：店长电话
	 */
	public void setShopownerMobile(Integer shopownerMobile) {
		this.shopownerMobile = shopownerMobile;
	}
	/**
	 * 获取：店长电话
	 */
	public Integer getShopownerMobile() {
		return shopownerMobile;
	}
	/**
	 * 设置：身份证号
	 */
	public void setShopownerIdcard(String shopownerIdcard) {
		this.shopownerIdcard = shopownerIdcard;
	}
	/**
	 * 获取：身份证号
	 */
	public String getShopownerIdcard() {
		return shopownerIdcard;
	}
	/**
	 * 设置：店面地址
	 */
	public void setShopAdd(String shopAdd) {
		this.shopAdd = shopAdd;
	}
	/**
	 * 获取：店面地址
	 */
	public String getShopAdd() {
		return shopAdd;
	}
	/**
	 * 设置：1建设中、2试营业、3正常营业、4关闭（退网）
	 */
	public void setShopStatus(Integer shopStatus) {
		this.shopStatus = shopStatus;
	}
	/**
	 * 获取：1建设中、2试营业、3正常营业、4关闭（退网）
	 */
	public Integer getShopStatus() {
		return shopStatus;
	}
	/**
	 * 设置：0乡镇；1县城
	 */
	public void setShopProperty(Integer shopProperty) {
		this.shopProperty = shopProperty;
	}
	/**
	 * 获取：0乡镇；1县城
	 */
	public Integer getShopProperty() {
		return shopProperty;
	}
	/**
	 * 设置：Pos机编号
	 */
	public void setPosId(Integer posId) {
		this.posId = posId;
	}
	/**
	 * 获取：Pos机编号
	 */
	public Integer getPosId() {
		return posId;
	}
	/**
	 * 设置：门店省份ID
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：门店省份ID
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：门店市ID
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：门店市ID
	 */
	public Integer getCityId() {
		return cityId;
	}
}
