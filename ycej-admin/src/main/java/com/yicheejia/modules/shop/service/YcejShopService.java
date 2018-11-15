package com.yicheejia.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.ztree.TreeNode;
import com.yicheejia.modules.shop.entity.YcejShopEntity;

import java.util.List;
import java.util.Map;

/**
 * 门店表
 *
 * @author
 * @email
 * @date 2018-06-21 15:37:31
 */
public interface YcejShopService extends IService<YcejShopEntity> {

    LayuiPage queryPage(Map<String, Object> params);

    List<Map<String,Object>> getShopListForUser();

    List<Map<String,Object>> getShopListById(Map<String,Object> params);

    List<TreeNode> getProvinceTreeNode();

    List<TreeNode> getCityTreeNode(Integer provinceId);

    List<TreeNode> getShopTreeNode(Integer cityId);

    void shopSave(YcejShopEntity ycejShop);

    void shopNoHandle(YcejShopEntity ycejShop);

    void deleteBatch(Integer[] shopIds);

    void shopUpdate(YcejShopEntity ycejShop);

	List<Map<String, Object>> warehouseAndShopListById(Map<String, Object> params);

	List<Map<String,Object>> getCityManagerList();

	List<Map<String,Object>> getExistShopByShopNo(String shopNo);

	List<Map<String, Object>> warehouseAndShopListAll(Map<String, Object> params);
	/*
	 * 梁占豪 --车辆查询模块
	 */
	List<Map<String, Object>> getWarehourseOrShopForCarQuery(Map<String, Object> params);
	/*
	 * 梁占豪--车辆查询模块--获取门店信息，城市经理以名字展示
	 */
	YcejShopEntity selectByCondition(String shopId);
	/**
	 * 日常入库模块--获取所有门店
	 * @param params 
	 * @return
	 */
	LayuiPage selectAllForDailyIncoming(Map<String, Object> params);
	/**
	 * 日常入库--获取门店信息
	 * @param params
	 * @return
	 */
	YcejShopEntity selectShopInfoById(Map<String, Object> params);
}

