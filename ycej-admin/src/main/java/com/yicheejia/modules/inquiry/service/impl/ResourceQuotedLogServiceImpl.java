package com.yicheejia.modules.inquiry.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;

import com.yicheejia.modules.inquiry.dao.ResourceQuotedLogDao;
import com.yicheejia.modules.inquiry.entity.ResourceQuotedLogEntity;
import com.yicheejia.modules.inquiry.service.ResourceQuotedLogService;


@Service("resourceQuotedLogService")
public class ResourceQuotedLogServiceImpl extends ServiceImpl<ResourceQuotedLogDao, ResourceQuotedLogEntity> implements ResourceQuotedLogService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Object quotedId = params.get("quotedId");
        EntityWrapper<ResourceQuotedLogEntity> wrapper = new EntityWrapper<>();

        if(quotedId != null && StringUtils.isNotBlank(quotedId.toString())){
            wrapper.eq("quoted_id",quotedId);
        }
        Page<ResourceQuotedLogEntity> page = this.selectPage(
                new Query<ResourceQuotedLogEntity>(params).getPage(),
                wrapper
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

}
