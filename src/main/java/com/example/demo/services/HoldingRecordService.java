package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.HoldingRecord;

public interface HoldingRecordService {
    HoldingRecord recordHolding(HoldingRecord holding);
    List<HoldingRecord> getHoldingsByInvestor(Long investorId);
    HoldingRecord getHoldingById(Long id);
    List<HoldingRecord> getAllHoldings();
}