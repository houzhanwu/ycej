package com.yicheejia.modules.shop.service.impl;

import com.yicheejia.common.annotation.ShopFilter;
import com.yicheejia.common.utils.Constant;
import com.yicheejia.common.utils.LayuiPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.shop.dao.IntentionCustomerDao;
import com.yicheejia.modules.shop.entity.IntentionCustomerEntity;
import com.yicheejia.modules.shop.service.IntentionCustomerService;


@Service("intentionCustomerService")
public class IntentionCustomerServiceImpl extends ServiceImpl<IntentionCustomerDao, IntentionCustomerEntity> implements IntentionCustomerService {

    @Override
    @ShopFilter
    public LayuiPage queryPage(Map<String, Object> params) {

        Page<IntentionCustomerEntity> page = new Query<IntentionCustomerEntity>(params).getPage();
        page.setRecords(baseMapper.selectListPage(page,params));
        return new LayuiPage(page.getRecords(),page.getTotal());
    }
    /**
     * 订单财务确认时修改意向客户订单信息
     */
	@Override
	public void updateCustomer(IntentionCustomerEntity intention) {
		baseMapper.updateCustomer(intention);
	}
	
	/**
	 * 根据传入参数查询意向客户信息
	 * @param params
	 * @return
	 */
	@Override
	public List<IntentionCustomerEntity> selectIntentionCust(Map<String, Object> params) {
		
		return baseMapper.selectIntentionCust(params);
	}

	@Override
	public int selectByIntentionCustomer(IntentionCustomerEntity intentionCustomer) {
		String idCardNo = intentionCustomer.getIdCardNo();
		String customerTel = intentionCustomer.getCustomerTel();

		EntityWrapper<IntentionCustomerEntity> wrapper = new EntityWrapper<IntentionCustomerEntity>();
		wrapper.addFilter("customer_tel = '"+ customerTel+"'");

		if(StringUtils.isNotBlank(idCardNo)){
			wrapper.addFilter("id_card_no ="+"'"+idCardNo+"'");

		}
		wrapper.ne("intention_customer_state",1);
		int count = baseMapper.selectCount(wrapper);
		return count;
	}
	/**
     * 订单财务确认时修改意向客户订单信息
     */
	@Override
	public void updateCustomer(List<IntentionCustomerEntity> intention) {
		baseMapper.batchUpdateCustomer(intention);
	}

}
