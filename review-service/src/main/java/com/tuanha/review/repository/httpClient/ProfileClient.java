package com.tuanha.review.repository.httpClient;

import com.tuanha.review.dto.response.ApiResponse;
import com.tuanha.review.dto.response.ProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service", url = "${app.services.profile}")
public interface ProfileClient {
    @GetMapping(value = "/{profileId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<ProfileResponse> getProfileById(@PathVariable("profileId") String profileId);
}
