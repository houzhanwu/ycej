package com.yicheejia.modules.insure.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.insure.entity.InsureDetailEntity;

import java.util.Map;

/**
 * 保险明细表
 *
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
public interface InsureDetailService extends IService<InsureDetailEntity> {

    LayuiPage queryPage(Map<String, Object> params);
    /**
     * 保险和保险明细插入
     * @param insureDetail
     */
	void insertDetail(InsureDetailEntity insureDetail);
	/**
	 * 保险和明细修改
	 * @param insureDetail
	 */
	void updateDetail(InsureDetailEntity insureDetail);
	/**
	 * 根据订单主键查询明细信息
	 * @param params
	 * @return
	 */
	InsureDetailEntity queryDetail(Map<String, Object> params);
}

