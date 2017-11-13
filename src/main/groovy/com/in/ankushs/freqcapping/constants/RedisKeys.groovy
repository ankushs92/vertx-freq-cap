package com.in.ankushs.freqcapping.constants

/**
 * Created by ankushsharma on 13/11/17.
 */
class RedisKeys {

    static final String USER_IMPRESSIONS_HOURLY_COUNT_HASH_KEY = "user_imp_count_hourly"
    static final String USER_IMPRESSIONS_HOURLY_COUNT_HASH_FIELD_TEMPLATE = "<camp_id>:<hour_as_iso>"

    static final String USER_IMPRESSIONS_DAILY_COUNT_HASH_KEY = "user_imp_count_daily"
    static final String USER_IMPRESSIONS_DAILY_COUNT_HASH_FIELD_TEMPLATE = "<camp_id>:<date_as_iso>"

    static final String USER_IMPRESSIONS_TOTAL_COUNT_HASH_KEY = "user_imp_count_total"
    static final String USER_IMPRESSIONS_TOTAL_COUNT_HASH_FIELD = "count"

}
