package com.profile.protection.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UsersResource {

    private UUID id;

    private String loginName;

    private String fullName;

    private String email;

    private OffsetDateTime createdAt;

    private OffsetDateTime modifiedAt;

    private List<UserRoleDto> roles;

}
