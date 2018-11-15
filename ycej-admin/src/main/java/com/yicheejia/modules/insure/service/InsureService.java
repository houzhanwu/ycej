package com.yicheejia.modules.insure.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.insure.entity.InsureEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;

import java.util.Map;

/**
 * 保险
 *
 * @author fkm
 * @email 
 * @date 2018-07-26 16:52:37
 */
public interface InsureService extends IService<InsureEntity> {

    LayuiPage queryPage(Map<String, Object> params);
    /**
     * 新增
     * @param insure
     */
	public void insertInsure(InsureEntity insure);
	/**
	 * 修改
	 * @param insure
	 */
	public void updateInsure(InsureEntity insure);
	
	YcejOrderEntity queryInsure(Map<String, Object> params);
}

