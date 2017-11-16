package com.in.ankushs.freqcapping.config

import com.in.ankushs.freqcapping.util.EnvironmentUtil
import io.vertx.core.Vertx
import io.vertx.redis.RedisClient
import io.vertx.redis.RedisOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Redis configuration goes here. We're going to use vertx-redis-client to work with redis
 * Created by Ankush on 03/03/17.
 */
@Configuration
class RedisConfig {

    @Autowired
    Vertx vertx

    @Autowired
    EnvironmentUtil env

    @Bean("redis")
    RedisClient redisClient(){
        def host
        def port = 6379
        def redisOptions =  RedisOptions.newInstance()
        if(env.isDev() || env.isTest()){
            host = 'localhost'
        }
        else{
          host = 'PUT_YOUR_HOST_THERE'
          redisOptions.auth = "AUTH_IF_NECESSARY"
        }

        redisOptions.host = host
        redisOptions.port = port

        RedisClient.create(vertx,redisOptions)
    }
}
