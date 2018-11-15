package com.yicheejia.modules.operate.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.SupplierDao;
import com.yicheejia.modules.operate.entity.SupplierEntity;
import com.yicheejia.modules.operate.service.SupplierService;


@Service("supplierService")
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, SupplierEntity> implements SupplierService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        /*Object supplierName = params.get("supplierName");
        EntityWrapper<SupplierEntity> wrapper = new EntityWrapper<>();

        if(supplierName != null && StringUtils.isNotBlank(supplierName.toString())){
            //wrapper.like("supplier_name",supplierName.toString());
            String filterSql = "(supplier_name like '%" + supplierName.toString()+"%' or supplier_moblie like '%"+supplierName.toString()+"%')";
            wrapper.addFilter(filterSql);
        }
        wrapper.orderBy("insert_time",false);
        Page<SupplierEntity> page = this.selectPage(
                new Query<SupplierEntity>(params).getPage(),
                wrapper
        );*/
        Page<SupplierEntity> page = new Query<SupplierEntity>(params).getPage();
        page.setRecords(baseMapper.getSupplierListPage(page,params));

        return new LayuiPage(page.getRecords(),page.getTotal());
    }

	@Override
	public List<SupplierEntity> getListForPurchase() {
		// TODO Auto-generated method stub
		return baseMapper.getListForPurchase();
	}

	@Override
	public void insertByCondition(SupplierEntity supplier) {
		// TODO Auto-generated method stub
		baseMapper.insertByCondition(supplier);
	}

	@Override
	public LayuiPage getSupplierListForCaution(Map<String, Object> params) {
		Page<SupplierEntity> page = new Query<SupplierEntity>(params).getPage();
        page.setRecords(baseMapper.getSupplierListForCaution(page,params));

        return new LayuiPage(page.getRecords(),page.getTotal());
	}

//	@Override	
//	public List<SupplierEntity> getSupplierListForCaution(Map<String, Object> params) {
//		return baseMapper.getSupplierListForCaution(params);
//	}

}
