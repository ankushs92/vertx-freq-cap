package com.in.ankushs.freqcapping.handler

import com.in.ankushs.freqcapping.util.Json
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.ext.dropwizard.MetricsService
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component

/**
 * Created by ankushsharma on 14/11/17.
 */
@Component
class MetricsHandler implements Handler<RoutingContext> {

    final MetricsService metricsService
    final Vertx vertx

    MetricsHandler(
            Vertx vertx,
            MetricsService metricsServic
    )
    {
        this.metricsService = metricsService
        this.vertx = vertx
    }

    @Override
    void handle(RoutingContext rc) {
        def response = rc.response()
        def jsonMetrics = metricsService.getMetricsSnapshot(vertx)
        def json = Json.toPrettyJson(jsonMetrics)
        response.putHeader("Content-type","application/json")
                .putHeader("Connection","Keep-Alive")
                .end(json)
    }
}
