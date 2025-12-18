package com.example.demo.services.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import com.example.demo.services.RebalancingAlertService;

@Service
public class RebalancingAlertServiceImpl implements RebalancingAlertService {

    private final RebalancingAlertRecordRepository alertRepo;

    public RebalancingAlertServiceImpl(RebalancingAlertRecordRepository alertRepo) {
        this.alertRepo = alertRepo;
    }

    @Override
    public RebalancingAlertRecord createAlert(RebalancingAlertRecord alert) {
        if (alert.getCurrentPercentage() <= alert.getTargetPercentage()) {
            throw null;
        }
        alert.setResolved(false);
        return alertRepo.save(alert);
    }

    @Override
    public RebalancingAlertRecord resolveAlert(Long id) {
        RebalancingAlertRecord alert = alertRepo.findById(id).orElseThrow();
        alert.setResolved(true);
        return alertRepo.save(alert);
    }

    @Override
    public List<RebalancingAlertRecord> getAlertsByInvestor(Long investorId) {
        return alertRepo.findByInvestorId(investorId);
    }

    @Override
    public Optional<RebalancingAlertRecord> getAlertById(Long id) {
        return alertRepo.findById(id);
    }

    @Override
    public List<RebalancingAlertRecord> getAllAlerts() {
        return alertRepo.findAll();
    }
}