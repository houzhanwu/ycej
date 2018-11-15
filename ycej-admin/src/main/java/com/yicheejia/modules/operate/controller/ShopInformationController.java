package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.operate.entity.ShopInformationEntity;
import com.yicheejia.modules.operate.service.ShopInformationService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;



/**
 * 门店信息表
 *
 * @author  
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:28
 */
@RestController
@RequestMapping("operate/shopinformation")
public class ShopInformationController {
    @Autowired
    private ShopInformationService shopInformationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:shopinformation:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
    	LayuiPage page = shopInformationService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{shopId}")
    @RequiresPermissions("operate:shopinformation:info")
    public R info(@PathVariable("shopId") Integer shopId){
			ShopInformationEntity shopInformation = shopInformationService.selectById(shopId);

        return R.ok().put("shopInformation", shopInformation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:shopinformation:save")
    public R save(@RequestBody ShopInformationEntity shopInformation){
			shopInformationService.insert(shopInformation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:shopinformation:update")
    public R update(@RequestBody ShopInformationEntity shopInformation){
			shopInformationService.updateById(shopInformation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:shopinformation:delete")
    public R delete(@RequestBody Integer[] shopIds){
			shopInformationService.deleteBatchIds(Arrays.asList(shopIds));

        return R.ok();
    }

}
