package com.tuanha.order.service;

import com.tuanha.order.dto.request.*;
import com.tuanha.order.dto.response.*;
import com.tuanha.order.entity.Order;
import com.tuanha.order.entity.OrderInfo;
import com.tuanha.order.entity.OrderItem;
import com.tuanha.order.entity.StatusOrder;
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
import com.tuanha.order.repository.httpClient.IProductClientRepository;
import com.tuanha.order.repository.httpClient.PaymentClient;
import com.tuanha.order.repository.httpClient.ProfileClient;
import com.tuanha.order.service.httpClient.ProductClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
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
    CartService cartService;
    IProductClientRepository iProductClientRepository;

    @PreAuthorize("hasRole('USER')")
    public OrderInfoResponse addNewOrderInfo(OrderInfoCreationRequest request) {
        OrderInfo orderInfo = orderInfoMapper.toOrderInfo(request);
        if (orderInfo.isDefaultAddress()) {
            var orderInfoList = orderInfoRepository.findAll().stream().filter(OrderInfo::isDefaultAddress);
            orderInfoList.forEach(item -> item.setDefaultAddress(false));
        }
        return orderInfoMapper.toOrderInfoResponse(orderInfoRepository.save(orderInfo));
    }

    public OrderInfoResponse updateOrderInfo(OrderInfoUpdationRequest request, Long id) {
        if (request.isDefaultAddress()) {
            var orderInfoDefault = orderInfoRepository.findAll().stream().filter(OrderInfo::isDefaultAddress);
            orderInfoDefault.forEach(orderInfo -> orderInfo.setDefaultAddress(false));
        }
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

        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for (ProductPurchaseRequest rq : request.getProducts()) {
            var productResponse = iProductClientRepository.getProductByVariantId(rq.getProductVariantId());
            OrderItemCreationRequest orderItemRequest = new OrderItemCreationRequest(
                    rq.getProductVariantId(),
                    productResponse.getResult().getSupplier().getId(),
                    rq.getQuantity(),
                    rq.getPricePerUnit()
            );
            OrderItem orderItem = orderItemMapper.toOrderItem(orderItemRequest);
            orderItem.setOrder(order);
            orderItemResponses.add(orderItemMapper.toOrderItemResponse(orderItem));
            orderItemRepository.save(orderItem);

            cartService.deleteCartByProductId(rq.getProductVariantId());
        }

        order.setTotalPrice(request.getTotalPrice());
        var orderResponse = orderMapper.toOrderResponse(orderRepository.save(order));
        orderResponse.setOrderItems(orderItemResponses);

        var paymentRequest = new PaymentRequest(
                order.getTotalPrice(),
                request.getPaymentMethod(),
                order.getId(),
                profile
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        order.getTotalPrice(),
                        request.getPaymentMethod(),
                        profile,
                        purchasedProducts,
                        order.getId()
                )
        );

        return orderResponse;
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toOrderResponse).toList();
    }

    public OrderResponse updateStatusOrder(Long id, UpdateStatusRequest request) {
        var order = orderRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        StatusOrder newStatus = StatusOrder.fromString(request.getStatus())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_STATUS_ORDER));
        order.setStatusOrder(newStatus);
        order = orderRepository.save(order);
        return orderMapper.toOrderResponse(order);
    }

    public List<OrderResponse> getAllOrdersByProfileId(String profileId) {
        return orderRepository.findAllByProfileId(profileId).stream().map(orderMapper::toOrderResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponse getOrderById(Long id) {
        return orderMapper.toOrderResponse(orderRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND)));
    }

    public List<OrderInfoResponse> getAllOrderInfo(String profileId) {
        return orderRepository.findAllOrderInfoByProfileId(profileId).stream().map(orderMapper::toOrderInfoResponse).toList();
    }

    public OrderInfoResponse setDefaultAddress(Long id) {
        var orderInfoDefault = orderInfoRepository.findAll().stream().filter(OrderInfo::isDefaultAddress);
        orderInfoDefault.forEach(orderInfo -> orderInfo.setDefaultAddress(false));
        var orderInfo = orderInfoRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_INFO_NOT_FOUND));
        orderInfo.setDefaultAddress(true);
        orderInfoRepository.save(orderInfo);
        return orderInfoMapper.toOrderInfoResponse(orderInfo);
    }

    public OrderInfoResponse findOrderInfoById(Long id) {
        var orderInfo = orderInfoRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_INFO_NOT_FOUND));
        return orderInfoMapper.toOrderInfoResponse(orderInfo);
    }

    public boolean deleteOrderInfoById(Long id) {
        var orderInfo = orderInfoRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_INFO_NOT_FOUND));
        orderInfo.setProfileId(null);
        orderInfoRepository.save(orderInfo);
        orderInfoRepository.delete(orderInfo);
        return !orderInfoRepository.existsById(id);
    }

}
