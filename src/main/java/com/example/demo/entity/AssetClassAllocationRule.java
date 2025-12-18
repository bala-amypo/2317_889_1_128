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
    private AssetClassType assestClass;
    @Min(0)
    @Max(100)
    private Double targetPercentage;
    private Boolean active;

    public AssetClassAllocationRule(Boolean active, AssetClassType assestClass, Long id, Long investorId, Double targetPercentage) {
        this.active = active;
        this.assestClass = assestClass;
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

    public AssetClassType getAssestClass() {
        return assestClass;
    }

    public void setAssestClass(AssetClassType assestClass) {
        this.assestClass = assestClass;
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
