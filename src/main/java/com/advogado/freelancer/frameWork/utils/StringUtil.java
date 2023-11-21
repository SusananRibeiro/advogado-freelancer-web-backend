package com.advogado.freelancer.frameWork.utils;
public class StringUtil {
    public static boolean validarString(String input){
        if(input == null || input == ""){
            return true;
        }

        return false;
    }
}
