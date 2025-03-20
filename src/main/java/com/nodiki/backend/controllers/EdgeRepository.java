package com.nodiki.backend.controllers;

import com.nodiki.backend.entities.Edge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdgeRepository extends JpaRepository<Edge, Long> {
}
