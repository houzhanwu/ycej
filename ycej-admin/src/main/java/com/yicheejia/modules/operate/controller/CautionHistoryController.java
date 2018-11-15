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

import com.yicheejia.modules.operate.excel.CautionHistoryBean;
import com.yicheejia.modules.operate.entity.CautionHistoryEntity;
import com.yicheejia.modules.operate.service.CautionHistoryService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-10-25 15:47:20
 */
@RestController
@RequestMapping("operate/cautionhistory")
public class CautionHistoryController {
    @Autowired
    private CautionHistoryService cautionHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:cautionhistory:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = cautionHistoryService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{hCautionId}")
    @RequiresPermissions("operate:cautionhistory:info")
    public R info(@PathVariable("hCautionId") Long hCautionId){
			CautionHistoryEntity cautionHistory = cautionHistoryService.selectById(hCautionId);

        return R.ok().put("cautionHistory", cautionHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:cautionhistory:save")
    public R save(@RequestBody CautionHistoryEntity cautionHistory){
			cautionHistoryService.insert(cautionHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:cautionhistory:update")
    public R update(@RequestBody CautionHistoryEntity cautionHistory){
			cautionHistoryService.updateById(cautionHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:cautionhistory:delete")
    public R delete(@RequestBody Long[] hCautionIds){
			cautionHistoryService.deleteBatchIds(Arrays.asList(hCautionIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:cautionhistory:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = cautionHistoryService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), CautionHistoryBean.class);
    }

}
