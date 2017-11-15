package com.in.ankushs.freqcapping.repository.impl

import com.in.ankushs.freqcapping.constants.RedisKeys
import com.in.ankushs.freqcapping.repository.UserImpressionCountRepository
import com.in.ankushs.freqcapping.util.Dates
import com.in.ankushs.freqcapping.util.PreConditions
import groovy.util.logging.Slf4j
import io.vertx.core.Future
import io.vertx.redis.RedisClient
import org.springframework.stereotype.Repository

import java.time.LocalDateTime

/**
 * Created by ankushsharma on 13/11/17.
 */
@Repository
@Slf4j
class UserImpressionCountRedisRepositoryImpl implements UserImpressionCountRepository {

    private static final ZERO_COUNT = 0

    final RedisClient redis

    UserImpressionCountRedisRepositoryImpl(RedisClient redis){
        this.redis = redis
    }

    @Override
    Future<Integer> getHourlyCount(
            Integer campaignId,
            LocalDateTime timestamp
    )
    {
        PreConditions.notNull(campaignId, 'campaignId cannot be null')
        PreConditions.notNull(timestamp, 'timestamp cannot be null')

        def hashKey = RedisKeys.USER_IMPRESSIONS_HOURLY_COUNT_HASH_KEY
        def hashField = RedisKeys.USER_IMPRESSIONS_HOURLY_COUNT_HASH_FIELD_TEMPLATE
                                 .replace("<camp_id>", campaignId as String)
                                 .replace("<date_as_iso>", Dates.getHourTimestampAsIsoString(timestamp) as String)

        return process(hashKey, hashField)
    }

    @Override
    Future<Integer> getDailyCount(
            Integer campaignId,
            LocalDateTime timestamp
    )
    {
        PreConditions.notNull(campaignId, 'campaignId cannot be null')
        PreConditions.notNull(timestamp, 'timestamp cannot be null')

        def hashKey = RedisKeys.USER_IMPRESSIONS_DAILY_COUNT_HASH_KEY
        def hashField = RedisKeys.USER_IMPRESSIONS_DAILY_COUNT_HASH_FIELD_TEMPLATE
                                .replace("<camp_id>", campaignId as String)
                                .replace("<date_as_iso>", Dates.getDateTimestampAsIsoString(timestamp) as String)

        return process(hashKey, hashField)
    }

    @Override
    Future<Integer> getTotalCount(
            Integer campaignId,
            LocalDateTime timestamp
    )
    {
        PreConditions.notNull(campaignId, 'campaignId cannot be null')
        PreConditions.notNull(timestamp, 'timestamp cannot be null')

        def hashKey = RedisKeys.USER_IMPRESSIONS_TOTAL_COUNT_HASH_KEY
        def hashField = RedisKeys.USER_IMPRESSIONS_TOTAL_COUNT_HASH_FIELD
        return process(hashKey, hashField)
    }

    private Future<Integer> process(
            String hashKey,
            String hashField
    )
    {
        Future<Integer> future = Future.future()
        redis.hget(
                hashKey,
                hashField,
                { ar ->
                    if(ar.succeeded()){
                        def count = ar.result() as Integer
                        if(!count)
                            count = ZERO_COUNT
                        future.complete(count)
                    }
                    else{
                        future.complete(ZERO_COUNT)
                        log.error '', ar.cause()
                    }
                }
        )
        return future
    }
}
