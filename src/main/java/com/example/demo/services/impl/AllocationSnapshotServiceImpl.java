package com.example.demo.services.impl;

import org.springframework.stereotype.Service;

import java.util.*;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AllocationSnapshotRecordRepository;
import com.example.demo.repository.AssetClassAllocationRepository;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import com.example.demo.services.AllocationSnapshotService;

@Service
public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository snapshotRepo;
    private final HoldingRecordRepository holdingRepo;
    private final AssetClassAllocationRepository ruleRepo;
    private final RebalancingAlertRecordRepository alertRepo;

    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository snapshotRepo,
            HoldingRecordRepository holdingRepo,
            AssetClassAllocationRepository ruleRepo,
            RebalancingAlertRecordRepository alertRepo) {
        this.snapshotRepo = snapshotRepo;
        this.holdingRepo = holdingRepo;
        this.ruleRepo = ruleRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {
        List<HoldingRecord> holdings = holdingRepo.findByInvestorId(investorId);
        if (holdings.isEmpty()) {
            throw new ResourceNotFoundException("No holdings");
        }

        double totalPortfolioValue = 0;
        for (HoldingRecord holding : holdings) {
            totalPortfolioValue += holding.getCurrentValue();
        }

        if (totalPortfolioValue <= 0) {
            throw new IllegalArgumentException("must be > 0");
        }

        Map<AssetClassType, Double> allocations = new HashMap<>();

        for (HoldingRecord holding : holdings) {
            AssetClassType assetClass = holding.getAssetClassType();
            double value = holding.getCurrentValue();

            allocations.put(
                    assetClass,
                    allocations.getOrDefault(assetClass, 0.0) + value);
        }

        Map<AssetClassType, Double> percentages = new HashMap<>();

        for (Map.Entry<AssetClassType, Double> entry : allocations.entrySet()) {
            double percentage = (entry.getValue() / totalPortfolioValue) * 100;
            percentages.put(entry.getKey(), percentage);
        }

        List<AssetClassAllocationRule> rules = ruleRepo.findByInvestorId(investorId);

        for (AssetClassAllocationRule rule : rules) {
            double currentPercentage = percentages.getOrDefault(rule.getAssetClassType(), 0.0);

            if (currentPercentage > rule.getTargetPercentage()) {
                RebalancingAlertRecord alert = new RebalancingAlertRecord();
                alert.setInvestorId(investorId);
                alert.setAssetClass(rule.getAssetClassType());
                alert.setCurrentPercentage(currentPercentage);
                alert.setTargetPercentage(rule.getTargetPercentage());
                alert.setResolved(false);

                alertRepo.save(alert);
            }
        }

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord();
        snapshot.setInvestorId(investorId);
        snapshot.setTotalPortfolioValue(totalPortfolioValue);
        snapshot.setAllocationString(percentages.toString());
        return snapshotRepo.save(snapshot);
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("not found"));
    }

    @Override
    public AllocationSnapshotRecord getSnapshotsByInvestor(Long investorId) {
        return snapshotRepo.findById(investorId).orElseThrow(
                () -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepo.findAll();
    }
}