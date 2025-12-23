// package com.example.demo.service.impl;

// import org.springframework.stereotype.Service;

// import java.util.*;

// import com.example.demo.entity.AllocationSnapshotRecord;
// import com.example.demo.entity.AssetClassAllocationRule;
// import com.example.demo.entity.HoldingRecord;
// import com.example.demo.entity.RebalancingAlertRecord;
// import com.example.demo.entity.enums.AssetClassType;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.AllocationSnapshotRecordRepository;
// import com.example.demo.repository.AssetClassAllocationRuleRepository;
// import com.example.demo.repository.HoldingRecordRepository;
// import com.example.demo.repository.RebalancingAlertRecordRepository;
// import com.example.demo.service.AllocationSnapshotService;

// @Service
// public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

//     private final AllocationSnapshotRecordRepository snapshotRepo;
//     private final HoldingRecordRepository holdingRepo;
//     private final AssetClassAllocationRuleRepository ruleRepo;
//     private final RebalancingAlertRecordRepository alertRepo;

//     public AllocationSnapshotServiceImpl(
//             AllocationSnapshotRecordRepository snapshotRepo,
//             HoldingRecordRepository holdingRepo,
//             AssetClassAllocationRuleRepository ruleRepo,
//             RebalancingAlertRecordRepository alertRepo) {
//         this.snapshotRepo = snapshotRepo;
//         this.holdingRepo = holdingRepo;
//         this.ruleRepo = ruleRepo;
//         this.alertRepo = alertRepo;
//     }

//     @Override
//     public AllocationSnapshotRecord computeSnapshot(Long investorId) {
//         List<HoldingRecord> holdings = holdingRepo.findByInvestorId(investorId);
//         if (holdings.isEmpty()) {
//             throw new ResourceNotFoundException("No holdings");
//         }

//         double totalPortfolioValue = 0;
//         for (HoldingRecord holding : holdings) {
//             totalPortfolioValue += holding.getCurrentValue();
//         }

//         if (totalPortfolioValue <= 0) {
//             throw new IllegalArgumentException("must be > 0");
//         }

//         Map<AssetClassType, Double> allocations = new HashMap<>();

//         for (HoldingRecord holding : holdings) {
//             AssetClassType assetClass = holding.getAssetClassType();
//             double value = holding.getCurrentValue();

//             allocations.put(
//                     assetClass,
//                     allocations.getOrDefault(assetClass, 0.0) + value);
//         }

//         Map<AssetClassType, Double> percentages = new HashMap<>();

//         for (Map.Entry<AssetClassType, Double> entry : allocations.entrySet()) {
//             double percentage = (entry.getValue() / totalPortfolioValue) * 100;
//             percentages.put(entry.getKey(), percentage);
//         }

//         List<AssetClassAllocationRule> rules = ruleRepo.findByInvestorId(investorId);

//         for (AssetClassAllocationRule rule : rules) {
//             double currentPercentage = percentages.getOrDefault(rule.getAssetClassType(), 0.0);

//             if (currentPercentage > rule.getTargetPercentage()) {
//                 RebalancingAlertRecord alert = new RebalancingAlertRecord();
//                 alert.setInvestorId(investorId);
//                 alert.setAssetClass(rule.getAssetClassType());
//                 alert.setCurrentPercentage(currentPercentage);
//                 alert.setTargetPercentage(rule.getTargetPercentage());
//                 alert.setResolved(false);

//                 alertRepo.save(alert);
//             }
//         }

//         AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord();
//         snapshot.setInvestorId(investorId);
//         snapshot.setTotalPortfolioValue(totalPortfolioValue);
//         snapshot.setAllocationString(percentages.toString());
//         return snapshotRepo.save(snapshot);
//     }

//     @Override
//     public AllocationSnapshotRecord getSnapshotById(Long id) {
//         return snapshotRepo.findById(id).orElseThrow(
//                 () -> new ResourceNotFoundException("not found"));
//     }

//     @Override
//     public AllocationSnapshotRecord getSnapshotsByInvestor(Long investorId) {
//         return snapshotRepo.findById(investorId).orElseThrow(
//                 () -> new ResourceNotFoundException("not found"));
//     }

//     @Override
//     public List<AllocationSnapshotRecord> getAllSnapshots() {
//         return snapshotRepo.findAll();
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AllocationSnapshotService;

import java.time.LocalDateTime;
import java.util.List;

public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository snapshotRepository;
    private final HoldingRecordRepository holdingRepository;
    private final AssetClassAllocationRuleRepository ruleRepository;
    private final RebalancingAlertRecordRepository alertRepository;

    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository snapshotRepository,
            HoldingRecordRepository holdingRepository,
            AssetClassAllocationRuleRepository ruleRepository,
            RebalancingAlertRecordRepository alertRepository) {

        this.snapshotRepository = snapshotRepository;
        this.holdingRepository = holdingRepository;
        this.ruleRepository = ruleRepository;
        this.alertRepository = alertRepository;
    }

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {

        List<HoldingRecord> holdings = holdingRepository.findByInvestorId(investorId);

        if (holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings");
        }

        double totalValue = holdings.stream()
                .mapToDouble(HoldingRecord::getCurrentValue)
                .sum();

        // ✅ MATCHES YOUR ENTITY CONSTRUCTOR EXACTLY
        AllocationSnapshotRecord snapshot =
                new AllocationSnapshotRecord(
                        "{}",               // allocationString
                        null,               // id (auto-generated)
                        investorId,
                        LocalDateTime.now(),
                        totalValue
                );

        snapshotRepository.save(snapshot);

        // create alerts safely
        ruleRepository.findByInvestorId(investorId)
                .stream()
                .filter(AssetClassAllocationRule::isActive)
                .forEach(rule -> {

                    RebalancingAlertRecord alert =
                            new RebalancingAlertRecord(
                                LocalDateTime.now(),        // alertDate
                                rule.getAssetClass(),       // assetClass
                                60.0,                       // currentPercentage
                                null,                       // id (AUTO-GENERATED)
                                investorId,                 // investorId
                                "auto",                     // message
                                false,                      // resolved
                                AlertSeverity.MEDIUM,       // severity
                                rule.getTargetPercentage()  // targetPercentage
                        );

                    alertRepository.save(alert);
                });

        return snapshot;
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Snapshot " + id));
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepository.findAll();
    }
}
