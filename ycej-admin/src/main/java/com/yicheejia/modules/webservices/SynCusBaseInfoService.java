package com.yicheejia.modules.webservices;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yicheejia.modules.order.entity.PutmoneyInfoEntity;
import com.yicheejia.modules.order.service.PutmoneyInfoService;
import com.yicheejia.modules.shop.entity.CustomerEntity;
import com.yicheejia.modules.webservices.model.CustomerPaymentInfo;

import org.apache.commons.collections4.Put;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.validator.ValidatorUtils;
import com.yicheejia.modules.shop.service.CustomerService;
import com.yicheejia.modules.webservices.model.CustomerVO;
import com.yicheejia.modules.webservices.model.RspVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author hunk
 * @email 
 * @date 2018-07-10 16:12:15
 */
@RestController
@RequestMapping("webService/customer")
public class SynCusBaseInfoService {
    
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PutmoneyInfoService putmoneyInfoService;

    /**
     * 保存
     */
    @RequestMapping("/synCustInfo")
    public RspVO save(@RequestBody CustomerVO customerVO){
        logger.info("同步客户接口开始调用："+customerVO.toString());
        if (customerVO != null) {
        //    ValidatorUtils.validateVO(customerVO);
            logger.info(customerVO.toString());
            List<CustomerEntity> customerEntityList = customerService.selectList(new EntityWrapper<CustomerEntity>().eq("card_no",customerVO.getIdNo()));
            if(customerEntityList == null || customerEntityList.size() == 0){
                customerService.saveCustomerInfo(customerVO);
            }else {
                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setCardNo(customerVO.getIdNo());
           //     customerEntity.setCarType(customerVO.getPurchaseType()+"");
                customerEntity.setCustomerName(customerVO.getCustomerName());
                customerEntity.setCustomerTel(customerVO.getPhoneNo());
                customerEntity.setBuyManner(customerVO.getPurchaseType());
                customerService.updateCustomerByCardNo(customerEntity);
            }

            return RspVO.ok();
        } else {
            return RspVO.error("对象为空");
        }
    }

    /**
     * 融资租赁客户付款成功信息推送接口
     *                  paymentBank:付款银行  paymentAccount：付款账户
     *                  paymentTotal：付款总额 incomeBank：收款银行
     *                  incomeAccountName：收款账户名  incomeAccountNo：收款账户
     *                 applyNo:申请编号   customerName：客户名称  payDate：付款时间
     * @return
     */
    @RequestMapping(value = "/customerPayInfo",method = RequestMethod.POST)
    public RspVO customerPayInfo(@RequestBody CustomerPaymentInfo info){
    	logger.info("融资租赁客户付款成功信息推送："+info.toString());
       if (info!=null && StringUtils.isNotBlank(info.getIdCardNo())) {
		//根据用户身份证查询库中是否已经存在，不存在进行插入操作，存在进行更新操作
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
    	   return RspVO.ok();
	}else {
		return RspVO.error("对象为空");
	}

    }


    

}
