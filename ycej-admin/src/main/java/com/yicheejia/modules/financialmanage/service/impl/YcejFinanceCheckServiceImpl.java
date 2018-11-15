package com.yicheejia.modules.financialmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.financialmanage.dao.YcejFinanceCheckDao;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceCheckEntity;
import com.yicheejia.modules.financialmanage.service.YcejFinanceCheckService;


@Service("ycejFinanceCheckService")
public class YcejFinanceCheckServiceImpl extends ServiceImpl<YcejFinanceCheckDao, YcejFinanceCheckEntity> implements YcejFinanceCheckService {
	@Autowired
	private YcejFinanceCheckDao ycejFinanceCheckDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	HashMap<Object, Object> map=new HashMap<>();
    	if (params.get("customer_name")!=null&&!"".equals(params.get("customer_name"))) {
    		map.put("customer_name", params.get("customer_name"));
		}
    	if (params.get("startTime")!=null&&!"".equals(params.get("startTime"))) {
    		map.put("startTime", params.get("startTime"));
		}
    	if (params.get("endTime")!=null&&!"".equals(params.get("endTime"))) {
    		map.put("endTime", params.get("endTime"));
    	}
    	if (params.get("startTime2")!=null&&!"".equals(params.get("startTime2"))) {
    		map.put("startTime2", params.get("startTime2"));
    	}
    	if (params.get("endTime2")!=null&&!"".equals(params.get("endTime2"))) {
    		map.put("endTime2", params.get("endTime2"));
    	}
    	if (params.get("shop_name")!=null&&!"".equals(params.get("shop_name"))) {
    		map.put("shop_name", params.get("shop_name"));
    	}
    	/*
    	String dString="2018-06-05 08:29:14";
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	ParsePosition pos = new ParsePosition(0);
    	map.put("startTime", sdf.parse(dString,pos));
    	map.put("customer_name", "2");
    	map.put("endTime", endTime);
    	map.put("startTime2", startTime2);
    	map.put("endTime2", endTime2);
    	map.put("shop_id", shop_id);
    	*/
    	Page<YcejFinanceCheckEntity> page=new Query<YcejFinanceCheckEntity>(params).getPage();
    	page.setRecords(baseMapper.selectAllPage( page,
    			map));
        return new PageUtils(page);
    }
	@Override
	public PageUtils queryDetailPage(Map<String, Object> params) {
		HashMap<Object, Object> map=new HashMap<>();
		if (params.get("customer_name")!=null&&!"".equals(params.get("customer_name"))) {
    		map.put("customer_name", params.get("customer_name"));
		}
    	if (params.get("startTime")!=null&&!"".equals(params.get("startTime"))) {
    		map.put("startTime", params.get("startTime"));
		}
    	if (params.get("endTime")!=null&&!"".equals(params.get("endTime"))) {
    		map.put("endTime", params.get("endTime"));
    	}
    	if (params.get("startTime2")!=null&&!"".equals(params.get("startTime2"))) {
    		map.put("startTime2", params.get("startTime2"));
    	}
    	if (params.get("endTime2")!=null&&!"".equals(params.get("endTime2"))) {
    		map.put("endTime2", params.get("endTime2"));
    	}
    	if (params.get("shop_name")!=null&&!"".equals(params.get("shop_name"))) {
    		map.put("shop_name", params.get("shop_name"));
    	}
    	/*
    	String dString="2018-06-05 08:29:14";
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	ParsePosition pos = new ParsePosition(0);
    	map.put("startTime", sdf.parse(dString,pos));
    	map.put("customer_name", "2");
    	map.put("endTime", endTime);
    	map.put("startTime2", startTime2);
    	map.put("endTime2", endTime2);
    	map.put("shop_id", shop_id);
    	*/
    	Page<YcejFinanceCheckEntity> page=new Query<YcejFinanceCheckEntity>(params).getPage();
    	page.setRecords(baseMapper.selectDetailPage( page,map));
        return new PageUtils(page);
	}
	@Override
	public void checkByIds(List<Integer> asList) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map=new HashMap<>();
		map.put("asList", asList);
		map.put("state","2");//订单完成的状态，后期改为常量
		baseMapper.checkByIds(map);
	}
	@Override
	public void returnByIds(List<Integer> asList) {
		// TODO Auto-generated method stub
				HashMap<String, Object> map=new HashMap<>();
				map.put("asList", asList);
				map.put("state","3");//财务退回的状态，后期改为常量
				baseMapper.returnByIds(map);
		
	}
	@Override
	public void accountCheckByIds(List<Integer> asList) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map=new HashMap<>();
		map.put("asList", asList);
		map.put("state","4");//财务核对车款的状态，后期改为常量
		baseMapper.accountCheckByIds(map);
		
	}
	@Override
	public List<YcejFinanceCheckEntity> getDataForExcel(List<Integer> asList) {
		// TODO Auto-generated method stub
		return baseMapper.getDataForExcel(asList);
	}
	@Override
	public List<Map<String, Object>> getDataForExcel2(List<Integer> asList) {
		// TODO Auto-generated method stub
		return baseMapper.getDataForExcel2(asList);
	}



}
