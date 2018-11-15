package com.yicheejia.modules.financialmanage.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-12 16:52:12
 */
public class PosterminalRecordBean {
    @Excel(name = "POS终端号变更记录")
    private Integer postRecordId;
    @Excel(name = "门店主键")
    private String shopId;
    @Excel(name = "终端编号")
    private String terminalNum;
    @Excel(name = "变更人")
    private String updUser;
    @Excel(name = "变更日期")
    private Date updTime;
    @Excel(name = "备注")
    private String remark;

    public void setPostRecordId(Integer postRecordId) {
        this.postRecordId = postRecordId;
    }
    public Integer getPostRecordId() {
        return postRecordId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    public String getShopId() {
        return shopId;
    }
    public void setTerminalNum(String terminalNum) {
        this.terminalNum = terminalNum;
    }
    public String getTerminalNum() {
        return terminalNum;
    }
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }
    public String getUpdUser() {
        return updUser;
    }
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
    public Date getUpdTime() {
        return updTime;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
}
