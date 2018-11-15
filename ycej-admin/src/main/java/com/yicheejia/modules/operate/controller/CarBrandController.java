package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.operate.entity.CarBrandEntity;
import com.yicheejia.modules.operate.service.CarBrandService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;



/**
 * 
 *
 * @author luwen
 * @email ${email}
 * @date 2018-07-03 16:35:00
 */
@RestController
@RequestMapping("operate/carbrand")
public class CarBrandController {
    @Autowired
    private CarBrandService carBrandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:carbrand:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = carBrandService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carBrandId}")
    @RequiresPermissions("operate:carbrand:info")
    public R info(@PathVariable("carBrandId") Integer carBrandId){
			CarBrandEntity carBrand = carBrandService.selectById(carBrandId);

        return R.ok().put("carBrand", carBrand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:carbrand:save")
    public R save(@RequestBody CarBrandEntity carBrand){
    	carBrand.setInsertTime(new Date());
    	carBrand.setInsertId(ShiroUtils.getUserEntity().getUsername());
    	carBrand.setCarBrand(carBrand.getCarBrand().trim());
    	int carBrandcount =0;
    	carBrandcount = carBrandService.selectByBrand(carBrand);
    	if(carBrandcount>0) {
    		return R.error("品牌已存在，保存失败");
    	}else {
    		carBrandService.insert(carBrand);
            return R.ok();
    	}
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:carbrand:update")
    public R update(@RequestBody CarBrandEntity carBrand){
    	carBrand.setUpdateTime(new Date());
    	carBrand.setUpdateId(ShiroUtils.getUserEntity().getUsername());
			carBrandService.updateById(carBrand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:carbrand:delete")
    public R delete(@RequestBody Integer[] carBrandIds){
			carBrandService.deleteBatchIds(Arrays.asList(carBrandIds));

        return R.ok();
    }
    
    /**
     * 获取所有品牌id,name
     * @return
     */
    @RequestMapping("/carBrandList")
    public R selectList(@RequestParam Map<String, Object> params){
        List<Map<String,Object>>  carBrandsList = carBrandService.getCarBrands(params);
        return R.ok().put("carBrands",carBrandsList);
    }

}
