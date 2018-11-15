package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.operate.entity.CautionMoneyEntity;
import com.yicheejia.modules.operate.entity.RefundDepositEntity;
import com.yicheejia.modules.operate.entity.SupplierFileEntity;
import com.yicheejia.modules.operate.excel.RefundDepositBean;
import com.yicheejia.modules.operate.service.CautionMoneyService;
import com.yicheejia.modules.operate.service.RefundDepositService;
import com.yicheejia.modules.operate.service.SupplierFileService;


/**
 * 
 *	保证金退款
 * @author 梁占豪
 * @email 
 * @date 2018-11-06 18:04:09
 */
@RestController
@RequestMapping("operate/refunddeposit")
public class RefundDepositController {
    @Autowired
    private RefundDepositService refundDepositService;
    @Autowired
    private CautionMoneyService cautionMoneyService;
    @Autowired
    private SupplierFileService supplierFileService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:refunddeposit:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page=null;
		try {
			page = refundDepositService.queryPage(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("operate:refunddeposit:info")
    public R info(@PathVariable("id") Long id){
			RefundDepositEntity refundDeposit = refundDepositService.selectById(id);

        return R.ok().put("refundDeposit", refundDeposit);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:refunddeposit:save")
    public R save(@RequestBody RefundDepositEntity refundDeposit){
			refundDepositService.insert(refundDeposit);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:refunddeposit:update")
    public R update(@RequestBody RefundDepositEntity refundDeposit){
			refundDepositService.updateById(refundDeposit);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:refunddeposit:delete")
    public R delete(@RequestBody Long[] ids){
			refundDepositService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:refunddeposit:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = refundDepositService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), RefundDepositBean.class);
    }
    /**
     * 新增提交--分暂存提交和直接提交
     */
    @RequestMapping("/submit")
    @RequiresPermissions("operate:refunddeposit:update")
    public R SubmitStartFormCmd(@RequestBody RefundDepositEntity refundDeposit){
    	if (refundDeposit.getId()!=null) {
			//更新
    		refundDepositService.updateById(refundDeposit);
		}else {
			//新增
			refundDepositService.insert(refundDeposit);
		}
    	return R.ok();
    }
    
    /**
     * 获取所有保证金支付供应商
     */
    @RequestMapping("/getallsupplier")
    public LayuiPage getAllSupplier(@RequestParam Map<String, Object> params){
    	LayuiPage page = cautionMoneyService.queryPage(params);
    	return page;
    }
    /**
     * 根据保证金支付供应商id获取保证金支付详细信息
     */
    @RequestMapping("/getsupplierinfo/{cautionId}")
    public R getSupplierInfo(@PathVariable String cautionId){
    	CautionMoneyEntity cautionMoneyEntity = cautionMoneyService.selectById(cautionId);
    	//根据供应商id获取附件列表
    	Map<String, Object> params = new HashMap<>();
    	params.put("supplierId", cautionMoneyEntity.getSupplierId());
    	List<SupplierFileEntity> fileList = supplierFileService.queryList(params);
    	return R.ok().put("cautionMoneyEntity", cautionMoneyEntity).put("fileList", fileList);
    }
    /**
     * 审核
     */
    @RequestMapping("/check")
    public R check(@RequestBody RefundDepositEntity refundDeposit){
    	refundDepositService.updateById(refundDeposit);
    return R.ok();
	}
}
