package com.yicheejia.modules.operate.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.IncomingFileDao;
import com.yicheejia.modules.operate.entity.IncomingFileEntity;
import com.yicheejia.modules.operate.service.IncomingFileService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.upload.entity.YcejFileEntity;


@Service("incomingFileService")
public class IncomingFileServiceImpl extends ServiceImpl<IncomingFileDao, IncomingFileEntity> implements IncomingFileService {
	@Autowired
	private IncomingFileService incomingFileService;
	
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<IncomingFileEntity> page = this.selectPage(
                new Query<IncomingFileEntity>(params).getPage(),
                new EntityWrapper<IncomingFileEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

	@Override
	public void insertReturnId(YcejFileEntity ycejFileEntity, Map<String, Object> params) {
		String customerId = params.get("dailyIncomingId")+"";
		if(StringUtils.isBlank(customerId) || "undefined".equals(customerId)){
			throw new RRException("请先暂存！");
		}
		IncomingFileEntity incomingFileEntity = new IncomingFileEntity();
		String fileType = params.get("fileType").toString();
		incomingFileEntity.setDailyIncomingId(customerId);
		incomingFileEntity.setFileId(ycejFileEntity.getFileId());
		incomingFileEntity.setFileType(fileType);
		incomingFileEntity.setInsertTime(DateUtils.getDate());
		incomingFileEntity.setInsertUser(ShiroUtils.getUserEntity().getName());
		incomingFileEntity.setImgName(ycejFileEntity.getFilePath());
		try {
			//判断文件是否已经存在，是更新，否新增
			params.put("dailyIncomingId", customerId);
			params.put("fileType",fileType);
			List<IncomingFileEntity> list = queryList(params);
			if (list!=null && list.size()>0) {
				incomingFileEntity.setId(list.get(0).getId());
				baseMapper.updateById(incomingFileEntity);
			}else{
				baseMapper.insert(incomingFileEntity);
			}
		} catch (Exception e) {
			throw new RRException("文件保存失败");
		}
	}

	@Override
	public List<IncomingFileEntity> queryList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseMapper.queryList(params);
	}
	

}
