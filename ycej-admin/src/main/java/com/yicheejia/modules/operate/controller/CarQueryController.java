package com.yicheejia.modules.operate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.modules.operate.entity.AllotEntity;
import com.yicheejia.modules.operate.entity.CarModelEntity;
import com.yicheejia.modules.operate.entity.CarPurchaseEntity;
import com.yicheejia.modules.operate.entity.InventoryEntity;
import com.yicheejia.modules.operate.entity.PurchaseDetailEntity;
import com.yicheejia.modules.operate.service.AllotService;
import com.yicheejia.modules.operate.service.CarModelService;
import com.yicheejia.modules.operate.service.CarPurchaseService;
import com.yicheejia.modules.operate.service.CarQueryService;
import com.yicheejia.modules.operate.service.InventoryService;
import com.yicheejia.modules.operate.service.PurchaseDetailService;
import com.yicheejia.modules.shop.entity.YcejShopEntity;
import com.yicheejia.modules.shop.service.YcejShopService;

/**
 * 车辆查询controller
 * @author 梁占豪
 * @date 2018-08-29
 */
@RestController
@RequestMapping("operate/carquery")
public class CarQueryController {

	@Autowired
	private CarQueryService carQueryService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private PurchaseDetailService purchaseDetailService;
	@Autowired
	private CarPurchaseService carPurchaseService;
	@Autowired
	private YcejShopService ycejShopService;
	@Autowired
	private AllotService allotService;
	@Autowired
	private CarModelService carModelService;
	
	@RequestMapping("/list")
	@RequiresPermissions("operate:carquery:list")
	 public LayuiPage list(@RequestParam Map<String, Object> params){
		LayuiPage page = carQueryService.queryPage(params);
		return page;
	}
	/**
	 * 根据id获取车辆所有相关信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("operate:carquery:info")
	public R getDetail(@PathVariable("id") String id){
		InventoryEntity inventoryEntity = inventoryService.selectById(id);
		//根据库存信息获取采购明细
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("inventory_id", inventoryEntity.getInventoryId());
		List<PurchaseDetailEntity> detailEntityList = purchaseDetailService.selectByMap(columnMap);
		Map<String, Object> hashMap = new HashMap<>();
		CarPurchaseEntity carPurchaseEntity = new CarPurchaseEntity();
		PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
		CarModelEntity carModelEntity = new CarModelEntity();
		if (detailEntityList!=null && detailEntityList.size()>0) {
			detailEntity= detailEntityList.get(0);
			//根据采购明细获取对应的采购单信息
			carPurchaseEntity= carPurchaseService.selectById(detailEntity.getPurchId());
			//根据车型id获取车型详细信息
			carModelEntity= carModelService.selectById(detailEntity.getModelId());
		}
		hashMap.put("detailEntity", detailEntity);
		hashMap.put("carPurchaseEntity", carPurchaseEntity);
		hashMap.put("carModelEntity", carModelEntity);
		//根据库存信息获取门店信息
		YcejShopEntity ycejShopEntity = new YcejShopEntity();
		if (inventoryEntity.getShopId()!=null && inventoryEntity.getShopId()!= "") {
			ycejShopEntity= ycejShopService.selectById(inventoryEntity.getShopId());
		}
		//根据车架号获取调拨记录
		Map<String, Object> map = new HashMap<>();
		if (inventoryEntity.getVin()!=null && inventoryEntity.getVin()!="") {
			map.put("vin", inventoryEntity.getVin());
			List<AllotEntity> allotEntity = allotService.selectByVin(map);
			hashMap.put("allotEntity", allotEntity);
		}
//		List<AllotEntity> allotEntity = allotService.selectByMap(map);
		hashMap.put("ycejShopEntity", ycejShopEntity);
		return R.ok().put("hashMap", hashMap);
	}
	
	//目前库存数据来自库存管理直接新增，不是走正常流程从采购入库的，所以现在先展示从库存管理中获取的数据
	@RequestMapping("/getdatabyid")
	@RequiresPermissions("operate:carquery:info")
	public R getDataById(@RequestParam String id){
		Map<String, Object> hashMap = new HashMap<>();
		YcejShopEntity ycejShopEntity = new YcejShopEntity();
		CarModelEntity carModelEntity = new CarModelEntity();
		try {
			InventoryEntity inventoryEntity = inventoryService.selectByCondition(id);
			hashMap.put("inventoryEntity", inventoryEntity);
			//根据库存信息获取门店信息
			if (inventoryEntity.getCarModel()!=null && inventoryEntity.getCarModel()!="") {
				carModelEntity = carModelService.selectById(inventoryEntity.getCarModel());
			}
			if (inventoryEntity.getShopId()!=null && inventoryEntity.getShopId()!= "") {
				//获取门店信息，城市经理以名字展示
				ycejShopEntity= ycejShopService.selectByCondition(inventoryEntity.getShopId());
			}
			hashMap.put("ycejShopEntity", ycejShopEntity);
			hashMap.put("carModelEntity", carModelEntity);
		} catch (Exception e) {
			throw new RRException(e.getMessage());
		}
		return R.ok().put("hashMap", hashMap);
	}
}
