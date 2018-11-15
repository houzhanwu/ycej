package com.yicheejia.modules.inquiry.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 资源客户品牌表
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:44:05
 */
public class ResourceBrandBean {
    @Excel(name = "")
    private Long id;
    @Excel(name = "")
    private Long resourceCustomerId;
    @Excel(name = "")
    private Long carBrandId;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setResourceCustomerId(Long resourceCustomerId) {
        this.resourceCustomerId = resourceCustomerId;
    }
    public Long getResourceCustomerId() {
        return resourceCustomerId;
    }
    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }
    public Long getCarBrandId() {
        return carBrandId;
    }
}
