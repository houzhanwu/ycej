package com.yicheejia.modules.product.controller;

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

import com.yicheejia.modules.product.excel.ProductInfoBean;
import com.yicheejia.modules.product.entity.ProductInfoEntity;
import com.yicheejia.modules.product.service.ProductInfoService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author hunk
 * @email 
 * @date 2018-07-16 19:57:23
 */
@RestController
@RequestMapping("product/productinfo")
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("product:productinfo:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = productInfoService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{productId}")
    @RequiresPermissions("product:productinfo:info")
    public R info(@PathVariable("productId") String productId){
			ProductInfoEntity productInfo = productInfoService.selectById(productId);

        return R.ok().put("productInfo", productInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("product:productinfo:save")
    public R save(@RequestBody ProductInfoEntity productInfo){
			productInfoService.insert(productInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:productinfo:update")
    public R update(@RequestBody ProductInfoEntity productInfo){
			productInfoService.updateById(productInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("product:productinfo:delete")
    public R delete(@RequestBody String[] productIds){
			productInfoService.deleteBatchIds(Arrays.asList(productIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("product:productinfo:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = productInfoService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), ProductInfoBean.class);
    }

}
