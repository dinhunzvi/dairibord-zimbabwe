package com.example.dairbordzimbabwe.models;

public class Product {

    private final int product_id;
    private final String product_name;
    private final int quantity_in_stock;
    private final int milk_required;

    public Product(int product_id, String product_name, int quantity_in_stock, int milk_required) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.quantity_in_stock = quantity_in_stock;
        this.milk_required = milk_required;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public int getMilk_required() {
        return milk_required;
    }

}
