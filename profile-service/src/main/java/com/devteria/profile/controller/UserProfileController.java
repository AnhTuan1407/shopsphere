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
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping
    ApiResponse<List<UserProfileResponse>> getAllProfile() {
        return ApiResponse.<List<UserProfileResponse>>builder()
                .result(userProfileService.getAllProfile())
                .build();
    }

    @GetMapping("/{profileId}")
    ApiResponse<UserProfileResponse> getUserProfile(@PathVariable("profileId") String profileId) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.getUserProfile(profileId))
                .build();
    }

    @PutMapping("/{profileId}")
    ApiResponse<UserProfileResponse> updateUserProfile(@PathVariable("profileId") String profileId, @RequestBody UserProfileUpdationRequest request) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.updateProfile(request, profileId))
                .build();
    }

    @DeleteMapping("/{profileId}")
    ApiResponse<String> deleteUserProfile(@PathVariable("profileId") String profileId) {
        userProfileService.delete(profileId);
        return ApiResponse.<String>builder()
                .result("User profile has been deleted!")
                .build();
    }
}
