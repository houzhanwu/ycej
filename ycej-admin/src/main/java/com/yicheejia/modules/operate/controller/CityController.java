package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.operate.entity.CityEntity;
import com.yicheejia.modules.operate.service.CityService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;



/**
 * 地级市表
 *
 * @author
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
@RestController
@RequestMapping("operate/city")
public class CityController {
    @Autowired
    private CityService cityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:city:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
    	LayuiPage page = cityService.queryPage(params);

        return page;
    }

    /**
     * 根据省份id获取所属城市
     * @param provinceId
     * @return
     */
    @RequestMapping("/cityList")
    public R cityList(@RequestParam Integer provinceId){
        List<Map<String,Object>> cityList = null;
        cityList = cityService.getCityListByProvinceId(provinceId);

        return R.ok().put("cities",cityList);
    }

    @RequestMapping("/allCity")
    public R allCitiesList(){
        List<Map<String,Object>> cities = cityService.getAllCities();

        return R.ok().put("cities",cities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{cityId}")
    @RequiresPermissions("operate:city:info")
    public R info(@PathVariable("cityId") Integer cityId){
			CityEntity city = cityService.selectById(cityId);

        return R.ok().put("city", city);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:city:save")
    public R save(@RequestBody CityEntity city){
			cityService.insert(city);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:city:update")
    public R update(@RequestBody CityEntity city){
			cityService.updateById(city);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:city:delete")
    public R delete(@RequestBody Integer[] cityIds){
			cityService.deleteBatchIds(Arrays.asList(cityIds));

        return R.ok();
    }

}
