package com.in.ankushs.freqcapping.constants

/**
 * Created by ankushsharma on 14/11/17.
 */
class CapConstants {

    //These are just hard coded for convenience purposes. In any production based setup, these fields should be fetched from db and saved to Redis
    static final HOURLY_COUNT_CAP = 100
    static final DAILY_COUNT_CAP = 1000
    static final TOTAL_COUNT_CAP = 10000

}
