package com.nodiki.backend.repository;

import com.nodiki.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing {@link User} entities.
 * <p>
 * Provides query methods to retrieve users by username and email,
 * and to check for their existence.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Finds a user by their username.
   *
   * @param username the username to search for
   * @return an {@link Optional} containing the user if found, or empty if not
   */
  Optional<User> findByUsername(String username);

  /**
   * Checks whether a user exists by their username.
   *
   * @param username the username to check
   * @return {@code true} if a user with the given username exists, otherwise {@code false}
   */
  boolean existsByUsername(String username);

  /**
   * Checks whether a user exists by their email address.
   *
   * @param email the email address to check
   * @return {@code true} if a user with the given email exists, otherwise {@code false}
   */
  boolean existsByEmail(String email);
}
