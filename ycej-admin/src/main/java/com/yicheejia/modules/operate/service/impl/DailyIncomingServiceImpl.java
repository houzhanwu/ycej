package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.DailyIncomingDao;
import com.yicheejia.modules.operate.entity.DailyIncomingEntity;
import com.yicheejia.modules.operate.service.DailyIncomingService;


@Service("dailyIncomingService")
public class DailyIncomingServiceImpl extends ServiceImpl<DailyIncomingDao, DailyIncomingEntity> implements DailyIncomingService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	Page<DailyIncomingEntity> page = new Query<DailyIncomingEntity>(params).getPage();
    	page.setRecords(baseMapper.selectAllPage(page,params));

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

}
