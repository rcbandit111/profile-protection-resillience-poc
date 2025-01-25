package com.profile.protection.admin.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

  private String loginName;

  private String fullName;

  private String email;

  private String password;

  private List<UserRoleDto> roles;

}
