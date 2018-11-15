package com.yicheejia.modules.insure.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 保险公司
 *
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
public class InsureCompanyBean {
    @Excel(name = "主键")
    private Integer companyId;
    @Excel(name = "保险公司名称")
    private String companyName;
    @Excel(name = "联系人")
    private String contacts;
    @Excel(name = "联系电话")
    private String tel;
    @Excel(name = "地址")
    private String address;
    @Excel(name = "备注")
    private String remake;
    @Excel(name = "")
    private String insertId;
    @Excel(name = "")
    private Date insertTime;
    @Excel(name = "")
    private String updateId;
    @Excel(name = "")
    private Date updateTime;

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
    public String getContacts() {
        return contacts;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getTel() {
        return tel;
    }
    public void setRemake(String remake) {
        this.remake = remake;
    }
    public String getRemake() {
        return remake;
    }
    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }
    public String getInsertId() {
        return insertId;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    public Date getInsertTime() {
        return insertTime;
    }
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
    public String getUpdateId() {
        return updateId;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
}
