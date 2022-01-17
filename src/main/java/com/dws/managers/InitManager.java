package com.dws.managers;

import static com.dws.managers.DriverManager.getDriver;
import static com.dws.managers.DriverManager.quitDriver;
import static com.dws.managers.PageManager.getPageManager;
import static com.dws.managers.PropertiesManager.getThisProperties;
import static com.dws.utils.ProperitesConstant.*;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;

@Slf4j
public class InitManager {
    private static final InitManager instance = new InitManager();

    public static void initFramework() {
        log.debug("Start init framework");
        int implicityWait = Integer.parseInt(getThisProperties()
                .getProperty(DRIVER_IMPLICITY_WAIT));
        log.debug("Setting implicityWait timeout to {} sec.", implicityWait);
        getDriver().manage().timeouts().implicitlyWait(implicityWait, TimeUnit.SECONDS);
        
        int pageLoadTimeout = Integer.parseInt(getThisProperties()
                .getProperty(DRIVER_PAGE_LOAD_TIMEOUT));
        log.debug("Setting the pageLoadTimeout timeout to {} sec.", pageLoadTimeout);
        getDriver().manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        quitDriver();
        log.debug("Clearing the class store of web pages");
        getPageManager().clearMapPage();
    }
    
    public static void openBrowser() {
        try {
            String app_URL = getThisProperties().getProperty(APP_URL);
            log.debug("Site opening: {}", app_URL);
            getDriver().get(app_URL);
        } catch(NullPointerException ex) {
            String message = "Webdriver not initialized";
            log.error(message);
            Assertions.fail(message);
        } catch(WebDriverException ex) {
            if(ex.getLocalizedMessage().contains("ERR_INTERNET_DISCONNECTED")) {
                String message = "No internet connection | "
                        + instance.getClass().getName();
                log.error(message);
                Assertions.fail(message);
            } else {
                String message = "Unknown error when opening the site related "
                        + "to the web driver: " +
                        ex.getLocalizedMessage() + " | " +
                        instance.getClass().getName();
                log.error(message);
                Assertions.fail(message);
            }
        } catch(Exception ex) {
            String message = "Unknown error when opening the site: " +
                        ex.getLocalizedMessage() + " |" +
                        instance.getClass().getName();
            log.error(message);
            Assertions.fail(message);
        }
    }
    
    public static void clearCookies() {
        getDriver().manage().deleteAllCookies();
        log.debug("Clear Cookies");
    }
}