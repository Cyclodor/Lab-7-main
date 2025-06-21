package com.example.studentapi.service;

import com.example.studentapi.model.Learner;
import com.example.studentapi.repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnerService {
    @Autowired
    private LearnerRepository studentRepository;

    public List<Learner> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Learner> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Learner saveStudent(Learner student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
} 