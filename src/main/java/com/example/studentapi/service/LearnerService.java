package com.example.studentapi.service;

import com.example.studentapi.model.Learner;
import com.example.studentapi.repository.LearnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LearnerService {
    @Autowired
    private LearnerRepository learnerRepository;
    
    @Autowired
    private CacheService cacheService;

    private static final Logger logger = LoggerFactory.getLogger(LearnerService.class);

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

    @SuppressWarnings("unchecked")
    public List<Learner> getLearnersByCourseDepartment(String department) {
        String cacheKey = "learners_by_department_" + department;
        logger.info("=== LEARNER SERVICE ===");
        logger.info("Searching learners by department: {}", department);
        logger.info("Cache key: {}", cacheKey);
        if (cacheService.containsKey(cacheKey)) {
            logger.info("\u2705 Data found in cache for key: {}", cacheKey);
            Object cachedData = cacheService.get(cacheKey);
            if (cachedData instanceof List<?>) {
                return (List<Learner>) cachedData;
            }
            return List.of();
        }
        logger.info("\u274c Data not in cache, fetching from database...");
        List<Learner> learners = learnerRepository.findLearnersByCourseDepartment(department);
        logger.info("Found {} learners in database", (learners != null ? learners.size() : 0));
        if (learners != null && !learners.isEmpty()) {
            cacheService.put(cacheKey, learners);
            logger.info("\u2705 Data cached with key: {}", cacheKey);
        } else {
            logger.info("\u26a0\ufe0f No data to cache (empty result)");
        }
        return learners;
    }

    public List<Learner> saveLearners(List<Learner> learners) {
        return learners == null ? List.of() : learners.stream()
                .map(learnerRepository::save)
                .collect(Collectors.toList());
    }
} 