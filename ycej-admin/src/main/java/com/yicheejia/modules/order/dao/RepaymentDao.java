package com.yicheejia.modules.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yicheejia.modules.order.entity.RepaymentEntity;

@Mapper
public interface RepaymentDao extends BaseMapper<RepaymentEntity>{
	public List<RepaymentEntity> queryOrderDetail(Map<String,Object> map);
	public void upOrderPay(Map<String,Object> map);
	public void insertOrderPay(Map<String,Object> map);
	public void upOrderPayDetail(Map<String,Object> map);
	public List<RepaymentEntity> queryOrderPayDetail(Map<String,Object> map);
	public void insertPay(Map<String,Object> map);
	
	public List<RepaymentEntity> queryDetail(Map<String, Object> map);
	
	public void updateOrder(Map<String, Object> map);
}
