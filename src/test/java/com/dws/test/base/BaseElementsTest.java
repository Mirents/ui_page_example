package com.dws.test.base;

import static com.dws.helper.CartHelper.getCartHelper;
import com.dws.managers.InitManager;
import org.junit.jupiter.api.*;
import com.dws.pageElements.steps.*;

public class BaseElementsTest {
    public CartSteps cartSteps = new CartSteps();
    public MenuSteps menuSteps = new MenuSteps();
    public ProductListSteps productListSteps = new ProductListSteps();
    public ProductSteps productSteps = new ProductSteps();
    
    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }
    
    @BeforeEach
    public void beforeEach() {
        InitManager.openBrowser();
    }
    
    @AfterEach
    public void afterEach() {
        InitManager.clearCookies();
        getCartHelper().clearCartList();
    }
}
