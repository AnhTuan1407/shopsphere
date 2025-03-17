package com.tuanha.notification.kafka;

import com.tuanha.notification.email.EmailService;
import com.tuanha.notification.kafka.order.OrderConfirmation;
import com.tuanha.notification.kafka.order.ProductPurchaseResponse;
import com.tuanha.notification.kafka.payment.PaymentConfirmation;
import com.tuanha.notification.entity.Notification;
import com.tuanha.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.tuanha.notification.enums.NotificationType.ORDER_CONFIRMATION;
import static com.tuanha.notification.enums.NotificationType.PAYMENT_CONFIRMATION;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic", groupId = "paymentGroup")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) {
        if (paymentConfirmation == null) {
            log.warn("⚠️ Received null PaymentConfirmation message!");
            return;
        }

        log.info("✅ Consumed payment confirmation: Amount=${}", paymentConfirmation.getAmount());

        try {
            repository.save(
                    Notification.builder()
                            .type(PAYMENT_CONFIRMATION)
                            .notificationDate(LocalDateTime.now())
                            .paymentConfirmation(paymentConfirmation)
                            .build()
            );

            String customerName = paymentConfirmation.getFirstName() + " " + paymentConfirmation.getLastName();
            emailService.sendPaymentSuccessEmail(
                    paymentConfirmation.getEmail(),
                    customerName,
                    paymentConfirmation.getAmount(),
                    paymentConfirmation.getOrderId()
            );
        } catch (Exception e) {
            log.error("❌ Error processing payment confirmation for: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "order-topic", groupId = "orderGroup")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) {
        if (orderConfirmation == null || orderConfirmation.getProfile() == null) {
            log.warn("⚠️ Received null OrderConfirmation message!");
            return;
        }

        log.info("✅ Consumed order confirmation: Total=${}", orderConfirmation.getTotalPrice());

        try {
            repository.save(
                    Notification.builder()
                            .type(ORDER_CONFIRMATION)
                            .notificationDate(LocalDateTime.now())
                            .orderConfirmation(orderConfirmation)
                            .build()
            );

            String customerName = orderConfirmation.getProfile().getFirstName() + " " + orderConfirmation.getProfile().getLastName();

            emailService.sendOrderConfirmationEmail(
                    orderConfirmation.getProfile().getEmail(),
                    customerName,
                    orderConfirmation.getTotalPrice(),
                    orderConfirmation.getProducts(),
                    orderConfirmation.getOrderId()
            );
        } catch (Exception e) {
            log.error("❌ Error processing order confirmation for: {}", e.getMessage());
        }
    }
}
