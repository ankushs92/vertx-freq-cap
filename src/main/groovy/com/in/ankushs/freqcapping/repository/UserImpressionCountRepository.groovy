package com.in.ankushs.freqcapping.repository

import io.vertx.core.Future

import java.time.LocalDateTime

/**
 * Created by ankushsharma on 13/11/17.
 */
interface UserImpressionCountRepository {

    Future<Integer> getHourlyCount(Integer campaignId, LocalDateTime timestamp)

    Future<Integer> getDailyCount(Integer campaignId, LocalDateTime timestamp)

    Future<Integer> getTotalCount(Integer campaignId, LocalDateTime timestamp)

}
