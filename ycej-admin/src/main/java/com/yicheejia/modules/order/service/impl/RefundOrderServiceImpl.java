package com.yicheejia.modules.order.service.impl;

import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.common.annotation.ShopFilter;
import com.yicheejia.common.exception.RRException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.common.utils.R;
import com.yicheejia.common.utils.SerialNoSerUtils;
import com.yicheejia.modules.operate.entity.InventoryEntity;
import com.yicheejia.modules.operate.service.InventoryService;
import com.yicheejia.modules.order.dao.RefundOrderDao;
import com.yicheejia.modules.order.entity.RefundOrderEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.order.service.RefundOrderService;
import com.yicheejia.modules.order.service.YcejOrderService;
import com.yicheejia.modules.sys.entity.SysRoleEntity;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.entity.SysUserRoleEntity;
import com.yicheejia.modules.sys.service.SysRoleService;
import com.yicheejia.modules.sys.service.SysUserRoleService;
import com.yicheejia.modules.sys.service.SysUserService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.workflow.service.ActivitiBaseService;



@Service("refundOrderService")
public class RefundOrderServiceImpl extends ServiceImpl<RefundOrderDao, RefundOrderEntity> implements RefundOrderService {
	@Autowired
	private ActivitiBaseService activiti;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
    private YcejOrderService ycejOrderService;
	@Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
	//日志
    @Autowired
    private SysUserService sysUserService;
    
    protected Logger logger = LoggerFactory.getLogger(RefundOrderService.class);
	@Autowired
	SerialNoSerUtils serialNoUtils;
	/**
	 * 退款订单信息查询
	 * @param params
	 * @return
	 */
	@Override
	@ShopFilter
	public LayuiPage queryRefundPage(Map<String, Object> params) {
		Page<YcejOrderEntity> page = new Query<YcejOrderEntity>(params).getPage();
    	page.setRecords(baseMapper.queryRefundOrder(page,params));
    	return new LayuiPage(page.getRecords(), page.getTotal());
	}
	/**
	 * 订单取消
	 * @param params
	 */
	@Override
	public void refundSubmit(Map<String, Object> params) {
		Long uId = ShiroUtils.getUserId();
    	Map<String, Object> map = new HashMap<>();
    	map.put("user_id", uId);
    	List<SysUserRoleEntity> list = sysUserRoleService.selectByMap(map);
    	Boolean flag = false;
    	for (SysUserRoleEntity sysUserRoleEntity : list) {
			SysRoleEntity entity = sysRoleService.selectById(sysUserRoleEntity.getRoleId());
			String roleName = entity.getRoleName();
			if (roleName.contains("资源")) {
				flag = true;
			}
		}
    	if (!flag) {
    		throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
		}
    	try {
    		params.put("status", "12");
    		String id = params.get("id") + "";
    		params.put("inventoryId", 0);
    		ycejOrderService.updateOrder(params);
    		//取消同时库存更新过来
    		YcejOrderEntity order = ycejOrderService.selectById(id);
    		InventoryEntity inventory = new InventoryEntity();
    		if(StringUtils.isNotBlank(order.getInventoryId())){
    			inventory.setInventoryId(order.getInventoryId());
    			//inventory.setUpdateId(ShiroUtils.getUserId()+"");
    			inventory.setUpdateTime(DateUtils.getDate());
    			inventory.setCarStatus(1);
    			inventoryService.updateinventory(inventory) ;
    		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("订单取消失败!"+e.getMessage());
			throw new RRException("订单取消失败!"+e.getMessage());
		}
	}
	/**
	 * 退款账户信息查询
	 */
	@Override
	public RefundOrderEntity queryRefund(Map<String, Object> params) {
		RefundOrderEntity refundOrder = baseMapper.queryRefund(params);
		String userId = ShiroUtils.getUserId() + "";
		if("".equals(refundOrder.getRefundId()) || refundOrder.getRefundId() == null){
			refundOrder.setRefundId(userId);
			SysUserEntity sysuser = sysUserService.selectById(userId);
			refundOrder.setUserName(sysuser.getName());
		}else{
			SysUserEntity sysuser = sysUserService.selectById(refundOrder.getRefundId());
			refundOrder.setUserName(sysuser.getName());
		}
		return refundOrder;
	}
	/**
	 * 财务审核并提交退款信息
	 */
	@Override
	public void saveRefundOrder(RefundOrderEntity refundOrder) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			String orderId = refundOrder.getOrderId();
			RefundOrderEntity refund = new RefundOrderEntity();
			refund.setOrderId(orderId);
			refund = baseMapper.selectOne(refund);
			if(refund != null){
				refundOrder.setUpdateId(ShiroUtils.getUserId()+"");
				//refundOrder.setRefundId(ShiroUtils.getUserId()+"");
				baseMapper.updateRefundOrder(refundOrder);
			}else{
				refundOrder.setInsertTime(DateUtils.getDate());
				refundOrder.setInsertId(ShiroUtils.getUserId()+"");
				refundOrder.setUpdateTime(DateUtils.getDate());
				baseMapper.insert(refundOrder);
			}
			//订单状态处理
			String status = refundOrder.getStatus();
			if("13".equals(status)){//财务会计审核
				params.put("status", "14");
			}
			if("15".equals(status)){//出纳退款
				params.put("status", "16");
			}
			params.put("updateId", ShiroUtils.getUserId()+"");
			params.put("id", orderId);
			ycejOrderService.updateOrder(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("提交审核失败！"+ e.getMessage());
			throw new RRException("提交审核失败！");
		}
	}
}
