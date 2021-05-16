package com.dws.pageElements.steps;

import com.dws.pageObject.pages.*;
import static com.dws.managers.PageManager.getPageManager;
import com.dws.pages.base.PageStepsBase;
import org.junit.jupiter.api.Assertions;
import static com.dws.helper.CartHelper.getCartHelper;

public class MenuSteps extends PageStepsBase {
    
    public MenuSteps mouseMoveToTopMenu(String name) {
        getMenuElements().getListTopMenu().mouseMoveToElementFromList(name);
        return this;
    }
    
    public MenuSteps clickTopMenu(String name) {        
        getMenuElements().getListTopMenu().clickToElementFromList(name);
        return this;
    }
    
    public MenuSteps clickTopSubMenu(String name) {
        getMenuElements().getListTopSubMenu().clickToElementFromList(name);
        return this;
    }
    
    public MenuSteps clickLeftMenu(String name) {
        getMenuElements().getListLeftMenuCategories().clickToElementFromList(name);
        return this;
    }
    
    public MenuSteps assertLabelShoppingCartQuantity() {
        String label = getMenuElements().getLabelShoppingCartQuantity()
                .getText().replaceAll("[^\\d.]", "");
        int fromLabel = Integer.parseInt(label);
        Assertions.assertEquals(getCartHelper().getTotalQuantity(),
                fromLabel, "Checking product quantity in the top menu");
        return this;
    }
    
    public CartPage goToCart() {
        getMenuElements().getLabelShoppingCart().click();
        return getPageManager().getCartPage();
    }
}