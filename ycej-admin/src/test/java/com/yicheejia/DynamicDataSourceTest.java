package com.yicheejia;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.HttpsClientUtil;
import com.yicheejia.modules.operate.entity.InventoryEntity;
import com.yicheejia.modules.operate.service.InventoryService;
import com.yicheejia.modules.order.entity.PutmoneyInfoEntity;
import com.yicheejia.modules.order.service.PutmoneyInfoService;
import com.yicheejia.modules.product.entity.ProductInfoEntity;
import com.yicheejia.modules.shop.service.CustomerService;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.webservices.model.CustomerPaymentInfo;
import com.yicheejia.modules.webservices.model.RspVO;
import com.yicheejia.service.DataSourceTestService;

import net.sf.json.JSONObject;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {
    @Autowired
    private DataSourceTestService dataSourceTestService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PutmoneyInfoService putmoneyInfoService;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void test(){
        //数据源1
       /* SysUserEntity user1 = dataSourceTestService.queryUser(1L);
        System.out.println(ToStringBuilder.reflectionToString(user1));

        //数据源2
        SysUserEntity user2 = dataSourceTestService.queryUser2(1L);
        System.out.println(ToStringBuilder.reflectionToString(user2));

        //数据源1
        SysUserEntity user3 = dataSourceTestService.queryUser(1L);
        System.out.println(ToStringBuilder.reflectionToString(user3));*/
        /*List<InventoryEntity> inventoryList= dataSourceTestService.getlist();
        for (InventoryEntity inventoryEntity : inventoryList) {
        	System.err.println(inventoryEntity.getVin());
			
		}*/
    	/*List<ProductInfoEntity> productInfoEntities=dataSourceTestService.getPeList();
    	
    	for (ProductInfoEntity productInfoEntity : productInfoEntities) {
			System.err.println(productInfoEntity);
		}*/
    	
    	String string = "{'idCardNo':'410222198303102517','applyNo':'666666','customerName':'豪哥','incomeAccountNo':'6666','incomeBank':'测试银行','paymentAccount':'测试','paymentBank':'测试'}";
    	JSONObject jsonObject = JSONObject.fromObject(string);
    	String url = "";
    	/*if (info!=null && StringUtils.isNotBlank(info.getIdCardNo())) {
    		//根据用户身份证查询库中是否已经存在，不存在进行插入操作，存在进行更新操作
    		logger.info("融资租赁客户付款成功信息推送："+info.toString());
        	   Map<String, Object> columnMap = new HashMap<>();
        	   columnMap.put("idcard_no", info.getIdCardNo());
        	   List<PutmoneyInfoEntity> list = putmoneyInfoService.selectByMap(columnMap);
        	   PutmoneyInfoEntity infoEntity = new PutmoneyInfoEntity();
        	   if (list!=null && list.size()>0) {
    			//更新
        		   infoEntity.setApplyNo(info.getApplyNo());
        		   infoEntity.setCreateTime(DateUtils.getDate());
        		   infoEntity.setCustomerName(info.getCustomerName());
        		   infoEntity.setIdcardNo(info.getIdCardNo());
        		   infoEntity.setIncomeAccountName(info.getIncomeAccountName());
        		   infoEntity.setIncomeAccountNo(info.getIncomeAccountNo());
        		   infoEntity.setIncomeBank(info.getIncomeBank());
        		   infoEntity.setPayDate(info.getPayDate());
        		   infoEntity.setPaymentAccount(info.getPaymentAccount());
        		   infoEntity.setPaymentBank(info.getPaymentBank());
        		   infoEntity.setPaymentTotal(info.getPaymentTotal());
        		   infoEntity.setPutmoneyInfoId(list.get(0).getPutmoneyInfoId());
        		   putmoneyInfoService.updateById(infoEntity);
    		}else {
    			//新增
    			infoEntity.setApplyNo(info.getApplyNo());
     		   infoEntity.setCreateTime(DateUtils.getDate());
     		   infoEntity.setCustomerName(info.getCustomerName());
     		   infoEntity.setIdcardNo(info.getIdCardNo());
     		   infoEntity.setIncomeAccountName(info.getIncomeAccountName());
     		   infoEntity.setIncomeAccountNo(info.getIncomeAccountNo());
     		   infoEntity.setIncomeBank(info.getIncomeBank());
     		   infoEntity.setPayDate(info.getPayDate());
     		   infoEntity.setPaymentAccount(info.getPaymentAccount());
     		   infoEntity.setPaymentBank(info.getPaymentBank());
     		   infoEntity.setPaymentTotal(info.getPaymentTotal());
    			putmoneyInfoService.insert(infoEntity);
    		}
        	 
    	}*/
    	
    	
    }

}
