package com.dws.pageObject.pages;

import static com.dws.managers.PageManager.getPageManager;
import com.dws.pages.base.PageObjectBase;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static com.dws.helper.CartHelper.getCartHelper;

public class MenuPage extends PageObjectBase {
    
    @FindBy(xpath = "//ul[@class='top-menu']//li")
    private List<WebElement> listTopMenu;
    
    @FindBy(xpath = "//ul[@class='top-menu']//a")
    private List<WebElement> listTopSubMenu;
    
    @FindBy(xpath = "//div[contains(@class,'block-category')]//a")
    private List<WebElement> listLeftMenuCategories;
    
    @FindBy(xpath = "//div[@class='header-links']//a[@href='/cart']")
    private WebElement labelShoppingCart;
    
    @FindBy(xpath = "//span[@class='cart-qty']")
    private WebElement labelShoppingCartQuantity;

    public MenuPage mouseMoveToTopMenu(String name) {
        mouseMoveToElementFromList(listTopMenu, name);
        return this;
    }
    
    public MenuPage clickTopMenu(String name) {        
        clickToElementFromList(listTopMenu, name);
        return this;
    }
    
    public MenuPage clickTopSubMenu(String name) {
        clickToElementFromList(listTopSubMenu, name);
        return this;
    }
    
    public MenuPage clickLeftMenu(String name) {        
        clickToElementFromList(listLeftMenuCategories, name);
        return this;
    }
    
    public MenuPage assertLabelShoppingCartQuantity() {
        String label = labelShoppingCartQuantity.getText().replaceAll("[^\\d.]", "");
        int fromLabel = Integer.parseInt(label);
        Assertions.assertEquals(getCartHelper().getTotalQuantity(),
                fromLabel, "Checking product quantity in the top menu");
        return this;
    }
    
    public CartPage goToCart() {
        labelShoppingCart.click();
        return getPageManager().getCartPage();
    }
}