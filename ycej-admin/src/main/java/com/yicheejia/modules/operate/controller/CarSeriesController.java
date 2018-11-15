package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yicheejia.common.exception.RRException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.operate.entity.CarSeriesEntity;
import com.yicheejia.modules.operate.service.CarSeriesService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;



/**
 * 车系表
 *
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
@RestController
@RequestMapping("operate/carseries")
public class CarSeriesController {
    @Autowired
    private CarSeriesService carSeriesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:carseries:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
    	LayuiPage page = carSeriesService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carSeriesId}")
    @RequiresPermissions("operate:carseries:info")
    public R info(@PathVariable("carSeriesId") Integer carSeriesId){
			CarSeriesEntity carSeries = carSeriesService.selectById(carSeriesId);

        return R.ok().put("carSeries", carSeries);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:carseries:save")
    public R save(@RequestBody CarSeriesEntity carSeries){
        int count = carSeriesService.selectBySeries(carSeries);
        if(count > 0){
            throw new RRException("车系已存在");
        }
    	carSeries.setInsertId(ShiroUtils.getUserEntity().getUsername());
    	carSeries.setInsertTime(new Date());
    	carSeries.setCarSeries(carSeries.getCarSeries().trim());
			carSeriesService.insert(carSeries);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:carseries:update")
    public R update(@RequestBody CarSeriesEntity carSeries){
    	carSeries.setUpdateId(ShiroUtils.getUserEntity().getUsername());
    	carSeries.setUpdateTime(new Date());
			carSeriesService.updateById(carSeries);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:carseries:delete")
    public R delete(@RequestBody Integer[] carSeriesIds){
			carSeriesService.deleteBatchIds(Arrays.asList(carSeriesIds));

        return R.ok();
    }
    /**
     * 根据厂商ID获取对应车系列表
     */
    @RequestMapping("/getCarSeriesList")
    public R getCarSeriesList(@RequestParam Map<String, Object> params){
		List<CarSeriesEntity>  carSeriesList = carSeriesService.getCarSeriesList(params);
        return R.ok().put("carSeriesList",carSeriesList);
    }
}
