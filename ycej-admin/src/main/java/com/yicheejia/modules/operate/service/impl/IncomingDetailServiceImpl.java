package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.IncomingDetailDao;
import com.yicheejia.modules.operate.entity.IncomingDetailEntity;
import com.yicheejia.modules.operate.service.IncomingDetailService;


@Service("incomingDetailService")
public class IncomingDetailServiceImpl extends ServiceImpl<IncomingDetailDao, IncomingDetailEntity> implements IncomingDetailService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<IncomingDetailEntity> page = this.selectPage(
                new Query<IncomingDetailEntity>(params).getPage(),
                new EntityWrapper<IncomingDetailEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

}
