package com.yicheejia.modules.inquiry.controller;

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

import com.yicheejia.modules.inquiry.excel.ResourceBrandBean;
import com.yicheejia.modules.inquiry.entity.ResourceBrandEntity;
import com.yicheejia.modules.inquiry.service.ResourceBrandService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 资源客户品牌表
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:44:05
 */
@RestController
@RequestMapping("inquiry/resourcebrand")
public class ResourceBrandController {
    @Autowired
    private ResourceBrandService resourceBrandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("inquiry:resourcebrand:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = resourceBrandService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("inquiry:resourcebrand:info")
    public R info(@PathVariable("id") Long id){
			ResourceBrandEntity resourceBrand = resourceBrandService.selectById(id);

        return R.ok().put("resourceBrand", resourceBrand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("inquiry:resourcebrand:save")
    public R save(@RequestBody ResourceBrandEntity resourceBrand){
			resourceBrandService.insert(resourceBrand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("inquiry:resourcebrand:update")
    public R update(@RequestBody ResourceBrandEntity resourceBrand){
			resourceBrandService.updateById(resourceBrand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("inquiry:resourcebrand:delete")
    public R delete(@RequestBody Long[] ids){
			resourceBrandService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("inquiry:resourcebrand:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = resourceBrandService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "资源客户品牌表", page.getData(), ResourceBrandBean.class);
    }

}
