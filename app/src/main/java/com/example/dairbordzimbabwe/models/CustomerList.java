package com.example.dairbordzimbabwe.models;

import java.util.List;

public class CustomerList {

    private final List<Customer> customers;

    public CustomerList(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

}
