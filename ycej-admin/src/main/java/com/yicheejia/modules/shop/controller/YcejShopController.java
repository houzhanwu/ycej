package com.yicheejia.modules.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.common.validator.ValidatorUtils;
import com.yicheejia.common.validator.group.AddGroup;
import com.yicheejia.common.validator.group.UpdateGroup;
import com.yicheejia.common.ztree.TreeNode;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceRebateEntity;
import com.yicheejia.modules.financialmanage.excel.YcejFinanceRebateBean;
import com.yicheejia.modules.financialmanage.service.PosterminalRecordService;
import com.yicheejia.modules.financialmanage.service.YcejFinanceRebateService;
import com.yicheejia.modules.shop.entity.ShopFileEntity;
import com.yicheejia.modules.shop.entity.YcejShopEntity;
import com.yicheejia.modules.shop.excel.YcejShopBean;
import com.yicheejia.modules.shop.service.ShopFileService;
import com.yicheejia.modules.shop.service.YcejShopService;
import com.yicheejia.modules.sys.entity.SysRoleEntity;
import com.yicheejia.modules.sys.entity.SysUserRoleEntity;
import com.yicheejia.modules.sys.service.SysRoleService;
import com.yicheejia.modules.sys.service.SysUserRoleService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;


/**
 * 门店表
 *
 * @author
 * @email
 * @date 2018-06-21 15:37:31
 */
@RestController
@RequestMapping("shop/ycejshop")
public class YcejShopController {

    private Logger logger = LoggerFactory.getLogger(YcejShopController.class);

    @Autowired
    private YcejShopService ycejShopService;
    @Autowired
    private PosterminalRecordService posterminalRecordService;
    @Autowired
    private ShopFileService shopFileService;
    @Autowired
	private YcejFinanceRebateService ycejFinanceRebateService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    /**
     * 列表
     * update by 梁占豪
     * @description 新增财务查看门店列表权限
     */
    @RequestMapping("/list")
    @RequiresPermissions(value = {"shop:ycejshop:list", "financialmanage:shop:list"}, logical = Logical.OR)
    public LayuiPage list(@RequestParam Map<String, Object> params){
        logger.info("门店查询开始");
        LayuiPage page = ycejShopService.queryPage(params);

        return page;
    }
    

    /**
     * 信息
     * * update by 梁占豪
     * @description 新增财务查看门店信息权限
     */
    @RequestMapping("/info/{shopId}")
    @RequiresPermissions(value = {"shop:ycejshop:info", "financialmanage:shop:info"}, logical = Logical.OR)
    public R info(@PathVariable("shopId") Integer shopId){
            if(shopId == null){
                throw new RRException("门店id为空");
            }
			YcejShopEntity ycejShop = ycejShopService.selectById(shopId);

            ShopFileEntity shopFileEntity = shopFileService.selectByShopId(shopId);
            //判断门店附件是否存在
            if(shopFileEntity != null){
                ycejShop.setFrontFileId(shopFileEntity.getFrontFileId());
                ycejShop.setFrontImg(shopFileEntity.getFrontImg());
                ycejShop.setBackFileId(shopFileEntity.getBackFileId());
                ycejShop.setBackImg(shopFileEntity.getBackImg());
            }

        return R.ok().put("ycejShop", ycejShop);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:ycejshop:save")
    public R save(@RequestBody YcejShopEntity ycejShop){
        ValidatorUtils.validateEntity(ycejShop, AddGroup.class);

        ycejShopService.shopSave(ycejShop);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:ycejshop:update")
    public R update(@RequestBody YcejShopEntity ycejShop){
        ValidatorUtils.validateEntity(ycejShop, UpdateGroup.class);
            /*ycejShopService.shopNoHandle(ycejShop);
            ycejShop.setUpdTime(new Date());
            ycejShop.setUpdUser(ShiroUtils.getUserEntity().getUsername());
			ycejShopService.updateById(ycejShop);*/
            ycejShopService.shopUpdate(ycejShop);
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:ycejshop:delete")
    public R delete(@RequestBody Integer[] shopIds){
			ycejShopService.deleteBatch(shopIds);

        return R.ok();
    }

    /**
     * 获取所有门店id,name键值对
     * @return
     */
    @RequestMapping("/shopList")
    public R shopList(){
       List<Map<String,Object>> shopList =  ycejShopService.getShopListForUser();
       return R.ok().put("shopList",shopList);
    }

    /**
     * 获取对应门店id,name键值对
     * @param params
     * @return
     */
    @RequestMapping("/shopListById")
    public R shopListById(Map<String,Object> params){
        List<Map<String,Object>> shopList = ycejShopService.getShopListById(params);
        return R.ok().put("shopList",shopList);
    }
    
    /**
     * 获取对应门店及所有的仓库id,name键值对
     * @param params
     * @return
     */
    @RequestMapping("/warehouseAndShopListById")
    public R warehouseAndShopListById(Map<String,Object> params){
        List<Map<String,Object>> shopList = ycejShopService.warehouseAndShopListById(params);
        if (shopList != null && shopList.size() > 0) {
        	for(Map<String,Object> map : shopList) {
        		if(map.get("shopId") != null) {
        			map.put("shopId", map.get("shopId").toString());
        		}
        	}
        }
        return R.ok().put("shopList",shopList);
    }
    /**
     * 车辆查询模块获取所有仓库和门店------供条件查询使用
     * @param params
     * 梁占豪
     */
    @RequestMapping("/getWarehourseOrShopForCarQuery")
    public R getWarehourseOrShopForCarQuery(Map<String,Object> params){
    	//判断用户角色，如果只有一个角色，而且是店长，则只显示自己的门店，否则有多个角色，并且包含店长，则展示全部包含仓库名称
    	Long uId=ShiroUtils.getUserId();
    	Map<String, Object> ma = new HashMap<>();
    	ma.put("user_id", uId);
    	List<SysUserRoleEntity> list = sysUserRoleService.selectByMap(ma);
    	Boolean boolean1 = false;
    	for (SysUserRoleEntity sysUserRoleEntity : list) {
			SysRoleEntity entity = sysRoleService.selectById(sysUserRoleEntity.getRoleId());
			String roleName = entity.getRoleName();
			if (roleName.contains("店长")) {
				boolean1 = true;
			}
		}
    	
    	List<Map<String,Object>> shopList = new ArrayList<>();
    	if (list != null && list.size()==1 && boolean1== true) {
    		shopList= ycejShopService.getWarehourseOrShopForCarQuery(params);
    		if (shopList != null && shopList.size() > 0) {
    			for(Map<String,Object> map : shopList) {
    				if(map.get("shopId") != null) {
    					map.put("shopId", map.get("shopId").toString());
    				}
    			}
    		}
		}else{
			shopList= ycejShopService.warehouseAndShopListAll(params);
			if (shopList != null && shopList.size() > 0) {
    			for(Map<String,Object> map : shopList) {
    				if(map.get("shopId") != null) {
    					map.put("shopId", map.get("shopId").toString());
    				}
    			}
    		}
		}
    	return R.ok().put("shopList",shopList);
    }
    /**
     * 获取调出方id,name键值对（包含门店及仓库）
     * @param params
     * @return
     */
    @RequestMapping("/warehouseAndShopListAll")
    public R warehouseAndShopListAll(Map<String,Object> params){
        List<Map<String,Object>> shopList = ycejShopService.warehouseAndShopListAll(params);
        if (shopList != null && shopList.size() > 0) {
        	for(Map<String,Object> map : shopList) {
        		if(map.get("shopId") != null) {
        			map.put("shopId", map.get("shopId").toString());
        		}
        	}
        }
        return R.ok().put("shopList",shopList);
    }

    /**
     * 构造门店树形结构
     * @return
     */
    @RequestMapping("/shopTreeList")
    public List<TreeNode> getShopTreeList(){
        List<TreeNode> provinceList = ycejShopService.getProvinceTreeNode();
        for(TreeNode province : provinceList){
            String provinceId = province.getProvinceId();
            if(provinceId != null && !"".equals(provinceId)){
                List<TreeNode> cityList = ycejShopService.getCityTreeNode(Integer.parseInt(provinceId));
                //province.setChildren(cityList);
                for(TreeNode city : cityList){
                    String cityId = city.getCityId();
                    if(cityId != null && !"".equals(cityId)){
                        List<TreeNode> shopList = ycejShopService.getShopTreeNode(Integer.parseInt(cityId));
                        city.setChildren(shopList);
                    }
                }
                province.setChildren(cityList);

            }
        }
        return provinceList;
    }

    /**
     * 获取城市经理集合
     * @return
     */
    @RequestMapping("/getCityManagerList")
    public R getCityManagerList(){
        List<Map<String,Object>> cityManagerList = ycejShopService.getCityManagerList();
        return R.ok().put("cityManagerList",cityManagerList);
    }

    /**
     * 导出
     */
    @RequestMapping("/export")
    @RequiresPermissions("shop:ycejshop:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = ycejShopService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "门店表", page.getData(), YcejShopBean.class);
    }
    
   /**
    * 返佣报表数据展示
    */
    @RequestMapping("/rebatelist")
    @RequiresPermissions("shop:ycejshop:rebatelist")
    public LayuiPage rebateList(@RequestParam Map<String, Object> params){
		LayuiPage page =  ycejFinanceRebateService.queryShopPage(params);
		return page;
	}
    
  //导出
  	@SysLog("返佣报表导出")
  	@RequestMapping("/rebateexport")
  	@RequiresPermissions("shop:ycejshop:rebateexport")
  	public void rebateExport(Long[] ids, HttpServletResponse response) throws Exception {
  		List<YcejFinanceRebateEntity> list =  ycejFinanceRebateService.selectExportData(ids);
  		ExcelUtils.exportExcelToTarget(response, "返佣报表", list, YcejFinanceRebateBean.class);
  	}

    /**
     * 页面跳转测试，此时注解应为@Controller
     * @return
     */
//    @GetMapping("/goToIntention")
//    public String goToIntention(){
//
//        return "shop/intentioncustomer";
//    }

}
