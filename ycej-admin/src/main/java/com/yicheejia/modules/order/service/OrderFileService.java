package com.yicheejia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.order.entity.OrderFileEntity;
import com.yicheejia.modules.upload.entity.YcejFileEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author fkm
 * @email 
 * @date 2018-07-11 14:16:58
 */
public interface OrderFileService extends IService<OrderFileEntity> {

    LayuiPage queryPage(Map<String, Object> params);
    /**
     * 附件上传
     * @param ycejFileEntity
     * @param params
     */
    void insertReturnId(YcejFileEntity ycejFileEntity,Map<String, Object> params);
    /**
     * 根据订单查询附件信息
     * @param params
     * @return
     */
	List<OrderFileEntity> queryList(Map<String, Object> params);
	/**
	 * 附件删除
	 * @param params
	 */
	void delClassesFile(Map<String, Object> params);
}

