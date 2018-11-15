package com.yicheejia.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.yicheejia.common.validator.group.AddGroup;
import com.yicheejia.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 供应商信息表
 * 
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:28
 */
@TableName("ycej_supplier")
public class SupplierEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 供应商ID（主键）
	 */
	@TableId
	private Integer supplierId;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 供应商类型
	 */
	private String supplierType;
	/**
	 * 车辆类型
	 */
	private String vehicleType;
	/**
	 * 车辆品牌List
	 */
	@TableField(exist = false)
	private List<SupplierCarbrandEntity> carBrandList;
	/**
	 * 车辆品牌
	 */
	private String carBrand;
	/**
	 * 车辆品牌名称
	 */
	private String carBrandName;
	
	/**
	 * 联系人
	 */
	private String supplierContact;
	/**
	 * 联系电话
	 */
	private String supplierMoblie;
	/**
	 * 地址
	 */
	private String supplierAdd;
	/**
	 * 省id
	 */
	private Integer provinceId;
	/**
	 * 省名
	 */
	private String provinceName;
	/**
	 * 省市县信息编号（市ID）
	 */
	private Integer cityId;
	/**
	 * 市名
	 */
	private String cityName;
	/**
	 * 保证金金额
	 */
	private BigDecimal supplierBail;
	/**
	 * 提供展车数量
	 */
	private Integer supplierCarnum;
	/**
	 * 供应商收款账号
	 */
	private String supplierBankcard;
	/**
	 * 供应商状态
	 */
	private Integer supplierState;

	@TableField(exist = false)
	private Integer fileId;

	@TableField(exist = false)
	private String licenseImg;
	/**
	 * 添加人
	 */
	private String insertUser;
	/**
	 * 添加时间
	 */
	private Date insertTime;
	/**
	 * 修改人
	 */
	private String updateUser;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 备注
	 */
	private String comment;
	/**
     * 收款开户行
     */
	private String dueBank;
    private Integer dueBankCode;
    /**
     * 收款开户支行
     */
	private String branchBank;
	/**
	 * 已用展车
	 */
	@TableField(exist = false)
	private Integer count;
	/**
	 * 备注 --审核时填写的备注
	 */
	@TableField(exist = false)
	private String remark;
	/**
	 * 收款账号类型
	 */
	private String dueAccountType;
	/**
	 * 收款账户名称
	 */
	private String dueAccountName;
	/**
	 * 标记，用来判断是否为点击变更
	 */
	@TableField(exist = false)
	private String flag;


	/**
	 * 设置：供应商ID（主键）
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：供应商ID（主键）
	 */
	public Integer getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：供应商名称
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * 获取：供应商名称
	 */
	public String getSupplierName() {
		return supplierName;
	}
	/**
	 * 设置：联系电话
	 */
	public void setSupplierMoblie(String supplierMoblie) {
		this.supplierMoblie = supplierMoblie;
	}
	/**
	 * 获取：联系电话
	 */
	public String getSupplierMoblie() {
		return supplierMoblie;
	}
	/**
	 * 设置：地址
	 */
	public void setSupplierAdd(String supplierAdd) {
		this.supplierAdd = supplierAdd;
	}
	/**
	 * 获取：地址
	 */
	public String getSupplierAdd() {
		return supplierAdd;
	}
	/**
	 * 设置：省市县信息编号（市ID）
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：省市县信息编号（市ID）
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * 设置：保证金金额
	 */
	public void setSupplierBail(BigDecimal supplierBail) {
		this.supplierBail = supplierBail;
	}
	/**
	 * 获取：保证金金额
	 */
	public BigDecimal getSupplierBail() {
		return supplierBail;
	}
	/**
	 * 设置：提供展车数量
	 */
	public void setSupplierCarnum(Integer supplierCarnum) {
		this.supplierCarnum = supplierCarnum;
	}
	/**
	 * 获取：提供展车数量
	 */
	public Integer getSupplierCarnum() {
		return supplierCarnum;
	}
	/**
	 * 设置：供应商收款账号
	 */
	public void setSupplierBankcard(String supplierBankcard) {
		this.supplierBankcard = supplierBankcard;
	}
	/**
	 * 获取：供应商收款账号
	 */
	public String getSupplierBankcard() {
		return supplierBankcard;
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
	/**
	 * 设置：备注
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：备注
	 */
	public String getComment() {
		return comment;
	}
	   /**
     * 设置：收款开户行
     */
    public void setDueBank(String dueBank) {
        this.dueBank = dueBank;
    }
    /**
     * 获取：收款开户行
     */
    public String getDueBank() {
        return dueBank;
    }
    /**
     * 设置：收款开户支行
     */
    public void setBranchBank(String branchBank) {
        this.branchBank = branchBank;
    }
    /**
     * 获取：收款开户支行
     */
    public String getBranchBank() {
        return branchBank;
    }

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getSupplierState() {
		return supplierState;
	}

	public void setSupplierState(Integer supplierState) {
		this.supplierState = supplierState;
	}

	public String getSupplierContact() {
		return supplierContact;
	}

	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}

	public Integer getDueBankCode() {
		return dueBankCode;
	}

	public void setDueBankCode(Integer dueBankCode) {
		this.dueBankCode = dueBankCode;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getLicenseImg() {
		return licenseImg;
	}

	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}

	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public List<SupplierCarbrandEntity> getCarBrandList() {
		return carBrandList;
	}
	public void setCarBrandList(List<SupplierCarbrandEntity> carBrandList) {
		this.carBrandList = carBrandList;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarBrandName() {
		return carBrandName;
	}
	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDueAccountType() {
		return dueAccountType;
	}
	public void setDueAccountType(String dueAccountType) {
		this.dueAccountType = dueAccountType;
	}
	public String getDueAccountName() {
		return dueAccountName;
	}
	public void setDueAccountName(String dueAccountName) {
		this.dueAccountName = dueAccountName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
