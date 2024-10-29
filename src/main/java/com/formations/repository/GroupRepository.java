package com.formations.repository;

import com.formations.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository  extends JpaRepository<Group ,Long> {
    boolean existsByName(String name);
}