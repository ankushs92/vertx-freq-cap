package com.in.ankushs.freqcapping.util
/**
 * Created by Ankush on 17/07/17.
 */
class Strings {

    /*
    * Returns false if the String does not have text after trimming
    * */
     static boolean hasText(String text)
     {
        if(!text){
            return false
        }
        for(char ch in text.toCharArray()){
            if(!Character.isWhitespace(ch)){
                return true
            }
        }
        return false
    }

}
