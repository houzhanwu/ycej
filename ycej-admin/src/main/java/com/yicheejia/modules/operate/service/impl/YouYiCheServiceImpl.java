package com.yicheejia.modules.operate.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.modules.operate.dao.YouYiCheDao;
import com.yicheejia.modules.operate.entity.YouYiCheEntity;
import com.yicheejia.modules.operate.service.YouYiCheService;

@Service("youYiCheService")
public class YouYiCheServiceImpl extends ServiceImpl<YouYiCheDao, YouYiCheEntity> implements YouYiCheService{

	@Override
	public YouYiCheEntity calculate(YouYiCheEntity youYiCheEntity) {
		
		//指导价
		BigDecimal carPrice = youYiCheEntity.getCarPrice();
		
		//采购价
		BigDecimal purchCostPrice = youYiCheEntity.getPurchCostPrice();
		
		//运管费(测算器中写死) 2018/10/17 由于公司政策由1050调整为1500
		BigDecimal transport = new BigDecimal(1500);
		
		//GPS融资额
		BigDecimal gpsCharge = new BigDecimal(3000);
		
		//购置税(进位取整)
		BigDecimal purchaseTax = carPrice.divide(new BigDecimal(11.6),0,RoundingMode.UP);
		
		//奖励
		BigDecimal reward = new BigDecimal(1400);
		
		if(purchCostPrice.compareTo(new BigDecimal(100000)) == 1) 
		{
			reward = new BigDecimal(2400);
		}
		else if(purchCostPrice.compareTo(new BigDecimal(100000)) == -1) 
		{
			reward = new BigDecimal(1400);
		}
		
		//业务奖励
		BigDecimal businessReward = new BigDecimal(1500);
		
		//车价录入金额
		BigDecimal carPriceInput = purchCostPrice.add(transport).add(reward).add(businessReward);
		
		//首付
		BigDecimal firstPayment = new BigDecimal(0);
		
		if(carPriceInput.compareTo(new BigDecimal(250000)) == 1 || carPriceInput.compareTo(new BigDecimal(250000)) == 0) 
		{
			firstPayment = carPriceInput.multiply(new BigDecimal(0.1)) ;
		}
		if(carPriceInput.compareTo(new BigDecimal(250000)) == -1) 
		{
			firstPayment = new BigDecimal(15800);
		}
		if(carPriceInput.compareTo(new BigDecimal(150000)) == -1)
		{
			firstPayment = new BigDecimal(12800);
		}
		if(carPriceInput.compareTo(new BigDecimal(100000)) == -1)
		{
			firstPayment = new BigDecimal(9800);
		}
		
		//提车服务费
		BigDecimal servicePrice = new BigDecimal(0);
		
		if(carPriceInput.compareTo(new BigDecimal(150000)) == -1)
		{
			servicePrice = new BigDecimal(2600);
		}else 
		{
			servicePrice = new BigDecimal(3600);
		}
		
		//车船税
		int ccs = 0;
		// 交强险
		double jqx = 0;
		//座位数
		int seatNum = youYiCheEntity.getSeatNum();
		//排量
		double emissions = youYiCheEntity.getEmissions();
		/**
		 * 车船税 河南省税务局征收标准1.0L-1.6L 300/年 ；1.6L-2.0L 420/年；2.0升以上至2.5升（含）的，
		 * 每辆车年税额为720元；2.5升以上至3.0升（含）的，每辆车年税额为1500元。首年由交强险按月代收
		 * @param dspmt 排量 1代表1.0L-1.6L；2代表1.6L-2.0L；3代表2.0L-2.5L；4代表2.5L-3.0L
		 * 车船税 购车当月到12月所交的车船税（含当月）
		 */
		Calendar calen = Calendar.getInstance();
		//获取当月月份
		int month = calen.get(Calendar.MONTH)+1;
		
		if(1.0 < emissions && emissions <= 1.6)
		{
			ccs = (300/12)*(12-month+1);
		}
		if(1.6 < emissions && emissions <= 2.0)
		{
			ccs = (420/12)*(12-month+1);
		}
		if(2.0 < emissions && emissions <= 2.5)
		{
			ccs = (720/12)*(12-month+1);
		}
		if(2.5 < emissions && emissions <= 3.0)
		{
			ccs = (1500/12)*(12-month+1);
		}
		/* 修改优壹车交强险金额 企业非营业车辆交强险费率区别于家庭自用车 2018/10/18 modify by lw */
		if (seatNum < 6) {
			jqx = 1000;
		}
		if (seatNum >= 6) {
			jqx = 1130;
		}
		//商业险
		//车损险保费
		double losePrice = 0;
		//第三责任险
		double thirdPrice = 0;
		//不计免赔险
		double bjmpPrice = 0;
		//找不到第三方特约责任险
		double nothirdPrice = 0;
		//盗抢险 update by luwen 2018/10/15
		double dqprice = 0;
		//6座及以下保费
		if(seatNum <= 6)
		{
			losePrice = 630+(purchCostPrice.doubleValue()*0.015);
			//第三者责任保险(50万) 改成100w update by luwen 2018/10/15
			thirdPrice = 2308;
			dqprice = ((purchaseTax.add(purchCostPrice).doubleValue())*0.0049)+120;
		}
		//6座以上
		if(seatNum > 6)
		{
			//车辆损失险
			losePrice = 756+(purchCostPrice.doubleValue()*0.015);
			//第三者责任保险(50万) 改成100w update by luwen 2018/10/15
			thirdPrice = 1976;
			dqprice = ((purchaseTax.add(purchCostPrice).doubleValue())*0.0044)+140;
		}
		//基本险不计免赔
		bjmpPrice = (losePrice*0.15)+(thirdPrice*0.15)+(dqprice*0.2);
		//找不到第三方特约责任险
		nothirdPrice = losePrice*0.025;
		//保险总额  
		BigDecimal insurancePrice = (new BigDecimal(losePrice+thirdPrice+dqprice+bjmpPrice+nothirdPrice)).setScale(2, RoundingMode.HALF_UP);
		
		/**
		* 计算月供
		* @param rate 年利率 年利率除以12就是月利率
		* @param term 贷款期数，单位月
		* @param financeAmount  贷款金额
		* @return
		*/
		//融资额
		double financeAmount = purchCostPrice.add(transport).add(gpsCharge).add(purchaseTax).add(new BigDecimal(jqx))
				.add(new BigDecimal(ccs)).add(insurancePrice).add(reward).add(businessReward).subtract(firstPayment).doubleValue();
		//36期月供 = PMT(15%/12,36,-(采购价+运管费+GPS融资额+车辆购置税+交强险+商业险+车船税-车价首付+奖励+业务奖励))
	    double v = (1+(0.15/12)); 
	    double t = (-(36/12)*12); 
	    double monthlySupply=(financeAmount*(0.15/12))/(1-Math.pow(v,t));
	
		//48期月供 = PMT(15%/12,48,-(采购价+运管费+GPS融资额+车辆购置税+交强险+商业险+车船税-车价首付+奖励+业务奖励))
	    double v1 = (1+(0.15/12)); 
	    double t1 = (-(48/12)*12); 
	    double monthlySupplys=(financeAmount*(0.15/12))/(1-Math.pow(v1,t1));
	    
	    //奖励
	    youYiCheEntity.setReward(reward);
	    //业务奖励
	    youYiCheEntity.setBusinessReward(businessReward);
	    //车价首付
	    youYiCheEntity.setFirstPayment(firstPayment);
	    //提车服务费
	    youYiCheEntity.setServicePrice(servicePrice);
	    //36期月供
	    youYiCheEntity.setMonthlySupply(new BigDecimal(monthlySupply).setScale(0, RoundingMode.UP));
	    //48期月供
	    youYiCheEntity.setMonthlySupplys(new BigDecimal(monthlySupplys).setScale(0, RoundingMode.UP));
	    
		return youYiCheEntity;
	}

}
