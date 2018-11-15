package com.yicheejia.modules.upload.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceOrderDetailEntity;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.upload.dao.YcejCusidImgDao;
import com.yicheejia.modules.upload.entity.YcejCusidImgEntity;
import com.yicheejia.modules.upload.entity.YcejFileEntity;
import com.yicheejia.modules.upload.service.YcejCusidImgService;


@Service("ycejCusidImgService")
public class YcejCusidImgServiceImpl extends ServiceImpl<YcejCusidImgDao, YcejCusidImgEntity> implements YcejCusidImgService {

	@Autowired
	private YcejCusidImgService ycejCusidImgService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YcejCusidImgEntity> page = this.selectPage(
                new Query<YcejCusidImgEntity>(params).getPage(),
                new EntityWrapper<YcejCusidImgEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public void insertReturnId(YcejFileEntity ycejFileEntity,String customerId) {
		// TODO Auto-generated method stub
		SysUserEntity user=ShiroUtils.getUserEntity();
		YcejCusidImgEntity ycejCusidImgEntity=new YcejCusidImgEntity();
		ycejCusidImgEntity.setBusinessId(customerId);
		ycejCusidImgEntity.setFileId(ycejFileEntity.getFileId());
		ycejCusidImgEntity.setInsertId(user.getUserId().intValue());
		baseMapper.insertReturnId(ycejCusidImgEntity);
	}
	
	@Override
	public List<YcejCusidImgEntity> selectAllPic(String customerId) {
		List<YcejCusidImgEntity> list=baseMapper.selectAllPic(customerId);
		return list;
	}

}
