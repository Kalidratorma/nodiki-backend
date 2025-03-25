package com.nodiki.backend.repository;

import com.nodiki.backend.model.Role;
import com.nodiki.backend.model.Role.ERole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
