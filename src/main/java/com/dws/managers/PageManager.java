package com.dws.managers;

import com.dws.pageObject.pages.CartPage;
import com.dws.pageObject.pages.ProductListPage;
import com.dws.pageObject.pages.ProductPage;
import com.dws.pages.base.PageObjectBase;
import com.dws.pageObject.pages.MenuPage;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageManager.class);
    private static PageManager instance;
    private static Map<String, PageObjectBase> mapPages = new HashMap<>();

    public static PageManager getPageManager() {
        if(instance == null) {
            instance = new PageManager();
        }
        return instance;
    }

    public MenuPage getMenuToPage() {
        return getPage(MenuPage.class);
    }
    
    public ProductListPage getProductListPage() {
        return getPage(ProductListPage.class);
    }
    
    public ProductPage getProductPage() {
        return getPage(ProductPage.class);
    }
    
    public CartPage getCartPage() {
        return getPage(CartPage.class);
    }

    private <T extends PageObjectBase> T getPage(Class<? extends PageObjectBase> classPage) {
        if(mapPages.isEmpty() || mapPages.get(classPage.getClass().getName()) == null) {
            if(classPage == MenuPage.class)
                mapPages.put(classPage.toString(), new MenuPage());
            else if(classPage == ProductListPage.class)
                mapPages.put(classPage.toString(), new ProductListPage());
            else if(classPage == ProductPage.class)
                mapPages.put(classPage.toString(), new ProductPage());
            else if(classPage == CartPage.class)
                mapPages.put(classPage.toString(), new CartPage());
        }
        return (T) mapPages.get(classPage.toString());
    }

    void clearMapPage() {
        mapPages.clear();
    }
}
