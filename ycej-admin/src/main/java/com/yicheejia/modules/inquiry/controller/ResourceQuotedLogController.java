package com.yicheejia.modules.inquiry.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.inquiry.entity.ResourceQuotedLogEntity;
import com.yicheejia.modules.inquiry.service.ResourceQuotedLogService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;


/**
 * 资源报价历史记录表
 *
 * @author 
 * @email 
 * @date 2018-10-29 17:42:28
 */
@RestController
@RequestMapping("inquiry/resourcequotedlog")
public class ResourceQuotedLogController {
    @Autowired
    private ResourceQuotedLogService resourceQuotedLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("inquiry:resourcequotedlog:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = resourceQuotedLogService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{logId}")
    @RequiresPermissions("inquiry:resourcequotedlog:info")
    public R info(@PathVariable("logId") Long logId){
			ResourceQuotedLogEntity resourceQuotedLog = resourceQuotedLogService.selectById(logId);

        return R.ok().put("resourceQuotedLog", resourceQuotedLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("inquiry:resourcequotedlog:save")
    public R save(@RequestBody ResourceQuotedLogEntity resourceQuotedLog){
			resourceQuotedLogService.insert(resourceQuotedLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("inquiry:resourcequotedlog:update")
    public R update(@RequestBody ResourceQuotedLogEntity resourceQuotedLog){
			resourceQuotedLogService.updateById(resourceQuotedLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("inquiry:resourcequotedlog:delete")
    public R delete(@RequestBody Long[] logIds){
			resourceQuotedLogService.deleteBatchIds(Arrays.asList(logIds));

        return R.ok();
    }



}
