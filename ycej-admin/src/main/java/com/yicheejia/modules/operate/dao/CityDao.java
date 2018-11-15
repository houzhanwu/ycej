package com.yicheejia.modules.operate.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yicheejia.modules.operate.entity.CityEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 地级市表
 * 
 * @author
 * @email zhongruizifu@163.com
 * @date 2018-06-20 16:43:29
 */
public interface CityDao extends BaseMapper<CityEntity> {

    List<Map<String,Object>> getCitiesByProvinceId(int provinceId);

    List<Map<String,Object>> getAllCities();

    List<Map<String,Object>> pageQueryTest(Pagination page,@Param("provinceId") int provinceId);

}
