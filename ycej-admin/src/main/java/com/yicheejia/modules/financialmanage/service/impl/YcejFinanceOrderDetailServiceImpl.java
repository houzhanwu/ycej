package com.yicheejia.modules.financialmanage.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.financialmanage.dao.YcejFinanceOrderDetailDao;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceOrderDetailEntity;
import com.yicheejia.modules.financialmanage.service.YcejFinanceOrderDetailService;


@Service("ycejFinanceOrderDetailService")
public class YcejFinanceOrderDetailServiceImpl extends ServiceImpl<YcejFinanceOrderDetailDao, YcejFinanceOrderDetailEntity> implements YcejFinanceOrderDetailService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	HashMap<Object, Object> map=new HashMap<>();
    	
    	Object shop_name=params.get("shop");
    	 if (shop_name!=null && !"".equals(shop_name.toString())) {
    		//门店
    		map.put("shop_name", shop_name);
		}
    	 Object sell_type=params.get("payWay");
    	 if (sell_type!=null && !"".equals(sell_type.toString())) {
    		 //付款方式
    		 map.put("sell_type", sell_type);
    	 }
    	Page<YcejFinanceOrderDetailEntity> page=new Query<YcejFinanceOrderDetailEntity>(params).getPage();
    	page.setRecords(baseMapper.selectAllPage(page,map));

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

}
