package com.yicheejia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.order.entity.SalePromotionEntity;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 活动代金券信息表
 *
 * @author 
 * @email 
 * @date 2018-10-26 13:44:25
 */
public interface SalePromotionService extends IService<SalePromotionEntity> {

    LayuiPage queryPage(Map<String, Object> params);

	void inportsaleorder(String fileName, MultipartFile file);

	void updateBySaleId(Map<String, Object> params);

	List<Map<String, Object>> getPromotionList(Map<String, Object> params);

	List<Map<String, Object>> getZPPromotionList(Map<String, Object> params);

	List<Map<String, Object>> getPromotionListByOrderId(Map<String, Object> params);

}

