package com.dws.entities;

import lombok.Getter;
import lombok.Setter;

public class Product {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private float price;
    @Getter @Setter
    private int quantity;
    
    public Product(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}