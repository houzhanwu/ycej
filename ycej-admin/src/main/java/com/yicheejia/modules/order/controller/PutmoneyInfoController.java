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

import com.yicheejia.modules.order.excel.PutmoneyInfoBean;
import com.yicheejia.modules.order.entity.PutmoneyInfoEntity;
import com.yicheejia.modules.order.service.PutmoneyInfoService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-10-12 15:12:37
 */
@RestController
@RequestMapping("order/putmoneyinfo")
public class PutmoneyInfoController {
    @Autowired
    private PutmoneyInfoService putmoneyInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:putmoneyinfo:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = putmoneyInfoService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{putmoneyInfoId}")
    @RequiresPermissions("order:putmoneyinfo:info")
    public R info(@PathVariable("putmoneyInfoId") Long putmoneyInfoId){
			PutmoneyInfoEntity putmoneyInfo = putmoneyInfoService.selectById(putmoneyInfoId);

        return R.ok().put("putmoneyInfo", putmoneyInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:putmoneyinfo:save")
    public R save(@RequestBody PutmoneyInfoEntity putmoneyInfo){
			putmoneyInfoService.insert(putmoneyInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:putmoneyinfo:update")
    public R update(@RequestBody PutmoneyInfoEntity putmoneyInfo){
			putmoneyInfoService.updateById(putmoneyInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:putmoneyinfo:delete")
    public R delete(@RequestBody Long[] putmoneyInfoIds){
			putmoneyInfoService.deleteBatchIds(Arrays.asList(putmoneyInfoIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("order:putmoneyinfo:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = putmoneyInfoService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), PutmoneyInfoBean.class);
    }
    /**
     * 根据身份证号码获取放款信息
     */
    @RequestMapping("/getinfo")
    public R getPutMoneyInfo(@RequestParam Map<String, Object> params){
    	PutmoneyInfoEntity putmoneyInfo = putmoneyInfoService.selectInfoByCondition(params);
    	return R.ok().put("putmoneyInfo", putmoneyInfo);
    }

}
