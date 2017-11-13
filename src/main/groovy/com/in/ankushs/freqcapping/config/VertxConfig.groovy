package com.in.ankushs.freqcapping.config

import groovy.util.logging.Slf4j
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.http.HttpServer
import io.vertx.ext.dropwizard.DropwizardMetricsOptions
import io.vertx.ext.dropwizard.MetricsService
import io.vertx.ext.web.Router
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by ankushsharma on 01/09/17.
 */
@Configuration
@Slf4j
class VertxConfig {

    @Bean("vertx")
    Vertx vertx(){
        Vertx.vertx(
                new VertxOptions()
                    .setBlockedThreadCheckInterval(2000L)
                    .setHAEnabled(true)
                    .setMetricsOptions(
                        new DropwizardMetricsOptions()
                                .setEnabled(true)
            )
        ).exceptionHandler{ ex ->
            log.error 'Unhandeled Exception {}', ex
        }
    }

    @Bean
    HttpServer httpServer(){
        vertx().createHttpServer().requestHandler(router().&accept).listen(8080)
    }

    @Bean
    Router router(){
        Router.router(vertx())
    }

    @Bean("metricsService")
    MetricsService metrics(){
        MetricsService.create(vertx())
    }

}

