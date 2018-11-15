package com.yicheejia.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yicheejia.common.utils.redis.SerialNoUtils;
import com.yicheejia.common.utils.redis.SerialType;
import com.yicheejia.modules.sys.service.CodeConfigService;

/**
 * 编码生成
 * 
 * @author hunk
 *
 */
@Component
public class SerialNoSerUtils {

    @Autowired
    CodeConfigService codeConfigService;
    @Autowired
    SerialNoUtils serialNoUtils;
    @Value("${ycej.serialNo.usedb}")
    private boolean useredis;

    /**
     * 生成编号 ： 数据库需要数据库同步配置
     * @param st
     * @param pre
     * @return
     */
    public String getSerialNumber(SerialType st, String pre) {
        if (useredis) {
            return serialNoUtils.getSerialNumber(st, pre);
        } else {
            return codeConfigService.getSerialNO(st.getValue(), pre);
        }
  }
}
