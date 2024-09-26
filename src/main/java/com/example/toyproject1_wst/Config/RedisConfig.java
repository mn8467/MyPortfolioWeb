package com.example.toyproject1_wst.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@CacheConfig
@Configuration
public class RedisConfig  {
    @Value("localhost")
    private String host;
    @Value("6379")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){ //redis에 접근하기위한 커넥션 팩토리
        return new LettuceConnectionFactory(host,port);
    }



}
