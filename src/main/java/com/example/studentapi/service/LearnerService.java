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
        
        System.out.println("=== LEARNER SERVICE ===");
        System.out.println("Searching learners by department: " + department);
        System.out.println("Cache key: " + cacheKey);
        
        if (cacheService.containsKey(cacheKey)) {
            System.out.println("✅ Data found in cache for key: " + cacheKey);
            List<Learner> cachedData = (List<Learner>) cacheService.get(cacheKey);
            System.out.println("Returning " + (cachedData != null ? cachedData.size() : 0) + " learners from cache");
            return cachedData;
        }
        
        System.out.println("❌ Data not in cache, fetching from database...");
        List<Learner> learners = learnerRepository.findLearnersByCourseDepartment(department);
        System.out.println("Found " + (learners != null ? learners.size() : 0) + " learners in database");
        
        if (learners != null && !learners.isEmpty()) {
            cacheService.put(cacheKey, learners);
            System.out.println("✅ Data cached with key: " + cacheKey);
        } else {
            System.out.println("⚠️ No data to cache (empty result)");
        }
        
        return learners;
    }
} 