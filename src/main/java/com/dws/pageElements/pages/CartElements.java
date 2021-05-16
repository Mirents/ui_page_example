package com.dws.pageElements.pages;

import com.dws.pageElements.element.Element;
import com.dws.pages.base.PageElementBase;
import lombok.Getter;
import org.openqa.selenium.By;

public class CartElements extends PageElementBase {
    @Getter
    protected Element labelTotal =
            new Element(By.xpath("//td[@class='cart-total-right']//strong"));
}