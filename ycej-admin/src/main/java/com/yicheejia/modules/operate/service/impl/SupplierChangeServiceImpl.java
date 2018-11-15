package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.SupplierChangeDao;
import com.yicheejia.modules.operate.entity.SupplierChangeEntity;
import com.yicheejia.modules.operate.service.SupplierChangeService;


@Service("supplierChangeService")
public class SupplierChangeServiceImpl extends ServiceImpl<SupplierChangeDao, SupplierChangeEntity> implements SupplierChangeService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<SupplierChangeEntity> page = this.selectPage(
                new Query<SupplierChangeEntity>(params).getPage(),
                new EntityWrapper<SupplierChangeEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

	@Override
	public List<SupplierChangeEntity> selectAllPage(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseMapper.selectAllPage(params);
	}

}
