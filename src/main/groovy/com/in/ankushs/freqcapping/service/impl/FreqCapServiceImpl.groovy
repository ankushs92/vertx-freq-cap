package com.in.ankushs.freqcapping.service.impl

import com.in.ankushs.freqcapping.model.FreqCapDetails
import com.in.ankushs.freqcapping.service.FreqCapService
import com.in.ankushs.freqcapping.service.UserImpressionCountService
import com.in.ankushs.freqcapping.util.PreConditions
import groovy.util.logging.Slf4j
import io.vertx.core.CompositeFuture
import io.vertx.core.Future
import org.springframework.stereotype.Service

import java.time.LocalDateTime

import static com.in.ankushs.freqcapping.constants.CapConstants.*

/**
 * Created by ankushsharma on 14/11/17.
 */
@Service
@Slf4j
class FreqCapServiceImpl implements FreqCapService{

    final UserImpressionCountService userImpressionCountService

    FreqCapServiceImpl(UserImpressionCountService userImpressionCountService){
        this.userImpressionCountService = userImpressionCountService
    }
    @Override
    Future<FreqCapDetails> getDetails(
            Integer campaignId,
            LocalDateTime timestamp
    )
    {
        PreConditions.notNull(campaignId, ' campaignId cannot be null')
        PreConditions.notNull(timestamp, ' timestamp cannot be null')

        def future = Future.future()

        def hourlyCountFuture = userImpressionCountService.getHourlyCount(campaignId, timestamp)
        def dailyCountFuture = userImpressionCountService.getDailyCount(campaignId, timestamp)
        def totalCountFuture = userImpressionCountService.getTotalCount(campaignId, timestamp)

        def futures = [hourlyCountFuture, dailyCountFuture, totalCountFuture].asImmutable()

        CompositeFuture.all(futures)
                       .setHandler{ ar ->
                            if(ar.succeeded()){
                                def resultList = ar.result().list()?.collect { it as Integer }

                                def hourlyCount = resultList[0]
                                def dailyCount = resultList[1]
                                def totalCount = resultList[2]

                                boolean capReached = false

                                if(hourlyCount > HOURLY_COUNT_CAP || dailyCount > DAILY_COUNT_CAP || totalCount > TOTAL_COUNT_CAP){
                                    capReached = true
                                }
                                def freqCapDetails = new FreqCapDetails(
                                        timestamp : timestamp,
                                        dailyCount : dailyCount,
                                        hourlyCount : hourlyCount,
                                        totalCount : totalCount,
                                        capReached : capReached
                                )
                                future.complete(freqCapDetails)
                            }
                            else{
                                future.fail(ar.cause())
                            }
                       }
        return future
    }
}
