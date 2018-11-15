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

import com.yicheejia.modules.operate.dao.CarManufacturerDao;
import com.yicheejia.modules.operate.entity.CarBrandEntity;
import com.yicheejia.modules.operate.entity.CarManufacturerEntity;
import com.yicheejia.modules.operate.service.CarManufacturerService;
import com.yicheejia.modules.sys.service.SysUserService;


@Service("carManufacturerService")
public class CarManufacturerServiceImpl extends ServiceImpl<CarManufacturerDao, CarManufacturerEntity> implements CarManufacturerService {

    @Autowired
    private CarManufacturerService carManufacturerService;
    @Autowired
	private SysUserService sysUserservice;
    /**
     * 查询
     */
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	//按品牌、厂商关键字查询
    	HashMap<String, Object> map=new HashMap<>();
//    	Object carManufacturer = params.get("carManufacturer");
//    	Object carBrand = params.get("carBrand");
    	//添加查询条件
    	if (params.get("keyword")!=null&&!"".equals(params.get("keyword"))) {
    		String keyword= params.get("keyword").toString();
    		map.put("keyword", keyword);
		}
//    	if(null != carBrand && !carBrand.toString().isEmpty()) {
//    		map.put("carBrand", carBrand);
//    	}
//    	if(null != carManufacturer && !carManufacturer.toString().isEmpty()) {
//    		map.put("carManufacturer", carManufacturer);
//    	}
    	
        Page<CarManufacturerEntity> page = this.selectPage(
        		new Query<CarManufacturerEntity>(params).getPage(),
        		new EntityWrapper<CarManufacturerEntity>()
        		);
        page.setRecords(baseMapper.carManufacturerList(page,map));
        
        List<String> userList = new ArrayList<String>();
        for (CarManufacturerEntity car : page.getRecords()) {
            userList.add(car.getInsertId());
            userList.add(car.getUpdateId());
        }
        Map<String,String> map1 = sysUserservice.getUserName(userList);
        for (CarManufacturerEntity car : page.getRecords()) {
            if (map1.containsKey(car.getInsertId())) {
            	car.setInsertId(map1.get(car.getInsertId()));
            }
            if (map1.containsKey(car.getUpdateId())) {
            	car.setUpdateId(map1.get(car.getUpdateId()));
            }
        }
        return new LayuiPage(page.getRecords(),page.getTotal());
    }

//    /**
//     * 修改
//     * @return 
//     */
//	@Override
//	public boolean updateById(CarManufacturerEntity carManufacturer) {
//		
//		//品牌、厂商、是否启用
//    	Integer carBrandId = carManufacturer.getCarBrandId();
//    	String carMr = carManufacturer.getCarManufacturer();
//    	int isEnabled = carManufacturer.getIsEnabled();
//    	
//    	if(carBrandId != 0 && null != carBrandId) {
////    		map.put("carBrand", carBrand);
//    	}
//    	if(null != carMr && !carMr.toString().isEmpty()) {
////    		map.put("carManufacturer", carManufacturer);
//    	}
//    	
//    	return carManufacturerService.updateById(carManufacturer);
//	}

	@Override
	public List<Map<String, Object>> getCarManufacturers(Map<String, Object> params) {
		return baseMapper.getCarManufacturers(params);
	}

    @Override
    public int selectByManufacturer(CarManufacturerEntity manufacturerEntity) {
        String carManufacturer = manufacturerEntity.getCarManufacturer().trim();
        EntityWrapper<CarManufacturerEntity> wrapper = new EntityWrapper<CarManufacturerEntity>();
        wrapper.addFilter("car_manufacturer ="+"'"+carManufacturer+"' and is_enabled = 1");
        int count = baseMapper.selectCount(wrapper);
        return count;
    }


}
