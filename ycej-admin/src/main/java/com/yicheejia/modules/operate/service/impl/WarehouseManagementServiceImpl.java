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

import com.yicheejia.modules.operate.dao.WarehouseManagementDao;
import com.yicheejia.modules.operate.entity.WarehouseManagementEntity;
import com.yicheejia.modules.operate.service.WarehouseManagementService;


@Service("warehouseManagementService")
public class WarehouseManagementServiceImpl extends ServiceImpl<WarehouseManagementDao, WarehouseManagementEntity> implements WarehouseManagementService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Object warehouseName = params.get("warehouseName");
        EntityWrapper<WarehouseManagementEntity> wrapper = new EntityWrapper<>();

        if(warehouseName != null && StringUtils.isNotBlank(warehouseName.toString())){
            wrapper.like("warehouse_name",warehouseName.toString());
        }
        wrapper.eq("delete_flag", 0).orderBy("update_time",false);
        Page<WarehouseManagementEntity> page = this.selectPage(
                new Query<WarehouseManagementEntity>(params).getPage(),
                wrapper
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

	@Override
	public List<WarehouseManagementEntity> selectList() {
		// TODO Auto-generated method stub
		return baseMapper.selectList();
	}

}
