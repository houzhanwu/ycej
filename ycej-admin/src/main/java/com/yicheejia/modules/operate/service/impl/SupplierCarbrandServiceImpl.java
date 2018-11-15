package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.SupplierCarbrandDao;
import com.yicheejia.modules.operate.entity.SupplierCarbrandEntity;
import com.yicheejia.modules.operate.service.SupplierCarbrandService;


@Service("supplierCarbrandService")
public class SupplierCarbrandServiceImpl extends ServiceImpl<SupplierCarbrandDao, SupplierCarbrandEntity> implements SupplierCarbrandService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<SupplierCarbrandEntity> page = this.selectPage(
                new Query<SupplierCarbrandEntity>(params).getPage(),
                new EntityWrapper<SupplierCarbrandEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

	@Override
	public List<SupplierCarbrandEntity> selectDataByMap(Map<String, Object> columnMap) {
		return baseMapper.selectDataByMap(columnMap);
	}

}
