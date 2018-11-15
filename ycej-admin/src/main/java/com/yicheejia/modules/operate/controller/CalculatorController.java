package com.yicheejia.modules.operate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.R;
import com.yicheejia.modules.operate.entity.CalculatorEntity;
import com.yicheejia.modules.operate.service.CalculatorService;
import com.yicheejia.modules.product.entity.ProductInfoEntity;
import com.yicheejia.modules.product.service.ProductInfoService;

@RestController
@RequestMapping("operate/calculator")
public class CalculatorController {
	
	@Autowired
    private CalculatorService calculatorService;
	@Autowired
	private ProductInfoService productInfoService;

	/**
     * 返利计算
     */
    @RequestMapping("/insurance")
//    @RequiresPermissions("operate:calculator:insurance")
    public R insurance(@RequestBody CalculatorEntity calculatorEntity){
    	calculatorEntity = calculatorService.insurance(calculatorEntity);

        return R.ok().put("calculatorEntity", calculatorEntity);
    }
	/**
     * 获取产品列表
     */
    @RequestMapping("/productList")
//    @RequiresPermissions("operate:calculator:insurance")
	 public R getProductList(){
			List<ProductInfoEntity>  productList = productInfoService.selectProductList();
	        return R.ok().put("productList",productList);
    }
}
