// package com.example.demo.service.impl;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.HoldingRecord;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.HoldingRecordRepository;
// import com.example.demo.service.HoldingRecordService;


// @Service
// public class HoldingRecordServiceImpl implements HoldingRecordService {

//     private final HoldingRecordRepository holdingRepo;

//     public HoldingRecordServiceImpl(HoldingRecordRepository holdingRepo) {
//         this.holdingRepo = holdingRepo;
//     }

//     @Override
//     public HoldingRecord recordHolding(HoldingRecord holding) {
//         if (holding.getCurrentValue() <= 0) {
//             throw new IllegalArgumentException("must be > 0");
//         }
//         holdingRepo.save(holding);
//         return holding;
//     }

//     @Override
//     public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
//         return holdingRepo.findByInvestorId(investorId);
//     }

//     @Override
//     public HoldingRecord getHoldingById(Long id) {
//         return holdingRepo.findById(id).orElseThrow(
//             () -> new ResourceNotFoundException("not found")
//         );
//     }

//     @Override
//     public List<HoldingRecord> getAllHoldings() {
//         return holdingRepo.findAll();
//     }
// }

// src/main/java/com/example/demo/service/impl/HoldingRecordServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.service.HoldingRecordService;

import java.util.List;
import java.util.Optional;

public class HoldingRecordServiceImpl implements HoldingRecordService {

    private final HoldingRecordRepository repository;

    public HoldingRecordServiceImpl(HoldingRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public HoldingRecord recordHolding(HoldingRecord holding) {
        if (holding.getCurrentValue() <= 0) {
            throw new IllegalArgumentException("must be > 0");
        }
        return repository.save(holding);
    }

    @Override
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }

    @Override
    public Optional<HoldingRecord> getHoldingById(Long id) {
        return repository.findById(id);
    }
}
