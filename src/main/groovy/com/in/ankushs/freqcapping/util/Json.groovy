package com.in.ankushs.freqcapping.util;


import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by Ankush on 17/07/17.
 */
class Json {

    private static final ObjectMapper objectMapper = new ObjectMapper()

    static String toPrettyJson(Object object) throws Exception
    {
        PreConditions.notNull(object, "object cannot be null")
        return objectMapper
                .writer()
                .withDefaultPrettyPrinter()
                .writeValueAsString(object)
    }

}
