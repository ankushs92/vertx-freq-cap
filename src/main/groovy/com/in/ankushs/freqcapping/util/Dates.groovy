package com.in.ankushs.freqcapping.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Date utilities
 * Created by ankushsharma on 14/11/17.
 */
class Dates {

    private Dates(){}

    //Convert iso string to LocalDateTime object
    static LocalDateTime isoToLocalDateTime(String timestamp){
        PreConditions.notNull(timestamp, 'timestamp cannot be null')
        LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }

    // If timestamp = 2017-10-10 12:12:12, then hourTimestamp = 2017-10-10 12:00:00
    static long getHourTimestampAsIsoString(LocalDateTime timestamp){
        LocalDateTime.of(
                    timestamp.year,
                    timestamp.monthValue,
                    timestamp.dayOfMonth,
                    timestamp.hour,
                    0,
                    0
                    )
                .atZone(ZoneId.of("UTC"))
                .toInstant()
                .toEpochMilli()
    }


    static long getDateTimestampAsIsoString(LocalDateTime timestamp){
        LocalDateTime.of(
                    timestamp.year,
                    timestamp.monthValue,
                    timestamp.dayOfMonth,
                    0,
                    0,
                    0
                    )
                .atZone(ZoneId.of("UTC"))
                .toInstant()
                .toEpochMilli()
    }
}
