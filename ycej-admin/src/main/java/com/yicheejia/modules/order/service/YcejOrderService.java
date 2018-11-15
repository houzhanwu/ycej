package com.yicheejia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.order.entity.YcejOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * @date 2018-06-21 16:27:42
 */
public interface YcejOrderService extends IService<YcejOrderEntity> {

	LayuiPage queryPage(Map<String, Object> params);
	/**
	 * 
	 * @param ycejOrder
	 */
    public void insertOrder(YcejOrderEntity ycejOrder);
    /**
     * 根据订单对象更新订单
     * @param ycejOrder
     */
    public Map updateOrderById(YcejOrderEntity ycejOrder);
    /**
     * 根据Map封装更新订单。where 条件 id 必须封装
     * @param params
     */
	void updateOrder(Map<String, Object> params);
	
	/**
	 * 订单状态对外处理
	 */
	public R submitService(List<YcejOrderEntity> list);
	/**
	 * 查询所以订单数据
	 * @return
	 */
	List<YcejOrderEntity> queryOrder();
	/**
	 * 定时任务批量更新订单状态为下一步即资源待匹配
	 * @param orderlist
	 * @return 
	 */
	public R updateOrderBatch(Map<String, Object> params);
	/**
	 * 工作流
	 * @param params
	 * @return
	 */
	public R updateTask(YcejOrderEntity ycejOrder,Map<String, Object> params);
	
	/**
	 * POS定金支付完毕修改订单状态，并触发工作流
	 * @param params
	 * @return
	 */
	public R updateOrderByPayment(Map<String, Object> params);
	/**
	 * 订单取消时修改库存状态
	 * @param params
	 */
	void updateInventory(Map<String, Object> params);
	/**
	 * 订单退回
	 * @param params
	 */
	void returnCheck(Map<String, Object> params);
	/**
	 * 尾款/首付款时提交
	 * @param order
	 */
	void submitOne(YcejOrderEntity order);
	/**
	 * 资源待匹配时 确认匹配时处理
	 * @param params
	 */
	void updateByOrder(Map<String, Object> params);
	/**
	 * 成交客户处理
	 * @param params
	 */
	void modifyCustomer(Map<String, Object> params);
	/**
	 * 车辆库存处理
	 * @param params
	 */
	void modifyInventory(Map<String, Object> params);
	/**
	 * 优壹车拆分--列表展示
	 * @param params
	 * @return
	 */
	LayuiPage queryCheapCarOrderPage(Map<String, Object> params);
	/**
	 * 定金待支付时提交
	 * @param order
	 */
	void submitTwo(YcejOrderEntity order);
}

