package com.dws.tests;

import com.dws.test.base.BaseElementsTest;
import com.dws.utils.ExpectedResultConstant;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Testing the product page")
public class ProductElementsTest extends BaseElementsTest {

    @Test
    public void CheckCartPriceTest() {
        // Go to the "Books" menu
        menuSteps
                .clickLeftMenu("Books");
        // Open product page "Computing and Internet"
        productListSteps
                .clickToProduct("Computing and Internet");
        // Add a product to the cart
        productSteps
                .inputQuanityClear()
                .inputQuanityEnterNumber(3)
                .clickButtonAddToCart()
                .assertBarNotificationColor(ExpectedResultConstant.COLOR_SUCCESS_MESSAGE)
                .assertBarNotificationText(ExpectedResultConstant.TEXT_SUCCESS_MESSAGE);
        // Go to the "Electronics - Cell phones" menu
        menuSteps
                .clickLeftMenu("Electronics")
                .clickLeftMenu("Cell phones");
        // Open product page "Cell phones"
        productListSteps
                .clickToProduct("Phone Cover");
        // Add a product to the cart and
        productSteps
                .inputQuanityClear()
                .inputQuanityEnterNumber(4)
                .clickButtonAddToCart()
                .assertBarNotificationColor(ExpectedResultConstant.COLOR_SUCCESS_MESSAGE)
                .assertBarNotificationText(ExpectedResultConstant.TEXT_SUCCESS_MESSAGE);
        // Checking product quantity in the top menu
        menuSteps
                .assertLabelShoppingCartQuantity()
                .goToCart()
                .assertTotalPrice();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"5", "94", "1025"})
    public void SuccessMessageTest(int input) {
        // Go to the "Electronics - Camera, photo" menu
        menuSteps
                .mouseMoveToTopMenu("Computers")
                .clickTopSubMenu("Notebooks");
        // Open product page "Computing and Internet"
        productListSteps
                .clickToProduct("14.1-inch Laptop");
        // Add a product to the cart and
        // check the correctness of the message
        productSteps
                .inputQuanityClear()
                .inputQuanityEnterNumber(input)
                .clickButtonAddToCart()
                .assertBarNotificationColor(ExpectedResultConstant.COLOR_SUCCESS_MESSAGE)
                .assertBarNotificationText(ExpectedResultConstant.TEXT_SUCCESS_MESSAGE);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"-1", "f", "6.5", "4,3", "!", "%:;?", "0"})
    public void ErrorMessageTest(String input) {
        // Go to the "Books" menu
        menuSteps
                .clickLeftMenu("Books");
        // Open product page "Computing and Internet"
        productListSteps
                .clickToProduct("Computing and Internet");
        // Add a product to the cart
        productSteps
                .inputQuanityClear()
                .inputQuanityEnterText(input)
                .clickButtonAddToCart()
                .assertBarNotificationColor(ExpectedResultConstant.COLOR_ERROR_MESSAGE)
                .assertBarNotificationText(ExpectedResultConstant.TEXT_ERROR_MESSAGE);
    }
    
    @Test
    public void CheckBrokenImageHomePageTest() {
        // Find Broken Image on Home Page
        menuSteps
                .findBrokenImage();
    }
    
    @Test
    public void CheckBrokenImageProductListPageTest() {
        // Find broken image on product listing page
        menuSteps
                .clickLeftMenu("Jewelry")
                .findBrokenImage();
        
        // Find broken image on product page
        productListSteps
                .clickToProduct("Create Your Own Jewelry");
        productSteps
                .findBrokenImage();
    }
    
    @Test
    public void CheckBrokenImageProductPageTest() {
        // Find broken image on product page
        menuSteps
                .clickLeftMenu("Jewelry");
        productListSteps
                .clickToProduct("Create Your Own Jewelry");
        productSteps
                .findBrokenImage();
    }
}