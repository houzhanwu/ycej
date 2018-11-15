package com.yicheejia;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yicheejia.common.utils.RedisUtils;
import com.yicheejia.modules.sys.entity.SysUserEntity;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void contextLoads() {
        SysUserEntity user = new SysUserEntity();
        user.setEmail("123456@qq.com");
      //  redisUtils.set("user", user);

        SysUserEntity user2 = new SysUserEntity();
        user2.setEmail("cn@163.com");

        List<SysUserEntity> list = new ArrayList<>();
        list.add(user);
        list.add(user2);

        redisUtils.set("list",list);

       // System.out.println(ToStringBuilder.reflectionToString(redisUtils.get("list", SysUserEntity.class)));

        System.out.println(redisUtils.get("list"));

        List<SysUserEntity> jsonList = JSONObject.parseArray(redisUtils.get("list"),SysUserEntity.class);
        for(SysUserEntity userEntity : jsonList){
            System.out.println(userEntity.getEmail());
        }
    }

}
