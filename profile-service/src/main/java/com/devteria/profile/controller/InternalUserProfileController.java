package com.devteria.profile.controller;

import com.devteria.profile.dto.request.UserProfileCreationRequest;
import com.devteria.profile.dto.request.UserProfileUpdationRequest;
import com.devteria.profile.dto.response.ApiResponse;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/internal/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InternalUserProfileController {
    UserProfileService userProfileService;

    @PostMapping
    ApiResponse<UserProfileResponse> createProfile(@RequestBody UserProfileCreationRequest request) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.createProfile(request))
                .build();
    }
}
