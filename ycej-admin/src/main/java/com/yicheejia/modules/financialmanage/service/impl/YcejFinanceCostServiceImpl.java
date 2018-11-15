package com.yicheejia.modules.financialmanage.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.financialmanage.dao.YcejFinanceCostDao;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceCostEntity;
import com.yicheejia.modules.financialmanage.service.YcejFinanceCostService;
import com.yicheejia.modules.operate.entity.CalculatorEntity;
import com.yicheejia.modules.operate.service.CalculatorService;
@Service("ycejFinanceCostService")
public class YcejFinanceCostServiceImpl extends ServiceImpl<YcejFinanceCostDao,YcejFinanceCostEntity> implements YcejFinanceCostService {

	@Autowired
	private CalculatorService calculatorService;
	@Override
	public LayuiPage queryPage(Map<String, Object> params) {
		HashMap<Object, Object> map=new HashMap<>();
		Object saleTime=params.get("saleTime");
		if (saleTime!=null && !"".equals(saleTime.toString())) {
			//销售日期
    		String rDate = (String) params.get("saleTime");
        	String[] rDates = rDate.split(" ");
        	String startSaleDate = rDates[0];
        	String endSaleDate = rDates[2];
        	map.put("startSaleDate", startSaleDate);
        	map.put("endSaleDate", endSaleDate);
		}
		Page<YcejFinanceCostEntity> page=new Query<YcejFinanceCostEntity>(params).getPage();
		
		List<YcejFinanceCostEntity> list =  baseMapper.selectAllPage(page,map);
		for (YcejFinanceCostEntity ycejFinanceCostEntity : list) {
			//调用返利测算，获取返利
			CalculatorEntity calculatorEntity = new CalculatorEntity();
			calculatorEntity.setFinancingDate(ycejFinanceCostEntity.getFinancingDate());//融资期限
			calculatorEntity.setShopAttr(ycejFinanceCostEntity.getShopAttr());//店铺属性
			calculatorEntity.setAllCharge(ycejFinanceCostEntity.getAllCharge());//融资总额
			calculatorEntity.setGpsCharge(ycejFinanceCostEntity.getGpsFee().intValue());//GPS融资额
			calculatorEntity.setInsurancePrice(ycejFinanceCostEntity.getComInsurance());//商业险
			calculatorEntity.setServiceCharge(ycejFinanceCostEntity.getServiceCharge());//服务费
			CalculatorEntity entity=calculatorService.caiwuRe(calculatorEntity);
			ycejFinanceCostEntity.setReturnedGps(entity.getGpsRe());//GPS返利
			ycejFinanceCostEntity.setReturnedInsurance(entity.getInsuranceRe());//返保险
		}
    	page.setRecords(list);

        return new LayuiPage(page.getRecords(), page.getTotal());	
	}
	@Override
	public List<YcejFinanceCostEntity> selectExportData(Long[] ids) {
		List<YcejFinanceCostEntity> list =  baseMapper.selectExportData(Arrays.asList(ids));
		for (YcejFinanceCostEntity ycejFinanceCostEntity : list) {
			//调用返利测算，获取返利
			CalculatorEntity calculatorEntity = new CalculatorEntity();
			calculatorEntity.setFinancingDate(ycejFinanceCostEntity.getFinancingDate());//融资期限
			calculatorEntity.setShopAttr(ycejFinanceCostEntity.getShopAttr());//店铺属性
			calculatorEntity.setAllCharge(ycejFinanceCostEntity.getAllCharge());//融资总额
			calculatorEntity.setGpsCharge(ycejFinanceCostEntity.getGpsFee().intValue());//GPS融资额
			calculatorEntity.setInsurancePrice(ycejFinanceCostEntity.getComInsurance());//商业险
			calculatorEntity.setServiceCharge(ycejFinanceCostEntity.getServiceCharge());//服务费
			CalculatorEntity entity=calculatorService.caiwuRe(calculatorEntity);
			ycejFinanceCostEntity.setReturnedGps(entity.getGpsRe());//GPS返利
			ycejFinanceCostEntity.setReturnedInsurance(entity.getInsuranceRe());//返保险
		}
		return list;
	}

}
