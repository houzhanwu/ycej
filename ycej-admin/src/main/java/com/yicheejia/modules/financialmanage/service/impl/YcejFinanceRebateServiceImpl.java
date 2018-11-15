package com.yicheejia.modules.financialmanage.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.annotation.ShopFilter;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.financialmanage.dao.YcejFinanceRebateDao;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceRebateEntity;
import com.yicheejia.modules.financialmanage.service.YcejFinanceRebateService;
import com.yicheejia.modules.operate.entity.CalculatorEntity;
import com.yicheejia.modules.operate.service.CalculatorService;
@Service("ycejFinanceRebateService")
public class YcejFinanceRebateServiceImpl extends ServiceImpl<YcejFinanceRebateDao,YcejFinanceRebateEntity> implements YcejFinanceRebateService {

	@Autowired
	private CalculatorService calculatorService;
	@Override
	public LayuiPage queryPage(Map<String, Object> params) {
		/*HashMap<String, Object> map = new HashMap<>();
		Object customerName = params.get("customerName");
		if (customerName!= null &&!"".equals(customerName.toString())) {
			map.put("customerName", customerName);
		}*/
		Page<YcejFinanceRebateEntity> page=new Query<YcejFinanceRebateEntity>(params).getPage();
		List<YcejFinanceRebateEntity> list = baseMapper.selectAllPage(page,params);
		for (YcejFinanceRebateEntity ycejFinanceRebateEntity : list) {
			//调用返利测算，获取返利、
			//融资总额=车款-首付款+所有其他融资金额
			CalculatorEntity calculatorEntity = new CalculatorEntity();
			calculatorEntity.setFinancingDate(ycejFinanceRebateEntity.getFinancingDate());//融资期限
			calculatorEntity.setShopAttr(ycejFinanceRebateEntity.getShopAttr());//门店属性
			calculatorEntity.setAllCharge(ycejFinanceRebateEntity.getAllCharge());//融资总额
			calculatorEntity.setGpsCharge(ycejFinanceRebateEntity.getGpsFee().intValue());//GPS融资额
			calculatorEntity.setServiceCharge(ycejFinanceRebateEntity.getServiceCharge());//服务费
			calculatorEntity.setInsurancePrice(ycejFinanceRebateEntity.getComInsurance());//商业险
			CalculatorEntity entity=calculatorService.caiwuRe(calculatorEntity);
			ycejFinanceRebateEntity.setGps_rebate(entity.getGpsRe());//GPS返利
			ycejFinanceRebateEntity.setInsurance_rebate(entity.getInsuranceRe());//保险返利
			ycejFinanceRebateEntity.setProduct_rebate(entity.getProductRebate());//产品返利（由融资总额、融资期限、产品方案的id 计算所得）
			ycejFinanceRebateEntity.setPartner_rebate(entity.getAllRebate());//合伙人返利
			ycejFinanceRebateEntity.setService_rebate(new BigDecimal(entity.getServiceCharge()));//手续费返利
		}
		page.setRecords(list);
		return new LayuiPage(page.getRecords(), page.getTotal());
	}
	@Override
	public List<YcejFinanceRebateEntity> selectExportData(Long[] ids) {
		List<YcejFinanceRebateEntity> list = baseMapper.selectExportData(Arrays.asList(ids));
		for (YcejFinanceRebateEntity ycejFinanceRebateEntity : list) {
			//调用返利测算，获取返利、
			//融资总额=车款-首付款+所有其他融资金额
			CalculatorEntity calculatorEntity = new CalculatorEntity();
			calculatorEntity.setFinancingDate(ycejFinanceRebateEntity.getFinancingDate());//融资期限
			calculatorEntity.setShopAttr(ycejFinanceRebateEntity.getShopAttr());//门店属性
			calculatorEntity.setAllCharge(ycejFinanceRebateEntity.getAllCharge());//融资总额
			calculatorEntity.setGpsCharge(ycejFinanceRebateEntity.getGpsFee().intValue());//GPS融资额
			calculatorEntity.setServiceCharge(ycejFinanceRebateEntity.getServiceCharge());//服务费
			calculatorEntity.setInsurancePrice(ycejFinanceRebateEntity.getComInsurance());//商业险
			CalculatorEntity entity=calculatorService.caiwuRe(calculatorEntity);
			ycejFinanceRebateEntity.setGps_rebate(entity.getGpsRe());//GPS返利
			ycejFinanceRebateEntity.setInsurance_rebate(entity.getInsuranceRe());//保险返利
			ycejFinanceRebateEntity.setProduct_rebate(entity.getProductRebate());//产品返利（由融资总额、融资期限、产品方案的id 计算所得）
			ycejFinanceRebateEntity.setPartner_rebate(entity.getAllRebate());//合伙人返利
			ycejFinanceRebateEntity.setService_rebate(new BigDecimal(entity.getServiceCharge()));//手续费返利
		}
		return list;
	}
	/**
	 * 门店返佣报表，包含数据过滤
	 */
	@Override
	@ShopFilter
	public LayuiPage queryShopPage(Map<String, Object> params) {
		/*HashMap<String, Object> map = new HashMap<>();
		Object customerName = params.get("customerName");
		if (customerName!= null &&!"".equals(customerName.toString())) {
			map.put("customerName", customerName);
		}*/
		Page<YcejFinanceRebateEntity> page=new Query<YcejFinanceRebateEntity>(params).getPage();
		List<YcejFinanceRebateEntity> list = baseMapper.selectAllShopPage(page,params);
		for (YcejFinanceRebateEntity ycejFinanceRebateEntity : list) {
			//调用返利测算，获取返利、
			//融资总额=车款-首付款+所有其他融资金额
			CalculatorEntity calculatorEntity = new CalculatorEntity();
			calculatorEntity.setFinancingDate(ycejFinanceRebateEntity.getFinancingDate());//融资期限
			calculatorEntity.setShopAttr(ycejFinanceRebateEntity.getShopAttr());//门店属性
			calculatorEntity.setAllCharge(ycejFinanceRebateEntity.getAllCharge());//融资总额
			calculatorEntity.setGpsCharge(ycejFinanceRebateEntity.getGpsFee().intValue());//GPS融资额
			calculatorEntity.setServiceCharge(ycejFinanceRebateEntity.getServiceCharge());//服务费
			calculatorEntity.setInsurancePrice(ycejFinanceRebateEntity.getComInsurance());//商业险
			CalculatorEntity entity=calculatorService.caiwuRe(calculatorEntity);
			ycejFinanceRebateEntity.setGps_rebate(entity.getGpsRe());//GPS返利
			ycejFinanceRebateEntity.setInsurance_rebate(entity.getInsuranceRe());//保险返利
			ycejFinanceRebateEntity.setProduct_rebate(entity.getProductRebate());//产品返利（由融资总额、融资期限、产品方案的id 计算所得）
			ycejFinanceRebateEntity.setPartner_rebate(entity.getAllRebate());//合伙人返利
			ycejFinanceRebateEntity.setService_rebate(new BigDecimal(entity.getServiceCharge()));//手续费返利
		}
		page.setRecords(list);
		return new LayuiPage(page.getRecords(), page.getTotal());
	}


}
