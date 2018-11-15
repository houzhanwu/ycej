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

import com.yicheejia.modules.operate.excel.CarBackBean;
import com.yicheejia.modules.operate.entity.CarBackEntity;
import com.yicheejia.modules.operate.service.CarBackService;
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
@RequestMapping("operate/carback")
public class CarBackController {
    @Autowired
    private CarBackService carBackService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:carback:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = carBackService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{backId}")
    @RequiresPermissions("operate:carback:info")
    public R info(@PathVariable("backId") Long backId){
			CarBackEntity carBack = carBackService.selectById(backId);

        return R.ok().put("carBack", carBack);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:carback:save")
    public R save(@RequestBody CarBackEntity carBack){
			carBackService.insert(carBack);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:carback:update")
    public R update(@RequestBody CarBackEntity carBack){
			carBackService.updateById(carBack);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:carback:delete")
    public R delete(@RequestBody Long[] backIds){
			carBackService.deleteBatchIds(Arrays.asList(backIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:carback:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = carBackService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), CarBackBean.class);
    }

}
