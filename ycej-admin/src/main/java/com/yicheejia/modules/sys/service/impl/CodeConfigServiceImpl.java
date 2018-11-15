package com.yicheejia.modules.sys.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.common.utils.redis.SerialNoUtils;
import com.yicheejia.modules.sys.dao.CodeConfigDao;
import com.yicheejia.modules.sys.entity.CodeConfigEntity;
import com.yicheejia.modules.sys.service.CodeConfigService;
/**
 * 编码生成实现类
 * @author hunk
 *
 */
@Service("codeConfigService")
public class CodeConfigServiceImpl extends ServiceImpl<CodeConfigDao, CodeConfigEntity> implements CodeConfigService {

//    @Autowired
//    private RedissonClient redissonClient;
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<CodeConfigEntity> page = this.selectPage(new Query<CodeConfigEntity>(params).getPage(),
                new EntityWrapper<CodeConfigEntity>());

        return new LayuiPage(page.getRecords(), page.getTotal());
    }

    /**
     * 根据配置生成编码
     * 
     * @param codeConfigId
     *            配置主键 ：初始化
     * @param variable
     *            变量值
     * @return
     */
    @Transactional
    public String getSerialNO(String codeConfigId, String pre) {
        return genSerialNOBySyn(codeConfigId, pre);
    }
    
    /**
     * 使用redis 锁
     * @param codeConfigId
     * @param pre
     * @return
     */
    public String genSerialNOByRedisLock(String codeConfigId, String pre) {
        //Long counter = redisTemplate.opsForValue().increment("COUNTER", 1);
        //RLock lock = redissonClient.getLock(codeConfigId);
        try {
            //lock.lock(1, TimeUnit.MINUTES);
            return genSerialNO(codeConfigId, pre);
        } catch (Exception ex) {
            logger.error("------Error occurred:"+ex.getMessage());
        } finally {
            //lock.unlock();
            logger.info("Request  unlocked...");
        }
        return null;
    }
    
    /**
     * 同步锁
     * @param codeConfigId
     * @param pre
     * @return
     */
    public String genSerialNOBySyn(String codeConfigId, String pre) {
        synchronized (this) {
            return genSerialNO(codeConfigId, pre);
        }
    }
    
    /**
     * 获取订单号
     * @param codeConfigId
     * @param pre
     * @return
     */
    public String genSerialNO(String codeConfigId, String pre) {
        //1.先更新
        baseMapper.updateSerialValue(codeConfigId);
        //2.查询
        CodeConfigEntity cce = baseMapper.selectById(codeConfigId);
        if (cce == null || cce.getSerialDate() == null) {
            throw new RRException("请维护" + codeConfigId + "的生成规则信息");
        }
        //若编号日期与数据库日期不符合，更新编号日期
        if (!cce.getSerialDate().equals(DateUtils.getYMDDate())) {
            baseMapper.resetSerialValue(DateUtils.getYMDDate(), codeConfigId);
            cce = baseMapper.selectById(codeConfigId);
        }
        // 变量值
        String suf = cce.getSerialValue() + "";
        String serialNO = SerialNoUtils.getSerialNumber(pre, cce.getSerialLength(), suf);
        logger.info(codeConfigId+"编号获取成功 :" + serialNO );
        return serialNO;
    }
    


}
