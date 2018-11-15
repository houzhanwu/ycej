package com.yicheejia.modules.operate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.operate.dao.CarBrandDao;
import com.yicheejia.modules.operate.entity.CarBrandEntity;
import com.yicheejia.modules.operate.service.CarBrandService;
import com.yicheejia.modules.sys.service.SysUserService;


@Service("carBrandService")
public class CarBrandServiceImpl extends ServiceImpl<CarBrandDao, CarBrandEntity> implements CarBrandService {
	@Autowired
	private SysUserService sysUserservice;
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	
    	Object carBrand = params.get("carBrand");
    	EntityWrapper<CarBrandEntity> wrapper = new EntityWrapper<CarBrandEntity>();
    	if(null != carBrand && !carBrand.toString().isEmpty()) {
//    		String a = carBrand.toString();
    		wrapper.like("car_brand", carBrand.toString());
    	}
    	wrapper.orderBy("insert_time",false);
        Page<CarBrandEntity> page = this.selectPage(
                new Query<CarBrandEntity>(params).getPage(),
                wrapper
        );
        List<String> userList = new ArrayList<String>();
        for (CarBrandEntity car : page.getRecords()) {
            userList.add(car.getInsertId());
            userList.add(car.getUpdateId());
        }
        Map<String,String> map = sysUserservice.getUserName(userList);
        for (CarBrandEntity car : page.getRecords()) {
            if (map.containsKey(car.getInsertId())) {
            	car.setInsertId(map.get(car.getInsertId()));
            }
            if (map.containsKey(car.getUpdateId())) {
            	car.setUpdateId(map.get(car.getUpdateId()));
            }
        }
    
        return new LayuiPage(page.getRecords(),page.getTotal());
    }

	@Override
	public List<Map<String, Object>> getCarBrands(Map<String, Object> params) {
		return baseMapper.getCarBrands(params);
	}

	@Override
	public int selectByBrand(CarBrandEntity carBrand) {
		String brand = carBrand.getCarBrand().trim();
		EntityWrapper<CarBrandEntity> wrapper = new EntityWrapper<CarBrandEntity>();
		wrapper.addFilter("car_brand ="+"'"+brand+"'");
		int brandCount = baseMapper.selectCount(wrapper);
		return brandCount;
	}

}
