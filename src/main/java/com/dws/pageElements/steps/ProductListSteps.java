package com.dws.pageElements.steps;

import com.dws.pages.base.PageStepsBase;
import org.openqa.selenium.By;

public class ProductListSteps extends PageStepsBase {
    
    public ProductListSteps clickToProduct(String name) {
        String xpath = "//h2//a[text()='" + name + "']";
        getProductListElements().getListProduct()
                .getElemFromListToBy(By.xpath(xpath)).click();
        return this;
    }
}