package com.example.dairbordzimbabwe.models;

import java.util.List;

public class DeliveryList {

    private final List<Delivery> deliveries;

    public DeliveryList(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

}
