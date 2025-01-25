package com.profile.protection.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "login_name", unique = true, nullable = false)
  private String loginName;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private List<Roles> roles;

  @Column(name = "created_at")
  private OffsetDateTime createdAt;

  @Column(name = "modified_at")
  private OffsetDateTime modifiedAt;
}
