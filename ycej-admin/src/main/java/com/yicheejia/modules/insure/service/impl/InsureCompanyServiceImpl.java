package com.yicheejia.modules.insure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.insure.dao.InsureCompanyDao;
import com.yicheejia.modules.insure.entity.InsureCompanyEntity;
import com.yicheejia.modules.insure.service.InsureCompanyService;
import com.yicheejia.modules.sys.service.SysUserService;


@Service("insureCompanyService")
public class InsureCompanyServiceImpl extends ServiceImpl<InsureCompanyDao, InsureCompanyEntity> implements InsureCompanyService {
	@Autowired
	private SysUserService sysUserservice;
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
    	String customer = params.get("customer")+"";
    	EntityWrapper<InsureCompanyEntity> wrapper = new EntityWrapper<>();
        if(!"null".equals(customer) && !"".equals(customer.toString())){
            wrapper.where("company_name like '%"+customer.toString()+"%'");
        }
        wrapper.orderBy("update_time desc");
        Page<InsureCompanyEntity> page = this.selectPage(
                new Query<InsureCompanyEntity>(params).getPage(),
                wrapper
        );
        List<String> userList = new ArrayList<String>();
        for (InsureCompanyEntity insure : page.getRecords()) {
            userList.add(insure.getInsertId());
            userList.add(insure.getUpdateId());
        }
        Map<String,String> map = sysUserservice.getUserName(userList);
        for (InsureCompanyEntity insure : page.getRecords()) {
            if (map.containsKey(insure.getInsertId())) {
            	insure.setInsertId(map.get(insure.getInsertId()));
            }
            if (map.containsKey(insure.getUpdateId())) {
            	insure.setUpdateId(map.get(insure.getUpdateId()));
            }
        }
    
        return new LayuiPage(page.getRecords(), page.getTotal());
    }

}
