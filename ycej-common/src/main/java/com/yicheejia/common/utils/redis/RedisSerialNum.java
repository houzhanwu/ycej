package com.yicheejia.common.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author hunk
 * 新增编码池步骤：
 * 1.新增枚举常量
 * 2.SerialNoUtils类中添加方法获取方法顺序编号生成工具(redis) 
 */
public class RedisSerialNum {

    private static final int WARNING_NUMBER = 10;
    private static final int NUMBER_QUEUE = 100;

    @Autowired
    public RedisTemplate<String, Integer> redisTemplate;

    private Logger log = LoggerFactory.getLogger(getClass());


    /**
     * 从编码池获取编码
     * 
     * @return
     */
    public static synchronized Integer getSerialNum(RedisTemplate<String, Integer> redisTemplate, String serial_type) {
        Integer num = 1;
        ListOperations<String, Integer> lop = redisTemplate.opsForList();
        //返回并删除链表尾部元素
        num = redisTemplate.opsForList().rightPop(serial_type);
        if (num == null || num == 0) {
            num = 1;
        }
        long count = getCount(lop, serial_type);
        if (count < WARNING_NUMBER) {// 小于安全数量，补充编码
            setSerialNums(createSerialNums(num + Integer.valueOf(String.valueOf(count))), redisTemplate, serial_type);
        }
        return num;
    }

    /**
     * 获取编码池编码数量
     * 
     * @return
     */
    public static long getCount(ListOperations<String, Integer> lop, String serial_type) {
        long count = lop.size(serial_type);
        return count;
    }

    /**
     * 向编码池添加编码
     * 
     * @param nums
     * @return
     */
    public static boolean setSerialNums(int[] nums, RedisTemplate<String, Integer> redisTemplate, String serial_type) {
        boolean boo = true;
        for (int num : nums) {
            //把值插入到链表头部
            redisTemplate.opsForList().leftPush(serial_type, num);
        }
        return boo;
    }

    /**
     * 创建新的编码
     * 
     * @param num
     * @return
     */
    public static int[] createSerialNums(Integer num) {
        int[] nums = new int[NUMBER_QUEUE];
        for (int i = 0; i < NUMBER_QUEUE; i++) {
            nums[i] = i + num + 1;
        }
        return nums;
    }

    /**
     * 批量重置编码池
     * 
     * @return
     */
    public boolean resetSerialNums() {
        boolean boo = true;
        for (SerialType s : SerialType.values()) {
            clearList(s.getValue());
        }
        return boo;
    }

    /**
     * 清空编码池
     * 
     * @param serial_num
     */
    public void clearList(String serial_num) {
        long count = redisTemplate.opsForList().size(serial_num);
        for (int i = 0; i < count; i++) {
            redisTemplate.opsForList().rightPop(serial_num);
        }
    }

}