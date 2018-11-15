package com.yicheejia.modules.product.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-16 19:57:23
 */
public class ProductInfoBean {
    @Excel(name = "主键")
    private String productId;
    @Excel(name = "产品大类ID")
    private String productCategoriesId;
    @Excel(name = "产品大类名称")
    private String productCategoriesName;
    @Excel(name = "产品方案ID")
    private String productSolutionId;
    @Excel(name = "产品方案名称")
    private String productSolutionName;
    @Excel(name = "添加人")
    private Integer insertId;
    @Excel(name = "添加时间")
    private Date insertTime;
    @Excel(name = "修改人")
    private Integer updateId;
    @Excel(name = "修改时间")
    private Date updateTime;

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductCategoriesId(String productCategoriesId) {
        this.productCategoriesId = productCategoriesId;
    }
    public String getProductCategoriesId() {
        return productCategoriesId;
    }
    public void setProductCategoriesName(String productCategoriesName) {
        this.productCategoriesName = productCategoriesName;
    }
    public String getProductCategoriesName() {
        return productCategoriesName;
    }
    public void setProductSolutionId(String productSolutionId) {
        this.productSolutionId = productSolutionId;
    }
    public String getProductSolutionId() {
        return productSolutionId;
    }
    public void setProductSolutionName(String productSolutionName) {
        this.productSolutionName = productSolutionName;
    }
    public String getProductSolutionName() {
        return productSolutionName;
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
