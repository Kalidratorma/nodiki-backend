package com.nodiki.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(unique = true)
  private ERole name;

  public enum ERole {
    ROLE_USER,
    ROLE_ADMIN
  }
}
