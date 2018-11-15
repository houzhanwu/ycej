package com.yicheejia.modules.webservices.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
/**
 * 接口同步：客户基本信息
 * @author hunk
 *
 */
public class CustomerVO {

    @NotBlank(message="客户名称不能为空")
    private String customerName;
    private String phoneNo;
    @Range(min = 1, max = 3, message = "购买方式：1，全款车，2，分期车，3，优壹车")
    private Integer purchaseType;
    @NotBlank(message="身份证类型不能为空")
    private String idType;
    @NotBlank(message="身份证号不能为空")
    private String idNo;
    
    private List<FileVO> fileList;
    
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public Integer getPurchaseType() {
        return purchaseType;
    }
    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }
    public String getIdType() {
        return idType;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }
    public String getIdNo() {
        return idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public List<FileVO> getFileList() {
        return fileList;
    }
    public void setFileList(List<FileVO> fileList) {
        this.fileList = fileList;
    }
    @Override
    public String toString() {
        return "CustomerVO [customerName=" + customerName + ", phoneNo=" + phoneNo + ", purchaseType=" + purchaseType
                + ", idType=" + idType + ", idNo=" + idNo + ", fileList=" + fileList + "]";
    }

}
