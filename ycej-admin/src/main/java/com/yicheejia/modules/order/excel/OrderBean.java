package com.yicheejia.modules.order.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author fkm
 * @email 
 * @date 2018-07-11 14:16:58
 */
public class OrderBean {
    @Excel(name = "主键")
    private Long id;
    @Excel(name = "门店id")
    private Integer shopId;
    @Excel(name = "库存ID")
    private Integer inventoryId;
    @Excel(name = "车型ID")
    private Integer carModelId;
    @Excel(name = "订单编号")
    private String orderNo;
    @Excel(name = "销售价")
    private BigDecimal sellPrice;
    @Excel(name = "销售类型")
    private Integer sellType;
    @Excel(name = "交强险是否外出")
    private Integer insuranceIfout;
    @Excel(name = "商业是否外出")
    private Integer commercialIfout;
    @Excel(name = "是否店保")
    private Integer insureInshop;
    @Excel(name = "客户姓名")
    private String customerName;
    @Excel(name = "性别")
    private Integer sex;
    @Excel(name = "联系方式")
    private String contactInformation;
    @Excel(name = "证件类型")
    private Integer idType;
    @Excel(name = "证件号码")
    private String idNo;
    @Excel(name = "外观颜色")
    private String appearanceColour;
    @Excel(name = "内饰颜色")
    private String trimColour;
    @Excel(name = "排量")
    private BigDecimal displacement;
    @Excel(name = "订单来源")
    private Integer orderSource;
    @Excel(name = "支付总额")
    private BigDecimal payTotle;
    @Excel(name = "销售价格")
    private BigDecimal sellingPrice;
    @Excel(name = "发票价格")
    private BigDecimal invoicePrice;
    @Excel(name = "融资总额")
    private BigDecimal factTotle;
    @Excel(name = "是否收到合格证书")
    private Integer isqualified;
    @Excel(name = "订单状态")
    private String status;
    @Excel(name = "创建时间")
    private Date insertTime;
    @Excel(name = "创建人")
    private String insertId;
    @Excel(name = "修改时间")
    private Date updateTime;
    @Excel(name = "修改人")
    private String updateId;
    @Excel(name = "备注")
    private String remark;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public Integer getShopId() {
        return shopId;
    }
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
    public Integer getInventoryId() {
        return inventoryId;
    }
    public void setCarModelId(Integer carModelId) {
        this.carModelId = carModelId;
    }
    public Integer getCarModelId() {
        return carModelId;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }
    public BigDecimal getSellPrice() {
        return sellPrice;
    }
    public void setSellType(Integer sellType) {
        this.sellType = sellType;
    }
    public Integer getSellType() {
        return sellType;
    }
    public void setInsuranceIfout(Integer insuranceIfout) {
        this.insuranceIfout = insuranceIfout;
    }
    public Integer getInsuranceIfout() {
        return insuranceIfout;
    }
    public void setCommercialIfout(Integer commercialIfout) {
        this.commercialIfout = commercialIfout;
    }
    public Integer getCommercialIfout() {
        return commercialIfout;
    }
    public void setInsureInshop(Integer insureInshop) {
        this.insureInshop = insureInshop;
    }
    public Integer getInsureInshop() {
        return insureInshop;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getSex() {
        return sex;
    }
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
    public String getContactInformation() {
        return contactInformation;
    }
    public void setIdType(Integer idType) {
        this.idType = idType;
    }
    public Integer getIdType() {
        return idType;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getIdNo() {
        return idNo;
    }
    public void setAppearanceColour(String appearanceColour) {
        this.appearanceColour = appearanceColour;
    }
    public String getAppearanceColour() {
        return appearanceColour;
    }
    public void setTrimColour(String trimColour) {
        this.trimColour = trimColour;
    }
    public String getTrimColour() {
        return trimColour;
    }
    public void setDisplacement(BigDecimal displacement) {
        this.displacement = displacement;
    }
    public BigDecimal getDisplacement() {
        return displacement;
    }
    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }
    public Integer getOrderSource() {
        return orderSource;
    }
    public void setPayTotle(BigDecimal payTotle) {
        this.payTotle = payTotle;
    }
    public BigDecimal getPayTotle() {
        return payTotle;
    }
    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }
    public void setInvoicePrice(BigDecimal invoicePrice) {
        this.invoicePrice = invoicePrice;
    }
    public BigDecimal getInvoicePrice() {
        return invoicePrice;
    }
    public void setFactTotle(BigDecimal factTotle) {
        this.factTotle = factTotle;
    }
    public BigDecimal getFactTotle() {
        return factTotle;
    }
    public void setIsqualified(Integer isqualified) {
        this.isqualified = isqualified;
    }
    public Integer getIsqualified() {
        return isqualified;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    public Date getInsertTime() {
        return insertTime;
    }
    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }
    public String getInsertId() {
        return insertId;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
    public String getUpdateId() {
        return updateId;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
}
