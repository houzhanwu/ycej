package com.yicheejia.modules.shop.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.shop.entity.CustomerTrackEntity;
import com.yicheejia.modules.shop.service.CustomerTrackService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;


/**
 * 客户跟踪表
 *
 * @author cn
 * @email 
 * @date 2018-07-17 15:06:30
 */
@RestController
@RequestMapping("shop/customertrack")
public class CustomerTrackController {
    @Autowired
    private CustomerTrackService customerTrackService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("shop:customertrack:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = customerTrackService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{customerId}")
    //@RequiresPermissions("shop:customertrack:info")
    public R info(@PathVariable("customerId") Integer customerId){
			CustomerTrackEntity customerTrack = customerTrackService.selectById(customerId);

        return R.ok().put("customerTrack", customerTrack);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("shop:customertrack:save")
    public R save(@RequestBody CustomerTrackEntity customerTrack){
			customerTrackService.insert(customerTrack);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
   // @RequiresPermissions("shop:customertrack:update")
    public R update(@RequestBody CustomerTrackEntity customerTrack){
			customerTrackService.updateById(customerTrack);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("shop:customertrack:delete")
    public R delete(@RequestBody Integer[] customerIds){
			customerTrackService.deleteBatchIds(Arrays.asList(customerIds));

        return R.ok();
    }

    /**
     * 导出
    */
//    @RequestMapping("/export")
//    @RequiresPermissions("shop:customertrack:export")
//    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
//        LayuiPage page = customerTrackService.queryPage(params);
//
//        ExcelUtils.exportExcelToTarget(response, "客户跟踪表", page.getData(), CustomerTrackBean.class);
//    }

}
