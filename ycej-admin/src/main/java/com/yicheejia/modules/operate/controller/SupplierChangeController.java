package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.operate.excel.SupplierChangeBean;
import com.yicheejia.modules.operate.entity.SupplierChangeEntity;
import com.yicheejia.modules.operate.service.SupplierChangeService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 供应商变更信息表
 *
 * @author 
 * @email 
 * @date 2018-10-27 15:51:56
 */
@RestController
@RequestMapping("operate/supplierchange")
public class SupplierChangeController {
    @Autowired
    private SupplierChangeService supplierChangeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:supplierchange:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = supplierChangeService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("operate:supplierchange:info")
    public R info(@PathVariable("id") Integer id){
			SupplierChangeEntity supplierChange = supplierChangeService.selectById(id);

        return R.ok().put("supplierChange", supplierChange);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:supplierchange:save")
    public R save(@RequestBody SupplierChangeEntity supplierChange){
			supplierChangeService.insert(supplierChange);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:supplierchange:update")
    public R update(@RequestBody SupplierChangeEntity supplierChange){
			supplierChangeService.updateById(supplierChange);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:supplierchange:delete")
    public R delete(@RequestBody Integer[] ids){
			supplierChangeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:supplierchange:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = supplierChangeService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "供应商变更信息表", page.getData(), SupplierChangeBean.class);
    }

}
