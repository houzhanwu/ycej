package com.yicheejia.modules.operate.dao;

import com.yicheejia.modules.operate.entity.ProvinceEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 省份表
 * 
 * @author
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
public interface ProvinceDao extends BaseMapper<ProvinceEntity> {

    List<Map<String,Object>> getAllProvince();
}
