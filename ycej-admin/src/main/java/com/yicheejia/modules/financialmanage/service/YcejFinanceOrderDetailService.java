package com.yicheejia.modules.financialmanage.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.financialmanage.entity.YcejFinanceOrderDetailEntity;

/**
 * 订单费用明细表
 *
 * @author cn
 * @email 
 * @date 2018-07-10 08:52:57
 */
public interface YcejFinanceOrderDetailService extends IService<YcejFinanceOrderDetailEntity> {

    LayuiPage queryPage(Map<String, Object> params);
}

