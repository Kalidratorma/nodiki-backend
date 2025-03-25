package com.nodiki.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Role entity representing a user role in the system.
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  /**
   * The unique identifier of the role.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The name of the role.
   */
  @Enumerated(EnumType.STRING)
  @Column(unique = true)
  private ERole name;

  /**
   * Constructs a new role with the specified name.
   *
   * @param name the name of the role
   */
  public Role(ERole name) {
    this.name = name;
  }

  /**
   * Enumeration of available roles in the system.
   */
  public enum ERole {
    /**
     * Standard user role with limited access.
     */
    ROLE_USER,

    /**
     * Administrator role with full access.
     */
    ROLE_ADMIN
  }
}
