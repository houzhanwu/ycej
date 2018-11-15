package com.yicheejia.common.constants;


/**
 * 壹车e家工作流参数类
 * 工作流增加的参数 最好定义为常量，在业务代码中使用，并放至 keyMaps
 * @author 	cao_hailong
 * @date 	2018年7月12日
 * @Copyright 
 *
 * <pre>
 * =================Modify Record=================
 * Modifier			date				Content
 *
 * </pre>
 */
public class WfConstants {
	
	/**工作流分支条件参数 0 ： 退回 ， 1： 通过 ，-1 ：取消，3：为优壹车跳过定金支付*/
	public static final String ACTION = "action";	
	/**工作流 备注信息 map key */
    public static final String REMARK = "remark";
    /**工作流 人员指定 
     * 必须传 List 放人员
     */
    public static final String USER = "candidateUserId";
    /**客户名称 封装参数*/
    public static final String customerName = "customerName";
    public static final String supplierName = "supplierName";
    /**暂时处理 :流分支条件 人员指定 */
    public static final String[] keyMaps = new String[] {ACTION,USER,REMARK,customerName};
    
    /**处理转账方式*/
    public static final String[] payMethod = new String[] {"04","05","06"};//建行转账,郑行转账
    /**支付时可修改到下一步时的订单状态*/
    public static final String[] status = new String[] {"03","08","09"};//定金/尾款/首付款待支付
    /**转账时需上传的附件类型*/
    public static final String[] fileType = new String[] {"03","04","05"};//定金/尾款/首付款附件
    /*****************************流程定义***********************************/
    /**订单流程*/
    public static final String PROCESS_ORDER_KEY = "shopOrderProcess";
    /**退款订单流程*/
    public static final String PROCESS_REFUNDORDER_KEY = "refundOrderProcess";
    /**优壹车订单订单流程*/
    public static final String CHEAP_CAR_ORDER_KEY = "cheapCarOrderProcess";
    
    /**调拨流程*/
    public static final String PROCESS_ALLOT_KEY = "allotProcess";
    /**采购流程*/
    public static final String PROCESS_PURCHASE_KEY = "purchaseProcess";
    /**供应商审核流程*/
    public static final String PROCESS_SUPPLIER_AUDIT_KEY = "supplierAuditProcess";
    /**日常入库流程*/
    public static final String PROCESS_DAILY_INCOMING_KEY = "dailyIncomingProcess";
    /**保证金退款流程*/
    public static final String PROCESS_REFUND_DEPOSIT_KEY = "refundDepositProcess";
    /*******************************流程定义***************************************/
    
    /**工作流自动柜员*/
    public static final String AUTO_USER = "auto";
	/**消息推送 --跳转路径*/
//    public static final String URL_PATH = "http://10.1.1.226:9000/index.html";
    /**消息推送--跳转路径*/
    public static final String URL_PATH = "http://10.1.1.183/index.html";
    
}
