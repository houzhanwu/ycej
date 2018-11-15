package com.yicheejia.modules.operate.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.operate.dao.CautionHistoryDao;
import com.yicheejia.modules.operate.entity.CautionHistoryEntity;
import com.yicheejia.modules.operate.service.CautionHistoryService;


@Service("cautionHistoryService")
public class CautionHistoryServiceImpl extends ServiceImpl<CautionHistoryDao, CautionHistoryEntity> implements CautionHistoryService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<CautionHistoryEntity> page = this.selectPage(
                new Query<CautionHistoryEntity>(params).getPage(),
                new EntityWrapper<CautionHistoryEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

	@Override
	public List<CautionHistoryEntity> selectListById(String cautionId) {
		// TODO Auto-generated method stub
		return baseMapper.selectListById(cautionId);
	}

}
