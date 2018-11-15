package com.yicheejia.modules.financialmanage.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceCheckEntity;
import com.yicheejia.modules.financialmanage.service.YcejFinanceCheckService;



/**
 * 
 *
 * @author liangzhanhao
 * @email zr_liangzhanhao@163.com
 * @date 2018-06-24 10:54:54
 */
@RestController
@RequestMapping("sys/financialmanage")
public class YcejFinanceCheckController {
    @Autowired
    private YcejFinanceCheckService ycejFinanceCheckService;
    /**
     * 财务对账列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:financialmanage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ycejFinanceCheckService.queryPage(params);
        
        return R.ok().put("page", page);
    }
    /**
     * 财务对账明细列表
     */
    @RequestMapping("/detaillist")
    @RequiresPermissions("sys:financialmanage:detaillist")
    public R detailList(@RequestParam Map<String, Object> params){
    	PageUtils page = ycejFinanceCheckService.queryDetailPage(params);
    	
    	return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:financialmanage:info")
    public R info(@PathVariable("id") Integer id){
			YcejFinanceCheckEntity ycejFinanceCheck = ycejFinanceCheckService.selectById(id);

        return R.ok().put("ycejFinanceCheck", ycejFinanceCheck);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:financialmanage:save")
    public R save(@RequestBody YcejFinanceCheckEntity ycejFinanceCheck){
			ycejFinanceCheckService.insert(ycejFinanceCheck);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:financialmanage:update")
    public R update(@RequestBody YcejFinanceCheckEntity ycejFinanceCheck){
			ycejFinanceCheckService.updateById(ycejFinanceCheck);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:financialmanage:delete")
    public R delete(@RequestBody Integer[] ids){
			ycejFinanceCheckService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * 对账
     */
    @RequestMapping("/check")
    @RequiresPermissions("sys:financialmanage:check")
    public R check(@RequestBody Integer[] ids){
    	ycejFinanceCheckService.checkByIds(Arrays.asList(ids));
    	
    	return R.ok();
    }
/*    @RequestMapping("/check")
    @RequiresPermissions("sys:financialmanage:check")
    public R check(@RequestBody Integer[] ids){
    	ycejFinanceCheckService.checkByIds(Arrays.asList(ids));
    	
    	return R.ok();
    }
*/    
    
    /**
     * 核实订单
     */
    @RequestMapping("/accountcheck")
    @RequiresPermissions("sys:financialmanage:accountcheck")
    public R accountCheck(@RequestBody Integer[] ids){
    	ycejFinanceCheckService.accountCheckByIds(Arrays.asList(ids));
    	
    	return R.ok();
    }
    
    /**
     * 退回
     */
    @RequestMapping("/returnback")
    @RequiresPermissions("sys:financialmanage:returnback")
    public R returnBack(@RequestBody Integer[] ids){
    	ycejFinanceCheckService.returnByIds(Arrays.asList(ids));
    	
    	return R.ok();
    }

    /**
     * 财务对账导出
     */
    @RequestMapping("/checkexport")
    @RequiresPermissions("sys:financialmanage:checkexport")
    public void checkExport(HttpServletRequest request, HttpServletResponse response,Integer[] ids){
//    	String ids=request.getParameter("ids");
    	List<YcejFinanceCheckEntity> userlList = ycejFinanceCheckService.getDataForExcel(Arrays.asList(ids));
    	LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
    	fieldMap.put("belongShop","所属门店");
    	fieldMap.put("customer_name","买车人姓名");
    	fieldMap.put("contact_information","联系方式");
    	fieldMap.put("carBrand","品牌");
    	fieldMap.put("carSeries","车系");
    	fieldMap.put("carModel","车型");
    	fieldMap.put("vin","vin码");
    	fieldMap.put("sell_price","售价");
    	fieldMap.put("sell_type","销售类型");
    	fieldMap.put("appoint_pay_type","定金支付方式");
    	fieldMap.put("appoint_time","定金支付时间");
    	fieldMap.put("appoint_money","定金");
    	fieldMap.put("down_pay_type","首付支付方式");
    	fieldMap.put("down_time","首付时间");
    	fieldMap.put("down_payment","首付");
    	fieldMap.put("insurance","保险");
    	fieldMap.put("renew_deposit","续保押金/优壹车保证金");
    	fieldMap.put("car_license_fee","上牌费");
    	fieldMap.put("handling_charge","手续费");
    	fieldMap.put("shop_protected_detail","店保详情");
    	fieldMap.put("shop_protected_fee","店保费用");
    	fieldMap.put("status","状态");
    	String excelName = "财务对账表";
//    	ExcelUtil.export(excelName, userlList, fieldMap, response);
    }
}
