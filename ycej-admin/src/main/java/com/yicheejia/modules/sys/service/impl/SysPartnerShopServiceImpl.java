package com.yicheejia.modules.sys.service.impl;

import com.yicheejia.common.utils.MapUtils;
import com.yicheejia.modules.sys.dao.SysPartnerShopDao;
import com.yicheejia.modules.sys.entity.SysPartnerShopEntity;
import com.yicheejia.modules.sys.service.SysPartnerShopService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;



@Service("sysPartnerShopService")
public class SysPartnerShopServiceImpl extends ServiceImpl<SysPartnerShopDao, SysPartnerShopEntity> implements SysPartnerShopService {

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<SysPartnerShopEntity> page = this.selectPage(
                new Query<SysPartnerShopEntity>(params).getPage(),
                new EntityWrapper<SysPartnerShopEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

    @Override
    public void saveOrUpdate(Long userId, List<Integer> partShopIdList) {
        this.deleteByMap(new MapUtils().put("user_id",userId));

        if(partShopIdList == null || partShopIdList.size() == 0){
            return;
        }

        List<SysPartnerShopEntity> list = new ArrayList<>(partShopIdList.size());
        for(Integer shopId : partShopIdList){
            SysPartnerShopEntity partnerShopEntity = new SysPartnerShopEntity();
            partnerShopEntity.setUserId(userId);
            partnerShopEntity.setShopId(shopId);

            list.add(partnerShopEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public int deleteBatch(Integer[] shopIds) {
        return baseMapper.deleteBatch(shopIds);
    }

    @Override
    public List<Integer> queryShopIdList(Long userId) {
        return baseMapper.queryPartnerShopIdList(userId);
    }

}
