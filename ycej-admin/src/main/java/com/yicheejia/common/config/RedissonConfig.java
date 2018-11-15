package com.yicheejia.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RedissonConfig {

    @Value("${spring.servlet.host}")
    private String host;
    
    @Value("${spring.servlet.port}")
    private String port;
    
    @Value("${spring.servlet.password}")
    private String password;
    
    
    @Bean
    public RedissonClient getRedisson(){
        
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        //添加主从配置
//        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
        
        return Redisson.create(config);
    }
    
}