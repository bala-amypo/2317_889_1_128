// package com.example.demo.service;

// import java.util.List;

// import com.example.demo.entity.HoldingRecord;

// public interface HoldingRecordService {
//     HoldingRecord recordHolding(HoldingRecord holding);
//     List<HoldingRecord> getHoldingsByInvestor(Long investorId);
//     HoldingRecord getHoldingById(Long id);
//     List<HoldingRecord> getAllHoldings();
// }

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.HoldingRecord;

public interface HoldingRecordService {

    HoldingRecord recordHolding(HoldingRecord holding);

    List<HoldingRecord> getHoldingsByInvestor(Long investorId);

    Optional<HoldingRecord> getHoldingById(Long id);
}
