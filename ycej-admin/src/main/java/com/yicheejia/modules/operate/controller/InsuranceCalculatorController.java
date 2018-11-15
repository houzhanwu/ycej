package com.yicheejia.modules.operate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.R;
import com.yicheejia.modules.operate.entity.InsuranceCalculatorEntity;
import com.yicheejia.modules.operate.service.InsuranceCalculatorService;

/**
 * 保险计算器
 *
 * @author lw
 * @email 
 * @date 2018-10-17 09:00:00
 */
@RestController
@RequestMapping("operate/insurance")
public class InsuranceCalculatorController {
	
	@Autowired
	private InsuranceCalculatorService insuranceCalculatorService;
	
	@RequestMapping("/calculate")
    public R insurance(@RequestBody InsuranceCalculatorEntity insuranceCalculatorEntity){
		insuranceCalculatorEntity = insuranceCalculatorService.calculate(insuranceCalculatorEntity);

        return R.ok().put("insuranceCalculatorEntity", insuranceCalculatorEntity);
    }

}
