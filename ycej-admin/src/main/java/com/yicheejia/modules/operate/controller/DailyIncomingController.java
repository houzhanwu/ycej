package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yicheejia.modules.operate.excel.DailyIncomingBean;
import com.yicheejia.modules.operate.entity.DailyIncomingEntity;
import com.yicheejia.modules.operate.entity.IncomingFileEntity;
import com.yicheejia.modules.operate.entity.SupplierEntity;
import com.yicheejia.modules.operate.service.DailyIncomingService;
import com.yicheejia.modules.operate.service.IncomingFileService;
import com.yicheejia.modules.operate.service.SupplierService;
import com.yicheejia.modules.shop.entity.YcejShopEntity;
import com.yicheejia.modules.shop.service.YcejShopService;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.upload.entity.YcejFileEntity;
import com.yicheejia.modules.upload.service.YcejFileService;
import com.yicheejia.workflow.service.ActivitiBaseService;
import com.yicheejia.common.utils.LayuiPage;
import com.alibaba.druid.util.StringUtils;
import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.common.annotation.StartFlow;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author 
 * @email 
 * @date 2018-11-06 10:37:14
 */
@RestController
@RequestMapping("operate/dailyincoming")
public class DailyIncomingController {
    @Autowired
    private DailyIncomingService dailyIncomingService;
    @Autowired
    private YcejShopService ycejShopService;
    @Autowired
	private YcejFileService ycejFileService;
    @Autowired
    private IncomingFileService incomingFileService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
	private ActivitiBaseService activiti;
    
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:dailyincoming:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = dailyIncomingService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("operate:dailyincoming:info")
    public R info(@PathVariable("id") Long id){
			DailyIncomingEntity dailyIncoming = dailyIncomingService.selectById(id);

        return R.ok().put("dailyIncoming", dailyIncoming);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:dailyincoming:save")
    public R save(@RequestBody DailyIncomingEntity dailyIncoming){
    	dailyIncoming.setInsertUser(ShiroUtils.getUserEntity().getName());
    	dailyIncoming.setInsertTime(DateUtils.getDate());
    	dailyIncoming.setUpdateUser(ShiroUtils.getUserEntity().getName());
		dailyIncoming.setUpdateTime(DateUtils.getDate());
		dailyIncomingService.insert(dailyIncoming);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:dailyincoming:update")
    public R update(@RequestBody DailyIncomingEntity dailyIncoming){
    		dailyIncoming.setUpdateUser(ShiroUtils.getUserEntity().getName());
    		dailyIncoming.setUpdateTime(DateUtils.getDate());
			dailyIncomingService.updateById(dailyIncoming);

        return R.ok();
    }
    /**
     * 新增提交
     */
    @RequestMapping("/submit")
    @RequiresPermissions("operate:dailyincoming:update")
    @StartFlow(id="id",processKey = WfConstants.PROCESS_DAILY_INCOMING_KEY)
    public R submit(@RequestBody DailyIncomingEntity dailyIncoming){
    	//判断id是否为空,不为空为修改提交，否则为新增提交
    	if (dailyIncoming.getId()!=null) {
    		dailyIncoming.setUpdateUser(ShiroUtils.getUserEntity().getName());
    		dailyIncoming.setUpdateTime(DateUtils.getDate());
    		dailyIncomingService.updateById(dailyIncoming);
		}else {
			dailyIncoming.setInsertUser(ShiroUtils.getUserEntity().getName());
	    	dailyIncoming.setInsertTime(DateUtils.getDate());
	    	dailyIncoming.setUpdateUser(ShiroUtils.getUserEntity().getName());
    		dailyIncoming.setUpdateTime(DateUtils.getDate());
			dailyIncomingService.insert(dailyIncoming);
		}
    	
    	return R.ok().put(WfConstants.REMARK, dailyIncoming.getComment());
    }
    /**
     * 审核提交
     */
    @RequestMapping("/resourcecheck")
    @RequiresPermissions("operate:dailyincoming:check")
    @ExecuteTask(id="id",processKey = WfConstants.PROCESS_DAILY_INCOMING_KEY)
    public R resourceCheck(@RequestBody DailyIncomingEntity dailyIncoming){
    	String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
    	if(!activiti.getUserHaveTasksUser(userId,dailyIncoming.getId())){
			throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
		}
    	dailyIncoming.setUpdateUser(ShiroUtils.getUserEntity().getName());
		dailyIncoming.setUpdateTime(DateUtils.getDate());
    	dailyIncomingService.updateById(dailyIncoming);
    	
    	return R.ok().put(WfConstants.ACTION,"1").put(WfConstants.REMARK, dailyIncoming.getComment());
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:dailyincoming:delete")
    public R delete(@RequestBody Long[] ids){
			dailyIncomingService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:dailyincoming:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = dailyIncomingService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), DailyIncomingBean.class);
    }
    /**
     * 获取所有门店
     */
    @RequestMapping("/getallshop")
    public LayuiPage getAllShop(@RequestParam Map<String, Object> params){
        LayuiPage page = ycejShopService.selectAllForDailyIncoming(params);
        
        return page;
    }
    /**
     * 根据shopid获取信息
     */
    @RequestMapping("/getshopinfo")
    public R getShopInfo(@RequestParam Map<String, Object> params){
    	YcejShopEntity ycejShopEntity = null;
			ycejShopEntity = ycejShopService.selectShopInfoById(params);
    	return R.ok().put("ycejShopEntity", ycejShopEntity);
    }
    /**
     * 上传附件
     */
    @RequestMapping("/uploadbill")
    public R uploadBill(@RequestParam(value="file",required=false) MultipartFile file,@RequestParam Map<String, Object> params,
			HttpServletRequest request){
    	try {		
				// 判断文件是否为空
				if (!file.isEmpty()) {
					//将文件保存到附件表中并返回主键ID
					YcejFileEntity ycejFileEntity=ycejFileService.insertReturnId(file,request,params);
					///String customerId = params.get("customerId").toString();
					//将业务数据的ID，附件表ID保存到业务附件表中
					incomingFileService.insertReturnId(ycejFileEntity,params);//订单附件表
					return R.ok().put("filePath",ycejFileEntity.getFilePath()).put("fileId",ycejFileEntity.getFileId());
				}
			} catch (Exception e) {
				logger.error("文件处理异常",e);
				logger.error(e.getMessage(),e);
				throw new RRException(e.getMessage());
			}
    	
    	return R.ok();
    }
    /**
     * 附件预览
     */
    @RequestMapping("/previewbill")
    public R previewBill(@RequestParam Map<String, Object> params){
    	List<IncomingFileEntity> list = incomingFileService.queryList(params);
    	
    	return R.ok().put("fileList", list);
    }
    /**
     * 查询审核通过的供应商列表
     */
    @RequestMapping("/querySupplierList")
    public R querySupplierList(@RequestParam Map<String, Object> params){
    	
    	LayuiPage supplierList = supplierService.getSupplierListForCaution(params);

        return R.ok().put("supplierList", supplierList.getData());
    }
    /**
     * 审核退回
     */
    @RequestMapping("/returnBack")
    @RequiresPermissions("operate:dailyincoming:returnback")
    @ExecuteTask(id="id",processKey = WfConstants.PROCESS_DAILY_INCOMING_KEY)
    public R returnBack(@RequestBody DailyIncomingEntity dailyIncoming){
    	dailyIncoming.setUpdateUser(ShiroUtils.getUserEntity().getName());
		dailyIncoming.setUpdateTime(DateUtils.getDate());
		dailyIncomingService.updateById(dailyIncoming);
    	return R.ok().put(WfConstants.ACTION,"0").put(WfConstants.REMARK, dailyIncoming.getComment());
    }
}
