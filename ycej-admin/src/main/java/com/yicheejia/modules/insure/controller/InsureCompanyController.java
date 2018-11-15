package com.yicheejia.modules.insure.controller;

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
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.insure.entity.InsureCompanyEntity;
import com.yicheejia.modules.insure.excel.InsureCompanyBean;
import com.yicheejia.modules.insure.service.InsureCompanyService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;


/**
 * 保险公司
 *
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
@RestController
@RequestMapping("order/insurecompany")
public class InsureCompanyController {
    @Autowired
    private InsureCompanyService insureCompanyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:insurecompany:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = insureCompanyService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{companyId}")
    @RequiresPermissions("order:insurecompany:info")
    public R info(@PathVariable("companyId") String companyId){
			InsureCompanyEntity insureCompany = insureCompanyService.selectById(companyId);

        return R.ok().put("insureCompany", insureCompany);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:insurecompany:save")
    public R save(@RequestBody InsureCompanyEntity insureCompany){
    		insureCompany.setInsertId(ShiroUtils.getUserEntity().getUsername());
    		insureCompany.setInsertTime(DateUtils.getDate());
    		insureCompany.setUpdateTime(DateUtils.getDate());
			insureCompanyService.insert(insureCompany);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:insurecompany:update")
    public R update(@RequestBody InsureCompanyEntity insureCompany){
    		insureCompany.setUpdateId(ShiroUtils.getUserEntity().getUsername());
    		insureCompany.setUpdateTime(DateUtils.getDate());			
    		insureCompanyService.updateById(insureCompany);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:insurecompany:delete")
    public R delete(@RequestBody Integer[] companyIds){
			insureCompanyService.deleteBatchIds(Arrays.asList(companyIds));
 
        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("order:insurecompany:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = insureCompanyService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "保险公司", page.getData(), InsureCompanyBean.class);
    }

}
