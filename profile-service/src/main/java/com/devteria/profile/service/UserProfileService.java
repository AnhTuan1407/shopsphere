package com.devteria.profile.service;

import com.devteria.profile.dto.request.UserProfileCreationRequest;
import com.devteria.profile.dto.request.UserProfileUpdationRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.exception.AppException;
import com.devteria.profile.exception.ErrorCode;
import com.devteria.profile.mapper.UserProfileMapper;
import com.devteria.profile.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserProfileService {
    UserProfileRepository userProfileRepository;

    UserProfileMapper userProfileMapper;

    public UserProfileResponse createProfile(UserProfileCreationRequest request) {
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileResponse> getAllProfile() {
        return userProfileRepository.findAll().stream().map(userProfileMapper::toUserProfileResponse).toList();
    }

    public UserProfileResponse updateProfile(UserProfileUpdationRequest request, String profileId) {
        UserProfile userProfile = userProfileRepository.findById(profileId).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        userProfileMapper.toUpdateUserProfile(userProfile, request);
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    public UserProfileResponse getUserProfile(String profileId) {
        return userProfileMapper.toUserProfileResponse(userProfileRepository.findById(profileId).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION)));
    }

    public void delete(String profileId) {
        userProfileRepository.deleteById(profileId);
    }
}
