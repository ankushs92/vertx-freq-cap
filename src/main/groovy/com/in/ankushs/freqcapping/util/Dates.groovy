package com.in.ankushs.freqcapping.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by ankushsharma on 14/11/17.
 */
class Dates {

    static LocalDateTime isoToLocalDateTime(String timestamp){
        PreConditions.notNull(timestamp, 'timestamp cannot be null')
        LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }

}
