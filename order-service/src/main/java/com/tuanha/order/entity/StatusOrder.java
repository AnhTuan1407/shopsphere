package com.tuanha.order.entity;

public enum StatusOrder {
    PENDING("PENDING", "Order is pending confirmation"),
    CONFIRMED("CONFIRMED", "Order has been confirmed"),
    SHIPPED("SHIPPED", "Order is being shipped"),
    DELIVERED("DELIVERED", "Order has been successfully delivered"),
    CANCELED("CANCELED", "Order has been canceled");

    private final String name;
    private final String description;

    StatusOrder(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
