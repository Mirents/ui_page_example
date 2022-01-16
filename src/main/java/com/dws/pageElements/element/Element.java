package com.dws.pageElements.element;

import static com.dws.managers.DriverManager.getDriver;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Element implements WebElement, WrapsElement {
    protected static final Logger LOGGER = LoggerFactory.getLogger(Element.class);
    private final By byThisElem;

    public Element(By by) {
        this.byThisElem = by;
    }
    
    @Override
    public void click() {
        getDriver().findElement(byThisElem).click();
    }

    @Override
    public void submit() {
        getDriver().findElement(byThisElem).submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        getDriver().findElement(byThisElem).sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        getDriver().findElement(byThisElem).clear();
    }

    @Override
    public String getTagName() {
        return getDriver().findElement(byThisElem).getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return getDriver().findElement(byThisElem).getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return getDriver().findElement(byThisElem).isSelected();
    }

    @Override
    public boolean isEnabled() {
        return getDriver().findElement(byThisElem).isEnabled();
    }

    @Override
    public String getText() {
        return getDriver().findElement(byThisElem).getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getDriver().findElement(byThisElem).findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return getDriver().findElement(byThisElem).findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return getDriver().findElement(byThisElem).isDisplayed();
    }

    @Override
    public Point getLocation() {
        return getDriver().findElement(byThisElem).getLocation();
    }

    @Override
    public Dimension getSize() {
        return getDriver().findElement(byThisElem).getSize();
    }

    @Override
    public Rectangle getRect() {
        return getDriver().findElement(byThisElem).getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return getDriver().findElement(byThisElem).getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) {
        return getDriver().findElement(byThisElem).getScreenshotAs(target);
    }

    @Override
    public WebElement getWrappedElement() {
        return getDriver().findElement(byThisElem);
    }
}
