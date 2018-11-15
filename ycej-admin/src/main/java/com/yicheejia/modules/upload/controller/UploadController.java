package com.yicheejia.modules.upload.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.ConfigUtil;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.operate.service.SupplierFileService;
import com.yicheejia.modules.order.service.OrderFileService;
import com.yicheejia.modules.upload.entity.YcejFileEntity;
import com.yicheejia.modules.upload.service.YcejCusidImgService;
import com.yicheejia.modules.upload.service.YcejFileService;

import io.netty.util.internal.StringUtil;
/**
 * 附件上传
 * @author 梁占豪
 *
 */
@RestController
@RequestMapping("upload")
public class UploadController {
	@Autowired
	private YcejFileService ycejFileService;
	@Autowired
	private YcejCusidImgService ycejCusidImgService;
	@Autowired
	private OrderFileService orderFileService;
	@Autowired
	private SupplierFileService supplierFileService;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/*
	 * 客户附件上传
	 */
	@RequestMapping(value = "/uploadcusidimg")
	//@RequiresPermissions("financialmanage:ycejorderdetail:list")
	@Transactional
		public R uploadClassesFile(@RequestParam(value="file",required=false) MultipartFile file,@RequestParam Map<String, Object> params,
				HttpServletRequest request){
    			//String branch="customer";
    			try {		
							// 判断文件是否为空
							if (!file.isEmpty()) {
								//将文件保存到附件表中并返回主键ID
								YcejFileEntity ycejFileEntity=ycejFileService.insertReturnId(file,request,params);
								String branch = params.get("branch").toString();
								///String customerId = params.get("customerId").toString();
								//将业务数据的ID，附件表ID保存到业务附件表中
								if(!StringUtils.isEmpty(branch) && "order".equals(branch)){
									orderFileService.insertReturnId(ycejFileEntity,params);//订单附件表
								}else if(!StringUtils.isEmpty(branch) && "supplier".equals(branch)){
									//ycejCusidImgService.insertReturnId(ycejFileEntity,params);
									supplierFileService.insertReturnId(ycejFileEntity,params);//供应商附件表
								}
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
	 * 附件删除
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delImg")
	@Transactional
	public R delClassesFile(@RequestParam Map<String, Object> params,HttpServletRequest request){
			try {
				orderFileService.delClassesFile(params);
			} catch (Exception e) {
				logger.error("删除失败",e);
				logger.error(e.getMessage(),e);
				throw new RRException(e.getMessage());
			}

			return R.ok();  
	} 
}
