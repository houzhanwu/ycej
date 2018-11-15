package com.yicheejia.modules.operate.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.annotation.PartnerShopFilter;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.operate.dao.InventoryDao;
import com.yicheejia.modules.operate.entity.InventoryEntity;
import com.yicheejia.modules.operate.service.InventoryService;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.sys.entity.SysLogEntity;
import com.yicheejia.modules.sys.service.SysUserService;


@Service("inventoryService")
public class InventoryServiceImpl extends ServiceImpl<InventoryDao, InventoryEntity> implements InventoryService {
	@Autowired
	private SysUserService sysUserservice;
    @Override
    @PartnerShopFilter
    public LayuiPage queryPage(Map<String, Object> params) {

        /*Page<InventoryEntity> page = this.selectPage(
                new Query<InventoryEntity>(params).getPage(),
                new EntityWrapper<InventoryEntity>()
        );*/
    	//Start增加时间范围搜索 update by lw 2018-10-10
    	if(null != params.get("rDate") && !params.get("rDate").toString().isEmpty()) {
        	String rDate = (String) params.get("rDate");
        	String[] rDates = rDate.split(" ");
        	String rStartDate = rDates[0];
        	String rEndDate = rDates[2];
        	params.put("rStartDate", rStartDate);
        	params.put("rEndDate", rEndDate);
    	}
    	//end增加时间范围搜索 update by lw 2018-10-10
        Page<InventoryEntity> page = new Query<InventoryEntity>(params).getPage();
        List<InventoryEntity> records = baseMapper.inventoryList(page,params);
        List<String> userList = new ArrayList<String>();
        for (InventoryEntity inventory : records) {
            userList.add(inventory.getInsertId()+"");
            userList.add(inventory.getUpdateId()+"");
        }
        Map<String,String> map = sysUserservice.getName(userList);
        for (InventoryEntity inventory : records) {
        	String getInsertId = inventory.getInsertId()+"";
        	String getUpdateId = inventory.getUpdateId()+"";
        	if (map.containsKey(getInsertId)) {
        		String insert = map.get(inventory.getInsertId()+"");
        		if(insert !=  null){
        			//insertId 是string  
        			//inventory.setInsertId(Integer.parseInt(insert));
        		}
            }
            if (map.containsKey(getUpdateId)) {
            	String update = map.get(inventory.getUpdateId()+"");
            	if(update != null ){
            		//updateId 是string  
        			//inventory.setUpdateId(Integer.parseInt(update));
        		}
            }
        }


        page.setRecords(records);
        return new LayuiPage(page.getRecords(),page.getTotal());
    }

	@Override
	public LayuiPage getInventoryByShopId(Map<String, Object> params) {
		Page<InventoryEntity> page = new Query<InventoryEntity>(params).getPage();
		List<InventoryEntity> records = baseMapper.getInventoryByShopId(page,params);
		page.setRecords(records);
		return new LayuiPage(page.getRecords(),page.getTotal());
	}

	/**
	 * 订单资源匹配返回库存信息
	 */
	@Override
	public LayuiPage selectInventory(Map<String, Object> params) {
		Page<InventoryEntity> page = new Query<InventoryEntity>(params).getPage();
    	page.setRecords(baseMapper.selectInventory(page,params));
    	return new LayuiPage(page.getRecords(), page.getTotal());
	}

	@Override
	public void insertReturnId(InventoryEntity inventoryEntity) {
		// TODO Auto-generated method stub
		baseMapper.insertReturnId(inventoryEntity);
	}

	@Override
	public List<InventoryEntity> selectByWareHouse(Long id) {
		// TODO Auto-generated method stub
		return baseMapper.selectByWareHouse(id);
	}

	@Override
	public void updateinventory(InventoryEntity inventoryEntity) {
		
		baseMapper.updateinventory(inventoryEntity);
	}
	/**
     * 根据库存id获取库存信息 --用于采购明细 车辆库存查询
     */
	@Override
	public InventoryEntity selectDataForCarPurchaseById(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseMapper.selectDataForCarPurchaseById(params);
	}

	@Override
	public InventoryEntity selectByinventoryId(String inventoryId) {
		return baseMapper.selectByinventoryId(inventoryId);
	}
	/**
	 * 库存新增数据 查询 ---不是走采购流程
	 */
	@Override
	public InventoryEntity selectByCondition(String id) {
		// TODO Auto-generated method stub
		return baseMapper.selectByCondition(id);
	}

}
