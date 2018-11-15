package com.yicheejia.modules.sms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.sms.dao.SmsDao;
import com.yicheejia.modules.sms.entity.SmsEntity;
import com.yicheejia.modules.sms.service.SmsService;


@Service("smsService")
public class SmsServiceImpl extends ServiceImpl<SmsDao, SmsEntity> implements SmsService {

//	private static SmsServiceImpl smsServiceImpl;
//    /**
//     * 创建SmsServiceImpl对象
//     * @return
//     */
//	private SmsServiceImpl() {
//		
//	}
//	
//    public static SmsServiceImpl getInstance(){
//        if(smsServiceImpl==null){
//        	smsServiceImpl = new SmsServiceImpl();
//        }
//        return smsServiceImpl;
//    }
    
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<SmsEntity> page = this.selectPage(
                new Query<SmsEntity>(params).getPage(),
                new EntityWrapper<SmsEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

}
