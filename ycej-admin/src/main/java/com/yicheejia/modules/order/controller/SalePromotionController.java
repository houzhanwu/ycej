package com.yicheejia.modules.order.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.order.entity.SalePromotionEntity;
import com.yicheejia.modules.order.excel.SalePromotionBean;
import com.yicheejia.modules.order.service.SalePromotionService;


/**
 * 活动代金券信息表
 *
 * @author 
 * @email 
 * @date 2018-10-26 13:44:25
 */
@RestController
@RequestMapping("order/salepromotion")
public class SalePromotionController {
    @Autowired
    private SalePromotionService salePromotionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:salepromotion:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = salePromotionService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{saleId}")
    @RequiresPermissions("order:salepromotion:info")
    public R info(@PathVariable("saleId") Long saleId){
			SalePromotionEntity salePromotion = salePromotionService.selectById(saleId);

        return R.ok().put("salePromotion", salePromotion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:salepromotion:save")
    public R save(@RequestBody SalePromotionEntity salePromotion){
			salePromotionService.insert(salePromotion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:salepromotion:update")
    public R update(@RequestBody SalePromotionEntity salePromotion){
			salePromotionService.updateById(salePromotion);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:salepromotion:delete")
    public R delete(@RequestBody Long[] saleIds){
			salePromotionService.deleteBatchIds(Arrays.asList(saleIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("order:salepromotion:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = salePromotionService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "活动代金券信息表", page.getData(), SalePromotionBean.class);
    }
    
    /**
     * 导入营销活动订单
    */
    @SysLog("导入营销活动订单")
    @RequestMapping("/inportsaleorder")
    @Transactional
//    @RequiresPermissions("order:salepromotion:import")
    public R inportsaleorder(@RequestParam(value="file",required=false) MultipartFile file) throws Exception {
//    	Map<String, Object> map=new HashMap<>();
		try {
	        String fileName = file.getOriginalFilename();
			salePromotionService.inportsaleorder(fileName,file);
		} catch (Exception e) {
			throw new RRException(e.getMessage());
		}
        return R.ok();
    }
    
    @SysLog("修改代金券为已使用状态")
    @RequestMapping("/updateUsed")
    public R updateUsed(@RequestParam Map<String, Object> params) {
    	
    	salePromotionService.updateBySaleId(params);
    	
    	return R.ok();
    }
    
    /**
     * 获取所有（非智跑）优惠券信息
     * @return
     */
    @RequestMapping("/getPromotionList")
    public R selectList(@RequestParam Map<String, Object> params){
        List<Map<String,Object>>  promotionList = salePromotionService.getPromotionList(params);
        return R.ok().put("promotionList",promotionList);
    }
    /**
     * 获取智跑优惠券信息
     * @return
     */
    @RequestMapping("/getZPPromotionList")
	public R selectZPList(@RequestParam Map<String, Object> params){
		List<Map<String,Object>>  ZPpromotionList = salePromotionService.getZPPromotionList(params);
		return R.ok().put("ZPpromotionList",ZPpromotionList);
	}
    /**
     * 通过订单号查询优惠券信息
     * @return
     */
    @RequestMapping("/getPromotionListByOrderId")
    public R promotionListByOrderId(@RequestParam Map<String, Object> params){
        List<Map<String,Object>> promotions = salePromotionService.getPromotionListByOrderId(params);
        return R.ok().put("promotions",promotions);
    }
}
