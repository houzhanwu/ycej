package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.OldCellRecord;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.operate.excel.CarPurchaseBean;
import com.yicheejia.modules.operate.entity.CarPurchaseEntity;
import com.yicheejia.modules.operate.entity.SupplierEntity;
import com.yicheejia.modules.operate.service.CarPurchaseService;
import com.yicheejia.modules.operate.service.InventoryService;
import com.yicheejia.modules.operate.service.PurchaseDetailService;
import com.yicheejia.modules.operate.service.SupplierService;
import com.yicheejia.modules.sys.entity.SysRoleEntity;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.entity.SysUserRoleEntity;
import com.yicheejia.modules.sys.service.SysRoleService;
import com.yicheejia.modules.sys.service.SysUserRoleService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.upload.service.YcejFileService;
import com.yicheejia.workflow.service.ActivitiBaseService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.common.annotation.StartFlow;
import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;
import com.yicheejia.common.utils.redis.SerialNoUtils;


/**
 * 
 *
 * @author lzh
 * @email 
 * @date 2018-07-18 16:43:36
 */
@RestController
@RequestMapping("operate/carpurchase")
public class CarPurchaseController {
    @Autowired
    private CarPurchaseService carPurchaseService;
    @Autowired
    SerialNoUtils serialNoUtils;
    @Autowired
	private YcejFileService ycejFileService;
    @Autowired
    private PurchaseDetailService purchaseDetailService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
	private ActivitiBaseService activiti;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 列表
     */
    @SysLog(value="查看采购单列表",id = "purchId")
    @RequestMapping("/list")
    @RequiresPermissions("operate:carpurchase:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = carPurchaseService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @SysLog(value="查看采购单信息",id = "purchId")
    @RequestMapping("/info/{purchId}")
    @RequiresPermissions("operate:carpurchase:info")
    public R info(@PathVariable("purchId") String purchId){
			CarPurchaseEntity carPurchase = carPurchaseService.selectById(purchId);

        return R.ok().put("carPurchase", carPurchase);
    }

    /**
     * 提交新增
     */
    /*@RequestMapping("/save")
    @RequiresPermissions("operate:carpurchase:save")
    public R save(@RequestBody CarPurchaseEntity carPurchase){
    	

			carPurchaseService.insertReturnId(carPurchase);

        return R.ok().put("carPurchase", carPurchase);
    }*/
    
    /**
     * 暂存新增
     */
    @SysLog(value="暂存新增",id = "purchId")
    @RequestMapping(value="/holdsave")
    @RequiresPermissions("operate:carpurchase:save")
    public R holdSave(@RequestBody CarPurchaseEntity carPurchase){
    	
    	carPurchaseService.holeSave(carPurchase);
    	return R.ok().put("carPurchase", carPurchase);
    }

    /**
     * 暂存修改
     */
    @SysLog(value="暂存修改",id = "purchId")
    @RequestMapping("/update")
    @RequiresPermissions("operate:carpurchase:update")
    public R update(@RequestBody CarPurchaseEntity carPurchase){
	    	
			carPurchaseService.updateAll(carPurchase);

        return R.ok().put("carPurchase", carPurchase);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:carpurchase:delete")
    public R delete(@RequestBody String[] purchIds){
			carPurchaseService.deleteBatchIds(Arrays.asList(purchIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:carpurchase:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = carPurchaseService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), CarPurchaseBean.class);
    }
    
    /**
     * 暂存后提交和直接提交两种情况
     */
    @Transactional
    @SysLog(value="暂存提交",id = "purchId")
    @StartFlow(id = "purchId" ,processKey=WfConstants.PROCESS_PURCHASE_KEY,args = {"supplierName"})
    @RequestMapping("/tijiao")
    @RequiresPermissions("operate:carpurchase:update")
    public R tiJiao(@RequestBody CarPurchaseEntity carPurchase,String remak,String action){
    	
    	/*String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
     	if(!activiti.getUserHaveTasksUser(userId,carPurchase.getPurchId())){
 			throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
 		}*/
    	//判断该用户是否为资源岗位，不是提示没有权限
    	Long userId=ShiroUtils.getUserId();
    	Map<String, Object> map = new HashMap<>();
    	map.put("user_id", userId);
    	List<SysUserRoleEntity> list = sysUserRoleService.selectByMap(map);
    	Boolean boolean1 = false;
    	for (SysUserRoleEntity sysUserRoleEntity : list) {
			SysRoleEntity entity = sysRoleService.selectById(sysUserRoleEntity.getRoleId());
			String roleName = entity.getRoleName();
			if (roleName.contains("资源")) {
				boolean1 = true;
			}
		}
    	if (!boolean1) {
    		throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
		}
    	carPurchaseService.tiJiaoUpdate(carPurchase);
    	
    	return R.ok().put(WfConstants.REMARK, remak).put(WfConstants.ACTION, action);//填写备注信息
    }
    
    //财务审核提交
    @Transactional
    @SysLog(value="财务审核操作",id = "purchId")
    @ExecuteTask(id = "purchId" ,processKey=WfConstants.PROCESS_PURCHASE_KEY,args = {"supplierName"})
    @RequestMapping("/financecheck")
    @RequiresPermissions("operate:carpurchase:update")
    public R financeCheck(@RequestBody CarPurchaseEntity carPurchase,String remak,String action) throws Exception {
    	String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
    	Long uId=ShiroUtils.getUserId();
    	Map<String, Object> map = new HashMap<>();
    	map.put("user_id", uId);
    	List<SysUserRoleEntity> list = sysUserRoleService.selectByMap(map);
    	Boolean boolean1 = false;
    	for (SysUserRoleEntity sysUserRoleEntity : list) {
			SysRoleEntity entity = sysRoleService.selectById(sysUserRoleEntity.getRoleId());
			String roleName = entity.getRoleName();
			if (roleName.contains("财务")) {
				boolean1 = true;
			}
		}
     	if(!activiti.getUserHaveTasksUser(userId,carPurchase.getPurchId()) || boolean1 == false){
 			throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
 		}
    	carPurchaseService.updateCarPurchase(carPurchase);
    	if(!"0".equals(action)){
    		R r = carPurchaseService.stock(carPurchase);
    		String ac = r.get("action").toString();
    		if("-1".equals(ac)){
    			action = "-1";
    		}
    	}
    	return R.ok().put(WfConstants.REMARK, remak).put(WfConstants.ACTION, action);//填写备注信息
    }
    
    /**
     * 入库提交	
     */
    @Transactional
    @SysLog(value="入库操作",id = "purchId")
    @ExecuteTask(id = "purchId" ,processKey=WfConstants.PROCESS_PURCHASE_KEY,args = {"supplierName"})
    @RequestMapping("/putstock")
    @RequiresPermissions("operate:carpurchase:update")
    public R putStock(@RequestBody CarPurchaseEntity carPurchase,String remak,String action){
    	try {
    		String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
    		Long uId=ShiroUtils.getUserId();
        	Map<String, Object> map = new HashMap<>();
        	map.put("user_id", uId);
        	List<SysUserRoleEntity> list = sysUserRoleService.selectByMap(map);
        	Boolean boolean1 = false;
        	for (SysUserRoleEntity sysUserRoleEntity : list) {
    			SysRoleEntity entity = sysRoleService.selectById(sysUserRoleEntity.getRoleId());
    			String roleName = entity.getRoleName();
    			if (roleName.contains("资源")) {
    				boolean1 = true;
    			}
    		}
         	if(!activiti.getUserHaveTasksUser(userId,carPurchase.getPurchId()) || boolean1 == false){
     			throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
     		}
			carPurchaseService.putStock(carPurchase);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RRException(e.getMessage());
		}
    	return R.ok().put(WfConstants.REMARK, remak).put(WfConstants.ACTION, action);//填写备注信息
    }
    /**
     * 调用供应商列表
     */
    @RequestMapping("/getlist")
    @RequiresPermissions("operate:carpurchase:list")
    public R getList(){
    	List<SupplierEntity> list=supplierService.getListForPurchase();
    	return R.ok().put("list", list);
    }
    /**
     * 根据供应商ID获取供应商信息
     */
    @RequestMapping("/getdetail")
    @RequiresPermissions("operate:carpurchase:list")
    public R getDetail(Integer supplierId ){
    	SupplierEntity supplier = supplierService.selectById(supplierId);

        return R.ok().put("supplier", supplier);
    }
    
}
