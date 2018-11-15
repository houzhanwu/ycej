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

import com.yicheejia.modules.operate.entity.CarModelEntity;
import com.yicheejia.modules.operate.service.CarModelService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;



/**
 * 车型表
 *
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
@RestController
@RequestMapping("operate/carmodel")
public class CarModelController {
    @Autowired
    private CarModelService carModelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:carmodel:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
    	LayuiPage page = carModelService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carModelId}")
    @RequiresPermissions("operate:carmodel:info")
    public R info(@PathVariable("carModelId") Integer carModelId){
			CarModelEntity carModel = carModelService.selectById(carModelId);

        return R.ok().put("carModel", carModel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:carmodel:save")
    public R save(@RequestBody CarModelEntity carModel){
        int count = carModelService.selectByCarModel(carModel);
        if(count > 0){
            throw new RRException("车型已存在");
        }
    	carModel.setInsertId(ShiroUtils.getUserEntity().getUsername());
    	carModel.setInsertTime(new Date());
    	carModel.setCarModel(carModel.getCarModel().trim());
			carModelService.insert(carModel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:carmodel:update")
    public R update(@RequestBody CarModelEntity carModel){
    	carModel.setUpdateId(ShiroUtils.getUserEntity().getUsername());
    	carModel.setUpdateTime(new Date());
			carModelService.updateById(carModel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:carmodel:delete")
    public R delete(@RequestBody Integer[] carModelIds){
			carModelService.deleteBatchIds(Arrays.asList(carModelIds));

        return R.ok();
    }
    
    /**
     * 根据车系ID获取车型
     */
    @RequestMapping("/getCarModelList")
    public R getCarModelList(@RequestParam Map<String, Object> params){
		List<CarModelEntity>  carModelList = carModelService.getCarModelList(params);
        return R.ok().put("carModelList",carModelList);
    }

}
