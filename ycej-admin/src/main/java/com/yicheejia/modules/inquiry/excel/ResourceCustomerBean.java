package com.yicheejia.modules.inquiry.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 资源客户表
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:27:35
 */
public class ResourceCustomerBean {
    @Excel(name = "")
    private Long resourceId;
    @Excel(name = "资源名称")
    private String resourceName;
    @Excel(name = "")
    private Integer provinceId;
    @Excel(name = "省")
    private String provinceName;
    @Excel(name = "")
    private Integer cityId;
    @Excel(name = "市")
    private String cityName;
    @Excel(name = "联系人")
    private String contact;
    @Excel(name = "手机号")
    private String mobile;
    @Excel(name = "登录密码")
    private String password;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "创建时间")
    private Date insTime;
    @Excel(name = "创建用户")
    private String insUser;

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
    public Long getResourceId() {
        return resourceId;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
    public Integer getProvinceId() {
        return provinceId;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public String getProvinceName() {
        return provinceName;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getCityName() {
        return cityName;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getContact() {
        return contact;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMobile() {
        return mobile;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
    public void setInsTime(Date insTime) {
        this.insTime = insTime;
    }
    public Date getInsTime() {
        return insTime;
    }
    public void setInsUser(String insUser) {
        this.insUser = insUser;
    }
    public String getInsUser() {
        return insUser;
    }
}
