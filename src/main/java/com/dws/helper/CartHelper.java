package com.dws.helper;

import com.dws.entities.Product;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CartHelper {
    private static CartHelper instance;
    private List<Product> cart;
    
    public static CartHelper getCartHelper() {
        if(instance == null) {
            instance = new CartHelper();
        }
        return instance;
    }

    private CartHelper() {
        this.cart = new ArrayList<>();
    }
    
    public void addProduct(Product product) {
        cart.add(product);
    }
    
    public boolean isExistsProductByName(String name) {
        for(Product ph: cart)
            if(ph.getName().equals(name))
                return true;
        return false;
    }
    
    public Product getProductByName(String name) {
        for(Product ph: cart)
            if(ph.getName().equals(name))
                return ph;
        return null;
    }
    
    public float getTotalPrice() {
        float total = 0.0f;
        for(Product ph: cart)
            total += (ph.getPrice() * ph.getQuantity());
        return total;
    }
    
    public int getTotalQuantity() {
        int total = 0;
        for(Product ph: cart)
            total += ph.getQuantity();
        return total;
    }
    
    public void clearCartList() {
        cart.clear();
    }
    
    @Override
    public String toString() {
        String data = "";
        for(Product ph: cart)
            data += ph.getName() + " - " + ph.getPrice() + " - "
                    + ph.getQuantity();
        log.debug(data);
        return data;
    }
}
