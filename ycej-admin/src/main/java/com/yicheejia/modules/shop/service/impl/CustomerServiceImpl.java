package com.yicheejia.modules.shop.service.impl;

import com.yicheejia.common.annotation.ShopFilter;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.Constant;
import com.yicheejia.common.utils.DownloadUtil;
import com.yicheejia.common.utils.LayuiPage;

import com.yicheejia.modules.operate.entity.CarModelEntity;
import com.yicheejia.modules.operate.service.CarModelService;
import com.yicheejia.modules.shop.constant.ShopConstant;
import com.yicheejia.modules.shop.entity.YcejShopEntity;
import com.yicheejia.modules.shop.service.IntentionCustomerService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.shop.dao.CustomerDao;
import com.yicheejia.modules.shop.entity.CustomerEntity;
import com.yicheejia.modules.shop.entity.IntentionCustomerEntity;
import com.yicheejia.modules.shop.service.CustomerService;
import com.yicheejia.modules.upload.service.YcejFileService;
import com.yicheejia.modules.webservices.model.CustomerVO;
import com.yicheejia.modules.webservices.model.FileVO;


@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired 
    YcejFileService ycejFileService;

    @Autowired
    CarModelService carModelService;

    @Autowired
    IntentionCustomerService intentionCustomerService;

    @Autowired
    YcejShopServiceImpl shopService;

    @Override
    @ShopFilter
    public LayuiPage queryPage(Map<String, Object> params) {
        Object customerName = params.get("customerName");
        Object handoverTime = params.get("handoverTime");
        EntityWrapper<CustomerEntity> wrapper = new EntityWrapper<>();

        if(customerName != null && !"".equals(customerName.toString())){
            // wrapper.like("customer_name",customerName.toString());
            String filterSql = "(customer_name like '%" + customerName.toString()+"%' or customer_tel like '%"+customerName.toString()+"%'" +
                    " or card_no like '%" + customerName.toString() + "%')";
            wrapper.addFilter(filterSql);
        }
        if(handoverTime != null && StringUtils.isNotBlank(handoverTime.toString())){
            wrapper.eq("handover_time",handoverTime.toString());
        }
        wrapper.eq("customer_state", ShopConstant.CustomerState.DEALED.getValue());
        wrapper.addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER));
        wrapper.orderBy("handover_time",false);
        Page<CustomerEntity> page = this.selectPage(
                new Query<CustomerEntity>(params).getPage(),
                wrapper
        );

        List<CustomerEntity> records = page.getRecords();

        /*EntityWrapper<IntentionCustomerEntity> intentionCustomerWrapper = new EntityWrapper<>();
        if(customerName != null && !"".equals(customerName.toString())){
            String filterSql2 = "(customer_name like '%" + customerName.toString()+"%' or customer_tel like '%"+customerName.toString()+"%'" +
                    " or id_card_no like '%" + customerName.toString() + "%')";
            intentionCustomerWrapper.addFilter(filterSql2);
        }
        intentionCustomerWrapper.eq("intention_customer_state",1);*/

        for(CustomerEntity customerEntity : records){

            String carType = customerEntity.getCarType();
            String shopId = customerEntity.getHandoverShop();
           /* if(StringUtils.isBlank(carType)){
                throw new RRException("车型不能为空！");
            }*/
            if(StringUtils.isNotBlank(carType)){
                CarModelEntity carModelEntity = carModelService.selectById(Integer.parseInt(carType));
                customerEntity.setCarModel(carModelEntity.getCarModel());
            }

            YcejShopEntity shopEntity = shopService.selectById(Integer.parseInt(shopId));
            if(shopEntity != null){
                customerEntity.setHandOverShopName(shopEntity.getShopName());

            }
        }

        return new LayuiPage(records,page.getTotal());
    }
    /**
     * 保存客户信息
     */
    public void saveCustomerInfo(CustomerVO customerVO) {
        if (customerVO != null) {
            //保存客户信息
            int id = baseMapper.insert(buildCustomer(customerVO));
            //保存客户附件信息
            List<FileVO>  list = customerVO.getFileList();
            //本次为空不处理
            if (list != null && list.size() > 0) {
                for (FileVO fileVO : list) {
                    String remoteFilePath = fileVO.getFileUrl();
                    String  localFilePath = "C:\\u01";//指定本地下载路径
                    logger.info("附件下载:"+fileVO.getFileName()+" URL："+fileVO.getFileUrl());
                    DownloadUtil.downloadFile(remoteFilePath,localFilePath);
                    //封装附件信息表
                    ycejFileService.insertFile(null, fileVO.getFileName(),fileVO.getFileType(), localFilePath);
                    //封装 业务附件表
                }
            }
        }
    }

    @Override
    public LayuiPage selectCustomerManageList(Map<String, Object> map) {
        Page<Map<String,Object>> page = new Query<Map<String,Object>>(map).getPage();
        List<Map<String,Object>> records = baseMapper.customerManageListPage(page,map);
        /*for(Map<String,Object> record : records){
            record.put("buyManner",2);
        }*/
        page.setRecords(records);
        return new LayuiPage(page.getRecords(),page.getTotal());
    }

    @Override
    public Map<String, Object> getCusManageInfoById(Integer customerId) {
        return baseMapper.getCusManageInfoById(customerId);
    }

    /**
     * 
     * @param customerVO
     * @return
     */
    private CustomerEntity buildCustomer(CustomerVO customerVO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerName(customerVO.getCustomerName());
        customerEntity.setCustomerTel(customerVO.getPhoneNo());
        customerEntity.setCardNo(customerVO.getIdNo());
       // customerEntity.setCarType(customerVO.getPurchaseType().toString());
        customerEntity.setBuyManner(customerVO.getPurchaseType());
        customerEntity.setCustomerState(0); //默认0：未成交
        customerEntity.setInsTime(new Date());
        return customerEntity;
    }
    /**
     * 订单修改客户为成交客户
     */
    @Override
    public void updateCustomer(CustomerEntity customer) {
         baseMapper.updateCustomer(customer);
    }

    @Override
    public int updateCustomerByCardNo(CustomerEntity customerEntity) {
        return baseMapper.updateCustomerByCardNo(customerEntity);
    }
	/**
	 * 根据身份证号查询风控客户
	 * @param params
	 * @return
	 */
	@Override
	public List<CustomerEntity> selectCustByCard(Map<String, Object> params) {
		
		 return baseMapper.selectCustByCard(params);
	}

    @Override
    public Map<String, Object> getCustomerInfoById(Integer customerId) {
        return baseMapper.getCustomerInfoById(customerId);
    }

}
