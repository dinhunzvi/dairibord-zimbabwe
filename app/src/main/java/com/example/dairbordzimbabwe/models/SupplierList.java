package com.example.dairbordzimbabwe.models;

import java.util.List;

public class SupplierList {

    private final List<Supplier> suppliers;

    public SupplierList(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

}
