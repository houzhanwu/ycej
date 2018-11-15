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

import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.operate.entity.IncomingDetailEntity;
import com.yicheejia.modules.operate.excel.IncomingDetailBean;
import com.yicheejia.modules.operate.service.IncomingDetailService;


/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-11-02 11:08:40
 */
@RestController
@RequestMapping("operate/incomingdetail")
public class IncomingDetailController {
    @Autowired
    private IncomingDetailService incomingDetailService;
    

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:incomingdetail:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = incomingDetailService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("operate:incomingdetail:info")
    public R info(@PathVariable("id") Long id){
			IncomingDetailEntity incomingDetail = incomingDetailService.selectById(id);

        return R.ok().put("incomingDetail", incomingDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:incomingdetail:save")
    public R save(@RequestBody IncomingDetailEntity incomingDetail){
			incomingDetailService.insert(incomingDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:incomingdetail:update")
    public R update(@RequestBody IncomingDetailEntity incomingDetail){
			incomingDetailService.updateById(incomingDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:incomingdetail:delete")
    public R delete(@RequestBody Long[] ids){
			incomingDetailService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:incomingdetail:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = incomingDetailService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), IncomingDetailBean.class);
    }
    
}
