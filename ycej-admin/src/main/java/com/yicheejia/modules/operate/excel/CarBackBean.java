package com.yicheejia.modules.operate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-10-25 15:47:20
 */
public class CarBackBean {
    @Excel(name = "id(主键)")
    private Long backId;
    @Excel(name = "调出门店ID")
    private Long backShopid;
    @Excel(name = "车辆库存ID")
    private Long inventoryId;
    @Excel(name = "状态")
    private Integer status;
    @Excel(name = "备注")
    private String backComment;

    public void setBackId(Long backId) {
        this.backId = backId;
    }
    public Long getBackId() {
        return backId;
    }
    public void setBackShopid(Long backShopid) {
        this.backShopid = backShopid;
    }
    public Long getBackShopid() {
        return backShopid;
    }
    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }
    public Long getInventoryId() {
        return inventoryId;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getStatus() {
        return status;
    }
    public void setBackComment(String backComment) {
        this.backComment = backComment;
    }
    public String getBackComment() {
        return backComment;
    }
}
