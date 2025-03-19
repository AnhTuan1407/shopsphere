package com.tuanha.order.repository.httpClient;

import com.tuanha.order.dto.response.ApiResponse;
import com.tuanha.order.dto.response.ProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "profile-service",
        url = "${app.services.profile}"
)
public interface ProfileClient {
    @GetMapping("/{profileId}")
    ApiResponse<ProfileResponse> getUserProfile(@PathVariable("profileId") String profileId);
}
