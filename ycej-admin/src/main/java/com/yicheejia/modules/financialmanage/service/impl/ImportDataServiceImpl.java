package com.yicheejia.modules.financialmanage.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.financialmanage.dao.ImportDataDao;
import com.yicheejia.modules.financialmanage.entity.ImportDataEntity;
import com.yicheejia.modules.financialmanage.service.ImportDataService;


@Service("importDataService")
public class ImportDataServiceImpl extends ServiceImpl<ImportDataDao, ImportDataEntity> implements ImportDataService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<ImportDataEntity> page = this.selectPage(
                new Query<ImportDataEntity>(params).getPage(),
                new EntityWrapper<ImportDataEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

	@Override
	public List<ImportDataEntity> selectPaymentDetail(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseMapper.selectPaymentDetail(params);
	}
	/**
	 * 银盛总账
	 */
	@Override
	public Map<String, Object> selectTotalForPos(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseMapper.selectTotalForPos(params);
	}
	/**
	 * 根据账户类型获取银盛总账明细
	 */
	@Override
	public Map<String, Object> selectTotalForPosByAccountType(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseMapper.selectTotalForPosByAccountType(params);
	}
	/**
	 * 从数据库查询导入pos明细
	 */
	@Override
	public List<ImportDataEntity> selectListByCondition(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseMapper.selectListByCondition(params);
	}
	/**
	 * 银行转账 确认收款页面 导入数据流水总账
	 */
	@Override
	public Map<String, Object> selectBankTotalByImport(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return baseMapper.selectBankTotalByImport(params);
	}

}
