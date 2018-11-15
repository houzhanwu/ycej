package com.yicheejia.common.aspect;

import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.Constant;
import com.yicheejia.modules.shop.constant.ShopConstant;
import com.yicheejia.modules.sys.service.SysPartnerShopService;
import com.yicheejia.modules.sys.service.SysUserShopService;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 合伙人查看门店过滤
 */
@Aspect
@Component
public class PartnerShopFilterAspect {

    @Autowired
    private SysPartnerShopService sysPartnerShopService;

    @Autowired
    private SysUserShopService sysUserShopService;

    @Pointcut("@annotation(com.yicheejia.common.annotation.PartnerShopFilter)")
    public void partShopPointCut(){

    }

    @Before("partShopPointCut()")
    public void partnerShopFilter(JoinPoint joinPoint){
        Object params = joinPoint.getArgs()[0];
        if(params != null && params instanceof Map){
            //登录用户id
            Long userId = ShiroUtils.getUserId();
            Integer dataFilter = ShiroUtils.getUserEntity().getDataFilter();
            if(userId != Constant.SUPER_ADMIN && dataFilter != Constant.DataFilter.FALSE.getValue()){
                Map map = (Map) params;
                map.put("filterFlag",Constant.FILTER_FLAG);
                List<Integer> partnerShopIdList = sysPartnerShopService.queryShopIdList(userId);
                List<Integer> shopIdList = sysUserShopService.queryShopIdList(userId);

                if(shopIdList != null && shopIdList.size() > 0){
                    if(partnerShopIdList != null && partnerShopIdList.size() >0){
                        //若合伙人门店集合不为空，取门店与合伙人门店集合并集
                        partnerShopIdList.removeAll(shopIdList);
                        shopIdList.addAll(partnerShopIdList);

                    }
                    map.put(Constant.SQL_FILTER,getSQLFilter(shopIdList));
                }else{
                    map.put(Constant.SQL_FILTER," (shop_id is null) ");
                }
                map.put(ShopConstant.SHOP_ID_LIST,shopIdList);

            }
            return;
        }

        throw new RRException("参数类型只能是Map,且不能为NULL");
    }

    private String getSQLFilter(List<Integer> shopIdList){

        StringBuilder sqlFilter = new StringBuilder();
        sqlFilter.append(" (");
        sqlFilter.append("shop_id in (").append(StringUtils.join(shopIdList,",")).append(")");
        sqlFilter.append(")");

        return sqlFilter.toString();
    }
}
