package com.yicheejia.modules.sys.dao;

import com.yicheejia.modules.sys.entity.CodeConfigEntity;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 编号生成服务：数据库版本
 * @author hunk
 * @email
 * @date 2018-07-22 16:55:33
 */
public interface CodeConfigDao extends BaseMapper<CodeConfigEntity> {

    /**
     * 更新序列号
     * 
     * @param codeConfigId
     */
    public void updateSerialValue(String codeConfigId);

    /**
     * 重置序列号号
     * 
     * @param serialDate
     * @param codeConfigId
     */
    public void resetSerialValue(@Param("serialDate") String serialDate, @Param("codeConfigId") String codeConfigId);

}
