package com.yicheejia.modules.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.order.dao.LoanInfoDao;
import com.yicheejia.modules.order.entity.LoanInfoEntity;
import com.yicheejia.modules.order.service.LoanInfoService;


@Service("loanInfoService")
public class LoanInfoServiceImpl extends ServiceImpl<LoanInfoDao, LoanInfoEntity> implements LoanInfoService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	EntityWrapper<LoanInfoEntity> wrapper = new EntityWrapper<>();
    	String customName = params.get("customerName").toString();
    	String idNo  = params.get("idNo").toString();
    	/*wrapper.where("1 = 1");
        if(customName !=null && !"".equals(customName)){
        	wrapper.and("customer_name = '"+customName+"'");
        }
        if( idNo!=null && !"".equals(idNo)){
        	wrapper.and("id_no ='"+idNo+"'"); 
        }*/
        wrapper.addFilter("customer_name = '" + customName + "' and id_no ='" + idNo + "'" );
        wrapper.orderBy("create_time",false);
        Page<LoanInfoEntity> page = this.selectPage(
                new Query<LoanInfoEntity>(params).getPage(),
                wrapper
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

	@Override
	public List<LoanInfoEntity> queryLoaninfo() {
		EntityWrapper<LoanInfoEntity> wrapper = new EntityWrapper<>();
		wrapper.where("order_id is null or order_id = ''");
		wrapper.orderBy("id_no");
		wrapper.orderBy("create_time desc");
		return this.selectList(wrapper);
	}
	
	@Override
	public LoanInfoEntity selectLoaninfo(Map<String, Object> params) {
		List<LoanInfoEntity> list = baseMapper.selectLoaninfo(params);
		LoanInfoEntity loaninfo = new LoanInfoEntity();
		if(list.size() > 0){
			loaninfo = list.get(0);
		}
		return loaninfo;
	}

}
