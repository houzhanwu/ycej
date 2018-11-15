package com.yicheejia.modules.insure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.annotation.ShopFilter;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.insure.dao.InsureDao;
import com.yicheejia.modules.insure.entity.InsureEntity;
import com.yicheejia.modules.insure.service.InsureService;
import com.yicheejia.modules.order.dao.YcejOrderDao;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.sys.shiro.ShiroUtils;


@Service("insureService")
public class InsureServiceImpl extends ServiceImpl<InsureDao, InsureEntity> implements InsureService {
	@Autowired
	private YcejOrderDao orderDao;
    @Override
    @ShopFilter
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<YcejOrderEntity> page = new Query<YcejOrderEntity>(params).getPage();
    	page.setRecords(baseMapper.queryInsure(page,params));
    	return new LayuiPage(page.getRecords(), page.getTotal());
    }

	@Override
	public void insertInsure(InsureEntity insure) {
		insure.setInsertTime(DateUtils.getDate());
		insure.setInsertId(ShiroUtils.getUserId()+"");
		baseMapper.insert(insure);//订单保存
	}
	@Override
	public void updateInsure(InsureEntity insure) {
		insure.setInsertTime(DateUtils.getDate());
		insure.setInsertId(ShiroUtils.getUserId()+"");
		baseMapper.updateById(insure);
	}

	@Override
	public YcejOrderEntity queryInsure(Map<String, Object> params) {
		return baseMapper.queryInsure(params);
	}
}
