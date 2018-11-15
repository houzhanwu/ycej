package com.yicheejia.modules.order.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.order.entity.YcejOrderDetailEntity;
import com.yicheejia.modules.order.excel.OrderDetailBean;
import com.yicheejia.modules.order.service.YcejOrderDetailService;




/**
 * 
 *
 * @date 2018-06-21 16:27:42
 */
@RestController
@RequestMapping("order/ycejorderdetail")
public class YcejOrderDetailController {
    @Autowired
    private YcejOrderDetailService ycejOrderDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:ycejorderdetail:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
    	LayuiPage page = ycejOrderDetailService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderDetailId}")
    @RequiresPermissions("order:ycejorderdetail:info")
    public R info(@PathVariable("orderDetailId") String orderDetailId){
			YcejOrderDetailEntity ycejOrderDetail = ycejOrderDetailService.selectById(orderDetailId);

        return R.ok().put("ycejOrderDetail", ycejOrderDetail);
    }

    /**
     * 费用明细保存同时保存支付明细
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:ycejorderdetail:save")
    public R save(@RequestBody YcejOrderDetailEntity ycejOrderDetail){
			ycejOrderDetailService.insert(ycejOrderDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:ycejorderdetail:update")
    public R update(@RequestParam Map<String, Object> params){
			ycejOrderDetailService.updateByOrder(params);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:ycejorderdetail:delete")
    public R delete(@RequestBody String[] orderDetailIds){
			ycejOrderDetailService.deleteBatchIds(Arrays.asList(orderDetailIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("order:orderdetail:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = ycejOrderDetailService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "订单费用明细表", page.getData(), OrderDetailBean.class);
    }
    
    /**
     * 根据订单主键查询费用明细
     */
    @RequestMapping("/query")
    @RequiresPermissions("order:ycejorderdetail:info")
    public R query(@RequestParam Map<String, Object> params){
			List<YcejOrderDetailEntity> list = ycejOrderDetailService.query(params);

        return R.ok().put("ycejOrderDetail", list);
    }

}
