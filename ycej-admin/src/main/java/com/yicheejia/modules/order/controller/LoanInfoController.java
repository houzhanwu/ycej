package com.yicheejia.modules.order.controller;

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

import com.yicheejia.modules.order.excel.LoanInfoBean;
import com.yicheejia.modules.order.entity.LoanInfoEntity;
import com.yicheejia.modules.order.service.LoanInfoService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-10 17:32:57
 */
@RestController
@RequestMapping("order/loaninfo")
public class LoanInfoController {
    @Autowired
    private LoanInfoService loanInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:loaninfo:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = loanInfoService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{loanInfoId}")
    @RequiresPermissions("order:loaninfo:info")
    public R info(@PathVariable("loanInfoId") String loanInfoId){
			LoanInfoEntity loanInfo = loanInfoService.selectById(loanInfoId);

        return R.ok().put("loanInfo", loanInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:loaninfo:save")
    public R save(@RequestBody LoanInfoEntity loanInfo){
			loanInfoService.insert(loanInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:loaninfo:update")
    public R update(@RequestBody LoanInfoEntity loanInfo){
			loanInfoService.updateById(loanInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:loaninfo:delete")
    public R delete(@RequestBody String[] loanInfoIds){
			loanInfoService.deleteBatchIds(Arrays.asList(loanInfoIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("order:loaninfo:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = loanInfoService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), LoanInfoBean.class);
    }
    /**
     * 根据定时任务更新的订单主键，查询贷款信息
     */
    @RequestMapping("/selectInfo")
    @RequiresPermissions("order:loaninfo:info")
    public R selectInfo(@RequestParam Map<String, Object> params){
			LoanInfoEntity loanInfo = loanInfoService.selectLoaninfo(params);
        return R.ok().put("loanInfo", loanInfo);
    }
}
