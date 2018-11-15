package com.yicheejia.modules.upload.service.impl;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.ConfigUtil;
import com.yicheejia.common.utils.FileRenameUtil;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.upload.dao.YcejFileDao;
import com.yicheejia.modules.upload.entity.YcejFileEntity;
import com.yicheejia.modules.upload.service.YcejFileService;


@Service("ycejFileService")
public class YcejFileServiceImpl extends ServiceImpl<YcejFileDao, YcejFileEntity> implements YcejFileService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YcejFileEntity> page = this.selectPage(
                new Query<YcejFileEntity>(params).getPage(),
                new EntityWrapper<YcejFileEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public YcejFileEntity insertReturnId(MultipartFile file,HttpServletRequest request,Map<String,Object>params) {
		// TODO Auto-generated method stub
//		String customer = params.get("customerId").toString();
//		if(StringUtils.isBlank(customer)){
//			throw new RRException("业务主键为空，请先暂存或稍后在试！");
//		}
		String branch = params.get("branch").toString();
		//配置信息
		Configuration config = ConfigUtil.getConfig();
		String filepath = config.getString("filePath")+ File.separator ;
		//String filepath = request.getServletContext().getRealPath("") + File.separator; 
		filepath = filepath	+ branch;
		//String path=request.getSession().getServletContext().getRealPath("/")+branch;
		File tmpFile = new File(filepath);
		if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdirs(); 
        }
		//转存成功后将文件信息保存到数据库中
		YcejFileEntity ycejFileEntity=new YcejFileEntity();
		try {
//			String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
//			String filePath = filepath + File.separator + file.getOriginalFilename();
			//文件类型，即后缀名
			String file_type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			String fileName = FileRenameUtil.fileRename().toString();
			String filePath = filepath + File.separator + fileName+"."+file_type;
			filePath =  filePath.replaceAll("\\\\", "/");
			// 转存文件
			File file2 = new File(filePath); 
			file.transferTo(file2);
			String size = ""; 
			long fileS = file2.length();
		    DecimalFormat df = new DecimalFormat("#.00"); 
		    if (fileS < 1024) {
		       size = df.format((double) fileS) + "BT";
		    } else if (fileS < 1048576) {
		       size = df.format((double) fileS / 1024) + "KB";
		    } else if (fileS < 1073741824) {
		       size = df.format((double) fileS / 1048576) + "MB";
		    } else {
		       size = df.format((double) fileS / 1073741824) +"GB";
		    }
		    String path =  branch + File.separator + fileName+"."+file_type;
		    ycejFileEntity=insertFile(size,fileName,file_type, path);
		} catch (Exception e) {
			logger.error("文件处理异常",e);
			logger.error(e.getMessage(),e);
			throw new RRException("文件处理异常");
		}
		return ycejFileEntity;
	}

	
	public YcejFileEntity insertFile(String size,String fileName,String file_type, String filePath){
		SysUserEntity user=ShiroUtils.getUserEntity();
		YcejFileEntity ycejFileEntity=new YcejFileEntity();
		//String fileType = params.get("fileType").toString();
		//String filePath = params.get("filePath").toString();
		//保存文件名
		ycejFileEntity.setFileName(fileName);
		//保存文件类型
		ycejFileEntity.setFileType(file_type);
		//保存文件大小
		ycejFileEntity.setFileSize(size);
		//保存文件路径
		ycejFileEntity.setFilePath(filePath);
		//保存操作用户
		ycejFileEntity.setInsertId(user.getUserId().intValue());
		//默认启用
		ycejFileEntity.setIsEnabled(1);
		baseMapper.insertReturnId(ycejFileEntity);
		
		return ycejFileEntity;
	
	}

}
