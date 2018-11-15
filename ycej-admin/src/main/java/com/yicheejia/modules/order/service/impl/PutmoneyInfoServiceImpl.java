package com.yicheejia.modules.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.order.dao.PutmoneyInfoDao;
import com.yicheejia.modules.order.entity.PutmoneyInfoEntity;
import com.yicheejia.modules.order.service.PutmoneyInfoService;


@Service("putmoneyInfoService")
public class PutmoneyInfoServiceImpl extends ServiceImpl<PutmoneyInfoDao, PutmoneyInfoEntity> implements PutmoneyInfoService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<PutmoneyInfoEntity> page = this.selectPage(
                new Query<PutmoneyInfoEntity>(params).getPage(),
                new EntityWrapper<PutmoneyInfoEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }
    /**
     * 根据条件获取放款信息
     */
	@Override
	public PutmoneyInfoEntity selectInfoByCondition(Map<String, Object> params) {
		return baseMapper.selectInfoByCondition(params);
	}

}
