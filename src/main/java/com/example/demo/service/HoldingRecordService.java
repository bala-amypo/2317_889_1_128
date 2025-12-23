// package com.example.demo.service;

// import java.util.List;

// import com.example.demo.entity.HoldingRecord;

// public interface HoldingRecordService {
//     HoldingRecord recordHolding(HoldingRecord holding);
//     List<HoldingRecord> getHoldingsByInvestor(Long investorId);
//     HoldingRecord getHoldingById(Long id);
//     List<HoldingRecord> getAllHoldings();
// }

// src/main/java/com/example/demo/service/HoldingRecordService.java
package com.example.demo.service;

import com.example.demo.entity.HoldingRecord;

import java.util.List;
import java.util.Optional;

public interface HoldingRecordService {
    HoldingRecord recordHolding(HoldingRecord holding);
    List<HoldingRecord> getHoldingsByInvestor(Long investorId);
    Optional<HoldingRecord> getHoldingById(Long id);
}
