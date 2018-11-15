package com.yicheejia.modules.order.controller;

import java.math.BigDecimal;
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

import com.yicheejia.modules.order.excel.OrderPaydetailBean;
import com.yicheejia.modules.order.entity.OrderPaydetailEntity;
import com.yicheejia.modules.order.service.OrderPaydetailService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 支付明细表
 *
 * @author fkm
 * @email 
 * @date 2018-07-09 11:16:31
 */
@RestController
@RequestMapping("order/orderpaydetail")
public class OrderPaydetailController {
    @Autowired
    private OrderPaydetailService orderPaydetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:orderpaydetail:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = orderPaydetailService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderPayId}")
    @RequiresPermissions("order:orderpaydetail:info")
    public R info(@PathVariable("orderPayId") Long orderPayId){
			OrderPaydetailEntity orderPaydetail = orderPaydetailService.selectById(orderPayId);

        return R.ok().put("orderPaydetail", orderPaydetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:orderpaydetail:save")
    public R save(@RequestBody OrderPaydetailEntity orderPaydetail){
			orderPaydetailService.insert(orderPaydetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:orderpaydetail:update")
    public R update(@RequestBody OrderPaydetailEntity orderPaydetail){
			orderPaydetailService.updateById(orderPaydetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:orderpaydetail:delete")
    public R delete(@RequestBody Long[] orderPayIds){
			orderPaydetailService.deleteBatchIds(Arrays.asList(orderPayIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("order:orderpaydetail:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = orderPaydetailService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "支付明细表", page.getData(), OrderPaydetailBean.class);
    }
    /**
     * 订单页面支付明细处理
     * @param params
     * @return
     */
    @RequestMapping("/queryPaydetail")
    @RequiresPermissions("order:orderpaydetail:list")
    public LayuiPage queryPaydetail(@RequestParam Map<String, Object> params){
        List<OrderPaydetailEntity> list = orderPaydetailService.queryPaydetail(params);
        LayuiPage page = new LayuiPage(list,list.size());
        return page;
    }
    /**
     * 支付明细中优惠金额查询
     * @param params
     * @return
     */
    @RequestMapping("/queryDiscount")
    public R queryDiscount(@RequestParam Map<String, Object> params){
    	String id = params.get("id") + "";
        BigDecimal discount = orderPaydetailService.discount(id);
        return R.ok().put("discount", discount);
    }
    /**
     * 转账明细查询
     * @param params
     * @return
     */
    @RequestMapping("/queryTransfer")
    public R queryTransfer(@RequestParam Map<String, Object> params){
    	List<OrderPaydetailEntity> list = orderPaydetailService.queryTransfer(params);
        return R.ok().put("list", list);
    }
    /**
     * 转账明细保存
     * @param params
     * @return
     */
    @RequestMapping("/saveTransfer")
    public R saveTransfer(@RequestParam List<OrderPaydetailEntity> list){
        orderPaydetailService.saveTransfer(list);
        return R.ok();
    }
}
