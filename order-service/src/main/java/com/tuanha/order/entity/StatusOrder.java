package com.tuanha.order.entity;

import java.util.Arrays;
import java.util.Optional;

public enum StatusOrder {
    PENDING("PENDING", "Order is pending confirmation"),
    CONFIRMED("CONFIRMED", "Order has been confirmed"),
    IN_TRANSIT("IN_TRANSIT", "Order is in transit"),
    OUT_FOR_DELIVERY("OUT_FOR_DELIVERY", "Order is out for delivery"),
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

    public static Optional<StatusOrder> fromString(String input) {
        return Arrays.stream(StatusOrder.values())
                .filter(status -> status.name.equalsIgnoreCase(input))
                .findFirst();
    }
}
