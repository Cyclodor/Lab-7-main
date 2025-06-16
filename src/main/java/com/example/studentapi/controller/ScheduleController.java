package com.example.studentapi.controller;

import com.example.studentapi.model.Group;
import com.example.studentapi.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController() {
        this.scheduleService = new ScheduleService();
    }

    @GetMapping("/student-groups")
    public List<Group> getStudentGroups() {
        return scheduleService.getStudentGroups();
    }
}