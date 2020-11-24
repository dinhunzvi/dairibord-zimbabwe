package com.example.dairbordzimbabwe.models;

public class Delivery {

    private final String created_user;
    private final String delivery_date;
    private final String quantity_delivered;
    private final String supplier_name;

    public Delivery(String created_user, String delivery_date, String quantity_delivered,
                    String supplier_name) {
        this.created_user = created_user;
        this.delivery_date = delivery_date;
        this.quantity_delivered = quantity_delivered;
        this.supplier_name = supplier_name;
    }

    public String getCreated_user() {
        return created_user;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public String getQuantity_delivered() {
        return quantity_delivered;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

}
