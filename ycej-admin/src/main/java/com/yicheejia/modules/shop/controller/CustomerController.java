package com.yicheejia.modules.shop.controller;

import java.util.Arrays;
import java.util.Map;

import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.shop.excel.CustomerBean;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.shop.entity.CustomerEntity;
import com.yicheejia.modules.shop.service.CustomerService;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 成交客户
 *
 * @author cn
 * @email 
 * @date 2018-06-27 15:10:45
 */
@RestController
@RequestMapping("shop/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shop:customer:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = customerService.queryPage(params);

        return page;
    }

    /**
     * 客户管理列表
     * @param params
     * @return
     */
    @RequestMapping("/customerManageList")
    @RequiresPermissions("shop:customer:list")
    public LayuiPage customerManageList(@RequestParam Map<String,Object> params){
        LayuiPage page = customerService.selectCustomerManageList(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{customerId}")
    @RequiresPermissions("shop:customer:info")
    public R info(@PathVariable("customerId") Integer customerId){
			//CustomerEntity customer = customerService.selectById(customerId);
        Map<String,Object> infoMap = customerService.getCustomerInfoById(customerId);

        return R.ok().put("customer", infoMap);
    }

    /**
     * 客户管理信息
     */
    @RequestMapping("/cusManageInfo/{customerId}")
    @RequiresPermissions("shop:customer:info")
    public R customerManageInfo(@PathVariable("customerId") Integer customerId){
        Map<String,Object> infoMap = customerService.getCusManageInfoById(customerId);

        return R.ok().put("customer",infoMap);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:customer:save")
    public R save(@RequestBody CustomerEntity customer){
			customerService.insert(customer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:customer:update")
    public R update(@RequestBody CustomerEntity customer){
			customerService.updateById(customer);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:customer:delete")
    public R delete(@RequestBody Integer[] customerIds){
			customerService.deleteBatchIds(Arrays.asList(customerIds));

        return R.ok();
    }

    /**
     * 导出
     */
    @RequestMapping("/export")
    @RequiresPermissions("shop:customer:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = customerService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "成交客户", page.getData(), CustomerBean.class);
    }

}
