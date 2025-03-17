package com.tuanha.order.dto.response;

import com.tuanha.order.entity.OrderItem;
import com.tuanha.order.entity.StatusOrder;
import com.tuanha.order.enums.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;
    String profileId;
    LocalDate orderDate;
    StatusOrder statusOrder;
    Double totalPrice;
    List<OrderItemResponse> orderItems;
    OrderInfoResponse orderInfo;
    PaymentMethod paymentMethod;
}
