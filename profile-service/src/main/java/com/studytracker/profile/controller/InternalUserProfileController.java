package com.studytracker.profile.controller;

import org.springframework.web.bind.annotation.*;

import com.studytracker.profile.dto.ApiResponse;
import com.studytracker.profile.dto.request.ProfileCreationRequest;
import com.studytracker.profile.dto.response.UserProfileResponse;
import com.studytracker.profile.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalUserProfileController {
    UserProfileService userProfileService;

    @PostMapping("/internal/users")
    ApiResponse<UserProfileResponse> createProfile(@RequestBody ProfileCreationRequest request) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.createProfile(request))
                .build();
    }
}
