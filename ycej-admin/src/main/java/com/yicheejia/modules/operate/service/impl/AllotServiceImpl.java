package com.yicheejia.modules.operate.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.annotation.ShopFilter;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.operate.dao.AllotDao;
import com.yicheejia.modules.operate.entity.AllotEntity;
import com.yicheejia.modules.operate.service.AllotService;
import com.yicheejia.modules.sys.service.SysUserShopService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;


@Service("allotService")
public class AllotServiceImpl extends ServiceImpl<AllotDao, AllotEntity> implements AllotService {
	
	@Autowired
	private SysUserShopService sysUserShopService;

    @Override
    @ShopFilter
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<AllotEntity> page = this.selectPage(
                new Query<AllotEntity>(params).getPage(),
                new EntityWrapper<AllotEntity>()
        );
        Long userId = ShiroUtils.getUserEntity().getUserId();
        List<Integer> shopIdList = sysUserShopService.queryShopIdList(userId);
        params.put("shop_id_list", shopIdList);
        page.setRecords(baseMapper.allotList(page,params));
        return new LayuiPage(page.getRecords(), page.getTotal());
    }

    /**
     * 调出确认
     */
	@Override
	public void updateAllotFrom(AllotEntity allot) {
//		allot.setAllotFromConfirmDate(DateUtils.getDate());
		allot.setAllotFromConfirmId(ShiroUtils.getUserEntity().getUserId().toString());
		allot.setAllotFromConfirm(1);//调拨确认状态后台写死
		allot.setAllotStatus(3);//调拨状态1.暂存；2.已提交；3.调出方已确认；4.已完成
		baseMapper.updateByVinFrom(allot);
		//修改库存表车辆状态为调拨中
		baseMapper.updateCarStatus(allot);
//		//库调拨到门店/库调拨到库
//		if(allot.getShopId() == null || allot.getShopId()==0) {
//			baseMapper.updateWarehouseToShop(allot);
//		}
//		//门店调拨到门店
//		else if(allot.getWarehouseId() == null || allot.getWarehouseId()==0) {
//			baseMapper.updateShopToShop(allot);
//		}
		
//		baseMapper.updateWarehouseAndShop(allot);
	}
	/**
     * 调入确认
     */
	@Override
	public void updateAllotTo(AllotEntity allot) {
//		allot.setAllotToConfirmDate(DateUtils.getDate());
		allot.setAllotToConfirmId(ShiroUtils.getUserId().toString());
		allot.setAllotToConfirm(1);//调拨确认状态后台写死
		allot.setAllotStatus(4);//调拨状态1.暂存；2.已提交；3.调出方已确认；4.已完成
		baseMapper.updateByVinTo(allot);
//		baseMapper.updateWarehouseAndShop(allot);
	}

	/**
	 * 新增提交
	 */
	@Override
	public void submitSave(AllotEntity allot) {
		if(null != allot.getVinList()) {
			for(String vin : allot.getVinList()) {
				allot.setVin(vin);
				allot.setAllotStatus(2);
				//获取当前登录用户添加调拨人信息
				allot.setAllotName(ShiroUtils.getUserEntity().getUsername());
				allot.setInsertTime(new Date());
				baseMapper.insert(allot);
				//修改库存表车辆状态为调拨中
//				baseMapper.updateCarStatus(allot);
			}
		}
	}

	/**
	 * 已暂存提交
	 */
	@Override
	public void submitUpdate(AllotEntity allot) {
		if(null != allot.getVinList() && allot.getVinList().size()>0 ) {
			for(String vin : allot.getVinList()) {
				allot.setVin(vin);
				allot.setAllotStatus(2);
				//获取当前登录用户添加调拨人信息(已暂存调拨信息更新调拨人信息)
				allot.setAllotName(ShiroUtils.getUserEntity().getUsername());
				baseMapper.updateallot(allot);
				baseMapper.updateCarStatus(allot);
			}
		}
		if(null != allot.getVin() && "" != allot.getVin())
		{
			
			allot.setAllotStatus(2);
			//获取当前登录用户添加调拨人信息(已暂存调拨信息更新调拨人信息)
			allot.setAllotName(ShiroUtils.getUserEntity().getUsername());
			baseMapper.updateallot(allot);
			baseMapper.updateCarStatus(allot);
		}
	}

	/**
	 * 查询调入方门店ID
	 */
	@Override
	public String getAllotToShopId(AllotEntity allot) {
		String allotId = allot.getAllotId(); 
		String allotShopId = baseMapper.queryShopId(allotId);
		return allotShopId;
	}

	@Override
	public AllotEntity selectById(String allotId) {
		return baseMapper.selectById(allotId);
	}
	/**
	 * 车辆查询中根据Vin获取调拨信息
	 */
	@Override
	public List<AllotEntity> selectByVin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseMapper.selectByVin(map);
	}
}
