package com.formations.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formations.model.Learner;

@Repository
public interface LearnerRepository extends JpaRepository<Learner,Long> {
    
}
