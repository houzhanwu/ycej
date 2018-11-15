package com.yicheejia.modules.operate.service.impl;

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

import com.yicheejia.modules.operate.dao.CarSeriesDao;
import com.yicheejia.modules.operate.entity.CarManufacturerEntity;
import com.yicheejia.modules.operate.entity.CarSeriesEntity;
import com.yicheejia.modules.operate.service.CarSeriesService;
import com.yicheejia.modules.sys.service.SysUserService;


@Service("carSeriesService")
public class CarSeriesServiceImpl extends ServiceImpl<CarSeriesDao, CarSeriesEntity> implements CarSeriesService {
	 @Autowired
	 private SysUserService sysUserservice;
	 
    @Override
    public LayuiPage queryPage(Map<String, Object> params) 
    {
    	HashMap<String, Object> map=new HashMap<>();
    	//添加查询条件
    	if (params.get("keyword")!=null&&!"".equals(params.get("keyword"))) {
    		String keyword= params.get("keyword").toString();
    		map.put("keyword", keyword);
		}
//    	if (params.get("carBrand")!=null&&!"".equals(params.get("carBrand"))) {
//    		String car_brand= params.get("carBrand").toString();
//    		map.put("carBrand", car_brand);
//		}
//    	if (params.get("carManufacturer")!=null&&!"".equals(params.get("carManufacturer"))) {
//    		String car_manufacturer= params.get("carManufacturer").toString();
//    		map.put("carManufacturer", car_manufacturer);
//		}
//    	if (params.get("carSeries")!=null&&!"".equals(params.get("carSeries"))) {
//    		String car_series= params.get("carSeries").toString();
//    		map.put("carSeries", car_series);
//		}
    	
        Page<CarSeriesEntity> page = this.selectPage(new Query<CarSeriesEntity>(params).getPage(),new EntityWrapper<CarSeriesEntity>());
        page.setRecords(baseMapper.carSeriesList(page,map));
        List<String> userList = new ArrayList<String>();
        for (CarSeriesEntity car : page.getRecords()) {
            userList.add(car.getInsertId());
            userList.add(car.getUpdateId());
        }
        Map<String,String> map1 = sysUserservice.getUserName(userList);
        for (CarSeriesEntity car : page.getRecords()) {
            if (map1.containsKey(car.getInsertId())) {
            	car.setInsertId(map1.get(car.getInsertId()));
            }
            if (map1.containsKey(car.getUpdateId())) {
            	car.setUpdateId(map1.get(car.getUpdateId()));
            }
        }
        return new LayuiPage(page.getRecords(),page.getTotal());
    }

	@Override
	public List<CarSeriesEntity> getCarSeriesList(Map<String, Object> params) {
		return baseMapper.getCarSeriesList(params);
	}

	@Override
	public CarSeriesEntity selectById(Integer carSeriesId) {
		
		return baseMapper.selectById(carSeriesId);
	}

    @Override
    public int selectBySeries(CarSeriesEntity seriesEntity) {
        String series = seriesEntity.getCarSeries().trim();
        EntityWrapper<CarSeriesEntity> wrapper = new EntityWrapper<CarSeriesEntity>();
        wrapper.addFilter("car_series ="+"'"+series+"' and is_enabled = 1");
        int count = baseMapper.selectCount(wrapper);
        return count;
    }

}
