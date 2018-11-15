package com.yicheejia.modules.shop.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 门店表
 *
 * @author cn
 * @email 
 * @date 2018-07-09 15:22:55
 */
public class YcejShopBean {
    @Excel(name = "门店id")
    private Integer shopId;
    @Excel(name = "门店编号")
    private String shopNo;
    @Excel(name = "门店属性:0乡镇,1县城")
    private Integer shopAttr;
    @Excel(name = "门店名称")
    private String shopName;
    @Excel(name = "省份名")
    private String provinceName;
    @Excel(name = "省份id")
    private Integer provinceId;
    @Excel(name = "市名")
    private String cityName;
    @Excel(name = "市id")
    private Integer cityId;
    @Excel(name = "区名")
    private String districtName;
    @Excel(name = "区号")
    private String districtCode;
    @Excel(name = "门店地址")
    private String shopAddress;
    @Excel(name = "pos机编号")
    private String posNo;
    @Excel(name = "店长姓名")
    private String managerName;
    @Excel(name = "联系方式")
    private String shopTel;
    @Excel(name = "其它电话")
    private String otherTel;
    @Excel(name = "身份证号")
    private String cardNo;
    @Excel(name = "身份证图片url")
    private String cardNoUrl;
    @Excel(name = "运营状态,0:建设中、1:试营业、2:正常营业、3:关闭（退网）")
    private Integer manageState;
    @Excel(name = "创建时间",format = "yyyy-MM-dd HH:mm:ss")
    private Date insTime;
    @Excel(name = "创建人")
    private String insUser;
    @Excel(name = "修改时间",format = "yyyy-MM-dd HH:mm:ss")
    private Date updTime;
    @Excel(name = "修改人")
    private String updUser;
    @Excel(name = "备注")
    private String remark;

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public Integer getShopId() {
        return shopId;
    }
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }
    public String getShopNo() {
        return shopNo;
    }
    public void setShopAttr(Integer shopAttr) {
        this.shopAttr = shopAttr;
    }
    public Integer getShopAttr() {
        return shopAttr;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getShopName() {
        return shopName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
    public Integer getProvinceId() {
        return provinceId;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getCityId() {
        return cityId;
    }
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    public String getDistrictName() {
        return districtName;
    }
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
    public String getDistrictCode() {
        return districtCode;
    }
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
    public String getShopAddress() {
        return shopAddress;
    }
    public void setPosNo(String posNo) {
        this.posNo = posNo;
    }
    public String getPosNo() {
        return posNo;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public String getManagerName() {
        return managerName;
    }
    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }
    public String getShopTel() {
        return shopTel;
    }
    public void setOtherTel(String otherTel) {
        this.otherTel = otherTel;
    }
    public String getOtherTel() {
        return otherTel;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNoUrl(String cardNoUrl) {
        this.cardNoUrl = cardNoUrl;
    }
    public String getCardNoUrl() {
        return cardNoUrl;
    }
    public void setManageState(Integer manageState) {
        this.manageState = manageState;
    }
    public Integer getManageState() {
        return manageState;
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
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
    public Date getUpdTime() {
        return updTime;
    }
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }
    public String getUpdUser() {
        return updUser;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
}
