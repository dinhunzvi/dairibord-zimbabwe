package com.example.dairbordzimbabwe.models;

public class Supplier {

    private final int supplier_id;
    private final String supplier_name;
    private final String address;
    private final String phone_number;

    public Supplier(int supplier_id, String supplier_name, String address, String phone_number) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

}
