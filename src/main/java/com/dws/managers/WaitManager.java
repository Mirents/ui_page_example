package com.dws.managers;

import static com.dws.managers.DriverManager.getDriver;
import static com.dws.managers.PropertiesManager.getThisProperties;
import static com.dws.utils.ProperitesConstant.WAIT_SLEEPINMILLIS;
import static com.dws.utils.ProperitesConstant.WAIT_TIMEOUTINSECONDS;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitManager {
    private static WebDriverWait INSTANCE = null;
    
    public static WebDriverWait getWaitManager() {
        if(INSTANCE == null) {
            long waitTime = Long.parseLong(getThisProperties()
                .getProperty(WAIT_TIMEOUTINSECONDS));
            long waitSleep = Long.parseLong(getThisProperties()
                    .getProperty(WAIT_SLEEPINMILLIS));
            INSTANCE = new WebDriverWait(getDriver(), waitTime, waitSleep);
        }
        return INSTANCE;
    }
}
