package com.yicheejia.modules.shop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yicheejia.common.validator.group.AddGroup;
import com.yicheejia.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 门店表
 * 
 * @author
 * @email
 * @date 2018-06-21 15:37:31
 */
@TableName("ycej_shop")
public class YcejShopEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 门店id
	 */
	@TableId(type = IdType.AUTO,value = "shop_id")
	private Integer shopId;
	/**
	 * 门店编号
	 */
	@NotNull(message="门店编号不能为空",groups = {AddGroup.class, UpdateGroup.class})
	private String shopNo;
	/**
	 * 门店属性:0乡镇,1县城
	 */
	@NotNull(message = "门店属性不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private Integer shopAttr;
	/**
	 * 门店名称
	 */
	@NotNull(message = "门店名称不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String shopName;
	/**
	 * 省份名
	 */
	private String provinceName;
	/**
	 * 省份id
	 */
	@NotNull(message = "省份不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private Integer provinceId;
	/**
	 * 市名
	 */
	private String cityName;
	/**
	 * 市id
	 */
	@NotNull(message = "城市不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private Integer cityId;
	/**
	 * 区名
	 */
	private String districtName;
	/**
	 * 区号
	 */
	private String districtCode;
	/**
	 * 门店地址
	 */
	@NotNull(message = "门店地址不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String shopAddress;
	/**
	 * pos机编号
	 */
	private String posNo;
	/**
	 * 店长姓名
	 */
	@NotNull(message = "店长姓名不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String managerName;
	/**
	 * 联系方式
	 */
	@NotNull(message = "联系方式不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String shopTel;
	/**
	 * 其它电话
	 */
	private String otherTel;
	/**
	 * 身份证号
	 */
	@NotNull(message = "身份证号不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String cardNo;
	/**
	 * 身份证图片url
	 */
	private String cardNoUrl;
	/**
	 * 身份证图片正面
	 */
	@TableField(exist = false)
	private String frontImg;
	/**
	 * 身份证图片反面
	 */
	@TableField(exist = false)
	private String backImg;
	/**
	 * 身份证正面对应文件表ID
	 */
	@TableField(exist = false)
	private Integer frontFileId;
	/**
	 * 身份证反面对应文件表ID
	 */
	@TableField(exist = false)
	private Integer backFileId;
	/**
	 * 运营状态,0:建设中、1:试营业、2:正常营业、3:关闭（退网）
	 */
	@NotNull(message = "运营状态不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private Integer manageState;
	/**
	 * 创建时间
	 */
	private Date insTime;
	/**
	 * 创建人
	 */
	private String insUser;
	/**
	 * 修改时间
	 */
	private Date updTime;
	/**
	 * 修改人
	 */
	private String updUser;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 城市经理id
	 */
	private Long cityManagerId;
	/**
	 * 城市经理名字
	 */
	@TableField(exist = false)
	private String cityManagerName;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date netApproveDate;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date budgetDecorateDate;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date decorateCompleteDate;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date firstCarPutDate;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date shopOpenApproveDate;

	/**
	 * 设置：门店id
	 */
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：门店id
	 */
	public Integer getShopId() {
		return shopId;
	}
	/**
	 * 设置：门店编号
	 */
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	/**
	 * 获取：门店编号
	 */
	public String getShopNo() {
		return shopNo;
	}
	/**
	 * 设置：门店属性:0乡镇,1县城
	 */
	public void setShopAttr(Integer shopAttr) {
		this.shopAttr = shopAttr;
	}
	/**
	 * 获取：门店属性:0乡镇,1县城
	 */
	public Integer getShopAttr() {
		return shopAttr;
	}
	/**
	 * 设置：门店名称
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**
	 * 获取：门店名称
	 */
	public String getShopName() {
		return shopName;
	}
	/**
	 * 设置：省份名
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * 获取：省份名
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * 设置：省份id
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：省份id
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：市名
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * 获取：市名
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * 设置：市id
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：市id
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * 设置：区名
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	/**
	 * 获取：区名
	 */
	public String getDistrictName() {
		return districtName;
	}
	/**
	 * 设置：区号
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	/**
	 * 获取：区号
	 */
	public String getDistrictCode() {
		return districtCode;
	}
	/**
	 * 设置：门店地址
	 */
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	/**
	 * 获取：门店地址
	 */
	public String getShopAddress() {
		return shopAddress;
	}
	/**
	 * 设置：pos机编号
	 */
	public void setPosNo(String posNo) {
		this.posNo = posNo;
	}
	/**
	 * 获取：pos机编号
	 */
	public String getPosNo() {
		return posNo;
	}
	/**
	 * 设置：店长姓名
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	/**
	 * 获取：店长姓名
	 */
	public String getManagerName() {
		return managerName;
	}
	/**
	 * 设置：联系方式
	 */
	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}
	/**
	 * 获取：联系方式
	 */
	public String getShopTel() {
		return shopTel;
	}
	/**
	 * 设置：身份证号
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * 获取：身份证号
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * 设置：身份证图片url
	 */
	public void setCardNoUrl(String cardNoUrl) {
		this.cardNoUrl = cardNoUrl;
	}
	/**
	 * 获取：身份证图片url
	 */
	public String getCardNoUrl() {
		return cardNoUrl;
	}
	/**
	 * 设置：运营状态,0:建设中、1:试营业、2:正常营业、3:关闭（退网）
	 */
	public void setManageState(Integer manageState) {
		this.manageState = manageState;
	}
	/**
	 * 获取：运营状态,0:建设中、1:试营业、2:正常营业、3:关闭（退网）
	 */
	public Integer getManageState() {
		return manageState;
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
	 * 设置：修改时间
	 */
	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdTime() {
		return updTime;
	}
	/**
	 * 设置：修改人
	 */
	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}
	/**
	 * 获取：修改人
	 */
	public String getUpdUser() {
		return updUser;
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

	public String getOtherTel() {
		return otherTel;
	}

	public void setOtherTel(String otherTel) {
		this.otherTel = otherTel;
	}

	public String getFrontImg() {
		return frontImg;
	}

	public void setFrontImg(String frontImg) {
		this.frontImg = frontImg;
	}

	public String getBackImg() {
		return backImg;
	}

	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}

	public Integer getFrontFileId() {
		return frontFileId;
	}

	public void setFrontFileId(Integer frontFileId) {
		this.frontFileId = frontFileId;
	}

	public Integer getBackFileId() {
		return backFileId;
	}

	public void setBackFileId(Integer backFileId) {
		this.backFileId = backFileId;
	}

	public Long getCityManagerId() {
		return cityManagerId;
	}

	public void setCityManagerId(Long cityManagerId) {
		this.cityManagerId = cityManagerId;
	}
	public String getCityManagerName() {
		return cityManagerName;
	}
	public void setCityManagerName(String cityManagerName) {
		this.cityManagerName = cityManagerName;
	}

	public Date getNetApproveDate() {
		return netApproveDate;
	}

	public void setNetApproveDate(Date netApproveDate) {
		this.netApproveDate = netApproveDate;
	}

	public Date getBudgetDecorateDate() {
		return budgetDecorateDate;
	}

	public void setBudgetDecorateDate(Date budgetDecorateDate) {
		this.budgetDecorateDate = budgetDecorateDate;
	}

	public Date getDecorateCompleteDate() {
		return decorateCompleteDate;
	}

	public void setDecorateCompleteDate(Date decorateCompleteDate) {
		this.decorateCompleteDate = decorateCompleteDate;
	}

	public Date getFirstCarPutDate() {
		return firstCarPutDate;
	}

	public void setFirstCarPutDate(Date firstCarPutDate) {
		this.firstCarPutDate = firstCarPutDate;
	}

	public Date getShopOpenApproveDate() {
		return shopOpenApproveDate;
	}

	public void setShopOpenApproveDate(Date shopOpenApproveDate) {
		this.shopOpenApproveDate = shopOpenApproveDate;
	}
}
