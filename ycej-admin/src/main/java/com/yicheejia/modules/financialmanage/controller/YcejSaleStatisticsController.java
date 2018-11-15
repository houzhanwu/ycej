package com.yicheejia.modules.financialmanage.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.financialmanage.entity.YcejSaleStatisticsEntity;
import com.yicheejia.modules.financialmanage.excel.SaleStatisticsBean;
import com.yicheejia.modules.financialmanage.service.YcejSaleStatisticsService;

/**
 * 财务--销售统计
 * @author 梁占豪
 * @email zr_liangzhanhao@163.com
 * @since 2018-07-04
 * 
 */
@RestController
@RequestMapping("financialmanage/salestatistics")
public class YcejSaleStatisticsController {
	@Autowired
    private YcejSaleStatisticsService ycejSaleStatisticsService;
	
	@SysLog("销售报表查询列表")
    @RequestMapping("/list")
    @RequiresPermissions("financialmanage:salestatistics:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = ycejSaleStatisticsService.queryPage(params);

        return page;
    }
	
	/**
     * 导出
    */
    @SysLog("销售报表导出")
    @RequestMapping("/export")
    @RequiresPermissions("financialmanage:salestatistics:export")
    public void export(Long[] ids, HttpServletResponse response) throws Exception {
        List<YcejSaleStatisticsEntity> list = ycejSaleStatisticsService.selectExportData(ids);
        ExcelUtils.exportExcelToTarget(response, "销售报表", list, SaleStatisticsBean.class);
    }
	
}
