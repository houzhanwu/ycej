package com.yicheejia.modules.financialmanage.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.financialmanage.dao.PosterminalRecordDao;
import com.yicheejia.modules.financialmanage.entity.PosterminalRecordEntity;
import com.yicheejia.modules.financialmanage.service.PosterminalRecordService;
import com.yicheejia.modules.shop.entity.YcejShopEntity;


@Service("posterminalRecordService")
public class PosterminalRecordServiceImpl extends ServiceImpl<PosterminalRecordDao, PosterminalRecordEntity> implements PosterminalRecordService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	Object shopId = params.get("shopId");
    	 EntityWrapper<PosterminalRecordEntity> wrapper = new EntityWrapper<>();
         if(shopId!=null && !"".equals(shopId.toString())){
             wrapper.eq("shop_id",shopId.toString());
             wrapper.orderBy("upd_time", false);
         }
        Page<PosterminalRecordEntity> page = this.selectPage(new Query<PosterminalRecordEntity>(params).getPage(),wrapper);

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

}
