package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yicheejia.common.annotation.ExecuteTask;
import com.yicheejia.common.annotation.StartFlow;
import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.operate.entity.AllotEntity;
import com.yicheejia.modules.operate.excel.AllotBean;
import com.yicheejia.modules.operate.service.AllotService;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.service.SysUserService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.upload.entity.YcejFileEntity;
import com.yicheejia.modules.upload.service.YcejFileService;
import com.yicheejia.workflow.service.ActivitiBaseService;


/**
 * 调拨记录表
 *
 * @author lw
 * @email 
 * @date 2018-07-18 16:37:00
 */
@RestController
@RequestMapping("operate/allot")
public class AllotController {
    @Autowired
    private AllotService allotService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
	private ActivitiBaseService activiti;
    @Autowired
    private YcejFileService ycejFileService;
	//日志
    protected Logger logger = LoggerFactory.getLogger(AllotController.class);

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:allot:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = allotService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{allotId}")
    @RequiresPermissions("operate:allot:info")
    public R info(@PathVariable("allotId") String allotId){
    	//获取当前登录用户名
    	String name = ShiroUtils.getUserEntity().getName();
		AllotEntity allot = allotService.selectById(allotId);
		//根据调拨状态判断传到页面的当前登录用户名，调拨状态1.暂存；2.已提交；3.调出方已确认；4.已完成
		int allotStatus = allot.getAllotStatus();
		if(allotStatus ==2) {
			allot.setAllotFromConfirmId(name);
		}else if(allotStatus ==3) {
			allot.setAllotToConfirmId(name);
		}
//		allot.setAllotFromConfirmId(name);
        return R.ok().put("allot", allot);
    }

    /**
     * 新增保存/暂存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:allot:save")
    public R save(@RequestBody AllotEntity allot){
    	if(null != allot.getVinList() && allot.getVinList().size() > 0) {
    		allot.setVin(allot.getVinList().get(0));
	    	allot.setAllotStatus(1);
	    	allot.setInsertTime(new Date());
			allotService.insert(allot);
    	}
    	return R.ok();
    }

    /**
     * 修改并暂存
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:allot:save")
    public R update(@RequestBody AllotEntity allot){
    	if(null != allot.getVinList()) {
    		allot.setVin(allot.getVinList().get(0));
    		allot.setAllotStatus(1);
    		allot.setInsertTime(new Date());
			allotService.updateById(allot);
    	}
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除调拨记录")
    @RequestMapping("/delete")
    @RequiresPermissions("operate:allot:delete")
    public R delete(@RequestBody Integer[] allotIds){
			allotService.deleteBatchIds(Arrays.asList(allotIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:allot:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = allotService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "调拨记录表", page.getData(), AllotBean.class);
    }
    
    /**
     * 调出确认
     * @param
     * @return
     */
    @SysLog("调出方确认")
    @ExecuteTask(id = "allotId",processKey = WfConstants.PROCESS_ALLOT_KEY)
    @RequestMapping("/updateAllotFrom")
    @Transactional
    public R updateAllotFrom(@RequestBody AllotEntity allot){
    	List<String> allotToList = null ;
    	try {
    		String id = allot.getAllotId()+"";
    		String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
         	if(!activiti.getUserHaveTasksUser(userId,id)){
     			throw new RRException("暂无操作权限！");
         	}
			allotService.updateAllotFrom(allot);
	    	//查询调入方（下一流程节点）的门店ID
	    	String shopId = allotService.getAllotToShopId(allot);
	    	//查询 下一流程节点user
	    	allotToList = sysUserService.queryUserByRoleAndShopIdTo(shopId);
    	}catch(Exception e) {
    		throw new RRException(e.getMessage());
    	}
    	return R.ok().put(WfConstants.USER, allotToList);
    }

    /**
     * 调入确认
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @ExecuteTask(id = "allotId",processKey=WfConstants.PROCESS_ALLOT_KEY)
    @RequestMapping("/updateAllotTo")
    @SysLog("调入方确认")
//    @RequiresPermissions("operate:allot:update")
    public R updateAllotTo(@RequestBody AllotEntity allot){
    	String id = allot.getAllotId()+"";
        String userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
    	if(!activiti.getUserHaveTasksUser(userId,id)){
			throw new RRException("暂无操作权限！");
		}
    	allotService.updateAllotTo(allot);
        return R.ok();
    }
    
    /**
     * 新增提交
     * @param allot
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @StartFlow(id="allotId",processKey=WfConstants.PROCESS_ALLOT_KEY)
    @SysLog("提交调拨单")
    @RequestMapping("/submitSave")
    public R submitSave(@RequestBody AllotEntity allot){
    	try {
    		allotService.submitSave(allot);
        	String allotId = allot.getAllotId();
        	//查询含有确认方门店权限的userList(需根据角色过滤)
        	Integer dataFilter = ShiroUtils.getUserEntity().getDataFilter();
        	List<String> user = sysUserService.queryUserByRoleAndShopIdTo(allot.getAllotFrom().toString());
        	if(user.size()>0) {
        		dataFilter = 1;
        	}
        	List<String> allotFromList = sysUserService.queryUserByRoleAndShopId(allot.getAllotFrom().toString(),dataFilter);
        	logger.info("调出方人员：");
        	logger.info(allotFromList.toString());
            return R.ok().put(WfConstants.USER, allotFromList);
    	}catch(Exception e) {
    		e.printStackTrace();
			logger.debug("数据保存异常！"+e.getMessage(),e);
			throw new RRException("插入错误！");
    	}
    }
    
    /**
     * 已暂存提交
     * @param allot
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @StartFlow(id="allotId",processKey=WfConstants.PROCESS_ALLOT_KEY)
    @RequestMapping("/submitUpdate")
    @SysLog("提交调拨单")
    public R submitUpdate(@RequestBody AllotEntity allot){
    	try {
    		Integer dataFilter = ShiroUtils.getUserEntity().getDataFilter();
//    		String allotId = allot.getAllotId();
    		allotService.submitUpdate(allot);
        	//查询含有确认方门店权限的userList(需根据角色过滤)
        	List<String> allotFromList = sysUserService.queryUserByRoleAndShopId(allot.getAllotFrom().toString(),dataFilter);
            return R.ok().put(WfConstants.USER, allotFromList);
    	}catch(Exception e) {
    		e.printStackTrace();
			logger.debug("数据保存异常（submitUpdate）！"+e.getMessage(),e);
			throw new RRException("调拨错误！");
    	}
    }
    
    /**
     * 上传车架号照片
     */
    @SysLog("上传车架号照片")
    @Transactional
    @RequestMapping("/upload")
    public R upload(@RequestParam(value="file",required=false) MultipartFile file,String allotId,String branch,HttpServletRequest request){
    	YcejFileEntity ycejFileEntity=new YcejFileEntity();
    	Map<String, Object> params=new HashMap<>();
    	try {
	    	params.put("customerId",allotId);
	    	params.put("branch",branch);
				// 判断文件是否为空
				if (!file.isEmpty()) {
					//将文件保存到附件表中并返回主键ID
					ycejFileEntity=ycejFileService.insertReturnId(file,request,params);
					//将附件主键保存到调拨表中
					AllotEntity allotEntity = new AllotEntity();
					allotEntity.setAllotId(allotId);
					allotEntity.setFileId(ycejFileEntity.getFileId());
					//获取文件路径保存到调拨表
					YcejFileEntity fileEntity=ycejFileService.selectById(allotEntity.getFileId());
					allotEntity.setVinPhotoAdd(fileEntity.getFilePath());
					allotService.updateById(allotEntity);
				}
			} catch (Exception e) {
				logger.error("文件处理异常",e);
				logger.error(e.getMessage(),e);
				throw new RRException(e.getMessage());
			}
    	
    	return R.ok().put("ycejFileEntity", ycejFileEntity);
    }
    
    /**
     * 查看图片
     */
    @SysLog("查看车辆合格证")
    @RequestMapping("/view")
    public R viewPhoto(String allotId){
    	//根据调拨表主键获取合格证路径
    	AllotEntity allotEntity = allotService.selectById(allotId);

    	return R.ok().put("path", allotEntity.getCertificateAdd());
    }
    
    /**
     * 查看图片
     */
    @SysLog("查看车架号照片")
    @RequestMapping("/viewVin")
    public R viewPhotoVin(String allotId){
    	//根据调拨表主键获取附件ID
    	AllotEntity allotEntity = allotService.selectById(allotId);
    	//根据附件主键获取该条附件信息
    	YcejFileEntity ycejFileEntity = ycejFileService.selectById(allotEntity.getFileId());
    	return R.ok().put("path", ycejFileEntity.getFilePath());
    }
    
}
