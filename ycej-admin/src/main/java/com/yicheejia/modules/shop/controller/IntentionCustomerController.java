package com.yicheejia.modules.shop.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.validator.ValidatorUtils;
import com.yicheejia.common.validator.group.AddGroup;
import com.yicheejia.common.validator.group.UpdateGroup;
import com.yicheejia.modules.shop.excel.IntentionCustomerBean;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.shop.entity.IntentionCustomerEntity;
import com.yicheejia.modules.shop.service.IntentionCustomerService;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 意向客户表
 *
 * @author cn
 * @email 
 * @date 2018-06-27 15:10:45
 */
@RestController
@RequestMapping("shop/intentioncustomer")
public class IntentionCustomerController {
    @Autowired
    private IntentionCustomerService intentionCustomerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shop:intentioncustomer:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = intentionCustomerService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{customerId}")
    @RequiresPermissions("shop:intentioncustomer:info")
    public R info(@PathVariable("customerId") Integer customerId){
			IntentionCustomerEntity intentionCustomer = intentionCustomerService.selectById(customerId);

        return R.ok().put("intentionCustomer", intentionCustomer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:intentioncustomer:save")
    public R save(@RequestBody IntentionCustomerEntity intentionCustomer){
        ValidatorUtils.validateEntity(intentionCustomer, AddGroup.class);
        int count = intentionCustomerService.selectByIntentionCustomer(intentionCustomer);
        if(count > 0){
            throw new RRException("该意向客户已存在(其它)门店中");
        }

        intentionCustomer.setInsTime(new Date());
        intentionCustomer.setInsUser(ShiroUtils.getUserEntity().getUsername());
        intentionCustomer.setIntentionCustomerState(0); //默认未成交
			intentionCustomerService.insert(intentionCustomer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:intentioncustomer:update")
    public R update(@RequestBody IntentionCustomerEntity intentionCustomer){
        ValidatorUtils.validateEntity(intentionCustomer, UpdateGroup.class);

        intentionCustomerService.updateById(intentionCustomer);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:intentioncustomer:delete")
    public R delete(@RequestBody Integer[] customerIds){
			intentionCustomerService.deleteBatchIds(Arrays.asList(customerIds));

        return R.ok();
    }

    /**
     * 导出
     */
    @RequestMapping("/export")
    @RequiresPermissions("shop:intentioncustomer:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = intentionCustomerService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "意向客户表", page.getData(), IntentionCustomerBean.class);
    }

}
