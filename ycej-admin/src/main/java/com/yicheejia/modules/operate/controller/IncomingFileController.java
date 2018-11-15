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

import com.yicheejia.modules.operate.excel.IncomingFileBean;
import com.yicheejia.modules.operate.entity.IncomingFileEntity;
import com.yicheejia.modules.operate.service.IncomingFileService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-11-02 11:08:40
 */
@RestController
@RequestMapping("operate/incomingfile")
public class IncomingFileController {
    @Autowired
    private IncomingFileService incomingFileService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:incomingfile:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = incomingFileService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("operate:incomingfile:info")
    public R info(@PathVariable("id") Long id){
			IncomingFileEntity incomingFile = incomingFileService.selectById(id);

        return R.ok().put("incomingFile", incomingFile);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:incomingfile:save")
    public R save(@RequestBody IncomingFileEntity incomingFile){
			incomingFileService.insert(incomingFile);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:incomingfile:update")
    public R update(@RequestBody IncomingFileEntity incomingFile){
			incomingFileService.updateById(incomingFile);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:incomingfile:delete")
    public R delete(@RequestBody Long[] ids){
			incomingFileService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:incomingfile:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = incomingFileService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), IncomingFileBean.class);
    }

}
