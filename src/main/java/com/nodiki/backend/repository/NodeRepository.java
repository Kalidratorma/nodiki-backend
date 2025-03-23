package com.nodiki.backend.repository;

import com.nodiki.backend.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Long> {}
