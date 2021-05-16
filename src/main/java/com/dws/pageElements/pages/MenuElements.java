package com.dws.pageElements.pages;

import com.dws.pageElements.element.Element;
import com.dws.pageElements.element.ListElements;
import com.dws.pages.base.PageElementBase;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class MenuElements extends PageElementBase {
    @Getter
    protected ListElements listTopMenu =
            new ListElements(By.xpath("//ul[@class='top-menu']//li"));
    
    @Getter
    @FindBy(xpath = "//ul[@class='top-menu']//a")
    protected ListElements listTopSubMenu =
            new ListElements(By.xpath("//ul[@class='top-menu']//a"));
    
    @Getter
    protected ListElements listLeftMenuCategories =
            new ListElements(By.xpath("//div[contains(@class,'block-category')]//a"));
    
    @Getter
    protected Element labelShoppingCart =
            new Element(By.xpath("//div[@class='header-links']//a[@href='/cart']"));
    
    @Getter
    protected Element labelShoppingCartQuantity =
            new Element(By.xpath("//span[@class='cart-qty']"));
}