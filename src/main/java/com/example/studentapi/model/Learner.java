package com.example.studentapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "learners")
public class Learner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String givenName;

    @Column(nullable = false)
    private String familyName;

    @Column(nullable = false)
    private String enrollmentNumber;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
} 