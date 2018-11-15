package com.yicheejia.common.utils.redis;

/**
 * @Auther hunk
 * @Date 2018年7月23日
 */
public enum SerialType {

    //订单编号设置
    order_no(1, "ser:order_no",4),
    //采购编号设置
    purchase_no(2, "ser:purchase_no",4);

    private int index;
    /**
     * 编码类别：唯一标识一个编码
     */
    private String value;
    /**
     * 流水号长度
     */
    private int length;

    SerialType(int index, String value,int length) {
        this.index = index;
        this.value = value;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}