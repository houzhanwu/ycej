package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.PurchaseDetailDao;
import com.yicheejia.modules.operate.entity.PurchaseDetailEntity;
import com.yicheejia.modules.operate.service.PurchaseDetailService;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	Object purch_id = params.get("purch_id");
    	EntityWrapper<PurchaseDetailEntity> wrapper= new EntityWrapper<>();
    	if (purch_id!=null && !"".equals(purch_id)) {
			wrapper.eq("purch_id", purch_id);
		}
        Page<PurchaseDetailEntity> page = this.selectPage(
                new Query<PurchaseDetailEntity>(params).getPage(),
                wrapper
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

	@Override
	public void insertReturnId(PurchaseDetailEntity purchaseDetailEntity) {
		
		baseMapper.insertReturnId(purchaseDetailEntity);
	}

	@Override
	public PurchaseDetailEntity selectByInventoryId(String inventoryId) {
		// TODO Auto-generated method stub
		return baseMapper.selectByInventoryId(inventoryId);
	}

}
