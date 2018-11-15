package com.yicheejia.modules.inquiry.service.impl;

import com.yicheejia.modules.inquiry.entity.ResourceBrandEntity;
import com.yicheejia.modules.inquiry.service.ResourceBrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.inquiry.dao.ResourceQuotedDao;
import com.yicheejia.modules.inquiry.entity.ResourceQuotedEntity;
import com.yicheejia.modules.inquiry.service.ResourceQuotedService;

import javax.annotation.Resource;


@Service("resourceQuotedService")
public class ResourceQuotedServiceImpl extends ServiceImpl<ResourceQuotedDao, ResourceQuotedEntity> implements ResourceQuotedService {

    @Resource
    private ResourceQuotedDao resourceQuotedDao;

    @Autowired
    private ResourceBrandService resourceBrandService;

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
       /* Object quotedTime = params.get("quotedTime");
        Object carModel = params.get("carModel");
        Object resourceId = params.get("resourceId");
        EntityWrapper<ResourceQuotedEntity> wrapper = new EntityWrapper<>();

        if(quotedTime != null && StringUtils.isNotBlank(quotedTime.toString())){
            wrapper.eq("date(quoted_time)",quotedTime.toString());
        }
        if(carModel != null && StringUtils.isNotBlank(carModel.toString())){
            wrapper.like("car_model",carModel.toString());
        }
        if(resourceId != null && StringUtils.isNotBlank(resourceId.toString())){
            wrapper.eq("resource_id",resourceId);
        }
        wrapper.orderBy("car_model_id",false).orderBy("quoted_time",false);

        Page<ResourceQuotedEntity> page = this.selectPage(
                new Query<ResourceQuotedEntity>(params).getPage(),
                wrapper
        );

        return new LayuiPage(page.getRecords(), page.getTotal());*/
        Page<Map<String,Object>> page = new Query<Map<String,Object>>(params).getPage();
        List<Map<String,Object>> records = baseMapper.getQuotedList(page,params);

       /* Object resourceId = params.get("resourceId");

        if(resourceId != null && StringUtils.isNumeric(resourceId.toString())){
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("resource_customer_id",resourceId);

            List<ResourceBrandEntity> brandEntityList = resourceBrandService.selectByMap(paramMap);
            List<Long> brandIdList = new ArrayList<>(brandEntityList.size());

            for(ResourceBrandEntity brandEntity:brandEntityList){
                brandIdList.add(brandEntity.getCarBrandId());
            }
            if(records != null && records.size() > 0){
                Map<String,Object> record = records.get(0);
                record.put("brandIdList",brandIdList);
            }
        }*/

        page.setRecords(records);
        return new LayuiPage(page.getRecords(),page.getTotal());
    }

    @Override
    public void updateResourceQuotedById(ResourceQuotedEntity resourceQuotedEntity) {
        resourceQuotedDao.updateResourceQuotedById(resourceQuotedEntity);
    }


}
