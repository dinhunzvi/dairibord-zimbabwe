package com.example.dairbordzimbabwe.models;

import java.util.List;

public class SupplierList {

    private final List<Supplier> supplier;

    public SupplierList(List<Supplier> supplier) {
        this.supplier = supplier;
    }

    public List<Supplier> getSupplier() {
        return supplier;
    }

}
