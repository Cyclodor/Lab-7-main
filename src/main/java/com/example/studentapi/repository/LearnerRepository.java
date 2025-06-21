package com.example.studentapi.repository;

import com.example.studentapi.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearnerRepository extends JpaRepository<Learner, Long> {
} 