package com.in.ankushs.freqcapping.listeners

import groovy.util.logging.Slf4j
import io.vertx.redis.RedisClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextClosedEvent
import org.springframework.stereotype.Component

/**
 * Class that can do certain stuff with Spring beans just before the app shutsdowns.
 * We only want to close redis connection
 * Created by ankushsharma on 14/11/17.
 */
@Component
@Slf4j
class ApplicationStopListener implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    RedisClient redis

    @Override
    void onApplicationEvent(ContextClosedEvent event) {
        log.info 'Closing down RedisClient'
        redis.close{ ar ->
            if(ar.succeeded()){
                log.info 'RedisClient closed succesfully'
            }
            else{
                log.error 'Unable to close Redis Client'
            }
        }
    }

}
