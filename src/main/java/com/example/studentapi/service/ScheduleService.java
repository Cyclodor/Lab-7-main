package com.example.studentapi.service;

import com.example.studentapi.dao.ScheduleDAO;
import com.example.studentapi.model.ClassGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleDAO scheduleDAO;

    public ScheduleService() {
        this.scheduleDAO = new ScheduleDAO();
    }

    public List<ClassGroup> getStudentGroups() {
        return scheduleDAO.getStudentGroups();
    }
}