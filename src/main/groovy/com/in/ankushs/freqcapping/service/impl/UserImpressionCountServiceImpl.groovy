package com.in.ankushs.freqcapping.service.impl

import com.in.ankushs.freqcapping.repository.UserImpressionCountRepository
import com.in.ankushs.freqcapping.service.UserImpressionCountService
import com.in.ankushs.freqcapping.util.PreConditions
import groovy.util.logging.Slf4j
import io.vertx.core.Future
import org.springframework.stereotype.Service

import java.time.LocalDateTime

/**
 * Created by ankushsharma on 13/11/17.
 */
@Service
@Slf4j
class UserImpressionCountServiceImpl implements UserImpressionCountService{

    final UserImpressionCountRepository repo

    UserImpressionCountServiceImpl(UserImpressionCountRepository repo){
        this.repo = repo
    }

    @Override
    Future<Integer> getHourlyCount(
            Integer campaignId,
            LocalDateTime timestamp
    )
    {
        PreConditions.notNull(campaignId, 'campaignId cannot be null')
        PreConditions.notNull(timestamp, 'timestamp cannot be null')

        return repo.getHourlyCount(campaignId, timestamp)
    }

    @Override
    Future<Integer> getDailyCount(
            Integer campaignId,
            LocalDateTime timestamp
    )
    {
        PreConditions.notNull(campaignId, 'campaignId cannot be null')
        PreConditions.notNull(timestamp, 'timestamp cannot be null')

        return repo.getDailyCount(campaignId, timestamp)
    }

    @Override
    Future<Integer> getTotalCount(
            Integer campaignId,
            LocalDateTime timestamp
    )
    {
        PreConditions.notNull(campaignId, 'campaignId cannot be null')
        PreConditions.notNull(timestamp, 'timestamp cannot be null')

        return repo.getTotalCount(campaignId, timestamp)
    }

}
