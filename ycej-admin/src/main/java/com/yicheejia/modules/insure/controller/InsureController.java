package com.yicheejia.modules.insure.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.insure.entity.InsureDetailEntity;
import com.yicheejia.modules.insure.entity.InsureEntity;
import com.yicheejia.modules.insure.excel.InsureBean;
import com.yicheejia.modules.insure.service.InsureDetailService;
import com.yicheejia.modules.insure.service.InsureService;
import com.yicheejia.modules.order.entity.YcejOrderEntity;


/**
 * 保险
 *
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
@RestController
@RequestMapping("order/insure")
public class InsureController {
    @Autowired
    private InsureService insureService;
    @Autowired
    private InsureDetailService insureDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:insure:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = insureService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:insure:info")
    public R info(@PathVariable("id") String id){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("id", id);
    	YcejOrderEntity insure = insureService.queryInsure(params);
        return R.ok().put("insure", insure);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:insure:save")
    public R save(@RequestBody InsureEntity insure){
			insureService.insertInsure(insure);
			//insureDetailService.insert(insureDetail);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:insure:update")
    public R update(@RequestBody InsureEntity insure){
			insureService.updateInsure(insure);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:insure:delete")
    public R delete(@RequestBody Integer[] insureIds){
			insureService.deleteBatchIds(Arrays.asList(insureIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("order:insure:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = insureService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "保险", page.getData(), InsureBean.class);
    }

}
