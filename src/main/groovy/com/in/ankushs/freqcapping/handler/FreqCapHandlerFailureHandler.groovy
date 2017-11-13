package com.in.ankushs.freqcapping.handler

import groovy.util.logging.Slf4j
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component

/**
 * Created by ankushsharma on 14/11/17.
 */
@Component
@Slf4j
class FreqCapHandlerFailureHandler implements Handler<RoutingContext> {

    @Override
    void handle(RoutingContext rc) {
        def failure = rc.failure()
        //We'll only be logging errors in this one

        log.error '', failure
        rc.response().end('Internal Server Error')

    }
}
