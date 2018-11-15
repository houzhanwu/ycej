package com.yicheejia.modules.shop.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.shop.dao.CustomerTrackDao;
import com.yicheejia.modules.shop.entity.CustomerTrackEntity;
import com.yicheejia.modules.shop.service.CustomerTrackService;


@Service("customerTrackService")
public class CustomerTrackServiceImpl extends ServiceImpl<CustomerTrackDao, CustomerTrackEntity> implements CustomerTrackService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Object customerId = params.get("customerId");
        Page<CustomerTrackEntity> page = this.selectPage(
                new Query<CustomerTrackEntity>(params).getPage(),
                new EntityWrapper<CustomerTrackEntity>().eq("customer_id",customerId != null?Integer.parseInt(customerId.toString()):null)
                .orderBy("track_time",false)
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

}
