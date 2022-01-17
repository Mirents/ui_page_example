package com.dws.pageElements.steps;

import static com.dws.managers.WaitManager.getWaitManager;
import com.dws.pages.base.PageStepsBase;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static com.dws.helper.CartHelper.getCartHelper;
import com.dws.entities.Product;

public class ProductSteps extends PageStepsBase {

    public ProductSteps assertBarNotificationColor(String color) {
        boolean isContains = false;
        getWaitManager().until(ExpectedConditions
                .visibilityOf(getProductElements().getBarNotification()));
        if(getProductElements().getBarNotification()
                .getCssValue("background").contains(color))
            isContains = true;
        Assertions.assertTrue(isContains, "Checking the correctness of the color");
        return this;
    }
    
    public ProductSteps assertBarNotificationText(String text) {
        getWaitManager().until(ExpectedConditions
                .textToBePresentInElement(getProductElements()
                .getBarNotificationMessage(), text));
        Assertions.assertEquals(getProductElements()
                .getBarNotificationMessage().getText(), text,
                "Checking the message text");
        return this;
    }
    
    public ProductSteps clickButtonAddToCart() {
        getProductElements().getButtonAddToCart().click();
        return this;
    }
    
    public ProductSteps inputQuanityEnterText(String text) {
        getProductElements().getInputQuanity().sendKeys(text);
        return this;
    }
    
    public ProductSteps inputQuanityEnterNumber(int number) {
        getProductElements().getInputQuanity().sendKeys(Integer.toString(number));
        Product ph = getCartHelper().getProductByName(getProductElements()
                .getLabelProductName().getText());
        ph.setQuantity(number);
        return this;
    }
    
    public ProductSteps inputQuanityClear() {
        getProductElements().getInputQuanity().clear();
        return this;
    }
}
