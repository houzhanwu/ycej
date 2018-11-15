package com.yicheejia.modules.financialmanage.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.yicheejia.modules.financialmanage.entity.YcejFinanceRebateEntity;
import com.yicheejia.modules.financialmanage.excel.CostStatisticsBean;
import com.yicheejia.modules.financialmanage.excel.YcejFinanceRebateBean;
import com.yicheejia.modules.financialmanage.service.YcejFinanceRebateService;

/**
 * 返佣报表
 * @author 梁占豪
 *
 */
@RestController
@RequestMapping("financialmanage/rebate")
public class YcejFinanceRebateController {
	
	@Autowired
	private YcejFinanceRebateService ycejFinanceRebateService;
	@RequestMapping("/list")
	@RequiresPermissions("financialmanage:rebate:list")
	public LayuiPage list(@RequestParam Map<String, Object> params){
		LayuiPage page =  ycejFinanceRebateService.queryPage(params);
		return page;
	}
	//导出
	@SysLog("返佣报表导出")
	@RequestMapping("/export")
	@RequiresPermissions("financialmanage:rebate:export")
	public void export(Long[] ids, HttpServletResponse response) throws Exception {
		List<YcejFinanceRebateEntity> list =  ycejFinanceRebateService.selectExportData(ids);
		ExcelUtils.exportExcelToTarget(response, "返佣报表", list, YcejFinanceRebateBean.class);
	}
}
