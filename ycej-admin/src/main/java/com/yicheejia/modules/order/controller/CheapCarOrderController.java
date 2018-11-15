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
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.order.excel.OrderBean;
import com.yicheejia.modules.order.service.OrderFileService;
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
 * 优壹车订单流程
 * @date 2018-06-21 16:27:42
 */
@RestController
@RequestMapping("order/cheapcarorder")
public class CheapCarOrderController {
    @Autowired
    private YcejOrderService ycejOrderService;
    @Autowired
    private YcejOrderDetailService ycejOrderDetailService;
    @Autowired
    private OrderFileService orderFileService;
    @Autowired
	private ActivitiBaseService activiti;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    //日志
    protected Logger logger = LoggerFactory.getLogger(CheapCarOrderController.class);
    /**
     * 列表
     */
    @RequestMapping("/list")
    @SysLog(value = "订单查看",id = "id")
    @RequiresPermissions("order:order:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
    	LayuiPage page = ycejOrderService.queryCheapCarOrderPage(params);

        return page;
    }


    /**
     * 信息
     */
    @SysLog(value = "订单查看",id = "id")
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:order:info")
    public R info(@PathVariable("id") Long id){
			YcejOrderEntity order = ycejOrderService.selectById(id);

        return R.ok().put("order", order);
    }

    /**
     * 订单保存
     * 费用明细保存同时保存支付明细
     * 保存
     */
    //@StartFlow(id = "id")
    @SysLog(value = "订单保存",id = "id")
    @PostMapping("/save")
    @RequiresPermissions("order:order:save")
    public R save(@RequestBody YcejOrderEntity order){
    	ycejOrderService.insertOrder(order);//订单
    	//ycejOrderDetailService.insertDetail(order);//费用明细
        return R.ok().put("order", order);
    }

    /**
     * 修改
     */
    @Transactional
    @SysLog(value = "订单修改",id = "id")
    @RequestMapping("/update")
    @RequiresPermissions("order:order:update")
    public R update(@RequestBody YcejOrderEntity order){
    	try {
    		ycejOrderService.updateOrderById(order);
    		ycejOrderDetailService.saveDetail(order);//费用明细
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据保存异常！"+e.getMessage(),e);
			throw new RRException("数据保存异常！");
		}
        return R.ok().put("order", order);
    }
    /**
     * 提交
     */
    @SysLog(value = "订单提交",id = "id")
    @RequestMapping("/submit")
    @RequiresPermissions("order:order:update")
    @Transactional
    @StartFlow(id = "id" ,processKey=WfConstants.PROCESS_ORDER_KEY,args = {"customerName"})
    public R submit(@RequestBody YcejOrderEntity order){
    	String id = order.getId()+"";
    	try {
    		Long uId=ShiroUtils.getUserId();
        	Map<String, Object> map = new HashMap<>();
        	map.put("user_id", uId);
        	List<SysUserRoleEntity> list = sysUserRoleService.selectByMap(map);
        	Boolean flag = false;
        	for (SysUserRoleEntity sysUserRoleEntity : list) {
    			SysRoleEntity entity = sysRoleService.selectById(sysUserRoleEntity.getRoleId());
    			String roleName = entity.getRoleName();
    			if (roleName.contains("店长")) {//只能店长提交
    				flag = true;
    			}
    		}
        	if (!flag) {
        		throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
    		}
    		/*if(StringUtils.isNotBlank(order.getPayMethod()) && "04".equals(order.getPayMethod())){//转账
    			Map<String, Object> params1 = new HashMap<String, Object>();
    	    	params1.put("orderId",order.getId());
    			params1.put("fileType", "03");//订金转账凭证
    			List<OrderFileEntity> list1 = orderFileService.queryList(params1);
    			if(list1.size() > 0){
    			}else{
    				logger.error("付款方式为转账时请上传转账小票！");
    				throw new RRException("付款方式为转账时请上传转账小票！");
    			}
    		}*/
			//如果没有id肯定是新增
    		if(StringUtils.isNotBlank(id) && !"null".equals(id)){
    			YcejOrderEntity ycejOrder = ycejOrderService.selectById(id);
    			if(ycejOrder.getId() == null){//未查询出来走insert
    				ycejOrderService.insertOrder(order);
    			}else{
    				ycejOrderService.updateOrderById(order);
    				ycejOrderDetailService.saveDetail(order);//费用明细
    			}
    		}else{
    			ycejOrderService.insertOrder(order);
    		}
    		//ycejOrderDetailService.saveDetail(order);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("提交失败！"+e.getMessage(),e);
			throw new RRException(e.getMessage());
		}
        return R.ok();
    }
    /**
     * 审核
     * 审核时开始流程
     */
    @ExecuteTask(id = "id",processKey=WfConstants.PROCESS_ORDER_KEY,args = {"status"})
    @Transactional
    @SysLog(value = "审核",id = "id")
    @RequestMapping("/check")
    @RequiresPermissions("order:order:update")
    public R check(@RequestParam Map<String, Object> params){
        String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
    	if(!activiti.getUserHaveTasksUser(userId,params.get("id")+"")){
			throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
		}
    	String payMethod = params.get("payMethod")+"";
    	String status = params.get("status")+"";
    	String action = "1";
    	//修改为定金待支付和定金已支付状态时需校验转账小票
    	Map<String, Object> params1 = new HashMap<String, Object>();
    	params1.put("orderId", params.get("id"));
    	/*String[] method = WfConstants.payMethod;
    	if("04".equals(status) || "05".equals(status) ){
    		 for (String key : method) {
                 if (payMethod.contains(key)) {
                	 params1.put("fileType", "03");//订金转账凭证
                	 List<OrderFileEntity> list = orderFileService.queryList(params1);
                	 if(list.size() > 0){ 
                		 params.put("payMethod", payMethod);
                		 ycejOrderDetailService.updateDetail(params);
                	 }else{
                		 logger.error("付款方式为转账时请上传转账小票！");
                		 throw new RRException("付款方式为转账时请上传转账小票！");
                	 }
                 }
             }
    		 
    	}*/
    	//add by fkm 20180929  优壹车取消定金环节 begin
    	/*if("03".equals(status)){//运营审核提交
    		YcejOrderEntity order = ycejOrderService.selectById(params.get("id").toString());
    		if(order != null && order.getSellType() == 3){//优壹车 
    			params.put("status", "05");
    			action = "3";
    		}
    	}*/
    	//add by fkm 20180929  优壹车取消定金环节 end
    	if("11".equals(status)){//财务确认时提交下一步时，需上传交接单
			params1.put("fileType", "07");//车辆交接单
			List<OrderFileEntity> list = orderFileService.queryList(params1);
			if(list.size() > 0){
				ycejOrderService.modifyCustomer(params);//成交客户处理
				ycejOrderService.modifyInventory(params);//库存处理
			}else{
				logger.error("请上传车辆交接单！");
				throw new RRException("请上传车辆交接单！");
			}
    	}
    	try {
    		ycejOrderService.updateInventory(params);
    		ycejOrderService.updateOrder(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("提交失败！"+e.getMessage(),e);
			throw new RRException("提交失败！"+e.getMessage());
		}
        return R.ok().put(WfConstants.REMARK, params.get("remak")).put(WfConstants.ACTION, action);//填写备注信息
        //分支  通过与不通过  return R.ok().put(WfConstants.ACTION, params.get("action"));
    }
   
    /**
     * 车辆已确认时需提交首付款信息或者尾款信息和状态更改，拆分出来
     * 订单信息不需要在做修改
     * @param params
     * @return
     */
    //@ExecuteTask(id = "id",processKey=WfConstants.PROCESS_ORDER_KEY,args = {"status"})
    @Transactional
    @SysLog(value = "车辆确认审核",id = "id")
    @RequestMapping("/newcheck")
    @RequiresPermissions("order:order:update")
    public R newcheck(@RequestBody YcejOrderEntity order){
    	String id = order.getId()+"";
        String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
    	if(!activiti.getUserHaveTasksUser(userId,id)){
			throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
		}
    	ycejOrderDetailService.newcheck(order);
        return R.ok();//填写备注信息
    }
    /**
     * 定金待支付
     * @param params
     * @return
     */
    @ExecuteTask(id = "id",processKey=WfConstants.PROCESS_ORDER_KEY,args = {"status"})
    @Transactional
    @SysLog(value = "定金支付",id = "id")
    @RequestMapping("/submitTwo")
    @RequiresPermissions("order:order:update")
    public R submitTwo(@RequestBody YcejOrderEntity order){
    	ycejOrderService.submitTwo(order);
        return R.ok().put(WfConstants.REMARK, order.getRemark()).put(WfConstants.ACTION, 1);
    }
    /**
     * 尾款或者首付款状态时可修改支付方式转账与上传小票
     * 订单信息以及状态不做修改，财务触发
     * @param params
     * @return
     */
    @ExecuteTask(id = "id",processKey=WfConstants.PROCESS_ORDER_KEY,args = {"status"})
    @Transactional
    @SysLog(value = "尾款/首付款支付",id = "id")
    @RequestMapping("/submitOne")
    @RequiresPermissions("order:order:update")
    public R submitOne(@RequestBody YcejOrderEntity order){
    	ycejOrderService.submitOne(order);
        return R.ok().put(WfConstants.REMARK, order.getRemark()).put(WfConstants.ACTION, 1);
    }
    /**
     * 订单退回
     * @param params
     * @return
     */
    @RequestMapping("/returnCheck")
    @RequiresPermissions("order:order:update")
    @SysLog(value = "订单退回",id = "id")
    @Transactional
    @ExecuteTask(id = "id",processKey=WfConstants.PROCESS_ORDER_KEY,args = {"status"})
    public R returnCheck(@RequestParam Map<String, Object> params){
    	ycejOrderService.returnCheck(params);
    	
    	return R.ok().put(WfConstants.REMARK, params.get("remak")).put(WfConstants.ACTION, "0");//填写备注信息
    }
    /**
     * APP权限查询
     * @param params
     * @return
     */
    @RequestMapping("/queryFlag")
    public R queryFlag(@RequestParam Map<String, Object> params){
    	String id = params.get("id") + "";
    	boolean flag = true;
    	String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
     	if(!activiti.getUserHaveTasksUser(userId,id)){
     		flag = false;
 		}
        return R.ok().put("flag", flag);
    }
    
    
    /**
     * 删除
     */
    @SysLog(value = "暂存删除",id = "id")
    @RequestMapping("/delete")
    @RequiresPermissions("order:order:delete")
    public R delete(@RequestBody Long[] ids){
    	ycejOrderService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * 导出
    */
    @SysLog(value = "订单导出",id = "id")
    @RequestMapping("/export")
    @RequiresPermissions("order:order:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
    	LayuiPage page = ycejOrderService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), OrderBean.class);
    }
    
}
