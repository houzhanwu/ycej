package com.yicheejia.modules.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.operate.entity.InventoryEntity;
import com.yicheejia.modules.operate.service.InventoryService;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.order.service.YcejOrderDetailService;
import com.yicheejia.modules.order.service.YcejOrderService;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.workflow.service.ActivitiBaseService;
/**
 *  订单车辆调拨
 * @author Administrator
 *
 */
@RestController
@RequestMapping("order/allot")
public class OrderAllotController {
	@Autowired
    private InventoryService inventoryService;
	@Autowired
    private YcejOrderService ycejOrderService;
	@Autowired
	private ActivitiBaseService activiti;
    /**
     * 当前车型库存列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:inventory:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
    	LayuiPage page = inventoryService.selectInventory(params);
        return page;
    }
    /**
     * 确认调拨修改订单车型id与状态
     */
    @RequestMapping("/updateModel")
    @SysLog("调拨确认")
    @RequiresPermissions("order:order:update")
    @Transactional
    public R updateModel(@RequestParam Map<String, Object> params){
    	try {
    		String shopId = params.get("shopId")+"";
    		String inventoryId = params.get("inventoryId")+"";
    		String id = params.get("id")+"";
    		if(StringUtils.isBlank(shopId) || StringUtils.isBlank(inventoryId)){
    			//return R.error("获取门店编号或者库存编号错误，请稍后在试！");
    		}
    		//库存处理
    		 YcejOrderEntity order = ycejOrderService.selectById(id);
			 if(StringUtils.isBlank(order.getId()+"")){
				 return R.error("未查询到订单号为"+order.getOrderNo()+"的相关订单！");
			 }
			 ycejOrderService.updateByOrder(params);
			 YcejOrderEntity order1 = ycejOrderService.selectById(id);
    		/*InventoryEntity inventory = new InventoryEntity();
    		inventory.setShopId(Integer.parseInt(shopId));
    		inventory.setInventoryId(Integer.parseInt(inventoryId));
    		inventory.setUpdateId(ShiroUtils.getUserId().intValue());
    		inventory.setUpdateTime(DateUtils.getDate());
    		inventoryService.updateById(inventory);*/
    		//YcejOrderEntity order = ycejOrderService.selectById(id);
    		return R.ok().put("order", order1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RRException("匹配失败，请稍后在试！"+e.getMessage());
		}
    }
    
    /**
     * 信息
     */
    @RequestMapping("/info/{inventoryId}")
    @RequiresPermissions("order:allot:info")
    public R info(@PathVariable("inventoryId") String inventoryId){
			InventoryEntity inventory = inventoryService.selectById(inventoryId);

        return R.ok().put("inventory", inventory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:allot:save")
    public R save(@RequestBody InventoryEntity inventory){
			inventoryService.insert(inventory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:inventory:list")
    public R update(@RequestBody InventoryEntity inventory){
			inventoryService.updateById(inventory);
        return R.ok();
    }
}
