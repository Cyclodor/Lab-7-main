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
    private LearnerRepository learnerRepository;
    
    @Autowired
    private CacheService cacheService;

    public List<Learner> getAllLearners() {
        return learnerRepository.findAll();
    }

    public Optional<Learner> getLearnerById(Long id) {
        return learnerRepository.findById(id);
    }

    public Learner saveLearner(Learner learner) {
        return learnerRepository.save(learner);
    }

    public void deleteLearner(Long id) {
        learnerRepository.deleteById(id);
    }

    public List<Learner> getLearnersByCourseDepartment(String department) {
        String cacheKey = "learners_by_department_" + department;
        
        // Проверяем кэш
        if (cacheService.containsKey(cacheKey)) {
            return (List<Learner>) cacheService.get(cacheKey);
        }
        
        // Если нет в кэше, получаем из БД и кэшируем
        List<Learner> learners = learnerRepository.findLearnersByCourseDepartment(department);
        cacheService.put(cacheKey, learners);
        
        return learners;
    }
} 