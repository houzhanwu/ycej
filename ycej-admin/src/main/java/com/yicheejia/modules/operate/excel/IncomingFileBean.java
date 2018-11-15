package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-11-02 11:08:40
 */
public class IncomingFileBean {
    @Excel(name = "日常入库附件表")
    private Long id;
    @Excel(name = "日常入库主键")
    private String dailyIncomingId;
    @Excel(name = "图片名字")
    private String imgName;
    @Excel(name = "附件主键")
    private String fileId;
    @Excel(name = "附件类型")
    private String fileType;
    @Excel(name = "")
    private String insertUser;
    @Excel(name = "")
    private Date insertTime;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setDailyIncomingId(String dailyIncomingId) {
        this.dailyIncomingId = dailyIncomingId;
    }
    public String getDailyIncomingId() {
        return dailyIncomingId;
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
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getFileType() {
        return fileType;
    }
    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }
    public String getInsertUser() {
        return insertUser;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    public Date getInsertTime() {
        return insertTime;
    }
}
