package com.tuanha.notification.email;

import com.tuanha.notification.kafka.order.ProductPurchaseResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tuanha.notification.email.EmailTemplates.ORDER_CONFIRMATION;
import static com.tuanha.notification.email.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {

    JavaMailSender mailSender;
    SpringTemplateEngine templateEngine;

    private void sendEmail(
            String destinationEmail,
            String subject,
            String templateName,
            Map<String, Object> variables
    ) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());

            messageHelper.setFrom("contact@tuanha.com");
            messageHelper.setTo(destinationEmail);
            messageHelper.setSubject(subject);

            Context context = new Context();
            context.setVariables(variables);
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            mailSender.send(mimeMessage);
            log.info("✅ Email sent to {} using template: {}", destinationEmail, templateName);
        } catch (MessagingException e) {
            log.error("❌ Failed to send email to {}: {}", destinationEmail, e.getMessage());
        }
    }

    @Async
    public void sendPaymentSuccessEmail(String destinationEmail, String customerName, double amount, Long orderId) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderId", orderId);

        sendEmail(destinationEmail, PAYMENT_CONFIRMATION.getSubject(), PAYMENT_CONFIRMATION.getTemplate(), variables);
    }

    @Async
    public void sendOrderConfirmationEmail(String destinationEmail, String customerName,
                                           double amount, ArrayList<ProductPurchaseResponse> products, Long orderId) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("products", products);
        variables.put("orderId", orderId);

        sendEmail(destinationEmail, ORDER_CONFIRMATION.getSubject(), ORDER_CONFIRMATION.getTemplate(), variables);
    }
}
