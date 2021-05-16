package com.dws.pageElements.steps;

import com.dws.pages.base.PageStepsBase;
import org.junit.jupiter.api.Assertions;
import static com.dws.helper.CartHelper.getCartHelper;

public class CartSteps extends PageStepsBase {
    public CartSteps assertTotalPrice() {
        float priceLabelTotal = Float.parseFloat(getCartElements()
                .getLabelTotal().getText());
        Assertions.assertEquals(getCartHelper().getTotalPrice(), priceLabelTotal,
                "Checking the total value of the goods");
        return this;
    }
}