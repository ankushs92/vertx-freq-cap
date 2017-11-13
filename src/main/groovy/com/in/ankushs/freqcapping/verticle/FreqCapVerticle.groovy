package com.in.ankushs.freqcapping.verticle

import com.in.ankushs.freqcapping.handler.FreqCapHandler
import com.in.ankushs.freqcapping.handler.FreqCapHandlerFailureHandler
import com.in.ankushs.freqcapping.handler.MetricsHandler
import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import org.springframework.stereotype.Component

/**
 * Created by ankushsharma on 13/11/17.
 */
@Component
class FreqCapVerticle extends AbstractVerticle{

    final Router router
    final FreqCapHandler freqCapHandler
    final FreqCapHandlerFailureHandler freqCapHandlerFailureHandler
    final MetricsHandler metricsHandler

    FreqCapVerticle(
            Router router,
            FreqCapHandler freqCapHandler,
            FreqCapHandlerFailureHandler freqCapHandlerFailureHandler,
            MetricsHandler metricsHandler
    )
    {
        this.router = router
        this.freqCapHandler = freqCapHandler
        this.freqCapHandlerFailureHandler = freqCapHandlerFailureHandler
        this.metricsHandler = metricsHandler
    }

    @Override
    void start(){

        router.get('/cap')
              .handler(freqCapHandler)
              .failureHandler(freqCapHandlerFailureHandler)

        router.get('/metrics')
              .handler(freqCapHandler)

    }
}
