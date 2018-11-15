package com.yicheejia.modules.sys.service.impl;

import com.yicheejia.common.utils.MapUtils;
import com.yicheejia.modules.sys.dao.SysUserShopDao;
import com.yicheejia.modules.sys.entity.SysUserShopEntity;
import com.yicheejia.modules.sys.service.SysUserShopService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.utils.Query;




@Service("sysUserShopService")
public class SysUserShopServiceImpl extends ServiceImpl<SysUserShopDao, SysUserShopEntity> implements SysUserShopService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysUserShopEntity> page = this.selectPage(
                new Query<SysUserShopEntity>(params).getPage(),
                new EntityWrapper<SysUserShopEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveOrUpdate(Long userId, List<Integer> shopIdList) {
        this.deleteByMap(new MapUtils().put("user_id",userId));

        if(shopIdList == null || shopIdList.size() == 0){
            return;
        }

        List<SysUserShopEntity> list = new ArrayList<>(shopIdList.size());
        for(Integer shopId : shopIdList){
            SysUserShopEntity sysUserShopEntity = new SysUserShopEntity();
            sysUserShopEntity.setUserId(userId);
            sysUserShopEntity.setShopId(shopId);

            list.add(sysUserShopEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Integer> queryShopIdList(Long userId) {
        return baseMapper.queryShopIdList(userId);
    }

    @Override
    public int deleteBatch(Integer[] shopIds) {
        return baseMapper.deleteBatch(shopIds);
    }

}
