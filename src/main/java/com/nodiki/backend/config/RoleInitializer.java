package com.nodiki.backend.config;

import com.nodiki.backend.model.Role;
import com.nodiki.backend.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Initializes default roles in the database on application startup
 * if they do not already exist.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RoleInitializer {

    /**
     * Repository for accessing and persisting Role entities.
     */
    private final RoleRepository roleRepository;

    /**
     * Ensures all roles defined in the {@link Role.ERole} enum are present in the database.
     * This method is called once after bean initialization.
     */
    @PostConstruct
    public void initRoles() {
        Arrays.stream(Role.ERole.values())
                .forEach(roleEnum -> {
                    boolean exists = roleRepository.findByName(roleEnum).isPresent();
                    if (!exists) {
                        roleRepository.save(new Role(roleEnum));
                        log.info("Created missing role: {}", roleEnum);
                    } else {
                        log.debug("Role {} already exists", roleEnum);
                    }
                });
    }
}
