package com.yicheejia.modules.order.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 
 *
 * @author fkm
 * @email 
 * @date 2018-07-11 14:16:58
 */
public class OrderFileBean {
    @Excel(name = "主键")
    private Integer orderFileId;
    @Excel(name = "图片名称")
    private String imgName;
    @Excel(name = "附件主键")
    private String fileId;
    @Excel(name = "添加人")
    private Integer insertId;
    @Excel(name = "添加时间")
    private Date insertTime;
    @Excel(name = "业务类型")
    private String businessType;
    @Excel(name = "业务主键")
    private String businessId;
    @Excel(name = "文件类型")
    private String fileType;

    public void setOrderFileId(Integer orderFileId) {
        this.orderFileId = orderFileId;
    }
    public Integer getOrderFileId() {
        return orderFileId;
    }
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
    public String getImgName() {
        return imgName;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getFileId() {
        return fileId;
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
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    public String getBusinessType() {
        return businessType;
    }
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    public String getBusinessId() {
        return businessId;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getFileType() {
        return fileType;
    }
}
