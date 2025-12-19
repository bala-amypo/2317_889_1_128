package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;

@Repository
public interface HoldingRecordRepository extends JpaRepository<HoldingRecord, Long> {
    List<HoldingRecord> findByInvestorId(Long investorId);
    List<HoldingRecord> findByCurrentValueGreaterThan(Double currentValue);
    List<HoldingRecord> findByInvestorIdAndAssetClass(Long investorId, AssetClassType assetClass);
}
