package com.in.ankushs.freqcapping.util
/**
 * Created by Ankush on 17/07/17.
 */
class PreConditions {

    static <T> void notNull(T t, String errorMsg)
    {
        if(Objects.isNull(t)){
            throw new IllegalArgumentException(errorMsg);
        }
    }

}
