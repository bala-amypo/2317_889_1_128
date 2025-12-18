package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import com.example.demo.entity.enums.AlertSeverity;

@Entity
public class RebalancingAlertRecord {
    @Id
    private Long id;
    private Long investorId;
    private AssestClassType assestClass;
    private Double currentPercentage;
    private Double targetPercentage;
    private AlertSeverity severity;
    private String message;
    private LocalDateTime alertDate;
    private Boolean resolved = false;

    public RebalancingAlertRecord(LocalDateTime alertDate, AssestClassType assestClass, Double currentPercentage, Long id, Long investorId, String message, Boolean resolved, AlertSeverity severity, Double targetPercentage) {
        this.alertDate = alertDate;
        this.assestClass = assestClass;
        this.currentPercentage = currentPercentage;
        this.id = id;
        this.investorId = investorId;
        this.message = message;
        this.resolved = resolved;
        this.severity = severity;
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

    public Double getCurrentPercentage() {
        return currentPercentage;
    }

    public void setCurrentPercentage(Double currentPercentage) {
        this.currentPercentage = currentPercentage;
    }

    public Double getTargetPercentage() {
        return targetPercentage;
    }

    public void setTargetPercentage(Double targetPercentage) {
        this.targetPercentage = targetPercentage;
    }

    public AlertSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(AlertSeverity severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(LocalDateTime alertDate) {
        this.alertDate = alertDate;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }
}
