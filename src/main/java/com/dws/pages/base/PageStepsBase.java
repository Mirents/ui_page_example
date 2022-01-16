package com.dws.pages.base;

import com.dws.helper.CartHelper;
import static com.dws.managers.DriverManager.getDriver;
import static com.dws.managers.PropertiesManager.getThisProperties;
import com.dws.pageElements.pages.CartElements;
import com.dws.pageElements.pages.MenuElements;
import com.dws.pageElements.pages.ProductElements;
import com.dws.pageElements.pages.ProductListElements;
import static com.dws.utils.ProperitesConstant.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageStepsBase {
    protected static final Logger LOGGER = LoggerFactory.getLogger(PageStepsBase.class);
    protected Actions action = new Actions(getDriver());
    protected CartHelper cartHelper;
    private CartElements cartElements;
    private MenuElements menuElements;
    private ProductListElements productListElements;
    private ProductElements productElements;
    
    public CartElements getCartElements() {
        if(cartElements == null)
            cartElements = new CartElements();
        
        return cartElements;
    }
    
    public MenuElements getMenuElements() {
        if(menuElements == null)
            menuElements = new MenuElements();
        
        return menuElements;
    }
    
    public ProductListElements getProductListElements() {
        if(productListElements == null)
            productListElements = new ProductListElements();
        
        return productListElements;
    }
    
    public ProductElements getProductElements() {
        return new ProductElements();
    }
    
    public <T extends PageStepsBase> T findBrokenLinks() {
        List<WebElement> links = getDriver().findElements(By.tagName("a"));
 
        if(links.size() > 0) {
            for(int i=0;i<links.size();i++)
            {
                WebElement element = links.get(i);
                String url = element.getAttribute("href");
                isBrokenLink(url);
            }
        }
        return (T) this;
    }

    public <T extends PageStepsBase> T findBrokenImage() {
        List<WebElement> images = getDriver().findElements(By.tagName("img"));

        if(images.size() > 0) {
            for(int index=0;index<images.size();index++)
            {
                WebElement image = images.get(index);
                String imageURL = image.getAttribute("src");
                
                isBrokenLink(imageURL);

                try {
                    boolean imageDisplayed =
                            execScript("return (typeof arguments[0]"
                                    + ".naturalWidth !=\"undefined\" "
                                    + "&& arguments[0].naturalWidth > 0);"
                                    , image);
                    if(!imageDisplayed) {
                        Assertions.fail("On the screen - a broken image with a link - "
                        + imageURL);
                    }
                } catch (Exception ex) {
                    Assertions.fail("Image verification error by reference - "
                    + imageURL);
                }
            }
        }
        return (T) this;
    }

    private void isBrokenLink(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
 
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()>=400) {
                Assertions.fail("Broken link - " + linkUrl + " - "
                        + httpURLConnect.getResponseMessage());
            }
        } catch (IOException ignore) {}
    }
    
    private boolean execScript(String script, WebElement element) {
        return (Boolean) ((JavascriptExecutor) getDriver())
                        .executeScript(script, element);
    }
    
    public boolean isElementExist(By by) {
        boolean flag = false;
        try {
            getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            getDriver().findElement(by);
            flag = true;
        } catch (NoSuchElementException ignore) {}
        finally {
            getDriver().manage().timeouts().implicitlyWait(
                    Integer.parseInt(getThisProperties()
                            .getProperty(DRIVER_IMPLICITY_WAIT)), TimeUnit.SECONDS);
        }
        return flag;
    }
}
