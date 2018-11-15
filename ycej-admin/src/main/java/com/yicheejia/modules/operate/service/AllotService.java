package com.yicheejia.modules.operate.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.operate.entity.AllotEntity;

/**
 * 调拨记录表
 *
 * @author lw
 * @email 
 * @date 2018-07-18 16:37:00
 */
public interface AllotService extends IService<AllotEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	void updateAllotFrom(AllotEntity allot);

	void updateAllotTo(AllotEntity allot);

	void submitSave(AllotEntity allot);

	void submitUpdate(AllotEntity allot);

	String getAllotToShopId(AllotEntity allot);

	AllotEntity selectById(String allotId);
	/**
	 * 车辆查询中根据Vin获取调拨信息
	 */
	List<AllotEntity> selectByVin(Map<String, Object> map);
}

