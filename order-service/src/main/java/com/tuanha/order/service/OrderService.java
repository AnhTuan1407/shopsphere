package com.tuanha.order.service;

import com.tuanha.order.dto.request.*;
import com.tuanha.order.dto.response.*;
import com.tuanha.order.entity.Order;
import com.tuanha.order.entity.OrderInfo;
import com.tuanha.order.entity.OrderItem;
import com.tuanha.order.exception.AppException;
import com.tuanha.order.exception.ErrorCode;
import com.tuanha.order.kafka.OrderConfirmation;
import com.tuanha.order.kafka.OrderProducer;
import com.tuanha.order.mapper.OrderInfoMapper;
import com.tuanha.order.mapper.OrderItemMapper;
import com.tuanha.order.mapper.OrderMapper;
import com.tuanha.order.repository.OrderInfoRepository;
import com.tuanha.order.repository.OrderItemRepository;
import com.tuanha.order.repository.OrderRepository;
import com.tuanha.order.repository.httpClient.PaymentClient;
import com.tuanha.order.repository.httpClient.ProfileClient;
import com.tuanha.order.service.httpClient.ProductClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderService {
    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;
    OrderInfoRepository orderInfoRepository;
    OrderInfoMapper orderInfoMapper;
    OrderMapper orderMapper;
    OrderItemMapper orderItemMapper;
    ProfileClient profileClient;
    ProductClient productClient;
    OrderProducer orderProducer;
    PaymentClient paymentClient;

    @PreAuthorize("hasRole('USER')")
    public OrderInfoResponse addNewOrderInfo(OrderInfoCreationRequest request) {
        OrderInfo orderInfo = orderInfoMapper.toOrderInfo(request);
        return orderInfoMapper.toOrderInfoResponse(orderInfoRepository.save(orderInfo));
    }

    public OrderInfoResponse updateOrderInfo(OrderInfoUpdationRequest request, Long id) {
        OrderInfo orderInfo = orderInfoRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_INFO_NOT_FOUND));
        orderInfoMapper.toUpdateOrderInfo(orderInfo, request);
        return orderInfoMapper.toOrderInfoResponse(orderInfoRepository.save(orderInfo));
    }

    @PreAuthorize("hasRole('USER')")
    @Transactional
    public OrderResponse createOrder(OrderCreationRequest request) {
        ApiResponse<ProfileResponse> apiResponse = profileClient.getUserProfile(request.getProfileId());
        var profile = apiResponse.getResult();
        log.info("Profile: {}", profile);

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        productClient.purchaseProducts(request.getProducts())
                .map(ApiResponse::getResult)
                .doOnNext(list -> log.info("✅ Products: {}", list))
                .subscribe(purchasedProducts::addAll);


        Order order = orderMapper.toOrder(request);
        OrderInfo orderInfo = orderInfoRepository.findById(request.getOrderInfoId())
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_INFO_NOT_FOUND));
        order.setOrderInfo(orderInfo);
        orderRepository.save(order);

        double totalPrice = 0.0;
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for (ProductPurchaseRequest rq : request.getProducts()) {
            OrderItemCreationRequest orderItemRequest = new OrderItemCreationRequest(
                    rq.getProductVariantId(),
                    rq.getQuantity(),
                    rq.getPricePerUnit()
            );
            OrderItem orderItem = orderItemMapper.toOrderItem(orderItemRequest);
            totalPrice += rq.getPricePerUnit() * rq.getQuantity();
            orderItem.setOrder(order);
            orderItemResponses.add(orderItemMapper.toOrderItemResponse(orderItem));
            orderItemRepository.save(orderItem);
        }

        order.setTotalPrice(totalPrice);
        var orderResponse = orderMapper.toOrderResponse(orderRepository.save(order));
        orderResponse.setOrderItems(orderItemResponses);


        var paymentRequest = new PaymentRequest(
                totalPrice,
                request.getPaymentMethod(),
                order.getId(),
                profile
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        totalPrice,
                        request.getPaymentMethod(),
                        profile,
                        purchasedProducts,
                        order.getId()
                )
        );


        return orderResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toOrderResponse).toList();
    }

    public List<OrderResponse> getAllOrdersByProfileId(String profileId) {
        log.info("Orders: {}", orderRepository.findAllByProfileId(profileId));
        return orderRepository.findAllByProfileId(profileId).stream().map(orderMapper::toOrderResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponse getOrderById(Long id) {
        return orderMapper.toOrderResponse(orderRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND)));
    }
}
