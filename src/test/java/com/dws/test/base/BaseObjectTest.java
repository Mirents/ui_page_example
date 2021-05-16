package com.dws.test.base;

import static com.dws.helper.CartHelper.getCartHelper;
import com.dws.managers.InitManager;
import com.dws.managers.PageManager;
import static com.dws.managers.PageManager.getPageManager;
import org.junit.jupiter.api.*;

public class BaseObjectTest {
    protected PageManager apptest = getPageManager();
    
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