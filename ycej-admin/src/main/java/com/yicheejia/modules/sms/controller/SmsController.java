package com.yicheejia.modules.sms.controller;

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

import com.yicheejia.modules.sms.excel.SmsBean;
import com.yicheejia.modules.sms.entity.SmsEntity;
import com.yicheejia.modules.sms.service.SmsService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:20:35
 */
@RestController
@RequestMapping("sms/sms")
public class SmsController {
    @Autowired
    private SmsService smsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sms:sms:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = smsService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{smsId}")
    @RequiresPermissions("sms:sms:info")
    public R info(@PathVariable("smsId") Integer smsId){
			SmsEntity sms = smsService.selectById(smsId);

        return R.ok().put("sms", sms);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sms:sms:save")
    public R save(@RequestBody SmsEntity sms){
			smsService.insert(sms);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sms:sms:update")
    public R update(@RequestBody SmsEntity sms){
			smsService.updateById(sms);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sms:sms:delete")
    public R delete(@RequestBody Integer[] smsIds){
			smsService.deleteBatchIds(Arrays.asList(smsIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("sms:sms:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = smsService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), SmsBean.class);
    }

}
