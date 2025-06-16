package com.example.studentapi.dao;

import com.example.studentapi.model.Group;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class ScheduleDAO {
    private final RestTemplate restTemplate;

    public ScheduleDAO() {
        this.restTemplate = new RestTemplate();
    }

    public List<Group> getStudentGroups() {
        String url = "https://iis.bsuir.by/api/v1/student-groups";
        try {
            Group[] groups = restTemplate.getForObject(url, Group[].class);
            return Arrays.asList(groups);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}