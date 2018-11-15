package com.yicheejia.modules.inquiry.service.impl;

import com.yicheejia.common.utils.MapUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.inquiry.dao.ResourceBrandDao;
import com.yicheejia.modules.inquiry.entity.ResourceBrandEntity;
import com.yicheejia.modules.inquiry.service.ResourceBrandService;


@Service("resourceBrandService")
public class ResourceBrandServiceImpl extends ServiceImpl<ResourceBrandDao, ResourceBrandEntity> implements ResourceBrandService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<ResourceBrandEntity> page = this.selectPage(
                new Query<ResourceBrandEntity>(params).getPage(),
                new EntityWrapper<ResourceBrandEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

    @Override
    public void saveOrUpdate(Long resourceId, List<Long> brandIdList) {
        if(resourceId == null){
            return;
        }
        this.deleteByMap(new MapUtils().put("resource_customer_id",resourceId));

        if(brandIdList == null || brandIdList.size() == 0){
            return;
        }

        //保存资源与品牌关系
        List<ResourceBrandEntity> list = new ArrayList<>(brandIdList.size());
        for(Long brandId : brandIdList){
            ResourceBrandEntity resourceBrandEntity = new ResourceBrandEntity();
            resourceBrandEntity.setResourceCustomerId(resourceId);
            resourceBrandEntity.setCarBrandId(brandId);

            list.add(resourceBrandEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Map<String, Object>> getResourceBrands(Map<String, Object> map) {
        return baseMapper.getResourceBrands(map);
    }

}
