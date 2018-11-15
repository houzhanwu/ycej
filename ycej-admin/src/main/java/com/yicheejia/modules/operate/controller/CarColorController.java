package com.yicheejia.modules.operate.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.modules.operate.entity.CarColorEntity;
import com.yicheejia.modules.operate.service.CarColorService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 汽车外观颜色表
 *
 * @author 
 * @email 
 * @date 2018-08-30 15:46:19
 */
@RestController
@RequestMapping("operate/carcolor")
public class CarColorController {
    @Autowired
    private CarColorService carColorService;

    /**
     * 外观颜色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:carcolor:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = carColorService.queryPage(params);

        return page;
    }

    /**
     * 内饰颜色列表
     *
     */
    @RequestMapping("/innerList")
    @RequiresPermissions("operate:carcolor:list")
    public LayuiPage innerList(@RequestParam Map<String, Object> params){
        LayuiPage page = carColorService.queryInnerPage(params);

        return page;
    }




    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("operate:carcolor:info")
    public R info(@PathVariable("id") Integer id){
			CarColorEntity carColor = carColorService.selectById(id);

        return R.ok().put("carColor", carColor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:carcolor:save")
    public R save(@RequestBody CarColorEntity carColor){

        int colorCount = carColorService.queryExistByCarColor(carColor);
        if(colorCount > 0){
            throw new RRException("颜色已存在");
        }

        String name = ShiroUtils.getUserEntity().getName();
        carColor.setInsUser(name);
        carColor.setInsTime(DateUtils.getDate());
			carColorService.insert(carColor);

        return R.ok();
    }

    /**
     * 内饰颜色保存
     */
    @RequestMapping("/innerSave")
    @RequiresPermissions("operate:carcolor:save")
    public R innerSave(@RequestBody CarColorEntity carColor){

        int colorCount = carColorService.queryExistDecorationColor(carColor);
        if(colorCount > 0){
            throw new RRException("颜色已存在");
        }

        String name = ShiroUtils.getUserEntity().getName();
        carColor.setInsUser(name);
        carColor.setInsTime(DateUtils.getDate());
        carColorService.insert(carColor);

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:carcolor:update")
    public R update(@RequestBody CarColorEntity carColor){
			carColorService.updateById(carColor);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:carcolor:delete")
    public R delete(@RequestBody Integer[] ids){
			carColorService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 获取所有汽车颜色
     */
    @RequestMapping("/getColors")
    public R getAllCarColors(){
        List<Map<String,Object>> colors = carColorService.getAllColors();

        return R.ok().put("colors",colors);
    }

    /**
     * 获取所有内饰颜色
     * @return
     */
    @RequestMapping("/getAllDecorationColors")
    public R getAllDecorationColors(){
        List<Map<String,Object>> colors = carColorService.getAllDecorationColors();

        return R.ok().put("decorations",colors);
    }


}
