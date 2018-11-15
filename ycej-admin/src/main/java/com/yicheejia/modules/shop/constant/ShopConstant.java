package com.yicheejia.modules.shop.constant;

public class ShopConstant {

    public static final String SHOP_ID_LIST = "shop_id_list";

    public enum ShopAttr{
        TOWN("乡镇",0),
        COUNTY("县城",1);

        String attr;
        int value;

        ShopAttr(String attr,int value){
            this.attr = attr;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String getAttr() {
            return attr;
        }
    }

    public enum ManageState{
        CONSTRUCTING("建设中",0),
        PREMANAGE("试营业",1),
        NORMALMANAGE("正常营业",2),
        CLOSED("关闭",3);

        String state;
        int value;

        ManageState(String state,int value){
            this.state = state;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String getState() {
            return state;
        }
    }

    public enum CustomerState{

        UNDEALED("未成交",0),
        DEALED("已成交",1);

        String state;
        int value;

        CustomerState(String state,int value){
            this.state = state;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String getState() {
            return state;
        }
    }
}
