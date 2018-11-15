package com.yicheejia.modules.insure.service.impl;

import org.apache.poi.hssf.record.chart.BeginRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.insure.controller.InsureDetailController;
import com.yicheejia.modules.insure.dao.InsureDao;
import com.yicheejia.modules.insure.dao.InsureDetailDao;
import com.yicheejia.modules.insure.entity.InsureDetailEntity;
import com.yicheejia.modules.insure.entity.InsureEntity;
import com.yicheejia.modules.insure.service.InsureDetailService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;


@Service("insureDetailService")
public class InsureDetailServiceImpl extends ServiceImpl<InsureDetailDao, InsureDetailEntity> implements InsureDetailService {
	@Autowired
	private InsureDao insureDao;
	//日志
    protected Logger logger = LoggerFactory.getLogger(InsureDetailService.class);
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<InsureDetailEntity> page = this.selectPage(
                new Query<InsureDetailEntity>(params).getPage(),
                new EntityWrapper<InsureDetailEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }
    
	@Override
	@Transactional
	public void insertDetail(InsureDetailEntity insureDetail) {
		try {
			InsureEntity insure = insureDetail.getInsureEntity();
			insure.setInsertId(ShiroUtils.getUserId()+"");
			insure.setInsertTime(DateUtils.getDate());
			insure.setUpdateTime(DateUtils.getDate());
			BigDecimal strongAmount = insureDetail.getStrongAmount();
			BigDecimal commercialAmount = insureDetail.getCommercialAmount();
			strongAmount = strongAmount == null ? strongAmount.ZERO : strongAmount ;
			commercialAmount = commercialAmount == null ? commercialAmount.ZERO : commercialAmount ;
			insure.setPremium(strongAmount.add(commercialAmount));
			insureDao.insert(insure);
			insureDetail.setInsureId(insure.getInsureId());
			insureDetail.setInsertId(ShiroUtils.getUserId()+"");
			insureDetail.setInsertTime(DateUtils.getDate());
			insureDetail.setUpdateTime(DateUtils.getDate());
			baseMapper.insert(insureDetail);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("数据保存异常！"+e.getMessage(),e);
			throw new RRException("数据保存异常！");
		}
	}

	@Override
	@Transactional
	public void updateDetail(InsureDetailEntity insureDetail) {
		try {
			InsureEntity insure = insureDetail.getInsureEntity();
			insure.setUpdateId(ShiroUtils.getUserId()+"");
			insure.setUpdateTime(DateUtils.getDate());
			BigDecimal strongAmount = insureDetail.getStrongAmount();
			BigDecimal commercialAmount = insureDetail.getCommercialAmount();
			strongAmount = strongAmount == null ? strongAmount.ZERO : strongAmount  ;
			commercialAmount = commercialAmount == null ? commercialAmount.ZERO : commercialAmount ;
			insure.setPremium(strongAmount.add(commercialAmount));
			//insure.setPremium(insureDetail.getStrongAmount().add(insureDetail.getCommercialAmount()));
			insure.setInsureId(insureDetail.getInsureId());
			insureDao.updateInsure(insure);
			insureDetail.setUpdateId(ShiroUtils.getUserId()+"");
			insureDetail.setUpdateTime(DateUtils.getDate());
			baseMapper.updateInsureDetail(insureDetail);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("数据保存异常！"+e.getMessage(),e);
			throw new RRException("数据保存异常！");
		}
	}

	@Override
	public InsureDetailEntity queryDetail(Map<String, Object> params) {
		try {
			return baseMapper.queryDetail(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("查询错误！"+e.getMessage(),e);
			throw new RRException("查询错误！");
		}
	}

}
