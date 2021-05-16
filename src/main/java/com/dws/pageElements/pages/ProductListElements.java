package com.dws.pageElements.pages;

import com.dws.pageElements.element.ListElements;
import com.dws.pages.base.PageElementBase;
import lombok.Getter;
import org.openqa.selenium.By;

public class ProductListElements extends PageElementBase {

    @Getter
    protected ListElements listProduct = new ListElements(By
            .xpath("//div[@class='product-grid']//div[@class='product-item']"));
}