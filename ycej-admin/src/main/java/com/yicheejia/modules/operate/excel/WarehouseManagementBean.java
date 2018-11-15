package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 
 *
 * @author hunk
 * @email 
 * @date 2018-07-24 11:30:47
 */
public class WarehouseManagementBean {
    @Excel(name = "主键")
    private Integer id;
    @Excel(name = "仓库编码")
    private String warehouseCode;
    @Excel(name = "仓库名称")
    private String warehouseName;
    @Excel(name = "联系人")
    private String linkman;
    @Excel(name = "联系方式")
    private String contactInformation;
    @Excel(name = "详细地址")
    private String detailedAddress;
    @Excel(name = "备注")
    private String remarks;
    @Excel(name = "添加人")
    private Integer insertId;
    @Excel(name = "添加时间")
    private Date insertTime;
    @Excel(name = "修改人")
    private Integer updateId;
    @Excel(name = "修改时间")
    private Date updateTime;

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
    public String getWarehouseCode() {
        return warehouseCode;
    }
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
    public String getWarehouseName() {
        return warehouseName;
    }
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }
    public String getLinkman() {
        return linkman;
    }
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
    public String getContactInformation() {
        return contactInformation;
    }
    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }
    public String getDetailedAddress() {
        return detailedAddress;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setInsertId(Integer insertId) {
        this.insertId = insertId;
    }
    public Integer getInsertId() {
        return insertId;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    public Date getInsertTime() {
        return insertTime;
    }
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }
    public Integer getUpdateId() {
        return updateId;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
}
