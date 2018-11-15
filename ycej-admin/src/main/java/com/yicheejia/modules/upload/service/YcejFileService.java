package com.yicheejia.modules.upload.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.modules.upload.entity.YcejFileEntity;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-06 09:24:25
 */
public interface YcejFileService extends IService<YcejFileEntity> {

    PageUtils queryPage(Map<String, Object> params);

    YcejFileEntity insertReturnId(MultipartFile file,HttpServletRequest request, Map<String,Object>params);
    
    YcejFileEntity insertFile(String size,String fileName,String file_type,String filePath);

}

