package com.yicheejia.modules.financialmanage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.yicheejia.modules.financialmanage.entity.ImportDataEntity;
import com.yicheejia.modules.financialmanage.entity.MoneyConfirmEntity;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceOrderPaydetailEntity;
import com.yicheejia.modules.financialmanage.excel.ImportPayDetailBean;
import com.yicheejia.modules.financialmanage.excel.OrderPaydetailBean;
import com.yicheejia.modules.financialmanage.service.ImportDataService;
import com.yicheejia.modules.financialmanage.service.YcejFinanceOrderPaydetailService;
import com.yicheejia.modules.order.entity.OrderFileEntity;
import com.yicheejia.modules.order.entity.OrderPaydetailEntity;
import com.yicheejia.modules.order.service.OrderFileService;
import com.yicheejia.modules.sys.entity.SysDictEntity;
import com.yicheejia.modules.sys.service.SysDictService;


/**
 * 支付明细表
 *
 * @author 梁占豪
 * @email 
 * @date 2018-07-08 10:29:31
 */
@RestController
@RequestMapping("financialmanage/orderpaydetail")
public class YcejFinanceOrderPaydetailController {
    @Autowired
    private YcejFinanceOrderPaydetailService orderPaydetailService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private OrderFileService orderFileService;
    @Autowired
    private ImportDataService importDataService;
    /**
     * 列表
     */
    @SysLog("账目核对列表")
    @RequestMapping("/list")
    @RequiresPermissions("paydetail:orderpaydetail:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = orderPaydetailService.queryPage(params);

        return page;
    }


    /**
     * 账目核对信息
     */
    @RequestMapping("/info/{orderPayId}")
    @RequiresPermissions("paydetail:orderpaydetail:info")
    public R info(@PathVariable("orderPayId") String orderPayId){
    	YcejFinanceOrderPaydetailEntity orderPaydetail = orderPaydetailService.selectById(orderPayId);

        return R.ok().put("orderPaydetail", orderPaydetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("paydetail:orderpaydetail:save")
    public R save(@RequestBody YcejFinanceOrderPaydetailEntity orderPaydetail){
			orderPaydetailService.insert(orderPaydetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("paydetail:orderpaydetail:update")
    public R update(@RequestBody YcejFinanceOrderPaydetailEntity orderPaydetail){
			orderPaydetailService.updateById(orderPaydetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("paydetail:orderpaydetail:delete")
    public R delete(@RequestBody String[] orderPayIds){
			orderPaydetailService.deleteBatchIds(Arrays.asList(orderPayIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @SysLog("账目核对导出")
    @RequestMapping("/export")
    @RequiresPermissions("paydetail:orderpaydetail:export")
    public void export(String[] ids, HttpServletResponse response) throws Exception {
        List<YcejFinanceOrderPaydetailEntity> list = orderPaydetailService.selectExportData(Arrays.asList(ids));

        ExcelUtils.exportExcelToTarget(response, "支付明细表", list, OrderPaydetailBean.class);
    }
    /**
     * 首页 确认收款
     */
    @SysLog("首页确认收款")
    @RequestMapping("/check")
    @Transactional
    @RequiresPermissions("paydetail:orderpaydetail:update")
    public R check(@RequestBody String[] orderPayIds,String remark){
    	orderPaydetailService.checkBatchIds(Arrays.asList(orderPayIds),remark);

        return R.ok();
    }
    /**
     * 导入POS流水,展示确认收款页面
    */
    @SysLog("导入POS流水")
    @RequestMapping("/inportpos")
    @Transactional
    @RequiresPermissions("paydetail:orderpaydetail:import")
//    @RequiresPermissions("paydetail:orderpaydetail:update")
    public R inportpos(@RequestParam(value="file",required=false) MultipartFile file,@RequestParam Map<String, Object> params, HttpServletRequest request) throws Exception {
    	Map<String, Object> map=new HashMap<>();
		try {
			map = orderPaydetailService.inportPos(file,params,request);
		} catch (Exception e) {
			throw new RRException(e.getMessage());
		}
        return R.ok().put("map", map);
    }
    /**
     * 在pos确认收款页面 进行确认收款操作
     * @date 2018-09-11
     * @author 梁占豪
     */
    @SysLog("POS确认收款")
    @RequestMapping("/posmoneyconfirm")
    @RequiresPermissions("paydetail:orderpaydetail:update")
    public R moneyConfirm(@RequestBody MoneyConfirmEntity moneyConfirmEntity){
    	Map<String, Object> map = new HashMap<>();
    	try {
    		Map<String, Object> m = orderPaydetailService.posMoneyConfirm(moneyConfirmEntity);
			map = (Map<String, Object>) m.get("m");
			if ((int)m.get("count")<=0) {
				throw new RRException("没有匹配数据!");
			}
		} catch (Exception e) {
			throw new RRException(e.getMessage());
		}
    	return R.ok().put("map", map);
    }
    /**
     * 在银行转账确认收款页面 进行确认收款操作
     * @date 2018-09-12
     * @author 梁占豪
     */
    @SysLog("银行确认收款")
    @RequestMapping("/bankmoneyconfirm")
    @RequiresPermissions("paydetail:orderpaydetail:update")
    public R bankMoneyConfirm(@RequestBody MoneyConfirmEntity moneyConfirmEntity){
    	Map<String, Object> map = new HashMap<>();
    	try {
			map=orderPaydetailService.bankMoneyConfirm(moneyConfirmEntity);
		} catch (Exception e) {
			throw new RRException(e.getMessage());
		}
    	return R.ok().put("map", map);
    }
    
    /**
     * 导入银行统一模板数据，展示银行确认收款页面
     */
    @SysLog("导入银行统一模板数据")
    @RequestMapping("/importbankdata")
    @Transactional
    @RequiresPermissions("paydetail:orderpaydetail:import")
    public R importBankData(@RequestParam(value="file",required=false) MultipartFile file,@RequestParam Map<String, Object> params, HttpServletRequest request) throws Exception {
    	Map<String, Object> map=new HashMap<>();
		try {
			map = orderPaydetailService.inportZcard(file,params,request);
		} catch (Exception e) {
			throw new RRException(e.getMessage());
		}
    	return R.ok().put("map", map);
    }
    /**
     * 导入POS流水
     */
    @SysLog("导入建行流水")
    @RequestMapping("/inportjcard")
    @Transactional
    @RequiresPermissions("paydetail:orderpaydetail:import")
    public R inportjcard(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request) throws Exception {
    	Map<String, Object> map=new HashMap<>();
		try {
			map = orderPaydetailService.inportJcard(file,request);
		} catch (Exception e) {
			throw new RRException(e.getMessage());
		}
    	return R.ok().put("map", map);
    }
    
    /**
     * 根据支付时间、支付方式获取收款总账及总账明细
     */
    @SysLog("查看收款总账")
    @RequestMapping("/gettotal")
    public R getTotal(@RequestParam Map<String, Object> params){
    	 Map<String, Object> list = orderPaydetailService.selectTotal(params);
    	 if (params.get("payDate") == null || "".equals(params.get("payDate"))) {
			list.put("pay_date", "");
		}
    	return R.ok().put("list", list);
    }
    @SysLog("查看收款总账明细")
    @RequestMapping("/gettotaldetail")
    public R getTotalDetail(@RequestParam Map<String, Object> params){
    	List<Map<String, Object>> list = new ArrayList<>();
    	
    	list = orderPaydetailService.selectTotalDetail(params);
    	return R.ok().put("list",list);
    }
    /**
     * 查看凭证
     */
    @SysLog("查看凭证")
    @RequestMapping("/viewproof")
    public R viewProog(@RequestParam Map<String, Object> params){
    	List<OrderFileEntity> list = orderFileService.queryList(params);
    	return R.ok().put("list", list);
    }
    /**
     * 根据支付明细id获取收款详情
     */
    @SysLog("查看收款详情")
    @RequestMapping("/selectpaymentdetail")
    public R selectPaymentDetail(@RequestParam Map<String, Object> params){
    	List<ImportDataEntity> list= importDataService.selectPaymentDetail(params);
    	return R.ok().put("list", list);
    }
    /**
     * 根据用户筛选条件在导入页面展示收款总账
     */
    @SysLog("系统收款总账")
    @RequestMapping("/totalforimport")
    public R selectTotalForImport(@RequestParam Map<String, Object> params){
    	Map<String, Object> map = new HashMap<>();
    	map = orderPaydetailService.selectTotalForImport(params);
    	return R.ok();
    }
    /**
     * 银行转账 确认收款页面 导入数据流水总账
     */
    @SysLog("银行转账导入数据总账")
    @RequestMapping("/banktotalbyimport")
    public R selectBankTotalByImport(@RequestParam Map<String, Object> params){
    	Map<String, Object> map = importDataService.selectBankTotalByImport(params);
    	return R.ok();
    }
    /**
     * 下载模板信息
     */
    @SysLog("下载转账模板")
    @RequestMapping(value = "/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response,HttpServletRequest request) {
    	try {
			ExcelUtils.downloadTemplate(response, request, "银行转账上传模板.xls");
		} catch (IOException e) {
			throw new RRException(e.getMessage());
		}  // 调用工具类的实现方法
    }
    
    /**
     * 银行转账页面进行名字搜索查询
     */
    @SysLog("账目核对列表")
    @RequestMapping("/selectlistforqueryname")
    @RequiresPermissions("paydetail:orderpaydetail:list")
    public LayuiPage selectListForQueryName(@RequestParam Map<String, Object> params){
        LayuiPage page = orderPaydetailService.queryPageForQueryName(params);

        return page;
    }
    /**
     * pos确认转账页面确认过后重载数据
     */
    @RequestMapping("/reloadposdata")
    @RequiresPermissions("paydetail:orderpaydetail:list")
    public R reloadPosData(@RequestParam Map<String, Object> params){
    	Map<String, Object> map = orderPaydetailService.selectReloadPosData(params);
    	return R.ok().put("map", map);
    }
    /**
     * 银行确认转账页面确认过后重载数据
     */
    @RequestMapping("/reloadbankdata")
    @RequiresPermissions("paydetail:orderpaydetail:list")
    public R reloadBankData(@RequestParam Map<String, Object> params){
    	Map<String, Object> map = orderPaydetailService.selectReloadBankData(params);
    	return R.ok().put("map", map);
    }
}
