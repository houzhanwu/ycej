package com.yicheejia.modules.operate.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.yicheejia.modules.inquiry.entity.ResourceQuotedEntity;
import com.yicheejia.modules.inquiry.service.ResourceQuotedService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.operate.excel.PriceQuestionBean;
import com.yicheejia.modules.operate.entity.PriceQuestionEntity;
import com.yicheejia.modules.operate.service.PriceQuestionService;
import com.yicheejia.modules.sys.entity.SysUserShopEntity;
import com.yicheejia.modules.sys.service.SysUserShopService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 
 *
 * @author 梁占豪
 * @email 
 * @date 2018-09-28 15:23:41
 */
@RestController
@RequestMapping("operate/pricequestion")
public class PriceQuestionController {
    @Autowired
    private PriceQuestionService priceQuestionService;
    @Autowired
    private SysUserShopService sysUserShopService;
    @Autowired
    private ResourceQuotedService quotedService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:pricequestion:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = priceQuestionService.queryPage(params);

        return page;
    }


    /**
     * 回复
     */
    @RequestMapping("/reply/{id}")
    @RequiresPermissions("operate:pricequestion:list")
    public R reply(@PathVariable("id") Long id){
			PriceQuestionEntity priceQuestion = priceQuestionService.selectById(id);
			//回复人
			priceQuestion.setReplier(ShiroUtils.getUserEntity().getName());
			//回复时间
			priceQuestion.setReplyTime(DateUtils.getDate());
        return R.ok().put("priceQuestion", priceQuestion);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("operate:pricequestion:info")
    public R info(@PathVariable("id") Long id){
			PriceQuestionEntity priceQuestion = priceQuestionService.selectById(id);
			Long quotedId = priceQuestion.getQuotedId();
			if(quotedId != null){
                ResourceQuotedEntity quotedEntity = quotedService.selectById(quotedId);
                priceQuestion.setQuotedEntity(quotedEntity);
            }

        return R.ok().put("priceQuestion", priceQuestion);
    }
    
    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:pricequestion:save")
    public R save(@RequestBody PriceQuestionEntity priceQuestion){
			priceQuestionService.insert(priceQuestion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:pricequestion:update")
    public R update(@RequestBody PriceQuestionEntity priceQuestion){
			priceQuestionService.updateById(priceQuestion);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:pricequestion:delete")
    public R delete(@RequestBody Long[] ids){
			priceQuestionService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("operate:pricequestion:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = priceQuestionService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "", page.getData(), PriceQuestionBean.class);
    }
    /**
     * 点击新增时，需要带出用户所在门店、用户名、时间
     */
    @RequestMapping("/getinsertdata")
    @RequiresPermissions("operate:pricequestion:save")
    public R getInsertData(){
    	PriceQuestionEntity priceQuestion = new PriceQuestionEntity();
    	//用户名--提问人
    	String name = ShiroUtils.getUserEntity().getName();
    	//根据用户获取该用户所在的门店
    	Long userId = ShiroUtils.getUserId();
    	Map<String, Object> columnMap = new HashMap<>();
    	columnMap.put("user_id",userId);
    	List<SysUserShopEntity> list;
		try {
			list = sysUserShopService.selectByMap(columnMap);
			//所在门店，只有当前用户名下只有一个门店才返回，否则不返回
			if (list!=null && list.size() == 1) {
				priceQuestion.setShopId(String.valueOf(list.get(0).getShopId()));
			}
			priceQuestion.setQuestioner(name);
			priceQuestion.setQuestionTime(DateUtils.getDate());
		} catch (Exception e) {
			throw new RRException(e.getMessage());
		}
    	return R.ok().put("priceQuestion", priceQuestion);
    }

}
