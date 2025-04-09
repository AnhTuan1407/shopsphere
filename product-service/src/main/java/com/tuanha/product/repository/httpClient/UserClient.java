package com.tuanha.product.repository.httpClient;

import com.tuanha.product.configuration.AuthenticationRequestInterceptor;
import com.tuanha.product.dto.request.SupplierCreationRequest;
import com.tuanha.product.dto.request.UserCreationRequest;
import com.tuanha.product.dto.response.ApiResponse;
import com.tuanha.product.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "identity-service", url = "${app.services.identity}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface UserClient {
    @PostMapping(value = "users/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request);
}
