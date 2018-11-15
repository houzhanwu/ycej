package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-18 16:43:36
 */
public class PurchaseDetailBean {
    @Excel(name = "采购明细主键")
    private Integer purchDetailId;
    @Excel(name = "品牌主键")
    private String brandId;
    @Excel(name = "厂商主键")
    private String manufacturerId;
    @Excel(name = "车系主键")
    private String seriesId;
    @Excel(name = "车型主键")
    private String modelId;
    @Excel(name = "外观颜色")
    private String colour;
    @Excel(name = "排量")
    private BigDecimal displacement;
    @Excel(name = "采购成本价")
    private BigDecimal purchCostPrice;
    @Excel(name = "建议销售价")
    private BigDecimal sugSalePrice;
    @Excel(name = "采购发票价")
    private BigDecimal purchInvoicePrice;
    @Excel(name = "出厂日期")
    private Date productDate;
    @Excel(name = "车架号")
    private String vin;
    @Excel(name = "发动机号")
    private String engineNo;
    @Excel(name = "备注")
    private String remark;

    public void setPurchDetailId(Integer purchDetailId) {
        this.purchDetailId = purchDetailId;
    }
    public Integer getPurchDetailId() {
        return purchDetailId;
    }
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    public String getBrandId() {
        return brandId;
    }
    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
    public String getManufacturerId() {
        return manufacturerId;
    }
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }
    public String getSeriesId() {
        return seriesId;
    }
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
    public String getModelId() {
        return modelId;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public String getColour() {
        return colour;
    }
    public void setDisplacement(BigDecimal displacement) {
        this.displacement = displacement;
    }
    public BigDecimal getDisplacement() {
        return displacement;
    }
    public void setPurchCostPrice(BigDecimal purchCostPrice) {
        this.purchCostPrice = purchCostPrice;
    }
    public BigDecimal getPurchCostPrice() {
        return purchCostPrice;
    }
    public void setSugSalePrice(BigDecimal sugSalePrice) {
        this.sugSalePrice = sugSalePrice;
    }
    public BigDecimal getSugSalePrice() {
        return sugSalePrice;
    }
    public void setPurchInvoicePrice(BigDecimal purchInvoicePrice) {
        this.purchInvoicePrice = purchInvoicePrice;
    }
    public BigDecimal getPurchInvoicePrice() {
        return purchInvoicePrice;
    }
    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }
    public Date getProductDate() {
        return productDate;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public String getVin() {
        return vin;
    }
    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }
    public String getEngineNo() {
        return engineNo;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
}
