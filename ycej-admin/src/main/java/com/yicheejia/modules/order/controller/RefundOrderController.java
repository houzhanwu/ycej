package com.yicheejia.modules.order.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.common.annotation.StartFlow;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.order.entity.OrderFileEntity;
import com.yicheejia.modules.order.entity.RefundOrderEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.order.excel.OrderBean;
import com.yicheejia.modules.order.service.OrderFileService;
import com.yicheejia.modules.order.service.RefundOrderService;
import com.yicheejia.modules.order.service.YcejOrderDetailService;
import com.yicheejia.modules.order.service.YcejOrderService;
import com.yicheejia.modules.sys.entity.SysRoleEntity;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.entity.SysUserRoleEntity;
import com.yicheejia.modules.sys.service.SysRoleService;
import com.yicheejia.modules.sys.service.SysUserRoleService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.workflow.service.ActivitiBaseService;




/**
 * 
 * 退款订单
 * @date 2018-09-25
 */
@RestController
@RequestMapping("order/refundorder")
public class RefundOrderController {
    @Autowired
    private YcejOrderService ycejOrderService;
    @Autowired
    private YcejOrderDetailService ycejOrderDetailService;
    @Autowired
	private ActivitiBaseService activiti;
    @Autowired
    private RefundOrderService refundOrderService;
    //日志
    protected Logger logger = LoggerFactory.getLogger(RefundOrderController.class);
    
    //退款订单-------------------------------------
    @ExecuteTask(id = "id" ,processKey = WfConstants.PROCESS_ORDER_KEY,args = {"status"})
    @StartFlow(id = "id" ,processKey = WfConstants.PROCESS_REFUNDORDER_KEY,args = {"customerName"})
    @Transactional
    @SysLog(value = "订单取消",id = "id")
    @RequestMapping("/refundSubmit")
    @RequiresPermissions("order:order:update")
    public R refundSubmit(@RequestParam Map<String, Object> params){
    	refundOrderService.refundSubmit(params);
        return R.ok().put(WfConstants.REMARK, params.get("remak")).put(WfConstants.ACTION, "-1");//填写备注信息
    }
    
    @RequestMapping("/refundlist")
    @RequiresPermissions("order:refundorder:refundlist")
    public LayuiPage refundlist(@RequestParam Map<String, Object> params){
    	LayuiPage page = refundOrderService.queryRefundPage(params);
        return page;
    }
    
    @ExecuteTask(id = "id",processKey=WfConstants.PROCESS_REFUNDORDER_KEY,args = {"status"})
    @Transactional
    @SysLog(value = "审核",id = "id")
    @RequestMapping("/refundCheck")
    @RequiresPermissions("order:refundorder:refundCheck")
    public R refundCheck(@RequestParam Map<String, Object> params){
    	String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
    	if(!activiti.getUserHaveTasksUser(userId,params.get("id")+"")){
			throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
		}
    	ycejOrderService.updateOrder(params);
        return R.ok().put(WfConstants.REMARK, params.get("remak")).put(WfConstants.ACTION, 1);//填写备注信息
    }
    /**
     * 财务审核并提交退款信息
     * @param refundOrder
     * @return
     */
    @ExecuteTask(id = "orderId",processKey=WfConstants.PROCESS_REFUNDORDER_KEY,args = {"status"})
    @Transactional
    @SysLog(value = "财务审核",id = "id")
    @RequestMapping("/check")
    @RequiresPermissions("order:refundorder:refundCheck")
    public R check(@RequestBody RefundOrderEntity refundOrder){
    	String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
    	if(!activiti.getUserHaveTasksUser(userId,refundOrder.getOrderId())){
			throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
		}
    	refundOrderService.saveRefundOrder(refundOrder);
        return R.ok().put(WfConstants.REMARK, refundOrder.getRemark()).put(WfConstants.ACTION, 1);//填写备注信息
    }
    /**
     * 退款账户信息查询
     * @param params
     * @return
     */
    @RequestMapping("/refundOrder")
    @RequiresPermissions("order:refundorder:refundOrder")
    public R refundOrder(@RequestParam Map<String, Object> params){
    	RefundOrderEntity refundOrder = refundOrderService.queryRefund(params);
        return R.ok().put("refundOrder", refundOrder);
    }
    
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:refundorder:save")
    public R save(@RequestBody RefundOrderEntity refundOrder){
		refundOrderService.insert(refundOrder);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:refundorder:update")
    public R update(@RequestBody RefundOrderEntity refundOrder){
		refundOrderService.updateById(refundOrder);
        return R.ok();
    }
}
