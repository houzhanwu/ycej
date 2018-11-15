package com.yicheejia.modules.financialmanage.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceOrderDetailEntity;
import com.yicheejia.modules.financialmanage.excel.OrderDetailBean;
import com.yicheejia.modules.financialmanage.service.YcejFinanceOrderDetailService;


/**
 * 订单费用明细表
 *
 * @author 梁占豪
 * @email 
 * @date 2018-07-10 08:52:57
 */
@RestController
@RequestMapping("financialmanage/orderdetail")
public class YcejFinanceOrderDetailController {
    @Autowired
    private YcejFinanceOrderDetailService orderDetailService;

    /**
     * 列表
     */
    @SysLog("缴款查询列表")
    @RequestMapping("/list")
    @RequiresPermissions("financialmanage:ycejorderdetail:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = orderDetailService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderDetailId}")
    @RequiresPermissions("shop:orderdetail:info")
    public R info(@PathVariable("orderDetailId") String orderDetailId){
    	YcejFinanceOrderDetailEntity orderDetail = orderDetailService.selectById(orderDetailId);

        return R.ok().put("orderDetail", orderDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:orderdetail:save")
    public R save(@RequestBody YcejFinanceOrderDetailEntity orderDetail){
			orderDetailService.insert(orderDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:orderdetail:update")
    public R update(@RequestBody YcejFinanceOrderDetailEntity orderDetail){
			orderDetailService.updateById(orderDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:orderdetail:delete")
    public R delete(@RequestBody String[] orderDetailIds){
			orderDetailService.deleteBatchIds(Arrays.asList(orderDetailIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @SysLog("缴款查询导出")
    @RequestMapping("/export")
    @RequiresPermissions("financialmanage:ycejorderdetail:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = orderDetailService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "订单费用明细表", page.getData(), OrderDetailBean.class);
    }

}
