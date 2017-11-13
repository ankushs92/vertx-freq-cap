package com.in.ankushs.freqcapping.handler

import com.in.ankushs.freqcapping.service.FreqCapService
import com.in.ankushs.freqcapping.util.Dates
import com.in.ankushs.freqcapping.util.Json
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component

/**
 * Created by ankushsharma on 13/11/17.
 */
@Component
class FreqCapHandler implements Handler<RoutingContext>{

    final FreqCapService freqCapService

    FreqCapHandler(FreqCapService freqCapService){
        this.freqCapService = freqCapService
    }


    @Override
    void handle(RoutingContext rc) {
        def req = rc.request()

        def campaignId = req.getParam('campaignId') as Integer
        def timestamp = Dates.isoToLocalDateTime(req.getParam('timestamp'))

        freqCapService.getDetails(campaignId, timestamp)
                      .setHandler{ ar ->
                            if(ar.succeeded()){
                                def details = ar.result()
                                def resp = rc.response()

                                //This would be serialized to JSON
                                def responseMap = [
                                        hourlyCount : details.hourlyCount,
                                        dailyCount : details.dailyCount,
                                        totalCount : details.totalCount,
                                        capReached : details.capReached ]

                                def json = Json.toPrettyJson(responseMap)
                                resp.putHeader('Connection','Keep-Alive')
                                    .putHeader('Content-Type','application/json')
                                    .end(json)
                            }
                            else{
                                rc.fail(ar.cause())
                            }
                      }
    }
}
