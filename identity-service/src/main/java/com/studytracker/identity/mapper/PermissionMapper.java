package com.studytracker.identity.mapper;

import org.mapstruct.Mapper;

import com.studytracker.identity.dto.request.PermissionRequest;
import com.studytracker.identity.dto.response.PermissionResponse;
import com.studytracker.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
