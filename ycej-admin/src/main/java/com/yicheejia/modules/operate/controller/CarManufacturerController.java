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

import com.yicheejia.modules.operate.entity.CarManufacturerEntity;
import com.yicheejia.modules.operate.service.CarManufacturerService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;



/**
 * 厂商表
 *
 * @author luwen
 * @email ${email}
 * @date 2018-07-03 16:35:00
 */
@RestController
@RequestMapping("operate/carmanufacturer")
public class CarManufacturerController {
    @Autowired
    private CarManufacturerService carManufacturerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:carmanufacturer:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
    	LayuiPage page = carManufacturerService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carManufacturerId}")
    @RequiresPermissions("operate:carmanufacturer:info")
    public R info(@PathVariable("carManufacturerId") Integer carManufacturerId){
			CarManufacturerEntity carManufacturer = carManufacturerService.selectById(carManufacturerId);

        return R.ok().put("carManufacturer", carManufacturer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:carmanufacturer:save")
    public R save(@RequestBody CarManufacturerEntity carManufacturer){
//    	carManufacturer.setCarBrandId(carManufacturer.getCarBrandId());
        int count = carManufacturerService.selectByManufacturer(carManufacturer);
        if(count > 0){
            throw new RRException("厂商已存在");
        }
    	carManufacturer.setInsertId(ShiroUtils.getUserEntity().getUsername());
    	carManufacturer.setInsertTime(new Date());
    	carManufacturer.setCarManufacturer(carManufacturer.getCarManufacturer().trim());
    	carManufacturerService.insert(carManufacturer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:carmanufacturer:update")
    public R update(@RequestBody CarManufacturerEntity carManufacturer){
    	carManufacturer.setUpdateTime(new Date());
    	carManufacturer.setUpdateId(ShiroUtils.getUserEntity().getUsername());
	    	carManufacturerService.updateById(carManufacturer);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:carmanufacturer:delete")
    public R delete(@RequestBody Integer[] carManufacturerIds){
    	carManufacturerService.deleteBatchIds(Arrays.asList(carManufacturerIds));

        return R.ok();
    }
    
    /**
     * 获取所有品牌id,name
     * @return
     */
    @RequestMapping("/carManufacturerList")
    public R selectList(@RequestParam Map<String, Object> params){
        List<Map<String,Object>>  carManufacturerList = carManufacturerService.getCarManufacturers(params);
        return R.ok().put("carManufacturers",carManufacturerList);
    }

}
