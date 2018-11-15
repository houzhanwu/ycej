package com.yicheejia.modules.financialmanage.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceCostEntity;
import com.yicheejia.modules.financialmanage.excel.CostStatisticsBean;
import com.yicheejia.modules.financialmanage.service.YcejFinanceCostService;

/**
 * 财务-成本统计
 * @author 梁占豪
 * @since 2018-7-15
 */
@RestController
@RequestMapping("financialmanage/cost")
public class YcejFinanceCostController {
	@Autowired
	private YcejFinanceCostService ycejFinanceCostService;
	 @SysLog("成本统计")
	 @RequestMapping("/list")
	 @RequiresPermissions("financialmanage:coststatistics:list")
	 public LayuiPage list(@RequestParam Map<String, Object> params){
		 LayuiPage page =ycejFinanceCostService.queryPage(params);
		 return page;
	 }
	 
	 @SysLog("成本统计导出")
	 @RequestMapping("/export")
	 @RequiresPermissions("financialmanage:coststatistics:export")
	 public void export(Long[] ids, HttpServletResponse response) throws Exception {
		 List<YcejFinanceCostEntity> list = ycejFinanceCostService.selectExportData(ids);
		 ExcelUtils.exportExcelToTarget(response, "成本统计",list, CostStatisticsBean.class);
	 }
}
