package com.example.studentapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "student_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String faculty;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;
}