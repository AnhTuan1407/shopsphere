package com.devteria.identity.mapper;

import com.devteria.identity.dto.request.UserCreationRequest;
import com.devteria.identity.dto.request.UserProfileCreationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    UserProfileCreationRequest toUserProfileCreationRequest(UserCreationRequest request);
}
