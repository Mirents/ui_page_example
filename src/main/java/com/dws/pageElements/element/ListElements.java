package com.dws.pageElements.element;

import static com.dws.managers.DriverManager.getDriver;
import static com.dws.managers.WaitManager.getWaitManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListElements {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ListElements.class);
    protected Actions action = new Actions(getDriver());
    private final By byThisElem;

    public ListElements(By by) {
        this.byThisElem = by;
    }
    
    public void clickToElementFromList(String name) {
        WebElement element = getElemFromListToName(name);
        element.click();
    }
    
    public void mouseMoveToElementFromList(String name) {
        WebElement element = getElemFromListToName(name);
        action.moveToElement(element).build().perform();
    }

    public WebElement getElemFromListToName(String name) {
        for (WebElement element: getDriver().findElements(byThisElem)) {
            if(element.getText().equalsIgnoreCase(name)) {
                getWaitManager().until(ExpectedConditions.visibilityOf(element));
                return element;
            }
        }
        String msg = "Element to name '" + name + "' not found to "
                + this.getClass().getName();
        LOGGER.error(msg);
        Assertions.fail(msg);
        return null;
    }
    
    public WebElement getElemFromListToBy(By by) {
        for (WebElement element: getDriver().findElements(byThisElem)) {
            getWaitManager().until(ExpectedConditions.visibilityOf(element));
            WebElement findElement = element.findElement(by);
            return findElement;
        }
        String msg = "Element to by '" + by + "' not found to "
                + this.getClass().getName();
        LOGGER.error(msg);
        Assertions.fail(msg);
        return null;
    }
}
