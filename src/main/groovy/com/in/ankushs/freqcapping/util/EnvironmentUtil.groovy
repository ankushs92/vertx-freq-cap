package com.in.ankushs.freqcapping.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

/**
 * Created by ankushsharma on 04/09/17.
 */
@Component
class EnvironmentUtil{

    @Autowired
    Environment environment

    boolean isDev(){
        "dev" in environment.getActiveProfiles()
    }

    boolean isTest(){
        "test" in environment.getActiveProfiles()
    }

}