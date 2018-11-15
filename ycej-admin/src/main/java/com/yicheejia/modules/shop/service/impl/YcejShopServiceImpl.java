package com.yicheejia.modules.shop.service.impl;

import com.yicheejia.common.annotation.PartnerShopFilter;
import com.yicheejia.common.annotation.ShopFilter;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.*;
import com.yicheejia.common.ztree.TreeNode;
import com.yicheejia.modules.operate.entity.SupplierEntity;
import com.yicheejia.modules.shop.constant.ShopConstant;
import com.yicheejia.modules.shop.dao.YcejShopDao;
import com.yicheejia.modules.shop.entity.ShopFileEntity;
import com.yicheejia.modules.shop.entity.YcejShopEntity;
import com.yicheejia.modules.shop.service.ShopFileService;
import com.yicheejia.modules.shop.service.YcejShopService;
import com.yicheejia.modules.sys.dao.SysConfigDao;
import com.yicheejia.modules.sys.dao.SysRoleDao;
import com.yicheejia.modules.sys.dao.SysUserDao;
import com.yicheejia.modules.sys.dao.SysUserRoleDao;
import com.yicheejia.modules.sys.entity.SysConfigEntity;
import com.yicheejia.modules.sys.entity.SysUserShopEntity;
import com.yicheejia.modules.sys.service.SysPartnerShopService;
import com.yicheejia.modules.sys.service.SysUserShopService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("ycejShopService")
public class YcejShopServiceImpl extends ServiceImpl<YcejShopDao, YcejShopEntity> implements YcejShopService {
    @Autowired
    private SysUserShopService sysUserShopService;
    @Autowired
    private SysPartnerShopService sysPartnerShopService;
    @Autowired
    private ShopFileService shopFileService;
    @Resource
    private SysConfigDao sysConfigDao;
    @Resource
    private SysUserRoleDao sysUserRoleDao;
    @Resource
    private SysRoleDao sysRoleDao;
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private YcejShopDao ycejShopDao;


    @Override
    @ShopFilter
    public LayuiPage queryPage(Map<String, Object> params) {
        Object shopNo = params.get("shopNo");
        Object shopName = params.get("shopName");
        Object manageState = params.get("manageState");
        EntityWrapper<YcejShopEntity> wrapper = new EntityWrapper<>();
        if(shopNo!=null && !"".equals(shopNo.toString())){
            //wrapper.like("shop_no",shopNo.toString());
            String queryStr = QueryUtils.toFuzzyQueryStr(shopNo.toString());
            String filterSql = "(shop_no like '" + queryStr+"' or shop_name like '"+queryStr+"' or manager_name like '"+ queryStr+"')";
            wrapper.addFilter(filterSql);
        }
        /*if(shopName != null && !"".equals(shopName.toString())){
            wrapper.like("shop_name",shopName.toString());

        }*/
        if(manageState != null && StringUtils.isNotBlank(manageState.toString())){
            wrapper.eq("manage_state",Integer.parseInt(manageState.toString()));
        }
        wrapper.addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER));
        wrapper.orderBy("RIGHT(shop_no,4)",false);
       // wrapper.addFilter(" order by RIGHT(shop_no,4) desc");

        Page<YcejShopEntity> page = this.selectPage(
                new Query<YcejShopEntity>(params).getPage(),
                wrapper
        );

        return new LayuiPage(page.getRecords(),page.getTotal());
    }

    @Override
    public List<Map<String, Object>> getShopListForUser() {
        return baseMapper.getShopListForUser();
    }

    @Override
    @ShopFilter
    public List<Map<String, Object>> getShopListById(Map<String, Object> params) {
          //  List<Integer> shopIdList = (List<Integer>) params.get(ShopConstant.SHOP_ID_LIST);
            return baseMapper.getShopListById(params);

    }

    @Override
    public List<TreeNode> getProvinceTreeNode() {
        return baseMapper.getProvinceTreeNode();
    }

    @Override
    public List<TreeNode> getCityTreeNode(Integer provinceId) {
        return baseMapper.getCityTreeNode(provinceId);
    }

    @Override
    public List<TreeNode> getShopTreeNode(Integer cityId) {
        return baseMapper.getShopTreeNode(cityId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void shopSave(YcejShopEntity ycejShop) {
       //构建门店编码
        String shopNo = ycejShop.getShopNo();
        if(shopNo == null || "".equals(shopNo)){
            throw new RRException("门店编号不能为空");
        }
        if(!shopNo.matches("[0-9]{4}")){
            throw new RRException("门店编号必须为4位数字");
        }
        List<Map<String,Object>> shopNoList = baseMapper.getExistShopByShopNo(ycejShop.getShopNo());
        if(shopNoList != null && shopNoList.size() > 0){
            throw new RRException("门店编号已存在");
        }
        shopNoHandle(ycejShop);

        ycejShop.setInsTime(new Date());
        String userName = ShiroUtils.getUserEntity().getName();
        ycejShop.setInsUser(userName);
        this.insert(ycejShop);

        SysUserShopEntity userShopEntity = new SysUserShopEntity();
        userShopEntity.setShopId(ycejShop.getShopId());
        userShopEntity.setUserId(ShiroUtils.getUserId());
        sysUserShopService.insert(userShopEntity);

        ShopFileEntity shopFileEntity = new ShopFileEntity();
        shopFileEntity.setShopId(ycejShop.getShopId());
        shopFileEntity.setBackImg(ycejShop.getBackImg());
        shopFileEntity.setFrontImg(ycejShop.getFrontImg());
        shopFileEntity.setFrontFileId(ycejShop.getFrontFileId());
        shopFileEntity.setBackFileId(ycejShop.getBackFileId());
        shopFileService.insert(shopFileEntity);

    }

    /**
     * 构建门店编码
     * @param ycejShop
     * @return
     */
    public void shopNoHandle(YcejShopEntity ycejShop){
        String shopNo = ycejShop.getShopNo();
        if(shopNo == null || "".equals(shopNo)){
            throw new RRException("门店编号不能为空");
        }
        if(!shopNo.matches("[0-9]{4}")){
            throw new RRException("门店编号必须为4位数字");
        }

        boolean flag = cityManagerAuthorityHandel(ycejShop);
        if(!flag){
            throw new RRException("所选城市不在权限范围内");
        }

        int provinceId = ycejShop.getProvinceId();
        int cityId = ycejShop.getCityId();
        StringBuilder sb = new StringBuilder();
        //区域编码,目前默认华中：10
        sb.append("10");
        //构建省份编码
        if(provinceId<10){
            sb.append("0").append(provinceId);
        }else{
            sb.append(provinceId);
        }
        //构建市编码
        if(cityId >= 100){
            sb.append(cityId);
        }else if(cityId >= 10) {
            sb.append("0").append(cityId);
        }else if(cityId < 10){
            sb.append("00").append(cityId);
        }
        //构建门店属性编码
        sb.append(ycejShop.getShopAttr());
        //构建用户输入门店号编码
       /* if(shopNo.length()==4){
            sb.append(shopNo);
        }else if(shopNo.length()==3){
            sb.append("0").append(shopNo);
        }else if(shopNo.length()==2){
            sb.append("00").append(shopNo);
        }else if(shopNo.length()==1){
            sb.append("000").append(shopNo);
        }*/
        sb.append(shopNo);

        ycejShop.setShopNo(sb.toString());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] shopIds) {
        //删除门店
        this.deleteBatchIds(Arrays.asList(shopIds));

        //删除门店与用户关联
        sysUserShopService.deleteBatch(shopIds);

        //删除门店与合伙人关联
        sysPartnerShopService.deleteBatch(shopIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void shopUpdate(YcejShopEntity ycejShop) {

        boolean flag = cityManagerAuthorityHandel(ycejShop);
        if(!flag){
            throw new RRException("所选城市不在权限范围内");
        }

        this.shopNoHandle(ycejShop);
        ycejShop.setUpdTime(new Date());
        ycejShop.setUpdUser(ShiroUtils.getUserEntity().getName());
        this.updateById(ycejShop);

        ShopFileEntity fileEntity = shopFileService.selectByShopId(ycejShop.getShopId());

        ShopFileEntity shopFileEntity = new ShopFileEntity();
        shopFileEntity.setShopId(ycejShop.getShopId());
        shopFileEntity.setFrontFileId(ycejShop.getFrontFileId());
        shopFileEntity.setFrontImg(ycejShop.getFrontImg());
        shopFileEntity.setBackFileId(ycejShop.getBackFileId());
        shopFileEntity.setBackImg(ycejShop.getBackImg());
        //更新或插入
        if(fileEntity != null){
            if(shopFileEntity.getFrontFileId() != null || shopFileEntity.getBackFileId() != null){
                shopFileService.updateByShopId(shopFileEntity);

            }
        }else {
            shopFileService.insert(shopFileEntity);
        }
    }

    @Override
	@ShopFilter
	public List<Map<String, Object>> warehouseAndShopListById(Map<String, Object> params) {
//		if(params.get(ShopConstant.SHOP_ID_LIST) != null){
//			params.put("filterFlag", (String)params.get(Constant.SQL_FILTER));
//            List<Long> shopIdList = (List<Long>) params.get(ShopConstant.SHOP_ID_LIST);
            return baseMapper.warehouseAndShopListById(params);
//        }
//        return null;
	}

    @Override
    public List<Map<String, Object>> getCityManagerList() {
        SysConfigEntity configEntity = sysConfigDao.queryByKey("cityManager");
        List<Map<String,Object>> cityManagerList = null;
        if(configEntity != null){
            String value = configEntity.getParamValue();

            Long roleId = sysRoleDao.selectByRoleName(value);

            List<Long> userIdList = sysUserRoleDao.queryUserIdList(roleId);

            cityManagerList = sysUserDao.queryByUserIdList(userIdList);
        }


        return cityManagerList;
    }

    @Override
    public List<Map<String, Object>> getExistShopByShopNo(String shopNo) {
        return baseMapper.getExistShopByShopNo(shopNo);
    }

	@Override
	public List<Map<String, Object>> warehouseAndShopListAll(Map<String, Object> params) {
		return baseMapper.warehouseAndShopListAll(params);
	}
	/*
	 * 梁占豪--车辆查询模块
	 */
	@Override
	@PartnerShopFilter
	public List<Map<String, Object>> getWarehourseOrShopForCarQuery(Map<String, Object> params) {
		return baseMapper.getWarehourseOrShopForCarQuery(params);
	}

    /**
     *
     * 新增/修改 门店时城市经理权限处理
     *
     */
	public boolean cityManagerAuthorityHandel(YcejShopEntity ycejShop){
            Long userId = ShiroUtils.getUserId();
            List<String> roles = sysUserRoleDao.queryRoleNameListByUserId(userId);
            int cityId = ycejShop.getCityId();
            for(String role : roles){
                if("城市经理".equals(role)){
                    List<Map<String,Object>> shopCities = ycejShopDao.queryShopCitiesByUserId(userId);
                    for(Map<String,Object> map : shopCities){
                        if(map != null){
                            int allotCityId = Integer.parseInt(map.get("cityId").toString());
                            if(allotCityId == cityId){
                                return true;
                            }
                        }

                    }
                    return false;
                }
            }
            return true;
    }
	/**
	 * 梁占豪--车辆查询模块--获取门店信息，城市经理以名字展示
	 */
	@Override
	public YcejShopEntity selectByCondition(String shopId) {
		// TODO Auto-generated method stub
		return baseMapper.selectByCondition(shopId);
	}
	/**
	 * 日常入库模块--获取所有门店
	 */
	@Override
	public LayuiPage selectAllForDailyIncoming(Map<String, Object> params) {
		Page<YcejShopEntity> page = new Query<YcejShopEntity>(params).getPage();
        page.setRecords(baseMapper.selectAllForDailyIncoming(page,params));

        return new LayuiPage(page.getRecords(),page.getTotal());
	}
	/**
	 * 日常入库--获取门店信息
	 * @param shopId
	 * @return
	 */
	@Override
	public YcejShopEntity selectShopInfoById(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseMapper.selectShopInfoById(params);
	}
}
