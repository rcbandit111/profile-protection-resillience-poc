package com.profile.protection.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "role_code", nullable = false)
  private String roleCode;

  @Column(name = "project_type", nullable = false)
  private String projectType;

  @Column(name = "created_at")
  private OffsetDateTime createdAt;

  @Column(name = "modified_at")
  private OffsetDateTime modifiedAt;

}
