package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.RefundDepositDao;
import com.yicheejia.modules.operate.entity.DailyIncomingEntity;
import com.yicheejia.modules.operate.entity.RefundDepositEntity;
import com.yicheejia.modules.operate.service.RefundDepositService;


@Service("refundDepositService")
public class RefundDepositServiceImpl extends ServiceImpl<RefundDepositDao, RefundDepositEntity> implements RefundDepositService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	Page<RefundDepositEntity> page = new Query<RefundDepositEntity>(params).getPage();
    	page.setRecords(baseMapper.selectAllPage(page,params));

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

}
