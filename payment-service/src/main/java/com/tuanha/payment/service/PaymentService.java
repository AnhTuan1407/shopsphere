package com.tuanha.payment.service;

import com.tuanha.payment.dto.request.PaymentNotificationRequest;
import com.tuanha.payment.dto.request.PaymentRequest;
import com.tuanha.payment.mapper.PaymentMapper;
import com.tuanha.payment.notification.NotificationProducer;
import com.tuanha.payment.repository.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentService {
    PaymentRepository repository;
    PaymentMapper mapper;
    NotificationProducer notificationProducer;

    public Long createPayment(PaymentRequest request) {
        var payment = this.repository.save(this.mapper.toPayment(request));

        this.notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.getAmount(),
                        request.getOrderId(),
                        request.getPaymentMethod(),
                        request.getProfile().getFirstName(),
                        request.getProfile().getLastName(),
                        request.getProfile().getEmail()
                )
        );
        return payment.getId();
    }
}
