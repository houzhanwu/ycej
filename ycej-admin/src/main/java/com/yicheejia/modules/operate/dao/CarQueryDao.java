package com.yicheejia.modules.operate.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yicheejia.modules.operate.entity.CarQueryEntity;

public interface CarQueryDao extends BaseMapper<CarQueryEntity> {

	List<CarQueryEntity> selectAllPage(Page<CarQueryEntity> page, Map<String, Object> params);

}
