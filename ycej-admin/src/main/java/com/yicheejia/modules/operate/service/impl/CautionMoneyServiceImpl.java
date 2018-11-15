package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.CautionMoneyDao;
import com.yicheejia.modules.operate.entity.CautionMoneyEntity;
import com.yicheejia.modules.operate.service.CautionMoneyService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;


@Service("cautionMoneyService")
public class CautionMoneyServiceImpl extends ServiceImpl<CautionMoneyDao, CautionMoneyEntity> implements CautionMoneyService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
//        Page<CautionMoneyEntity> page = this.selectPage(
//                new Query<CautionMoneyEntity>(params).getPage(),
//                new EntityWrapper<CautionMoneyEntity>()
//        );
//
//        return new LayuiPage(page.getRecords(), page.getTotal());
        
        Page<CautionMoneyEntity> page = new Query<CautionMoneyEntity>(params).getPage();
        
        List<CautionMoneyEntity> cautionList = baseMapper.selectCautionPage(page, params);

        page.setRecords(cautionList);
        return new LayuiPage(page.getRecords(), page.getTotal());
    }

    /**
     * 新增提交
     */
	@Override
	public void submitCaution(CautionMoneyEntity cautionMoney) {
		
		cautionMoney.setStatus("2");
		cautionMoney.setInsertTime(new Date());
    	cautionMoney.setInsertId(ShiroUtils.getUserEntity().getUserId().toString());
		baseMapper.submitCaution(cautionMoney);
		
	}

	/**
	 * 已暂存提交
	 */
	@Override
	public void updateCaution(CautionMoneyEntity cautionMoney) {

		cautionMoney.setStatus("2");
		cautionMoney.setUpdateId(ShiroUtils.getUserEntity().getUserId().toString());
		cautionMoney.setUpdateTime(new Date());
		baseMapper.updateCaution(cautionMoney);
	}

}
