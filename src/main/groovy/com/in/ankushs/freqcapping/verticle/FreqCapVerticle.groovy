package com.in.ankushs.freqcapping.verticle

import com.in.ankushs.freqcapping.handler.FailureHandler
import com.in.ankushs.freqcapping.handler.FreqCapHandler
import com.in.ankushs.freqcapping.handler.MetricsHandler
import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE

/**
 * Created by ankushsharma on 13/11/17.
 */
@Component
@Scope(SCOPE_PROTOTYPE)
class FreqCapVerticle extends AbstractVerticle{

    final Router router
    final FreqCapHandler freqCapHandler
    final FailureHandler failureHandler
    final MetricsHandler metricsHandler

    FreqCapVerticle(
            Router router,
            FreqCapHandler freqCapHandler,
            FailureHandler failureHandler,
            MetricsHandler metricsHandler
    )
    {
        this.router = router
        this.freqCapHandler = freqCapHandler
        this.failureHandler = failureHandler
        this.metricsHandler = metricsHandler
    }

    @Override
    void start(){
        router.get('/cap')
              .handler(freqCapHandler)
              .failureHandler(failureHandler)

        router.get('/metrics')
              .handler(metricsHandler)
              .failureHandler(failureHandler)
    }
}
