package com.in.ankushs.freqcapping.service

import io.vertx.core.Future

import java.time.LocalDateTime

/**
 * Created by ankushsharma on 13/11/17.
 */
interface UserImpressionCountService {

    Future<Integer> getHourlyCount(Integer campaignId, LocalDateTime timestamp)

    Future<Integer> getDailyCount(Integer campaignId, LocalDateTime timestamp)

    Future<Integer> getTotalCount(Integer campaignId, LocalDateTime timestamp)

}
