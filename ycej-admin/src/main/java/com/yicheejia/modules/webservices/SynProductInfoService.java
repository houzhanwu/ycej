package com.yicheejia.modules.webservices;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.modules.product.entity.ProductInfoEntity;
import com.yicheejia.modules.product.service.ProductInfoService;
import com.yicheejia.modules.webservices.model.RspVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yicheejia.common.utils.R;
import com.yicheejia.common.validator.ValidatorUtils;


/**
 * 
 * 产品同步服务
 * @author hunk
 * @email 
 * @date 2018-07-10 16:12:15
 */
@RestController
@RequestMapping("webService/prodectinfo")
public class SynProductInfoService {
    @Autowired
    private ProductInfoService prodectInfoService;


    /**
     * 保存产品  存在
     * @param productInfo
     * @return
     */
    @RequestMapping("/synPdtInfo")
    public RspVO save(@RequestBody ProductInfoEntity productInfo) {
        ValidatorUtils.validateVO(productInfo);
        EntityWrapper<ProductInfoEntity> wap = new EntityWrapper<ProductInfoEntity>();
        wap.setEntity(productInfo);
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("product_solution_id", productInfo.getProductSolutionId());
        List<ProductInfoEntity> pe=prodectInfoService.selectByMap(columnMap);
//        ProductInfoEntity pe = prodectInfoService.selectOne(wap);
        if (pe == null || pe.size()<=0) {
            prodectInfoService.insert(productInfo);
        } else {
        	productInfo.setProductId(pe.get(0).getProductId());
        	prodectInfoService.updateById(productInfo);
        }
        return RspVO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("product:prodectinfo:update")
    public R update(@RequestBody ProductInfoEntity productInfo){
			prodectInfoService.updateById(productInfo);
        return R.ok();
    }

    

}
