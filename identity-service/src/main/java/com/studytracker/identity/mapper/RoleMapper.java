package com.studytracker.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.studytracker.identity.dto.request.RoleRequest;
import com.studytracker.identity.dto.response.RoleResponse;
import com.studytracker.identity.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
