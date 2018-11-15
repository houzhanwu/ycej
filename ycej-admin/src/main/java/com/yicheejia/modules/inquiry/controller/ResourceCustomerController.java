package com.yicheejia.modules.inquiry.controller;

import java.util.*;
import javax.servlet.http.HttpServletResponse;


import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.modules.inquiry.entity.ResourceBrandEntity;
import com.yicheejia.modules.inquiry.service.ResourceBrandService;
import com.yicheejia.modules.operate.service.CarBrandService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.yicheejia.modules.inquiry.excel.ResourceCustomerBean;
import com.yicheejia.modules.inquiry.entity.ResourceCustomerEntity;
import com.yicheejia.modules.inquiry.service.ResourceCustomerService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.R;


/**
 * 资源客户表
 *
 * @author 
 * @email 
 * @date 2018-10-23 16:27:35
 */
@RestController
@RequestMapping("inquiry/resourcecustomer")
public class ResourceCustomerController {
    @Autowired
    private ResourceCustomerService resourceCustomerService;

    @Autowired
    private ResourceBrandService resourceBrandService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("inquiry:resourcecustomer:list")
    public LayuiPage list(@RequestParam Map<String, Object> params){
        LayuiPage page = resourceCustomerService.queryPage(params);

        return page;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{resourceId}")
    @RequiresPermissions("inquiry:resourcecustomer:info")
    public R info(@PathVariable("resourceId") Long resourceId){
        ResourceCustomerEntity resourceCustomer = resourceCustomerService.selectById(resourceId);
        if(resourceCustomer != null){
            Map<String,Object> params = new HashMap<>();
            params.put("resourceCustomerId",resourceId);

           /* List<ResourceBrandEntity> brandEntityList = resourceBrandService.selectByMap(params);
            List<Long> brandIdList = new ArrayList<>();

            for(ResourceBrandEntity brandEntity:brandEntityList){
                brandIdList.add(brandEntity.getCarBrandId());
            }*/

            List<Map<String,Object>> brandList = resourceBrandService.getResourceBrands(params);

          //  resourceCustomer.setSellBrands(brandIdList);
            resourceCustomer.setBrandList(brandList);
        }


        return R.ok().put("resourceCustomer", resourceCustomer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @Transactional
    @RequiresPermissions("inquiry:resourcecustomer:save")
    public R save(@RequestBody ResourceCustomerEntity resourceCustomer){
            /*ResourceCustomerEntity customerEntity = resourceCustomerService.queryByUserMobile(resourceCustomer);
            if(customerEntity != null){
                throw new RRException("手机号已存在");
            }*/
            resourceCustomer.setInsTime(new Date());
            resourceCustomer.setInsUser(ShiroUtils.getUserEntity().getUsername());
            try{
                resourceCustomerService.insert(resourceCustomer);
            }catch (Exception e){
                e.printStackTrace();
                throw new RRException("手机号已存在");
            }

			List<Long> sellBrands = resourceCustomer.getSellBrands();
			//保存资源销售品牌
			resourceBrandService.saveOrUpdate(resourceCustomer.getResourceId(),sellBrands);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @RequiresPermissions("inquiry:resourcecustomer:update")
    public R update(@RequestBody ResourceCustomerEntity resourceCustomer){
        resourceCustomer.setUpdTime(new Date());
        resourceCustomer.setUpdUser(ShiroUtils.getUserEntity().getUsername());
        resourceCustomerService.updateById(resourceCustomer);

        List<Long> sellBrands = resourceCustomer.getSellBrands();
        //保存资源销售品牌
        resourceBrandService.saveOrUpdate(resourceCustomer.getResourceId(),sellBrands);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("inquiry:resourcecustomer:delete")
    public R delete(@RequestBody Long[] resourceIds){
			resourceCustomerService.deleteBatchIds(Arrays.asList(resourceIds));

        return R.ok();
    }

    /**
     * 导出
    */
    @RequestMapping("/export")
    @RequiresPermissions("inquiry:resourcecustomer:export")
    public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        LayuiPage page = resourceCustomerService.queryPage(params);

        ExcelUtils.exportExcelToTarget(response, "资源客户表", page.getData(), ResourceCustomerBean.class);
    }

    @RequestMapping("/wxLogin")
    public Map<String,Object> wxLogin(@RequestBody ResourceCustomerEntity customerEntity){
        ResourceCustomerEntity resourceCustomerEntity = resourceCustomerService.queryByUserMobile(customerEntity);
        if(resourceCustomerEntity == null || !resourceCustomerEntity.getPassword().equals(customerEntity.getPassword())){
            return R.error("账号或密码不正确");
        }

        return R.ok().put("resource_id",resourceCustomerEntity.getResourceId())
                .put("resource_name",resourceCustomerEntity.getResourceName());
    }

}
