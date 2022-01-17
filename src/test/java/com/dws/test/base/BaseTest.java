package com.dws.test.base;

import java.util.UUID;
import org.slf4j.MDC;

public class BaseTest {
    public static void setupLoggerUID() {
        MDC.put("uid", getShortUUID(4));
    }
    
    public static void clearLoggerUID() {
        MDC.clear();
    }
    
    private static String getShortUUID(int numCharacters) {
        StringBuffer uuid = 
                new StringBuffer(UUID.randomUUID().toString().replaceAll("-", ""));
        if(numCharacters > uuid.length()-1)
            numCharacters = uuid.length() - 1;
        return uuid.substring(0, numCharacters);
    }
}
