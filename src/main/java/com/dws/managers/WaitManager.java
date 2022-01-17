package com.dws.managers;

import static com.dws.managers.DriverManager.getDriver;
import static com.dws.managers.PropertiesManager.getThisProperties;
import static com.dws.utils.ProperitesConstant.WAIT_SLEEPINMILLIS;
import static com.dws.utils.ProperitesConstant.WAIT_TIMEOUTINSECONDS;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitManager {
    private static WebDriverWait instance = null;
    
    public static WebDriverWait getWaitManager() {
        if(instance == null) {
            long waitTime = Long.parseLong(getThisProperties()
                .getProperty(WAIT_TIMEOUTINSECONDS));
            long waitSleep = Long.parseLong(getThisProperties()
                    .getProperty(WAIT_SLEEPINMILLIS));
            instance = new WebDriverWait(getDriver(), waitTime, waitSleep);
        }
        return instance;
    }
}
