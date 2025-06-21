package com.example.studentapi.controller;

import com.example.studentapi.model.ClassGroup;
import com.example.studentapi.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class ClassGroupController {
    @Autowired
    private ClassGroupService groupService;

    @GetMapping
    public List<ClassGroup> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassGroup> getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClassGroup createGroup(@RequestBody ClassGroup group) {
        return groupService.saveGroup(group);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassGroup> updateGroup(@PathVariable Long id, @RequestBody ClassGroup group) {
        return groupService.getGroupById(id)
                .map(existingGroup -> {
                    group.setId(id);
                    return ResponseEntity.ok(groupService.saveGroup(group));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        return groupService.getGroupById(id)
                .map(group -> {
                    groupService.deleteGroup(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 