package com.yicheejia.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.modules.sys.entity.CodeConfigEntity;

import java.util.Map;

/**
 * 
 *
 * @author hunk
 * @email 
 * @date 2018-07-22 16:55:33
 */
public interface CodeConfigService extends IService<CodeConfigEntity> {

    LayuiPage queryPage(Map<String, Object> params);
    /**
     * 根据配置生成编码
     * @param codeConfigId 配置主键 ：初始化
     * @param variable  变量值
     * @return
     */
    public String getSerialNO(String codeConfigId,String variable);
    
}

