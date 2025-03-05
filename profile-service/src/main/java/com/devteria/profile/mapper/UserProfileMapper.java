package com.devteria.profile.mapper;

import com.devteria.profile.dto.request.UserProfileCreationRequest;
import com.devteria.profile.dto.request.UserProfileUpdationRequest;
import com.devteria.profile.dto.response.UserProfileResponse;
import com.devteria.profile.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(UserProfileCreationRequest request);

    void toUpdateUserProfile(@MappingTarget UserProfile userProfile, UserProfileUpdationRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
}
