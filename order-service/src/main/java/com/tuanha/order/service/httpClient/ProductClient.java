package com.tuanha.order.service.httpClient;

import com.tuanha.order.dto.request.ProductPurchaseRequest;
import com.tuanha.order.dto.response.ApiResponse;
import com.tuanha.order.dto.response.ProductPurchaseResponse;
import com.tuanha.order.dto.response.ProductVariantResponse;
import com.tuanha.order.exception.AppException;
import com.tuanha.order.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${app.services.product}")
    private String productUrl;

    private WebClient webClient() {
        return webClientBuilder.baseUrl(productUrl).build();
    }

    public Mono<ApiResponse<List<ProductPurchaseResponse>>> purchaseProducts(List<ProductPurchaseRequest> requestBody) {
        return webClient().post()
                .uri("/product-variants/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> {
                    log.error("Error purchasing products: {}", clientResponse.statusCode());
                    return Mono.error(new AppException(ErrorCode.PURCHASE_ERROR));
                })
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<ProductPurchaseResponse>>>() {});
    }


    public Mono<ProductVariantResponse> getProductVariantById(Long id) {
        return webClient().get()
                .uri("product-variants/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> {
                    log.error("Error fetching product variant ID {}: {}", id, clientResponse.statusCode());
                    return Mono.error(new AppException(ErrorCode.PURCHASE_ERROR));
                })
                .bodyToMono(ProductVariantResponse.class);
    }
}
