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

import com.yicheejia.modules.order.excel.OrderFileBean;
import com.yicheejia.modules.order.entity.OrderFileEntity;
import com.yicheejia.modules.order.service.OrderFileService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author fkm
 * @email 
 * @date 2018-07-11 14:16:58
 */
@RestController
@RequestMapping("order/orderfile")
public class OrderFileController {
    @Autowired
    private OrderFileService orderFileService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:orderfile:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = orderFileService.queryPage(params);

        return page;
    }
    /**
     * 根据订单查询附件列表
     */
    @RequestMapping("/queryList")
    //@RequiresPermissions("order:orderfile:list")
    public R queryList(@RequestParam Map<String, Object> params){
    	List<OrderFileEntity> list = orderFileService.queryList(params);

        return R.ok().put("orderFileList", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{orderFileId}")
    @RequiresPermissions("order:orderfile:info")
    public R info(@PathVariable("orderFileId") Integer orderFileId){
			OrderFileEntity orderFile = orderFileService.selectById(orderFileId);

        return R.ok().put("orderFile", orderFile);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:orderfile:save")
    public R save(@RequestBody OrderFileEntity orderFile){
			orderFileService.insert(orderFile);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:orderfile:update")
    public R update(@RequestBody OrderFileEntity orderFile){
			orderFileService.updateById(orderFile);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:orderfile:delete")
    public R delete(@RequestBody Integer[] orderFileIds){
			orderFileService.deleteBatchIds(Arrays.asList(orderFileIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("order:orderfile:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = orderFileService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), OrderFileBean.class);
    }

}
