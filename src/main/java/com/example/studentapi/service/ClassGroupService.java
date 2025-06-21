package com.example.studentapi.service;

import com.example.studentapi.model.ClassGroup;
import com.example.studentapi.repository.ClassGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassGroupService {
    @Autowired
    private ClassGroupRepository groupRepository;

    public List<ClassGroup> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<ClassGroup> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    public ClassGroup saveGroup(ClassGroup group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
} 