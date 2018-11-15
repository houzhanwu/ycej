package com.yicheejia.modules.operate.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.modules.operate.dao.InsuranceCalculatorDao;
import com.yicheejia.modules.operate.entity.InsuranceCalculatorEntity;
import com.yicheejia.modules.operate.service.InsuranceCalculatorService;
/**
 * 2018/10/17 
 * @author LW
 *
 */
@Service("insuranceCalculatorService")
public class InsuranceCalculatorServiceImpl extends ServiceImpl<InsuranceCalculatorDao, InsuranceCalculatorEntity> implements InsuranceCalculatorService {

	@Override
	public InsuranceCalculatorEntity calculate(InsuranceCalculatorEntity insuranceCalculatorEntity) {
		// 从页面获取车辆价格
		BigDecimal carPrice = insuranceCalculatorEntity.getCarPrice();
		// 座位数 0（6座以下）；1（6座以上）
		String seatNum = insuranceCalculatorEntity.getSeatNum();
		// 排量 1为1.6L及以下；0为1.6L以上
		String emissions = insuranceCalculatorEntity.getEmissions();
		// 第三者责任险1有；0没有勾选
		String dsz = insuranceCalculatorEntity.getDsz();
		// 车辆损失险1有；0没有勾选
		String clss = insuranceCalculatorEntity.getClss();
		// 乘员责任险1有；0没有勾选(司机)
		String cyzrDriver = insuranceCalculatorEntity.getCyzrDriver();
		// 乘员责任险1有；0没有勾选(司机)
		String cyzrPassenger = insuranceCalculatorEntity.getCyzrPassenger();
		// 盗抢险1有；0没有勾选
		String dq = insuranceCalculatorEntity.getDq();
		// 玻璃破碎险1(国产)；2（进口）；0没有勾选
		String blps = insuranceCalculatorEntity.getBlps();
		// 车身划痕险1有；0没有勾选
		String cshh = insuranceCalculatorEntity.getCshh();
		// 基本险不计免赔1有；0没有勾选
		String bjmp = insuranceCalculatorEntity.getBjmp();
		//无法找到第三方责任险
		String noOther = insuranceCalculatorEntity.getNoOther();
		// 交强险
		double jqx = getjqx(seatNum);
		// 车船税
		double ccs = getccs(emissions);
		// 商业保险
		double baoxian = baoxian(carPrice, seatNum, dsz, clss, cyzrDriver, cyzrPassenger, dq, blps, cshh, bjmp, noOther);
		
		insuranceCalculatorEntity.setCcs(BigDecimal.valueOf(ccs));
		insuranceCalculatorEntity.setInsurancePrice(BigDecimal.valueOf(baoxian).setScale(2,RoundingMode.UP));
		insuranceCalculatorEntity.setJqxPrice(BigDecimal.valueOf(jqx));
		
		return insuranceCalculatorEntity;
	}

	/**
	 * 获取交强险金额
	 * @param seatNum
	 * @return
	 */
	private double getjqx(String seatNum) {
		double jqxPrice = 0;
		if (seatNum.equals("0")) {
			jqxPrice = 950;
		}
		if (seatNum.equals("1")) {
			jqxPrice = 1100;
		}
		return jqxPrice;
	}

	/**
	 * 获取保险金额
	 * @param carPrice 车价
	 * @param seatNum 座位数
	 * @param dsz 三责
	 * @param clss 车损
	 * @param cyzrDriver 乘员（司机）
	 * @param cyzrPassenger 乘员（乘客）
	 * @param dq 盗抢
	 * @param blps 玻璃皮髓
	 * @param cshh 车身划痕
	 * @param bjmp 不计免赔
	 * @param noOther 无法找到第三方
	 * @return
	 */
	private double baoxian(BigDecimal carPrice, String seatNum, String dsz, String clss, String cyzrDriver,
			String cyzrPassenger, String dq, String blps, String cshh, String bjmp, String noOther) 
	{
		//各险种对应金额
		double clssPrice = 0;
		double dszPrice = 0;
		double cyzrDriverPrice = 0;
		double cyzrPassengerPrice = 0;
		double dqPrice = 0;
		double blpsPrice = 0;
		double cshhPrice = 0;
		double bjmpPrice = 0;
		double baoxianPrice = 0;
		double noOtherPrice = 0;
		
		BigDecimal gzs = carPrice.divide(new BigDecimal("11.7"),2,BigDecimal.ROUND_UP);
		
				
		//6座以下保费
		if("0".equals(seatNum))
		{
			//车辆损失险
			if("1".equals(clss))
			{
				clssPrice = 630+(carPrice.doubleValue()*0.015);
			}
			//第三者责任保险
			if("1".equals(dsz))
			{
				dszPrice = 1772;
			}
			//第三者责任保险100W
			if("2".equals(dsz))
			{
				dszPrice = 2308;
			}
			//乘员责任险按10000元计算(司机)
			if("1".equals(cyzrDriver)) 
			{
				cyzrDriverPrice = 10000*0.0042;
			}
			//乘员责任险按10000元计算(乘客)
			if("1".equals(cyzrDriver))
			{
				cyzrPassengerPrice = 10000*0.0027*4;
			}
			//车辆盗抢险
			if("1".equals(dq))
			{
				dqPrice = ((gzs.add(carPrice).doubleValue())*0.0049)+120;
			}
		}
		//6座以上
		if("1".equals(seatNum))
		{
			//车辆损失险
			if("1".equals(clss))
			{
				clssPrice = 756+(carPrice.doubleValue()*0.015);
			}
			//第三者责任保险
			if("1".equals(dsz))
			{
				dszPrice = 1517;
			}
			//第三者责任保险100W
			if("2".equals(dsz))
			{
				dszPrice = 1976;
			}
			//乘员责任险 7座(司机)
			if("1".equals(cyzrDriver))
			{
				cyzrDriverPrice = 10000*0.0040;
			}
			//乘员责任险按10000元计算(乘客)
			if("1".equals(cyzrDriver))
			{
				cyzrPassengerPrice = 10000*0.0026*6 ;
			}
			//车辆盗抢险
			if("1".equals(dq))
			{
				dqPrice = (gzs.add(carPrice).doubleValue()*0.0044)+140;
			}
		}
		
		//玻璃单独破碎险
		if("1".equals(blps))
		{
			blpsPrice = carPrice.doubleValue() *0.0019;
		}
		//车辆划痕险
		if("1".equals(cshh))
		{
			cshhPrice = 600;
		}
		//找不到第三方特约责任险
		if("1".equals(noOther)) {
			noOtherPrice = clssPrice*0.025;
		}
		//不计免赔险
		bjmpPrice = (clssPrice*0.15)+(dszPrice*0.15)+(cyzrDriverPrice*0.15)+(cyzrPassengerPrice*0.15)+(dqPrice*0.2);
		//保险总额  
		baoxianPrice = clssPrice+dszPrice+cyzrDriverPrice+cyzrPassengerPrice+dqPrice+blpsPrice+cshhPrice+bjmpPrice+noOtherPrice;
		return baoxianPrice;
		
	}

	/**
	 * 获取车船税
	 * @param emissions
	 * @return
	 */
	private double getccs(String emissions) {
		double ccs = 0;
		Calendar calen = Calendar.getInstance();
		//获取当月月份
		int month = calen.get(Calendar.MONTH)+1;
		
		double emission = Double.parseDouble(emissions);
		
		//车船税 购车当月到12月所交的车船税（含当月）
		if(emission>1.0 && emission<=1.6)
		{
			ccs = (300/12)*(12-month+1);
		}
		if(emission>1.6 && emission<=2.0)
		{
			ccs = (420/12)*(12-month+1);
		}
		if(emission>2.0 && emission<=2.5)
		{
			ccs = (720/12)*(12-month+1);
		}
		if(emission>2.5 && emission<=3.0)
		{
			ccs = (1500/12)*(12-month+1);
		}
		return ccs;
	}

}
