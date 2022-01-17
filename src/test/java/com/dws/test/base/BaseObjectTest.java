package com.dws.test.base;

import static com.dws.helper.CartHelper.getCartHelper;
import com.dws.managers.InitManager;
import com.dws.managers.PageManager;
import static com.dws.managers.PageManager.getPageManager;
import org.junit.jupiter.api.*;

public class BaseObjectTest extends BaseTest {
    protected PageManager apptest = getPageManager();
    
    @BeforeAll
    public static void beforeAll() {
        setupLoggerUID();
        InitManager.initFramework();
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }
    
    @BeforeEach
    public void beforeEach() {
        InitManager.openBrowser();
        clearLoggerUID();
    }
    
    @AfterEach
    public void afterEach() {
        InitManager.clearCookies();
        getCartHelper().clearCartList();
    }
}