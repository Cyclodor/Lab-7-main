package com.example.studentapi.service;

import com.example.studentapi.dao.ScheduleDAO;
import com.example.studentapi.model.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleDAO scheduleDAO;

    public ScheduleService() {
        this.scheduleDAO = new ScheduleDAO();
    }

    public List<Group> getStudentGroups() {
        return scheduleDAO.getStudentGroups();
    }
}