package com.yicheejia.modules.financialmanage.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.TopMarginRecord;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.financialmanage.excel.PosterminalRecordBean;
import com.yicheejia.modules.financialmanage.entity.PosterminalRecordEntity;
import com.yicheejia.modules.financialmanage.service.PosterminalRecordService;
import com.yicheejia.modules.shop.entity.YcejShopEntity;
import com.yicheejia.modules.shop.service.YcejShopService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;
import com.yicheejia.common.validator.ValidatorUtils;
import com.yicheejia.common.validator.group.UpdateGroup;


/**
 * 
 * POS管理及POS变更记录
 * @author cn
 * @email 
 * @date 2018-07-12 16:52:12
 */
@RestController
@RequestMapping("financialmanage/posterminalrecord")
public class PosterminalRecordController {
    @Autowired
    private PosterminalRecordService posterminalRecordService;
    @Autowired
    private YcejShopService ycejShopService;
    
    /**
     * POS变更记录列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("financialmanage:posterminalrecord:list")
    public LayuiPage list(@RequestParam Map<String, Object> params,String shopId){
    	params.put("shopId", shopId);
        LayuiPage page = posterminalRecordService.queryPage(params);
        return page;
    }


    /**
     * POS变更记录信息
     */
    @RequestMapping("/info/{postRecordId}")
    @RequiresPermissions("financialmanage:posterminalrecord:info")
    public R info(@PathVariable("postRecordId") Integer postRecordId){
			PosterminalRecordEntity posterminalRecord = posterminalRecordService.selectById(postRecordId);

        return R.ok().put("posterminalRecord", posterminalRecord);
    }

    /**
     * POS变更记录保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("financialmanage:posterminalrecord:save")
    public R save(@RequestBody PosterminalRecordEntity posterminalRecord){
			posterminalRecordService.insert(posterminalRecord);

        return R.ok();
    }

    /**
     * POS变更记录修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("financialmanage:posterminalrecord:update")
    public R update(@RequestBody PosterminalRecordEntity posterminalRecord){
			posterminalRecordService.updateById(posterminalRecord);

        return R.ok();
    }

    /**
     * POS变更记录删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("financialmanage:posterminalrecord:delete")
    public R delete(@RequestBody Integer[] postRecordIds){
			posterminalRecordService.deleteBatchIds(Arrays.asList(postRecordIds));

        return R.ok();
    }

    /**
     * POS变更记录导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("financialmanage:posterminalrecord:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = posterminalRecordService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), PosterminalRecordBean.class);
    }
    
    /**
     * @author 梁占豪
     * 财务修改门店pos机编号
     */
    @Transactional
    @RequestMapping("/updateByFinance")
    @RequiresPermissions("financialmanage:postmanage:update")
    public R updateByFinance(@RequestBody YcejShopEntity ycejShop){
    	ValidatorUtils.validateEntity(ycejShop, UpdateGroup.class);
    	ycejShopService.shopNoHandle(ycejShop);
    	ycejShop.setUpdTime(new Date());
    	ycejShop.setUpdUser(ShiroUtils.getUserEntity().getName());
    	ycejShopService.updateById(ycejShop);
    	//更新POS机编号的同时插入POS记录表
    	PosterminalRecordEntity recordEntity = new PosterminalRecordEntity();
    	recordEntity.setTerminalNum(ycejShop.getPosNo());
    	recordEntity.setUpdTime(new Date());
    	recordEntity.setShopId(String.valueOf(ycejShop.getShopId()));
    	recordEntity.setUpdUser(ShiroUtils.getUserEntity().getName());
    	posterminalRecordService.insert(recordEntity);
    	return R.ok();
    }

}
