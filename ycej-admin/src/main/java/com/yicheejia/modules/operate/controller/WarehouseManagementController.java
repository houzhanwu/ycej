package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.operate.excel.WarehouseManagementBean;
import com.yicheejia.modules.operate.entity.InventoryEntity;
import com.yicheejia.modules.operate.entity.WarehouseManagementEntity;
import com.yicheejia.modules.operate.service.InventoryService;
import com.yicheejia.modules.operate.service.WarehouseManagementService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author hunk
 * @email 
 * @date 2018-07-24 11:30:47
 */
@RestController
@RequestMapping("operate/warehousemanagement")
@Api(tags="仓库管理接口")
public class WarehouseManagementController {
    @Autowired
    private WarehouseManagementService warehouseManagementService;
    @Autowired
    private InventoryService inventoryservice;

    /**
     * 列表
     */
    
    @RequestMapping("/list")
    @RequiresPermissions("operate:warehousemanagement:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = warehouseManagementService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    
    @RequestMapping("/info/{id}")
    @RequiresPermissions("operate:warehousemanagement:info")
    public R info(@PathVariable("id") Long id){
			WarehouseManagementEntity warehouseManagement = warehouseManagementService.selectById(id);

        return R.ok().put("warehouseManagement", warehouseManagement);
    }

    /**
     * 保存
     */
    
    @RequestMapping("/save")
    @RequiresPermissions("operate:warehousemanagement:save")
    public R save(@RequestBody WarehouseManagementEntity warehouseManagement){
            warehouseManagement.setInsertTime(DateUtils.getDate());
            warehouseManagement.setUpdateTime(DateUtils.getDate());
            warehouseManagement.setDeleteFlag(0);
            warehouseManagement.setInsertId(ShiroUtils.getUserEntity().getUserId().intValue());
			warehouseManagementService.insert(warehouseManagement);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:warehousemanagement:update")
    public R update(@RequestBody WarehouseManagementEntity warehouseManagement){
            warehouseManagement.setUpdateTime(DateUtils.getDate());
            warehouseManagement.setUpdateId(ShiroUtils.getUserEntity().getUserId().intValue());
			warehouseManagementService.updateById(warehouseManagement);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:warehousemanagement:delete")
    public R delete(@RequestBody Long[] ids){
    	WarehouseManagementEntity warehouseManagement = new WarehouseManagementEntity();
    	//删除之前，要先判断该仓库中是否还有车
    	for (Long id : ids) {
    		List<InventoryEntity> inventoryList = inventoryservice.selectByWareHouse(id);
    		if (inventoryList!=null && inventoryList.size()>0) {
				return R.error("该仓库下还有库存,不能删除!");
			}
    		warehouseManagement.setId(String.valueOf(id));
    		warehouseManagement.setUpdateTime(DateUtils.getDate());
            warehouseManagement.setUpdateId(ShiroUtils.getUserEntity().getUserId().intValue());
            warehouseManagement.setDeleteFlag(1);
			warehouseManagementService.updateById(warehouseManagement);
//    		warehouseManagementService.deleteBatchIds(Arrays.asList(ids));
		}

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:warehousemanagement:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = warehouseManagementService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), WarehouseManagementBean.class);
    }
    /**
     * 采购或其他模块调用，获取list
     */
    @RequestMapping("/getlist")
    public R getList(){
    	List<WarehouseManagementEntity> list = warehouseManagementService.selectList();
    	return R.ok().put("list", list);
    }

}
