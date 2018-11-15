package com.yicheejia.modules.operate.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.modules.operate.entity.CalculatorEntity;
import com.yicheejia.modules.product.entity.ProductInfoEntity;

public interface CalculatorService extends IService<CalculatorEntity> {

	CalculatorEntity insurance(CalculatorEntity calculatorEntity);
	
	CalculatorEntity caiwuRe(CalculatorEntity calculatorEntity);

	List<ProductInfoEntity> productList();
	
}
