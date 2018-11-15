package com.yicheejia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.order.entity.LoanInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author cn
 * @email 
 * @date 2018-07-10 17:32:57
 */
public interface LoanInfoService extends IService<LoanInfoEntity> {

    LayuiPage queryPage(Map<String, Object> params);
    /**
     * 查询所有数据
     * @return
     */
	List<LoanInfoEntity> queryLoaninfo();
	/**
	 * 根据订单信息查询贷款信息
	 * @param params
	 * @return
	 */
	LoanInfoEntity selectLoaninfo(Map<String, Object> params);
}

