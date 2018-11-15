package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.CarModelDao;
import com.yicheejia.modules.operate.entity.CarModelEntity;
import com.yicheejia.modules.operate.entity.InventoryEntity;
import com.yicheejia.modules.operate.service.CarModelService;


@Service("carModelService")
public class CarModelServiceImpl extends ServiceImpl<CarModelDao, CarModelEntity> implements CarModelService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) 
    {
    	HashMap<String, Object> map=new HashMap<>();
    	//添加查询条件
    	if (params.get("keyword")!=null&&!"".equals(params.get("keyword"))) {
    		String keyword= params.get("keyword").toString();
    		map.put("keyword", keyword);
		}
//    	if (params.get("carManufacturer")!=null&&!"".equals(params.get("carManufacturer"))) {
//    		String car_manufacturer= params.get("carManufacturer").toString();
//    		map.put("carManufacturer", car_manufacturer);
//		}
//    	if (params.get("carSeries")!=null&&!"".equals(params.get("carSeries"))) {
//    		String car_series= params.get("carSeries").toString();
//    		map.put("carSeries", car_series);
//		}
//    	if (params.get("carModel")!=null&&!"".equals(params.get("carModel"))) {
//    		String car_Model= params.get("carModel").toString();
//    		map.put("carModel", car_Model);
//		}
    	
        Page<CarModelEntity> page = this.selectPage(
                new Query<CarModelEntity>(params).getPage(),
                new EntityWrapper<CarModelEntity>()
        );
        page.setRecords(baseMapper.carModelList(page,map));
        return new LayuiPage(page.getRecords(),page.getTotal());
    }

	@Override
	public CarModelEntity selectById(Integer carModelId) {
		
		return baseMapper.getCarModelInfo(carModelId);
	}

	@Override
	public List<CarModelEntity> getCarModelList(Map<String, Object> params) {
		return baseMapper.getCarModelList(params);
	}

	@Override
	public void insertCarModel(CarModelEntity carModelEntity) {

		baseMapper.insert(carModelEntity);
	}

	@Override
	public int selectByCarModel(CarModelEntity carModelEntity) {
    	String carModel = carModelEntity.getCarModel().replace(" ", "");
    	String carSeriesId = carModelEntity.getCarSeries();
    	int count = 0;
    	List<String> modelList = baseMapper.selectModelList(carSeriesId);
    	if(null != modelList && modelList.size()>0) {
    		for(String model : modelList) {
        		model = model.replace(" ", "");
        		if(model.equals(carModel)) {
        			count = 1;
        		}
        	}
    	}
//		EntityWrapper<CarModelEntity> wrapper = new EntityWrapper<CarModelEntity>();
//		wrapper.addFilter("car_model ="+"'"+carModel+"' and is_enabled = 1");
//		int count = baseMapper.selectCount(wrapper);
		return count;
	}

}
