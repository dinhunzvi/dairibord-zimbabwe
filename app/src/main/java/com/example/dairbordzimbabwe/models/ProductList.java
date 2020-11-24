package com.example.dairbordzimbabwe.models;

import java.util.List;

public class ProductList {

    private final List<Product> products;

    public ProductList(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

}
