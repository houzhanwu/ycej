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

import com.yicheejia.modules.operate.entity.ProvinceEntity;
import com.yicheejia.modules.operate.service.ProvinceService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;



/**
 * 省份表
 *
 * @author
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
@RestController
@RequestMapping("operate/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:province:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
    	LayuiPage page = provinceService.queryPage(params);

        return page;
    }

    /**
     * 获取所有省份id,name
     * @return
     */
    @RequestMapping("/provinceList")
    public R selectList(){
        List<Map<String,Object>>  provinceList = provinceService.getAllProvince();
        return R.ok().put("provinces",provinceList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{provinceId}")
    @RequiresPermissions("operate:province:info")
    public R info(@PathVariable("provinceId") Integer provinceId){
			ProvinceEntity province = provinceService.selectById(provinceId);

        return R.ok().put("province", province);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:province:save")
    public R save(@RequestBody ProvinceEntity province){
			provinceService.insert(province);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:province:update")
    public R update(@RequestBody ProvinceEntity province){
			provinceService.updateById(province);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:province:delete")
    public R delete(@RequestBody Integer[] provinceIds){
			provinceService.deleteBatchIds(Arrays.asList(provinceIds));

        return R.ok();
    }

}
