package com.yicheejia.modules.financialmanage.dao;

import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceOrderDetailEntity;

/**
 * 订单费用明细表
 * 
 * @author cn
 * @email 
 * @date 2018-07-10 08:52:57
 */
public interface YcejFinanceOrderDetailDao extends BaseMapper<YcejFinanceOrderDetailEntity> {

	List<YcejFinanceOrderDetailEntity> selectAllPage(Page<YcejFinanceOrderDetailEntity> page, HashMap<Object, Object> map);
	
}
