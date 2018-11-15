package com.yicheejia.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.modules.operate.entity.InsuranceCalculatorEntity;

public interface InsuranceCalculatorService extends IService<InsuranceCalculatorEntity> {

	InsuranceCalculatorEntity calculate(InsuranceCalculatorEntity insuranceCalculatorEntity);



}
