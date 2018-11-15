package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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

import com.yicheejia.modules.operate.excel.PurchaseDetailBean;
import com.yicheejia.modules.operate.entity.PurchaseDetailEntity;
import com.yicheejia.modules.operate.service.PurchaseDetailService;
import com.yicheejia.modules.sys.entity.SysRoleEntity;
import com.yicheejia.modules.sys.entity.SysUserRoleEntity;
import com.yicheejia.modules.sys.service.SysRoleService;
import com.yicheejia.modules.sys.service.SysUserRoleService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.upload.entity.YcejFileEntity;
import com.yicheejia.modules.upload.service.YcejFileService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-18 16:43:36
 */
@RestController
@RequestMapping("operate/purchasedetail")
public class PurchaseDetailController {
    @Autowired
    private PurchaseDetailService purchaseDetailService;
    @Autowired
    private YcejFileService ycejFileService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 列表
     */
    @SysLog("查看采购明细")
    @RequestMapping("/list")
    @RequiresPermissions("operate:carpurchase:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = purchaseDetailService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @SysLog("展示采购明细信息")
    @RequestMapping("/info/{purchDetailId}")
    @RequiresPermissions("operate:carpurchase:info")
    public R info(@PathVariable("purchDetailId") String purchDetailId){
			PurchaseDetailEntity purchaseDetail = purchaseDetailService.selectById(purchDetailId);

        return R.ok().put("purchaseDetail", purchaseDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:carpurchase:save")
    public R save(@RequestBody PurchaseDetailEntity purchaseDetail){
			purchaseDetailService.insert(purchaseDetail);

        return R.ok().put("purchaseDetail", purchaseDetail);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:carpurchase:update")
    public R update(@RequestBody PurchaseDetailEntity purchaseDetail){
			purchaseDetailService.updateById(purchaseDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] purchDetailIds){
			purchaseDetailService.deleteBatchIds(Arrays.asList(purchDetailIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:purchasedetail:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = purchaseDetailService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), PurchaseDetailBean.class);
    }
    
    /**
     * 上传合格证
     */
    @SysLog("上传车辆合格证")
    @Transactional
    @RequestMapping("/upload")
    @RequiresPermissions("operate:carpurchase:list")
    public R upload(@RequestParam(value="file",required=false) MultipartFile file,String purchDetailId,String branch,HttpServletRequest request){
    	YcejFileEntity ycejFileEntity=new YcejFileEntity();
    	Map<String, Object> params=new HashMap<>();
    	try {
    		//判断该用户是否为资源岗位，不是提示没有权限
        	Long userId=ShiroUtils.getUserId();
        	Map<String, Object> map = new HashMap<>();
        	map.put("user_id", userId);
        	List<SysUserRoleEntity> list = sysUserRoleService.selectByMap(map);
        	Boolean boolean1 = false;
        	for (SysUserRoleEntity sysUserRoleEntity : list) {
    			SysRoleEntity entity = sysRoleService.selectById(sysUserRoleEntity.getRoleId());
    			String roleName = entity.getRoleName();
    			if (roleName.contains("资源")) {
    				boolean1 = true;
    			}
    		}
        	if (!boolean1) {
        		throw new RRException("暂无操作权限，您已提交审核或者未到当前审核！");
    		}
    		
	    	if(purchDetailId.length() == 13){
				throw new RRException("请先暂存或稍后在试！");
			}
	    	params.put("customerId",purchDetailId);
	    	params.put("branch",branch);
				// 判断文件是否为空
				if (!file.isEmpty()) {
					//将文件保存到附件表中并返回主键ID
					ycejFileEntity=ycejFileService.insertReturnId(file,request,params);
					//将附件主键保存到采购明细表中
					PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
					purchaseDetailEntity.setPurchDetailId(purchDetailId);
					purchaseDetailEntity.setFileId(ycejFileEntity.getFileId());
					purchaseDetailService.updateById(purchaseDetailEntity);
				}
			} catch (Exception e) {
				logger.error("文件处理异常",e);
				logger.error(e.getMessage(),e);
				throw new RRException(e.getMessage());
			}
    	
    	return R.ok().put("ycejFileEntity", ycejFileEntity).put("purchDetailId", purchDetailId);
    }
    
    /**
     * 查看图片
     */
    @SysLog("查看车辆合格证")
    @RequestMapping("/view")
    @RequiresPermissions("operate:carpurchase:list")
    public R viewPhoto(String purchDetailId){
    	//根据采购明细主键获取附件ID
    	PurchaseDetailEntity purchaseDetail = purchaseDetailService.selectById(purchDetailId);
    	//根据附件主键获取该条附件信息
    	YcejFileEntity ycejFileEntity = ycejFileService.selectById(purchaseDetail.getFileId());
    	return R.ok().put("path", ycejFileEntity.getFilePath());
    }
}
