package com.dws.managers;

import static com.dws.managers.DriverManager.getDriver;
import static com.dws.managers.DriverManager.quitDriver;
import static com.dws.managers.PageManager.getPageManager;
import static com.dws.managers.PropertiesManager.getThisProperties;
import static com.dws.utils.ProperitesConstant.*;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitManager.class);
    private static final InitManager instance = new InitManager();

    public static void initFramework() {
        LOGGER.debug("Start init framework");
        int implicityWait = Integer.parseInt(getThisProperties()
                .getProperty(DRIVER_IMPLICITY_WAIT));
        LOGGER.debug("Setting implicityWait timeout to {} sec.", implicityWait);
        getDriver().manage().timeouts().implicitlyWait(implicityWait, TimeUnit.SECONDS);
        
        int pageLoadTimeout = Integer.parseInt(getThisProperties()
                .getProperty(DRIVER_PAGE_LOAD_TIMEOUT));
        LOGGER.debug("Setting the pageLoadTimeout timeout to {} sec.", pageLoadTimeout);
        getDriver().manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        quitDriver();
        LOGGER.debug("Clearing the class store of web pages");
        getPageManager().clearMapPage();
    }
    
    public static void openBrowser() {
        try {
            String app_URL = getThisProperties().getProperty(APP_URL);
            LOGGER.debug("Site opening: {}", app_URL);
            getDriver().get(app_URL);
        } catch(NullPointerException ex) {
            String message = "Webdriver not initialized";
            LOGGER.error(message);
            Assertions.fail(message);
        } catch(WebDriverException ex) {
            if(ex.getLocalizedMessage().contains("ERR_INTERNET_DISCONNECTED")) {
                String message = "No internet connection | "
                        + instance.getClass().getName();
                LOGGER.error(message);
                Assertions.fail(message);
            } else {
                String message = "Unknown error when opening the site related "
                        + "to the web driver: " +
                        ex.getLocalizedMessage() + " | " +
                        instance.getClass().getName();
                LOGGER.error(message);
                Assertions.fail(message);
            }
        } catch(Exception ex) {
            String message = "Unknown error when opening the site: " +
                        ex.getLocalizedMessage() + " |" +
                        instance.getClass().getName();
            LOGGER.error(message);
            Assertions.fail(message);
        }
    }
    
    public static void clearCookies() {
        getDriver().manage().deleteAllCookies();
        LOGGER.debug("Clear Cookies");
    }
}