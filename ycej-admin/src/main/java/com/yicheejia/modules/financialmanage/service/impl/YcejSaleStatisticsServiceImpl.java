package com.yicheejia.modules.financialmanage.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.financialmanage.dao.YcejSaleStatisticsDao;
import com.yicheejia.modules.financialmanage.entity.YcejSaleStatisticsEntity;
import com.yicheejia.modules.financialmanage.service.YcejSaleStatisticsService;
@Service("ycejSaleStatisticsService")
public class YcejSaleStatisticsServiceImpl extends ServiceImpl<YcejSaleStatisticsDao, YcejSaleStatisticsEntity> implements YcejSaleStatisticsService{

	@Override
	public LayuiPage queryPage(Map<String, Object> params) {
		HashMap<Object, Object> map=new HashMap<>();
		Object saleTime=params.get("saleTime");
		if (saleTime!=null && !"".equals(saleTime.toString())) {
			//销售日期
    		String rDate = (String) params.get("saleTime");
        	String[] rDates = rDate.split(" ");
        	String startSaleDate = rDates[0];
        	String endSaleDate = rDates[2];
        	map.put("startSaleDate", startSaleDate);
        	map.put("endSaleDate", endSaleDate);
		}
		Page<YcejSaleStatisticsEntity> page=new Query<YcejSaleStatisticsEntity>(params).getPage();
    	try {
			page.setRecords(baseMapper.selectAllPage(page,map));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return new LayuiPage(page.getRecords(), page.getTotal());	
        }

	@Override
	public List<YcejSaleStatisticsEntity> selectExportData(Long[] ids) {

        return baseMapper.selectExportData(Arrays.asList(ids));
	}

}
