package com.yicheejia.modules.operate.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.annotation.PartnerShopFilter;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.operate.dao.CarQueryDao;
import com.yicheejia.modules.operate.entity.CarQueryEntity;
import com.yicheejia.modules.operate.service.CarQueryService;
@Service("carQueryService")
public class CarQueryServiceImpl extends ServiceImpl<CarQueryDao, CarQueryEntity> implements CarQueryService {

	@Override
	@PartnerShopFilter
	public LayuiPage queryPage(Map<String, Object> params) {
		Page<CarQueryEntity> page = new Query<CarQueryEntity>(params).getPage();
		List<CarQueryEntity> list = baseMapper.selectAllPage(page,params);
		return new LayuiPage(list, page.getTotal());
	}


}
