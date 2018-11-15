package com.yicheejia.modules.operate.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.modules.operate.dao.CalculatorDao;
import com.yicheejia.modules.operate.entity.CalculatorEntity;
import com.yicheejia.modules.operate.service.CalculatorService;
import com.yicheejia.modules.product.entity.ProductInfoEntity;
import com.yicheejia.modules.product.service.ProductInfoService;

@Service("CalculatorService")
public class CalculatorServiceImpl extends ServiceImpl<CalculatorDao, CalculatorEntity> implements CalculatorService 
{
	@Autowired
	private ProductInfoService productInfoService;

	/**
	 * 返利测算
	 */
	@Override
	public CalculatorEntity insurance(CalculatorEntity calculatorEntity) 
	{
		//商业险总金额
		BigDecimal insurancePrice = insurancePrice(calculatorEntity);
		//交强险总金额（含车船税）
		BigDecimal jqxPrice = jqxPrice(calculatorEntity);
		//商业险返利
		BigDecimal insuranceRe = null;
		//门店属性
		int shopAttr = calculatorEntity.getShopAttr();
		//返利总额
		BigDecimal allRebate = null;
		//GPS融资额
		int gpsCharge = 0;
		if(null != calculatorEntity.getGpsCharge()) {
			gpsCharge = calculatorEntity.getGpsCharge();
		}
		//GPS返利
		BigDecimal gpsChargeRe = new BigDecimal(0);
		if (gpsCharge != 0) 
		{
			gpsChargeRe = new BigDecimal(gpsCharge-600);
		}
		else if(gpsCharge == 0)
		{
			gpsChargeRe = new BigDecimal(0);
		}
		
		int serviceCharge = 0;
		if(null != calculatorEntity.getServiceCharge()) {
			//金融服务费融资额
			serviceCharge = calculatorEntity.getServiceCharge();
		}
		BigDecimal allCharge = null;
		if(null != calculatorEntity.getAllCharge()) {
			//融资总额
			allCharge = calculatorEntity.getAllCharge();
		}
		String financingDate = null;
		if(null != calculatorEntity.getFinancingDate()) {
			//融资期限24期1；36期2
			financingDate = calculatorEntity.getFinancingDate();
		}
		
		
		/**
		 * 保险返利*(仅返商业)
		 */
		insuranceRe = (insurancePrice.divide(new BigDecimal(1.06),2, RoundingMode.HALF_UP).multiply(new BigDecimal(0.2))).setScale(2, RoundingMode.HALF_UP);
		
		calculatorEntity.setJqxPrice(jqxPrice);
		calculatorEntity.setInsurancePrice(insurancePrice);
		calculatorEntity.setInsuranceRebate(insuranceRe);
		
		/**
		 * 金融返利
		 */
		if(shopAttr==0 && null != calculatorEntity.getProductId()) {
			/**
			 * 县城店：参与返佣计算金额=融资总额-GPS融资额-金融服务费融资额
			 * 返利总额=（融资总额-GPS融资额-金融服务费融资额）*产品返利点数+（GPS融资额-600）+金融服务费融资额+（商业保险总额-车船税）/1.06*0.2
			 */
			//产品方案ID
			String productId = calculatorEntity.getProductId();

			BigDecimal productRebate = productRebate(productId, allCharge, gpsCharge, serviceCharge, financingDate);
			
			calculatorEntity.setProductRebate(productRebate);
			//返利总额
			allRebate = (new BigDecimal(serviceCharge).add(gpsChargeRe).add(insuranceRe).add(productRebate)).setScale(2, RoundingMode.HALF_UP);
		}else if(shopAttr==1) {
			/**
			 * 乡镇店：返利总额=（GPS融资额-600）+金融服务费融资额+（保险总额-车船税）/1.06*0.2 + 500（分期奖励）
			 */
			//返利总额
			allRebate = (new BigDecimal(serviceCharge+500).add(gpsChargeRe).add(insuranceRe)).setScale(2, RoundingMode.HALF_UP);
		}
		
		calculatorEntity.setAllRebate(allRebate);

		return calculatorEntity;
	}
	/**
	 * 商业险
	 * @param calculatorEntity
	 * @return
	 */
	public BigDecimal insurancePrice(CalculatorEntity calculatorEntity) {
		
		BigDecimal insurancePrice = null;
		//车价
		double salePrice = calculatorEntity.getSalePrice();
		//购置税
		double gzs = salePrice/1.17*0.1;
		//座位数
		int seatNum = calculatorEntity.getSeatNum();
		//第三者责任险
		int third = calculatorEntity.getThird();
		//乘员责任险(驾驶人)
		int driver = calculatorEntity.getDriver();
		//乘员责任险(乘客)
		int passenger = calculatorEntity.getPassenger();
		//盗抢险
		int robbery = calculatorEntity.getRobbery();
		//车身划痕险
		int scratch = calculatorEntity.getScratch();
		//基本险不计免赔
		int noCompensate = calculatorEntity.getNoCompensate();
		//车辆损失险
		int lose = calculatorEntity.getLose();
		//玻璃单独破碎险
		int glass = calculatorEntity.getGlass();
		//车辆损失险保费
		double losePrice = 0;
		//第三者责任险保费
		double thirdPrice = 0;
		//成员责任险保费（司机）
		double driverPrice = 0;
		//成员责任险保费（乘客）
		double passengerPrice = 0;
		//车辆盗抢险保费
		double robberyPrice = 0;
		//玻璃破碎险金额
		double glassPrice = 0;
		//车身划痕险保费
		double scratchPrice = 0;
		//基本险不计免赔保费
		double bjmpPrice = 0;
		
		/**
		 *  商业保险
		 */
		//6座及以下保费
		if(seatNum == 0)
		{
			//车辆损失险
			if(lose == 1)
			{
				losePrice = 630+(salePrice*0.015);
			}
			//第三者责任保险(1:30万；2:50万；3:100万)
			if(third == 1)
			{
				thirdPrice = 1492;
			}
			else if(third == 2)
			{
				thirdPrice = 1772;
			}
			else if(third == 3)
			{
				thirdPrice = 2308;
			}
			//乘员责任险按10000元计算(司机)
			if(driver == 1)
			{
				driverPrice = (10000*0.0042);
			}
			//乘员责任险按10000元计算(乘客)
			if(passenger == 1)
			{
				passengerPrice = (10000*0.0027*4);
			}
			//车辆盗抢险
			if(robbery == 1)
			{
				robberyPrice = ((gzs+salePrice)*0.0049)+120;
			}
			//玻璃单独破碎险
			if(glass == 1)
			{
				glassPrice = salePrice *0.0019;
			}
			else if(glass == 2)
			{
				glassPrice = salePrice *0.0031;
			}
		}
		//6座以上
		if(seatNum == 1)
		{
			//车辆损失险
			if(lose == 1)
			{
				losePrice = 756+(salePrice*0.015);
			}
			//第三者责任保险(1:30万；2:50万；3:100万)
			if(third == 1)
			{
				thirdPrice = 1277;
			}
			else if(third == 2)
			{
				thirdPrice = 1517;
			}
			else if(third == 3)
			{
				thirdPrice = 1976;
			}
			//乘员责任险 7座（司机）
			if(driver == 1)
			{
				driverPrice = (10000*0.0040);
			}
			//乘员责任险 7座（乘客）
			if(passenger == 1)
			{
				passengerPrice = (10000*0.0026*6);
			}
			//车辆盗抢险
			if(robbery == 1)
			{
				robberyPrice = ((gzs+salePrice)*0.0044)+140;
			}
			//玻璃单独破碎险
			if(glass == 1)
			{
				glassPrice = salePrice *0.0019;
			}
			else if(glass == 2)
			{
				glassPrice = salePrice *0.0030;
			}
		}
		//车辆划痕险(车龄<=2,保费按最低2000计算)
		if(scratch==1) 
		{
			if(salePrice<300000)
			{
				scratchPrice = 400;
			}
			else if(salePrice>=300000 && 500000>salePrice)
			{
				scratchPrice = 585;
			}
			else if(salePrice>500000)
			{
				scratchPrice = 850;
			}
		}
		//基本险不计免赔
		if(noCompensate ==1) {
			bjmpPrice = (losePrice*0.15)+(thirdPrice*0.15)+((driverPrice+passengerPrice)*0.15)+(robbery*0.2);
		}
		//保险总额  
		insurancePrice = (new BigDecimal(losePrice+thirdPrice+(driverPrice+passengerPrice)+robberyPrice+glassPrice+scratchPrice+bjmpPrice)).setScale(2, RoundingMode.HALF_UP);

		return insurancePrice;
		
	}
	/**
	 * 交强险（含车船税）
	 * @param calculatorEntity
	 * @return
	 */
	public BigDecimal jqxPrice (CalculatorEntity calculatorEntity) {
		// 交强险
		double jqx = 0;
		//车船税
		int ccs = 0;
		//交强险总金额（含车船税）
		BigDecimal jqxPrice = null;
		//座位数
		int seatNum = calculatorEntity.getSeatNum();
		//排量
		double emissions = calculatorEntity.getEmissions();
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
		/**
		 *  交强险
		 */
		if (seatNum==0) {
			jqx = 950;
		}
		if (seatNum==1) {
			jqx = 1100;
		}
		jqxPrice = new BigDecimal(jqx+ccs);
		return jqxPrice;
	}
	//①合伙人应得保险返利
	public BigDecimal baoxianRe(BigDecimal insurancePrice) {
		//商业险返利
		BigDecimal insuranceRe = null;
		insuranceRe = (insurancePrice.divide(new BigDecimal(1.06),2, RoundingMode.HALF_UP).multiply(new BigDecimal(0.2))).setScale(2, RoundingMode.HALF_UP);
		return insuranceRe;
	}
	/**
	 * 返佣报表 返利明细
	 *	①合伙人应得保险返利
	 *	②产品返利
	 *	③手续费返利
	 *	④GPS返利
	 *	⑤合伙人返利
	 * @param calculatorEntity
	 * @return
	 */
	public CalculatorEntity caiwuRe(CalculatorEntity calculatorEntity) {
		//门店属性
		int shopAttr = calculatorEntity.getShopAttr();
		//融资总额
		BigDecimal allCharge = calculatorEntity.getAllCharge();
		//GPS融资额
		int gpsCharge = calculatorEntity.getGpsCharge();
		//金融服务费融资额
		int serviceCharge = calculatorEntity.getServiceCharge();
		//融资期限24期1；36期2
		String financingDate = calculatorEntity.getFinancingDate();
		//返利总额
		BigDecimal allRebate = null;
		//商业保险金额
		BigDecimal insurancePrice = calculatorEntity.getInsurancePrice();
		//金融产品返利
		BigDecimal productRebate = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
		//商业险返利
		BigDecimal insuranceRe = null;
		if(insurancePrice != null) {
			insuranceRe = (insurancePrice.divide(new BigDecimal(1.06),2, RoundingMode.HALF_UP).multiply(new BigDecimal(0.2))).setScale(2, RoundingMode.HALF_UP);
		}
		//GPS返利
		BigDecimal gpsChargeRe = new BigDecimal(0);
		if (gpsCharge != 0) 
		{
			gpsChargeRe = new BigDecimal(gpsCharge-600);
		}
		else if(gpsCharge == 0)
		{
			gpsChargeRe = new BigDecimal(0);
		}
		
		/**
		 * 金融返利
		 */
		if(shopAttr==0) {
			/**
			 * 县城店：参与返佣计算金额=融资总额-GPS融资额-金融服务费融资额
			 * 返利总额=（融资总额-GPS融资额-金融服务费融资额）*产品返利点数+（GPS融资额-600）+金融服务费融资额+（商业保险总额-车船税）/1.06*0.2
			 */
			List<ProductInfoEntity> list = productList();
			for(ProductInfoEntity product : list) {
				String productId = product.getProductId();
				//获取产品返利金额
				productRebate = productRebate(productId, allCharge, gpsCharge, serviceCharge, financingDate);
			}
			//返利总额
			allRebate = (new BigDecimal(serviceCharge).add(gpsChargeRe).add(insuranceRe).add(productRebate)).setScale(2, RoundingMode.HALF_UP);
		}else if(shopAttr==1) {
			/**
			 * 乡镇店：返利总额=（GPS融资额-600）+金融服务费融资额+（保险总额-车船税）/1.06*0.2 + 500（分期奖励）
			 */
			//返利总额
			allRebate = (new BigDecimal(serviceCharge+500).add(gpsChargeRe).add(insuranceRe)).setScale(2, RoundingMode.HALF_UP);
		}
		//⑤合伙人返利
		calculatorEntity.setAllRebate(allRebate);
		//②产品返利
		calculatorEntity.setProductRebate(productRebate);
		//①合伙人应得保险返利
		calculatorEntity.setInsuranceRe(insuranceRe);
		//④GPS返利
		calculatorEntity.setGpsRe(gpsChargeRe);
		//③手续费返利(全返)
		calculatorEntity.setServiceCharge(serviceCharge);
		
		return calculatorEntity;
	}
	/**
	 * 金融产品返利
	 * @param productId
	 * @param allCharge
	 * @param gpsCharge
	 * @param serviceCharge
	 * @param financingDate
	 * @return
	 */
	public BigDecimal productRebate(String productId,BigDecimal allCharge,int gpsCharge,int serviceCharge,String financingDate) {
		//金融产品返利
		BigDecimal productRebate = (allCharge.subtract(new BigDecimal(gpsCharge+serviceCharge))).setScale(2, RoundingMode.HALF_UP);
		//B类ZZ-壹车e家（331）无返佣
		if(productId.equals("20")) {
			productRebate = new BigDecimal(0);
		}
		//B类ZZ-壹车e家（336） 24期0.5%；36期1.0%
		else if(productId.equals("19")) {
			if("1".equals(financingDate)) {
				productRebate = (new BigDecimal(0.005).multiply(productRebate)).setScale(2, RoundingMode.HALF_UP);
			}else if("2".equals(financingDate)) {
				productRebate = (new BigDecimal(0.01).multiply(productRebate)).setScale(2, RoundingMode.HALF_UP);
			}
		}
		//B类ZZ-壹车e家（341） 24期1.0%；36期2.0%
		else if(productId.equals("18")) {
			if("1".equals(financingDate)) {
				productRebate = (new BigDecimal(0.01).multiply(productRebate)).setScale(2, RoundingMode.HALF_UP);
			}else if("2".equals(financingDate)) {
				productRebate = (new BigDecimal(0.02).multiply(productRebate)).setScale(2, RoundingMode.HALF_UP);
			}
		}
		//B类ZZ-壹车e家（346） 24期1.5%；36期3.0%
		else if(productId.equals("17")) {
			if("1".equals(financingDate)) {
				productRebate = (new BigDecimal(0.015).multiply(productRebate)).setScale(2, RoundingMode.HALF_UP);
			}else if("2".equals(financingDate)) {
				productRebate = (new BigDecimal(0.03).multiply(productRebate)).setScale(2, RoundingMode.HALF_UP);
			}
		}
		//B 类ZZ-壹车e 家-特惠贷（6 万） ，服务费返2000 元；
		else if(productId.equals("25")) {
			productRebate = new BigDecimal(2000.00).setScale(2, RoundingMode.HALF_UP);;
		}
		//B 类ZZ-壹车e 家-特惠贷（7 万） ，服务费返1500 元；
		else if(productId.equals("24")) {
			productRebate = new BigDecimal(1500.00).setScale(2, RoundingMode.HALF_UP);;
		}
		//B 类ZZ-壹车e 家-特惠贷（8 万） ，服务费返1000 元。
		else if(productId.equals("23")) {
			productRebate = new BigDecimal(1000.00).setScale(2, RoundingMode.HALF_UP);;
		}
		//B类ZZ-壹车e家-轻松融（475） 无返佣
		else if(productId.equals("35")) {
			if("1".equals(financingDate)) {
				productRebate = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);;
			}else{
				productRebate = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);;
			}
		}
		//B类ZZ-壹车e家-轻松融（480）24 期返标的×0.5%；
		else if(productId.equals("36")) {
			if("1".equals(financingDate)) {
				productRebate = (new BigDecimal(0.005).multiply(productRebate)).setScale(2, RoundingMode.HALF_UP);
			}else{
				productRebate = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);;
			}
		}
		//B类ZZ-壹车e家-轻松融（485）24 期返标的×1.0%；
		else if(productId.equals("40")) {
			if("1".equals(financingDate)) {
				productRebate = (new BigDecimal(0.01).multiply(productRebate)).setScale(2, RoundingMode.HALF_UP);
			}else{
				productRebate = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);;
			}
		}
		//B类ZZ-壹车e家-轻松融（490）24 期返标的×1.5%
		else if(productId.equals("39")) {
			if("1".equals(financingDate)) {
				productRebate = (new BigDecimal(0.015).multiply(productRebate)).setScale(2, RoundingMode.HALF_UP);
			}else{
				productRebate = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
			}
		}
		//B 类ZZ-壹车e 家-汽贸贷（332） ，888/471/332，无返佣；
		else if(productId.equals("37")) {
			productRebate = new BigDecimal(0);	
		}
		//B 类ZZ-壹车e 家-汽贸贷（337） ，893/475/337，24 期返标的×0.5%,36 期返标的×1.0% 。
		else if(productId.equals("38")) {
			if("1".equals(financingDate)) {
				productRebate = (new BigDecimal(0.005).multiply(productRebate)).setScale(2, RoundingMode.HALF_UP);
			}else if("2".equals(financingDate)) {
				productRebate = (new BigDecimal(0.01).multiply(productRebate)).setScale(2, RoundingMode.HALF_UP);
			}
		}
		return productRebate;
	}
	
	/**
	 * 获取产品LIST
	 * @return
	 */
	public List<ProductInfoEntity> productList(){
		//筛选状态为使用中的产品
		List<ProductInfoEntity> list = productInfoService.selectProductList();
		//List<ProductInfoEntity> list = baseMapper.selectList(null);
		
		return list;
	}
}
