package com.in.ankushs.freqcapping.service

import com.in.ankushs.freqcapping.model.FreqCapDetails
import io.vertx.core.Future

import java.time.LocalDateTime

/**
 * Created by ankushsharma on 14/11/17.
 */
interface FreqCapService {

    Future<FreqCapDetails> getDetails(Integer campaign, LocalDateTime timestamp)
}
