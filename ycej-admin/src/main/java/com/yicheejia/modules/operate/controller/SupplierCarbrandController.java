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

import com.yicheejia.modules.operate.excel.SupplierCarbrandBean;
import com.yicheejia.modules.operate.entity.SupplierCarbrandEntity;
import com.yicheejia.modules.operate.service.SupplierCarbrandService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 供应商品牌关联信息表
 *
 * @author 
 * @email 
 * @date 2018-10-27 15:51:56
 */
@RestController
@RequestMapping("operate/suppliercarbrand")
public class SupplierCarbrandController {
    @Autowired
    private SupplierCarbrandService supplierCarbrandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:suppliercarbrand:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = supplierCarbrandService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("operate:suppliercarbrand:info")
    public R info(@PathVariable("id") Integer id){
			SupplierCarbrandEntity supplierCarbrand = supplierCarbrandService.selectById(id);

        return R.ok().put("supplierCarbrand", supplierCarbrand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:suppliercarbrand:save")
    public R save(@RequestBody SupplierCarbrandEntity supplierCarbrand){
			supplierCarbrandService.insert(supplierCarbrand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:suppliercarbrand:update")
    public R update(@RequestBody SupplierCarbrandEntity supplierCarbrand){
			supplierCarbrandService.updateById(supplierCarbrand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:suppliercarbrand:delete")
    public R delete(@RequestBody Integer[] ids){
			supplierCarbrandService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:suppliercarbrand:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = supplierCarbrandService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "供应商品牌关联信息表", page.getData(), SupplierCarbrandBean.class);
    }

}
