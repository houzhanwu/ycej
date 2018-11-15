package com.yicheejia.modules.operate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yicheejia.modules.operate.entity.AllotEntity;
import com.yicheejia.modules.operate.entity.InventoryEntity;
import com.yicheejia.modules.operate.excel.InventoryBean;
import com.yicheejia.modules.operate.service.InventoryService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.upload.entity.YcejFileEntity;
import com.yicheejia.modules.upload.service.YcejFileService;
import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;



/**
 * 库存表
 *
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:28
 */
@RestController
@RequestMapping("operate/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private YcejFileService ycejFileService;
    //日志
    protected Logger logger = LoggerFactory.getLogger(AllotController.class);

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:inventory:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
    	LayuiPage page = inventoryService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{inventoryId}")
    @RequiresPermissions("operate:inventory:info")
    public R info(@PathVariable("inventoryId") String inventoryId){
			InventoryEntity inventory = inventoryService.selectByinventoryId(inventoryId);

        return R.ok().put("inventory", inventory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:inventory:save")
    public R save(@RequestBody InventoryEntity inventory){
    	inventory.setCarStatus(1);
    	inventory.setInsertId(ShiroUtils.getUserEntity().getUserId().intValue());
    	
//    	inventory.setSupplierId(Integer.valueOf(inventory.getSupplierId()));

		inventoryService.insert(inventory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:inventory:update")
    public R update(@RequestBody InventoryEntity inventory){
    	//修改销售价时采购成本价与销售价保持一致（财务、资源共同提出2018/9/18）
		inventory.setCostPrice(inventory.getSalePrice());
		inventoryService.updateById(inventory);

        return R.ok();
    }

    /**
     * 删除 通过CarStatus进行软删除（4出库）
     */
    @RequestMapping("/delete/{inventoryId}")
    @RequiresPermissions("operate:inventory:delete")
    public R delete(@PathVariable("inventoryId") String inventoryId){
    	InventoryEntity inventory = new InventoryEntity();
    	inventory.setCarStatus(4);
    	inventory.setInventoryId(inventoryId);
		inventoryService.updateById(inventory);

        return R.ok();
    }
//    @RequestMapping("/delete")
//    @RequiresPermissions("operate:inventory:delete")
//    public R delete(@RequestBody Integer[] inventoryIds){
//			inventoryService.deleteBatchIds(Arrays.asList(inventoryIds));
//
//        return R.ok();
//    }
    
    /**
     * 导出
     */
    @RequestMapping("/export")
    @RequiresPermissions("operate:inventory:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = inventoryService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "库存表", page.getData(), InventoryBean.class);
    }
    
    /**
     * 根据门店ID获取对应库存车辆列表
     */
    @RequestMapping("/getInventoryByShopId")
    public LayuiPage getInventoryByShopId(@RequestParam Map<String, Object> params){
//		List<InventoryEntity>  shopInventoryList = inventoryService.getInventoryByShopId(params);
//      return R.ok().put("shopInventoryList",shopInventoryList);
        LayuiPage page = inventoryService.getInventoryByShopId(params);
        return page;
    }
    
    /**
     * 上传合格证照片
     */
    @SysLog("上传合格证照片")
    @Transactional
    @RequestMapping("/upload")
    public R upload(@RequestParam(value="file",required=false) MultipartFile file,String inventoryId,String branch,HttpServletRequest request){
    	YcejFileEntity ycejFileEntity=new YcejFileEntity();
    	Map<String, Object> params=new HashMap<>();
    	try {
	    	params.put("customerId",inventoryId);
	    	params.put("branch",branch);
				// 判断文件是否为空
				if (!file.isEmpty()) {
					//将文件保存到附件表中并返回主键ID
					ycejFileEntity=ycejFileService.insertReturnId(file,request,params);
					//将附件主键保存到调拨表中
					InventoryEntity inventoryEntity = new InventoryEntity();
					inventoryEntity.setInventoryId(inventoryId);
					inventoryEntity.setFileId(ycejFileEntity.getFileId());
					//获取文件路径保存到调拨表
					YcejFileEntity fileEntity=ycejFileService.selectById(inventoryEntity.getFileId());
					inventoryEntity.setCertificateAdd(fileEntity.getFilePath());
					inventoryService.updateById(inventoryEntity);
				}
			} catch (Exception e) {
				logger.error("文件处理异常",e);
				logger.error(e.getMessage(),e);
				throw new RRException(e.getMessage());
			}
    	
    	return R.ok().put("ycejFileEntity", ycejFileEntity);
    }
    /**
     * 根据库存id获取库存信息 --用于采购明细 车辆库存查询
     */
    @RequestMapping("/selectdataforcarpurchasebyid")
    public R selectDataForCarPurchaseById(@RequestParam Map<String, Object> params){
    	InventoryEntity inventoryEntity = inventoryService.selectDataForCarPurchaseById(params);
    	return R.ok().put("inventoryEntity", inventoryEntity);
    }
}
