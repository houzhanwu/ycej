package com.yicheejia.common.utils.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.RedisUtils;

/**
 * @author hunk
 * desc: 新增编码池步骤：
 * 1.新增枚举常量
 * 2.SerialNoUtils类中添加方法获取方法顺序编号生成工具 
 */
@Component
public class SerialNoUtils {
    
    @Autowired
    RedisUtils redisUtils;


    /**
     * 获取前缀，当前时间戳
     * 
     * @return
     */
    public static String getPrefix() {
        String str = DateUtils.getYMDDate();
        return str;
    }

    /**
     * 获取后缀（redis队列中取）
     * 
     * @param redisTemplate
     * @return
     */
    public static String getRedisSuffix(RedisTemplate<String, Integer> redisTemplate, String serialNumType) {
        int num = RedisSerialNum.getSerialNum(redisTemplate, serialNumType);
        return String.valueOf(num);
    }

    /**
     * 
     * @param redisTemplate
     * @return
     */
    public static String getRedisSerialNumber(RedisTemplate<String, Integer> redisTemplate, String serialNumType,int length) {
        String pre = getPrefix();
        String suf = getRedisSuffix(redisTemplate, serialNumType);
        suf = getPreZeroNumber(length, suf);
        return pre + "" + suf;
    }



    /**
     * 数字补0
     * 
     * @param num
     *            需要补充到num位
     * @param str
     *            待补充0的字符串(数字转化而来)
     * @return
     */
    public static String getPreZeroNumber(int num, String str) {
        int strLen = str.length();
        if (strLen < num) {
            for (int i = strLen; i < num; i++) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);
                str = sb.toString();
            }
        }
        return str;
    }

    /**
     * key = 类别+日期
     * @param serialNumType
     * @param pre
     * @return
     */
    public  String getSerialNumber(SerialType st,String pre) {
        
        return pre + getRedisSerialNumber(redisUtils.getRedisTemplate(), st.getValue()+getPrefix(),st.getLength());
    }
    
    /**
     * 数据库使用
     * @param pre 常量
     * @param length 变量长度
     * @param suf 变量
     * @return
     */
    public static String getSerialNumber(String pre,int length, String suf) {
        
        String date = getPrefix();
        return pre + date + getPreZeroNumber(length, suf);
    }

}