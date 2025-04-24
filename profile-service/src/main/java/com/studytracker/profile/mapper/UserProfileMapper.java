package com.studytracker.profile.mapper;

import org.mapstruct.Mapper;

import com.studytracker.profile.dto.request.ProfileCreationRequest;
import com.studytracker.profile.dto.response.UserProfileResponse;
import com.studytracker.profile.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile entity);
}
