package com.profile.protection.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles {

  @Id
  @GeneratedValue
  private UUID id; // Unique ID for each role

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")  // This column maps to the Users' ID column
  private Users user;

  @Column(name = "role_code", nullable = false)
  private String roleCode;

  @Column(name = "project_type", nullable = false)
  private String projectType;

  @Column(name = "created_at")
  private OffsetDateTime createdAt;

  @Column(name = "modified_at")
  private OffsetDateTime modifiedAt;

}
