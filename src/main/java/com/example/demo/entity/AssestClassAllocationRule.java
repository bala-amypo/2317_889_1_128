package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AssetClassAllocationRule {
    @Id
    private Long id;
    private Long investorId;
    private AssestClassType assestClass;
    private Double targetPercentage;
    private Boolean active;

    public AssetClassAllocationRule(Boolean active, AssestClassType assestClass, Long id, Long investorId, Double targetPercentage) {
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

    public AssestClassType getAssestClass() {
        return assestClass;
    }

    public void setAssestClass(AssestClassType assestClass) {
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
