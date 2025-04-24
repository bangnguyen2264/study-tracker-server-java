package com.studytracker.identity.mapper;

import org.mapstruct.Mapper;

import com.studytracker.identity.dto.request.ProfileCreationRequest;
import com.studytracker.identity.dto.request.UserCreationRequest;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
