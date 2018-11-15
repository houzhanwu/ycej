package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

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

import com.yicheejia.modules.operate.excel.CautionMoneyBean;
import com.yicheejia.modules.operate.entity.AllotEntity;
import com.yicheejia.modules.operate.entity.CautionHistoryEntity;
import com.yicheejia.modules.operate.entity.CautionMoneyEntity;
import com.yicheejia.modules.operate.entity.SupplierEntity;
import com.yicheejia.modules.operate.entity.SupplierFileEntity;
import com.yicheejia.modules.operate.service.CautionHistoryService;
import com.yicheejia.modules.operate.service.CautionMoneyService;
import com.yicheejia.modules.operate.service.SupplierFileService;
import com.yicheejia.modules.operate.service.SupplierService;
import com.yicheejia.modules.sys.service.SysUserService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.common.annotation.StartFlow;
import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *	保证金支付
 * @author LW
 * @email 
 * @date 2018-10-25 15:47:20
 */
@RestController
@RequestMapping("operate/cautionmoney")
public class CautionMoneyController {
    @Autowired
    private CautionMoneyService cautionMoneyService;
    @Autowired
    private CautionHistoryService cautionHistoryService;
    @Autowired
    private SupplierFileService supplierFileService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private SysUserService sysUserService;
    //日志
    protected Logger logger = LoggerFactory.getLogger(CautionMoneyController.class);

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:cautionmoney:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = cautionMoneyService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{cautionId}")
    @RequiresPermissions("operate:cautionmoney:info")
    public R info(@PathVariable("cautionId") String cautionId){
			CautionMoneyEntity cautionMoney = cautionMoneyService.selectById(cautionId);

        return R.ok().put("cautionMoney", cautionMoney);
    }

    /**
     * 暂存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:cautionmoney:save")
    public R save(@RequestBody CautionMoneyEntity cautionMoney){
    		
    	cautionMoney.setStatus("1");
    	cautionMoney.setInsertTime(new Date());
    	cautionMoney.setInsertId(ShiroUtils.getUserEntity().getUserId().toString());
    	//先删除再重新插入
    	cautionMoneyService.deleteById(cautionMoney.getCautionId());
		cautionMoneyService.insert(cautionMoney);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:cautionmoney:update")
    public R update(@RequestBody CautionMoneyEntity cautionMoney){
    	
    	cautionMoney.setUpdateTime(new Date());
    	cautionMoney.setUpdateId(ShiroUtils.getUserEntity().getUserId().toString());
		cautionMoneyService.updateById(cautionMoney);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:cautionmoney:delete")
    public R delete(@RequestBody Long[] cautionIds){
			cautionMoneyService.deleteBatchIds(Arrays.asList(cautionIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:cautionmoney:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = cautionMoneyService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), CautionMoneyBean.class);
    }
    
    /**
     * 查询审核通过的供应商列表
     */
//    @RequestMapping("/querySupplierList")
//    public R querySupplierList(@RequestParam Map<String, Object> params){
//    	
//    	List<SupplierEntity> supplierList = supplierService.getSupplierListForCaution(params);
//
//        return R.ok().put("supplierList", supplierList);
//    }
    @RequestMapping("/querySupplierList")
    public LayuiPage querySupplierList(@RequestParam Map<String, Object> params){
        LayuiPage page = supplierService.getSupplierListForCaution(params);

        return page;
    }
    
    /**
     * 根据供应商id获取附件列表
     */
    @RequestMapping("/querySupplierFile/{supplierId}")
    public R querySupplierFile(@PathVariable("supplierId") Integer supplierId){
    	
    	//根据供应商id获取附件列表
        List<SupplierFileEntity> list = supplierFileService.selectListBySupplierId(supplierId);

        return R.ok().put("supplierFile", list);
    }
    
    /**
     * 新增提交保证金支付
     * @param allot
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
//    @StartFlow(id="cautionId",processKey=WfConstants.PROCESS_CAUTIONMONEY_KEY)
    @SysLog("提交保证金支付")
    @RequestMapping("/submitCaution")
    public R submitCaution(@RequestBody CautionMoneyEntity cautionMoney){
    	try {
    			cautionMoney.setInsertId(ShiroUtils.getUserEntity().getUserId().toString());
    			cautionMoney.setInsertTime(new Date());
    			cautionMoney.setStatus("2");
    			cautionMoneyService.insert(cautionMoney);
	        	String cautionId = cautionMoney.getCautionId();
	        	
	        	//将信息插入历史表
	        	CautionHistoryEntity historyEntity = new CautionHistoryEntity();
	        	historyEntity.setBranchBank(cautionMoney.getBranchBank());
	        	historyEntity.setCarBrands(cautionMoney.getCarBrands());
	        	historyEntity.setCautionId(cautionMoney.getCautionId());
	        	historyEntity.setDueBank(cautionMoney.getDueBank());
	        	historyEntity.setHAllCaution(cautionMoney.getAllCaution());
	        	historyEntity.setHCarNumber(cautionMoney.getCarNumber());
	        	historyEntity.setHComment(cautionMoney.getCauComment());
	        	historyEntity.setHOneCaution(cautionMoney.getOneCaution());
	        	historyEntity.setInsertId(cautionMoney.getInsertId());
	        	historyEntity.setInsertTime(cautionMoney.getInsertTime());
	        	historyEntity.setIsPay(cautionMoney.getIsPay());
	        	historyEntity.setSupplierAdd(cautionMoney.getSupplierAdd());
	        	historyEntity.setSupplierBankcard(cautionMoney.getSupplierBankcard());
	        	historyEntity.setSupplierContact(cautionMoney.getSupplierContact());
	        	historyEntity.setSupplierId(cautionMoney.getSupplierId());
	        	historyEntity.setSupplierMobile(cautionMoney.getSupplierMobile());
	        	historyEntity.setSupplierName(cautionMoney.getSupplierName());
	        	historyEntity.setSupplierType(cautionMoney.getSupplierType());
	        	historyEntity.setVehicleType(cautionMoney.getVehicleType());
	        	cautionHistoryService.insert(historyEntity);
            return R.ok();
    	}catch(Exception e) {
    		e.printStackTrace();
			logger.debug("数据保存异常！"+e.getMessage(),e);
			throw new RRException("插入错误！");
    	}
    }
    
    @Transactional(rollbackFor = Exception.class)
//  @StartFlow(id="cautionId",processKey=WfConstants.PROCESS_CAUTIONMONEY_KEY)
    @SysLog("提交保证金支付(已暂存)")
    @RequestMapping("/updateCaution")
    public R updateCaution(@RequestBody CautionMoneyEntity cautionMoney) {
    	try {
		
    		cautionMoney.setStatus("2");
    		cautionMoney.setUpdateId(ShiroUtils.getUserEntity().getUserId().toString());
    		cautionMoney.setUpdateTime(new Date());
    		//将信息插入历史表
        	CautionHistoryEntity historyEntity = new CautionHistoryEntity();
        	historyEntity.setBranchBank(cautionMoney.getBranchBank());
        	historyEntity.setCarBrands(cautionMoney.getCarBrands());
        	historyEntity.setCautionId(cautionMoney.getCautionId());
        	historyEntity.setDueBank(cautionMoney.getDueBank());
        	historyEntity.setHAllCaution(cautionMoney.getAllCaution());
        	historyEntity.setHCarNumber(cautionMoney.getCarNumber());
        	historyEntity.setHComment(cautionMoney.getCauComment());
        	historyEntity.setHOneCaution(cautionMoney.getOneCaution());
        	historyEntity.setInsertId(cautionMoney.getInsertId());
        	historyEntity.setInsertTime(cautionMoney.getInsertTime());
        	historyEntity.setIsPay(cautionMoney.getIsPay());
        	historyEntity.setSupplierAdd(cautionMoney.getSupplierAdd());
        	historyEntity.setSupplierBankcard(cautionMoney.getSupplierBankcard());
        	historyEntity.setSupplierContact(cautionMoney.getSupplierContact());
        	historyEntity.setSupplierId(cautionMoney.getSupplierId());
        	historyEntity.setSupplierMobile(cautionMoney.getSupplierMobile());
        	historyEntity.setSupplierName(cautionMoney.getSupplierName());
        	historyEntity.setSupplierType(cautionMoney.getSupplierType());
        	historyEntity.setVehicleType(cautionMoney.getVehicleType());
        	historyEntity.setLastUpdateId(cautionMoney.getUpdateId());
        	historyEntity.setLastUpdateTime(cautionMoney.getUpdateTime());
        	cautionHistoryService.insert(historyEntity);

			cautionMoneyService.updateById(cautionMoney);

	        return R.ok();
		}catch(Exception e) {
			e.printStackTrace();
			logger.debug("数据保存异常！"+e.getMessage(),e);
			throw new RRException("提交失败！");
		}
    	
    }
    
    /**
     * 审核保证金
     */
    @SysLog("审核保证金")
    @Transactional(rollbackFor = Exception.class)
//    @ExecuteTask(id="cautionId",processKey=WfConstants.PROCESS_CAUTIONMONEY_KEY)
    @RequestMapping("/auditCaution")
    public R auditCaution(@RequestBody CautionMoneyEntity cautionMoney) {
    	String status = cautionMoney.getStatus();
    	//资源部总经理
    	if(status.equals("2")) {
    		cautionMoney.setStatus("3");
    	}
    	//会计审核
    	if(status.equals("3")) {
    		cautionMoney.setStatus("4");
    	}
    	//运营副总裁审核
    	if(status.equals("4")) {
    		cautionMoney.setStatus("5");
    	}
    	//出纳审核
    	if(status.equals("5")) {
    		cautionMoney.setStatus("6");
    	}
    	
    	cautionMoneyService.updateById(cautionMoney);
		return R.ok();
//				.put(WfConstants.REMARK, cautionMoney.getRemark());
    }
    
    /**
     * 保证金审核退回
     * @param params
     * @return
     */
    @RequestMapping("/returnCaution")
    @SysLog(value = "保证金审核退回",id = "cautionId")
    @Transactional
//    @ExecuteTask(id = "cautionId",processKey=WfConstants.PROCESS_CAUTIONMONEY_KEY,args = {"status"})
    public R returnCaution(@RequestBody CautionMoneyEntity cautionMoney){
    	String status = cautionMoney.getStatus();
    	//资源部总经理
    	if(status.equals("2")) {
    		cautionMoney.setStatus("1");
    	}
    	//会计审核
    	if(status.equals("3")) {
    		cautionMoney.setStatus("2");
    	}
    	//运营副总裁审核
    	if(status.equals("4")) {
    		cautionMoney.setStatus("3");
    	}
    	//出纳审核
    	if(status.equals("5")) {
    		cautionMoney.setStatus("4");
    	}
    	cautionMoneyService.updateById(cautionMoney);
    	
    	return R.ok();
//    			.put(WfConstants.REMARK, cautionMoney.get("remak")).put(WfConstants.ACTION, "0");//填写备注信息
    }
    
    @SysLog("查询变更记录")
    @RequestMapping("/selectChangeLog/{cautionId}")
    public R selectChangeLog(@PathVariable("cautionId") String cautionId) {
		
    	List<CautionHistoryEntity> list = cautionHistoryService.selectListById(cautionId);
    	return R.ok().put("changeLogList", list);
    }
    
}
