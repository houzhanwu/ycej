package com.yicheejia.modules.insure.controller;

import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.yicheejia.modules.insure.excel.InsureDetailBean;
import com.yicheejia.modules.insure.service.InsureDetailService;
import com.yicheejia.modules.insure.service.InsureService;


/**
 * 保险明细表
 *
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
@RestController
@RequestMapping("order/insuredetail")
public class InsureDetailController {
    @Autowired
    private InsureDetailService insureDetailService;
    @Autowired
    private InsureService insureService;
    //日志
    protected Logger logger = LoggerFactory.getLogger(InsureDetailController.class);
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:insuredetail:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = insureDetailService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{insureDetailId}")
    @RequiresPermissions("order:insuredetail:info")
    public R info(@PathVariable("insureDetailId") String insureDetailId){
			InsureDetailEntity insureDetail = insureDetailService.selectById(insureDetailId);

        return R.ok().put("insureDetail", insureDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:insuredetail:save")
    public R save(@RequestBody InsureDetailEntity insureDetail){
    		InsureEntity insure = insureDetail.getInsureEntity();
    		if(StringUtils.isNotBlank(insureDetail.getInsureId())){
    			insureDetailService.updateDetail(insureDetail);
    		}else{
    			insureDetailService.insertDetail(insureDetail);
    		}
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:insuredetail:update")
    public R update(@RequestBody InsureDetailEntity insureDetail){
			insureDetailService.updateDetail(insureDetail);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:insuredetail:delete")
    public R delete(@RequestBody Integer[] insureDetailIds){
			insureDetailService.deleteBatchIds(Arrays.asList(insureDetailIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("order:insuredetail:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = insureDetailService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "保险明细表", page.getData(), InsureDetailBean.class);
    }
    /**
     * 根据订单主键查询保险与保险明细
     * @param params
     * @return
     */
    @RequestMapping("/queryDetail")
    @RequiresPermissions("order:insuredetail:info")
    public R queryDetail(@RequestParam Map<String, Object> params){
    	InsureDetailEntity insureDetail = insureDetailService.queryDetail(params);

        return R.ok().put("detail", insureDetail);
    }
    /**
     * 查询保险是否已出
     * @param params
     * @return
     */
    @RequestMapping("/queryInsure")
    @RequiresPermissions("order:insuredetail:info")
    public R queryInsure(@RequestParam Map<String, Object> params){
    	InsureDetailEntity insureDetail = insureDetailService.queryDetail(params);

        return R.ok().put("detail", insureDetail);
    }
}
