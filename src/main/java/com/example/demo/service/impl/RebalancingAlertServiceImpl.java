// package com.example.demo.service.impl;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.RebalancingAlertRecord;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.RebalancingAlertRecordRepository;
// import com.example.demo.service.RebalancingAlertService;

// @Service
// public class RebalancingAlertServiceImpl implements RebalancingAlertService {

//     private final RebalancingAlertRecordRepository alertRepo;

//     public RebalancingAlertServiceImpl(RebalancingAlertRecordRepository alertRepo) {
//         this.alertRepo = alertRepo;
//     }

//     @Override
//     public RebalancingAlertRecord createAlert(RebalancingAlertRecord alert) {
//         if (alert.getCurrentPercentage() <= alert.getTargetPercentage()) {
//             throw new IllegalArgumentException("between 0 and 100");
//         }
//         alert.setResolved(false);
//         return alertRepo.save(alert);
//     }

//     @Override
//     public RebalancingAlertRecord resolveAlert(Long id) {
//         RebalancingAlertRecord alert = alertRepo.findById(id).orElseThrow(
//                 () -> new ResourceNotFoundException("not found"));
//         alert.setResolved(true);
//         return alertRepo.save(alert);
//     }

//     @Override
//     public List<RebalancingAlertRecord> getAlertsByInvestor(Long investorId) {
//         return alertRepo.findByInvestorId(investorId);
//     }

//     @Override
//     public RebalancingAlertRecord getAlertById(Long id) {
//         return alertRepo.findById(id).orElseThrow(
//                 () -> new ResourceNotFoundException("not found"));
//     }

//     @Override
//     public List<RebalancingAlertRecord> getAllAlerts() {
//         return alertRepo.findAll();
//     }
// }

// src/main/java/com/example/demo/service/impl/RebalancingAlertServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import com.example.demo.service.RebalancingAlertService;

import java.util.List;

public class RebalancingAlertServiceImpl implements RebalancingAlertService {

    private final RebalancingAlertRecordRepository repository;

    public RebalancingAlertServiceImpl(RebalancingAlertRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public RebalancingAlertRecord createAlert(RebalancingAlertRecord alert) {
        if (alert.getCurrentPercentage() <= alert.getTargetPercentage()) {
            throw new IllegalArgumentException("currentPercentage > targetPercentage");
        }
        return repository.save(alert);
    }

    @Override
    public RebalancingAlertRecord resolveAlert(Long id) {
        RebalancingAlertRecord alert = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found " + id));
        alert.setResolved(true);
        return repository.save(alert);
    }

    @Override
    public List<RebalancingAlertRecord> getAlertsByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }
}
