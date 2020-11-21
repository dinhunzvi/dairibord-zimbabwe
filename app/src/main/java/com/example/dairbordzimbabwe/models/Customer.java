package com.example.dairbordzimbabwe.models;

public class Customer {

    private final int customer_id;
    private final String customer_name;
    private final String address;
    private final String contact_person;
    private final String contact_mobile;

    public Customer(int customer_id, String customer_name, String address, String contact_person,
                    String contact_mobile) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.contact_person = contact_person;
        this.contact_mobile = contact_mobile;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact_person() {
        return contact_person;
    }

    public String getContact_mobile() {
        return contact_mobile;
    }

}
