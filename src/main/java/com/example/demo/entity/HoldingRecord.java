package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class HoldingRecord {
    @Id
    private Long id;
    private Long investorId;
    private AssetClassType assetClass;
    private Double currentValue;
    private LocalDateTime snapShotDate;

    public HoldingRecord(AssetClassType assetClass, Double currentValue, Long id, Long investorId, LocalDateTime snapShotDate) {
        this.assetClass = assetClass;
        this.currentValue = currentValue;
        this.id = id;
        this.investorId = investorId;
        this.snapShotDate = snapShotDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public AssetClassType getAssetClassType() {
        return assetClass;
    }

    public void setAssetClassType(AssetClassType assetClass) {
        this.assetClass = assetClass;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public LocalDateTime getSnapShotDate() {
        return snapShotDate;
    }

    public void setSnapShotDate(LocalDateTime snapShotDate) {
        this.snapShotDate = snapShotDate;
    }
}
