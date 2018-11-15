package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.PriceQuestionDao;
import com.yicheejia.modules.operate.entity.PriceQuestionEntity;
import com.yicheejia.modules.operate.service.PriceQuestionService;


@Service("priceQuestionService")
public class PriceQuestionServiceImpl extends ServiceImpl<PriceQuestionDao, PriceQuestionEntity> implements PriceQuestionService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	Page<PriceQuestionEntity> page = new Query<PriceQuestionEntity>(params).getPage();
        page.setRecords(baseMapper.selectAllPage(page,params));

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

}
