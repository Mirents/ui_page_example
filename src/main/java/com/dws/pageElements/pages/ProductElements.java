package com.dws.pageElements.pages;

import com.dws.entities.Product;
import com.dws.pageElements.element.Element;
import com.dws.pages.base.PageElementBase;
import lombok.Getter;
import org.openqa.selenium.By;
import static com.dws.helper.CartHelper.getCartHelper;

public class ProductElements extends PageElementBase {
    
    @Getter
    protected Element buttonAddToCart = new Element(By.xpath(
                "//div[contains(@class, 'add-to')]//input[contains(@class, 'add-to-c')]"));;
    
    @Getter
    protected Element inputQuanity =
            new Element(By.xpath("//input[contains(@class, 'qty-input')]"));
    
    @Getter
    protected Element barNotification =
            new Element(By.xpath("//div[@id='bar-notification']"));
    
    @Getter
    protected Element barNotificationMessage =
            new Element(By.xpath("//div[@id='bar-notification']//p[@class=\"content\"]"));
    
    @Getter
    protected Element labelProductName =
            new Element(By.xpath("//h1"));
    
    @Getter
    protected Element labelProductPrice =
            new Element(By.xpath("//span[@itemprop='price']"));
    
    public ProductElements() {
        if(!getCartHelper().isExistsProductByName(getLabelProductName().getText())) {
            String labelPrice = getLabelProductPrice()
                    .getText().replaceAll("[^\\d.]", "");
            float price = Float.parseFloat(labelPrice);
            String labelQuantity = getInputQuanity()
                    .getAttribute("value").replaceAll("[^\\d.]", "");
            int quantity = Integer.parseInt(labelQuantity);
            getCartHelper().addProduct(new Product(
                    getLabelProductName().getText(),
                    price, quantity));
        }
    }
}