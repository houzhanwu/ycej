package com.yicheejia.modules.product.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.product.dao.ProductInfoDao;
import com.yicheejia.modules.product.entity.ProductInfoEntity;
import com.yicheejia.modules.product.service.ProductInfoService;

@Service("productInfoService")
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoDao, ProductInfoEntity>
        implements ProductInfoService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        String keyword = (String) params.get("keyword");
        Page<ProductInfoEntity> page = this.selectPage(new Query<ProductInfoEntity>(params).getPage(),
                new EntityWrapper<ProductInfoEntity>().or()
                        .like(StringUtils.isNotBlank(keyword), "product_categories_id", keyword).or()
                        .like(StringUtils.isNotBlank(keyword), "product_categories_name", keyword).or()
                        .like(StringUtils.isNotBlank(keyword), "product_solution_id", keyword).or()
                        .like(StringUtils.isNotBlank(keyword), "product_solution_name", keyword)

        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

    @Override
    public List<ProductInfoEntity> selectProductList() {

        return baseMapper.selectProductList();
    }

}
