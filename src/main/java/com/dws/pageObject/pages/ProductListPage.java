package com.dws.pageObject.pages;

import com.dws.pages.base.PageObjectBase;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductListPage extends PageObjectBase {

    @FindBy(xpath = "//div[@class='product-grid']//div[@class='product-item']")
    private List<WebElement> listProduct;
    
    public ProductListPage clickToProduct(String name) {
        String xpath = "//h2//a[text()='" + name + "']";
        getElemFromListToBy(listProduct, By.xpath(xpath)).click();
        return this;
    }
}
