package com.in.ankushs.freqcapping.model

import java.time.LocalDateTime

/**
 * Created by ankushsharma on 14/11/17.
 */
class FreqCapDetails {

    LocalDateTime timestamp
    Integer hourlyCount = 0
    Integer dailyCount = 0
    Integer totalCount = 0
    boolean capReached

}
