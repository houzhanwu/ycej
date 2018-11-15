package com.yicheejia.modules.inquiry.controller;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.yicheejia.common.exception.RRException;
import com.yicheejia.modules.inquiry.entity.ResourceQuotedLogEntity;
import com.yicheejia.modules.inquiry.service.ResourceBrandService;
import com.yicheejia.modules.inquiry.service.ResourceQuotedLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.yicheejia.modules.inquiry.excel.ResourceQuotedBean;
import com.yicheejia.modules.inquiry.entity.ResourceQuotedEntity;
import com.yicheejia.modules.inquiry.service.ResourceQuotedService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 资源报价表
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:44:05
 */
@RestController
@RequestMapping("inquiry/resourcequoted")
public class ResourceQuotedController {
    @Autowired
    private ResourceQuotedService resourceQuotedService;

    @Autowired
    private ResourceQuotedLogService quotedLogService;

    @Autowired
    private ResourceBrandService resourceBrandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = resourceQuotedService.queryPage(params);

        return page;
    }

    @RequestMapping("/listForWX")
    public LayuiPage listForWX(@RequestParam Map<String,Object> params){
        LayuiPage page = resourceQuotedService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{quotedId}")
    @RequiresPermissions("inquiry:resourcequoted:info")
    public R info(@PathVariable("quotedId") Long quotedId){
			ResourceQuotedEntity resourceQuoted = resourceQuotedService.selectById(quotedId);

        return R.ok().put("resourceQuoted", resourceQuoted);
    }

    @RequestMapping("/brandInfo")
    public R brandInfo(@RequestParam Long resourceId){
        Map<String,Object> params = new HashMap<>();
        params.put("resourceCustomerId",resourceId);

        List<Map<String,Object>> brandList = resourceBrandService.getResourceBrands(params);

        return R.ok().put("brandList",brandList);
    }

    @RequestMapping("/historyList")
    public R historyList(@RequestParam Long quotedId){
        if(quotedId == null){
            throw new RRException("报价记录不存在");
        }
        ResourceQuotedEntity resourceQuoted = resourceQuotedService.selectById(quotedId);
        if(resourceQuoted != null){
            Map<String,Object> params = new HashMap<>();
            params.put("quotedId",quotedId);
            LayuiPage page = quotedLogService.queryPage(params);

            resourceQuoted.setHistories(page.getData());
        }


        return R.ok().put("resourceQuoted",resourceQuoted);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @Transactional
    public R save(@RequestBody ResourceQuotedEntity resourceQuoted){
            Date quotedTime = new Date();
            resourceQuoted.setQuotedTime(quotedTime);
			resourceQuotedService.insert(resourceQuoted);

            ResourceQuotedLogEntity logEntity = new ResourceQuotedLogEntity();
            logEntity.setOfferPrice(resourceQuoted.getOfferPrice());
            logEntity.setQuotedId(resourceQuoted.getQuotedId());
            logEntity.setQuotedTime(resourceQuoted.getQuotedTime());
            logEntity.setRemark(resourceQuoted.getRemark());
            logEntity.setQuotedTime(quotedTime);
			quotedLogService.insert(logEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ResourceQuotedEntity resourceQuoted){
            Date quotedTime = new Date();
            resourceQuoted.setQuotedTime(quotedTime);
			Boolean resultFlag = resourceQuotedService.updateById(resourceQuoted);
		//	resourceQuotedService.updateResourceQuotedById(resourceQuoted);
            if(resultFlag){
                ResourceQuotedLogEntity logEntity = new ResourceQuotedLogEntity();
                logEntity.setQuotedId(resourceQuoted.getQuotedId());
                logEntity.setQuotedTime(quotedTime);
                logEntity.setRemark(resourceQuoted.getRemark());
                logEntity.setOfferPrice(resourceQuoted.getOfferPrice());
                quotedLogService.insert(logEntity);
            }

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("inquiry:resourcequoted:delete")
    public R delete(@RequestBody Long[] quotedIds){
			resourceQuotedService.deleteBatchIds(Arrays.asList(quotedIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("inquiry:resourcequoted:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = resourceQuotedService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "资源报价表", page.getData(), ResourceQuotedBean.class);
    }





}
