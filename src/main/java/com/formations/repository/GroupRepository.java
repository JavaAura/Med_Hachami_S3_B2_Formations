package com.formations.repository;

import com.formations.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository  extends JpaRepository<Group ,Long> {
    //boolean existsByName(String name);
    Optional<Group> existsByName(String name);
}
