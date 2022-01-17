package com.dws.utils;

import com.dws.managers.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

@Slf4j
public class WebDriverListener implements WebDriverEventListener {
    private final String color;
    private final int interval;
    private final int count;
    
    public WebDriverListener(String color, int interval, int count) {
        this.color = color;
        this.interval = interval;
        this.count = count;
    }

    @Override
    public void beforeAlertAccept(WebDriver wd) {
        log.trace("beforeAlertAccept");
    }

    @Override
    public void afterAlertAccept(WebDriver wd) {
        log.trace("afterAlertAccept");
    }

    @Override
    public void afterAlertDismiss(WebDriver wd) {
        log.trace("afterAlertDismiss");
    }

    @Override
    public void beforeAlertDismiss(WebDriver wd) {
        log.trace("beforeAlertDismiss");
    }

    @Override
    public void beforeNavigateTo(String string, WebDriver wd) {
        log.trace("beforeNavigateTo");
    }

    @Override
    public void afterNavigateTo(String string, WebDriver wd) {
        log.trace("afterNavigateTo");
    }

    @Override
    public void beforeNavigateBack(WebDriver wd) {
        log.trace("beforeNavigateBack");
    }

    @Override
    public void afterNavigateBack(WebDriver wd) {
        log.trace("afterNavigateBack");
    }

    @Override
    public void beforeNavigateForward(WebDriver wd) {
        log.trace("beforeNavigateForward");
    }

    @Override
    public void afterNavigateForward(WebDriver wd) {
        log.trace("afterNavigateForward");
    }

    @Override
    public void beforeNavigateRefresh(WebDriver wd) {
        log.trace("beforeNavigateRefresh");
    }

    @Override
    public void afterNavigateRefresh(WebDriver wd) {
        log.trace("afterNavigateRefresh");
    }

    @Override
    public void beforeFindBy(By by, WebElement we, WebDriver wd) {
        log.trace("beforeFindBy");
    }

    @Override
    public void afterFindBy(By by, WebElement we, WebDriver wd) {
        log.trace("afterFindBy");
    }

    @Override
    public void beforeClickOn(WebElement we, WebDriver wd) {
        log.trace("beforeClickOn");        
        flash(we);
    }

    @Override
    public void afterClickOn(WebElement we, WebDriver wd) {
        log.trace("afterClickOn");
    }

    @Override
    public void beforeChangeValueOf(WebElement we, WebDriver wd, CharSequence[] css) {
        log.trace("beforeChangeValueOf");
        flash(we);
    }

    @Override
    public void afterChangeValueOf(WebElement we, WebDriver wd, CharSequence[] css) {
        log.trace("afterChangeValueOf");
    }

    @Override
    public void beforeScript(String string, WebDriver wd) {
        log.trace("beforeScript");
    }

    @Override
    public void afterScript(String string, WebDriver wd) {
        log.trace("afterScript");
    }

    @Override
    public void beforeSwitchToWindow(String string, WebDriver wd) {
        log.trace("beforeSwitchToWindow");
    }

    @Override
    public void afterSwitchToWindow(String string, WebDriver wd) {
        log.trace("afterSwitchToWindow");
    }

    @Override
    public void onException(Throwable thrwbl, WebDriver wd) {
        log.trace("onException");
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> ot) {
        log.trace("beforeGetScreenshotAs");
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> ot, X x) {
        log.trace("afterGetScreenshotAs");
    }

    @Override
    public void beforeGetText(WebElement we, WebDriver wd) {
        log.trace("beforeGetText");
    }

    @Override
    public void afterGetText(WebElement we, WebDriver wd, String string) {
        log.trace("afterGetText");
    }
    
    private void flash(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getDriver());
        String bgcolor  = element.getCssValue("backgroundColor");
        
        for (int i = 0; i < count; i++) {
            changeColor(color, element, js);
            changeColor(bgcolor, element, js);
        }
    }

    private void changeColor(String color, WebElement element,  JavascriptExecutor js) {
        js.executeScript("arguments[0].style.backgroundColor = '"+color+"'",  element);
        try {
            Thread.sleep(interval);
        }  catch (InterruptedException ignore) {}
    }
}
