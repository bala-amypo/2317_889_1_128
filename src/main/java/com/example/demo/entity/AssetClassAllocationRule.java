package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import com.example.demo.entity.enums.AssetClassType;

@Entity
public class AssetClassAllocationRule {
    @Id
    private Long id;
    private Long investorId;
    private AssetClassType assetClass;
    private Double targetPercentage;
    private Boolean active;

    public AssetClassAllocationRule() {
    }

    public AssetClassAllocationRule(Boolean active, AssetClassType assetClass, Long id, Long investorId, Double targetPercentage) {
        this.active = active;
        this.assetClass = assetClass;
        this.id = id;
        this.investorId = investorId;
        this.targetPercentage = targetPercentage;
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

    public void setAssetClass(AssetClassType assetClass) {
        this.assetClass = assetClass;
    }

    public Double getTargetPercentage() {
        return targetPercentage;
    }

    public void setTargetPercentage(Double targetPercentage) {
        this.targetPercentage = targetPercentage;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
