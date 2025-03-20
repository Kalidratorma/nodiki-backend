package com.nodiki.backend.controllers;

import com.nodiki.backend.entities.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Long> {
}
