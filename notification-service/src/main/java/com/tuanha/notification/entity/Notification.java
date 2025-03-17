package com.tuanha.notification.entity;

import com.tuanha.notification.enums.NotificationType;
import com.tuanha.notification.kafka.order.OrderConfirmation;
import com.tuanha.notification.kafka.payment.PaymentConfirmation;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {
    @Id
    String id;
    NotificationType type;
    LocalDateTime notificationDate;
    OrderConfirmation orderConfirmation;
    PaymentConfirmation paymentConfirmation;
}
