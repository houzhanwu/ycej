package com.yicheejia.modules.shop.dao;

import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.ztree.TreeNode;
import com.yicheejia.modules.shop.entity.YcejShopEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 门店表
 * 
 * @author
 * @email
 * @date 2018-06-21 15:37:31
 */
public interface YcejShopDao extends BaseMapper<YcejShopEntity> {

    List<Map<String,Object>> getShopListForUser();

    List<Map<String,Object>> getShopListById(Map<String,Object> map);

    List<TreeNode> getProvinceTreeNode();

    List<TreeNode> getCityTreeNode(Integer provinceId);

    List<TreeNode> getShopTreeNode(Integer cityId);

	List<Map<String, Object>> warehouseAndShopListById(Map<String, Object> params);

	List<Map<String,Object>> getExistShopByShopNo(@Param(value="shopNo")String shopNo);

	List<Map<String, Object>> warehouseAndShopListAll(Map<String, Object> params);

	List<Map<String, Object>> getWarehourseOrShopForCarQuery(Map<String, Object> params);

	List<Map<String,Object>> queryShopCitiesByUserId(Long userId);
	/**
	 * 梁占豪--车辆查询模块--获取门店信息，城市经理以名字展示
	 * @param shopId
	 * @return
	 */
	YcejShopEntity selectByCondition(String shopId);
	/**
	 * 日常入库模块--获取所有门店信息
	 * @param page 
	 * @param params 
	 * @return
	 */
	List<YcejShopEntity> selectAllForDailyIncoming(Page<YcejShopEntity> page, Map<String, Object> params);
	/**
	 * 日常入库--获取门店信息
	 * @param params
	 * @return
	 */
	YcejShopEntity selectShopInfoById(Map<String, Object> params);

}
