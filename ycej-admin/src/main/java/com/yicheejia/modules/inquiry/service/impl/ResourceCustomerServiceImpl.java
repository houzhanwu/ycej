package com.yicheejia.modules.inquiry.service.impl;

import com.yicheejia.modules.inquiry.entity.ResourceBrandEntity;
import com.yicheejia.modules.inquiry.service.ResourceBrandService;
import com.yicheejia.modules.operate.entity.CarBrandEntity;
import com.yicheejia.modules.operate.service.CarBrandService;
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

import com.yicheejia.modules.inquiry.dao.ResourceCustomerDao;
import com.yicheejia.modules.inquiry.entity.ResourceCustomerEntity;
import com.yicheejia.modules.inquiry.service.ResourceCustomerService;


@Service("resourceCustomerService")
public class ResourceCustomerServiceImpl extends ServiceImpl<ResourceCustomerDao, ResourceCustomerEntity> implements ResourceCustomerService {

    @Autowired
    private CarBrandService carBrandService;

    @Autowired
    private ResourceBrandService resourceBrandService;

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Object resourceName = params.get("resourceName");
        EntityWrapper<ResourceCustomerEntity> wrapper = new EntityWrapper<ResourceCustomerEntity>();
        if(resourceName != null && StringUtils.isNotBlank(resourceName.toString())){
            wrapper.like("resource_name",resourceName.toString());
        }
        wrapper.orderBy("ins_time",false);
        Page<ResourceCustomerEntity> page = this.selectPage(
                new Query<ResourceCustomerEntity>(params).getPage(),
                wrapper
        );
        List<ResourceCustomerEntity> records = page.getRecords();

        for(ResourceCustomerEntity entity : records){
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("resource_customer_id",entity.getResourceId());

            List<ResourceBrandEntity> brandEntityList = resourceBrandService.selectByMap(paramMap);
            List<Long> brandIdList = new ArrayList<>(brandEntityList.size());

            for(ResourceBrandEntity brandEntity:brandEntityList){
                brandIdList.add(brandEntity.getCarBrandId());
            }

            if(brandIdList != null && brandIdList.size() > 0){
                List<CarBrandEntity> carBrandEntities = carBrandService.selectBatchIds(brandIdList);
                List<String> brandNameList = new ArrayList<>();

                for(CarBrandEntity carBrandEntity : carBrandEntities){
                    brandNameList.add(carBrandEntity.getCarBrand());
                }

                entity.setSellBrands(brandIdList);
                entity.setSellBrandNames(brandNameList);
            }

        }

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

    @Override
    public ResourceCustomerEntity queryByUserMobile(ResourceCustomerEntity resourceCustomerEntity) {
        return baseMapper.queryByUserMobile(resourceCustomerEntity);
    }

}
