// package com.example.demo.entity;

// import java.time.LocalDateTime;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;

// @Entity
// public class AllocationSnapshotRecord {
//     @Id
//     @GeneratedValue(strategy=GenerationType.IDENTITY)
//     private Long id;
//     private Long investorId;
//     private LocalDateTime snapShotDate;
//     private double totalPortfolioValue;
//     private String allocationString;

//     public AllocationSnapshotRecord() {
//     }

//     public AllocationSnapshotRecord(String allocationString, Long id, Long investorId, LocalDateTime snapShotDate, double totalPortfolioValue) {
//         this.allocationString = allocationString;
//         this.id = id;
//         this.investorId = investorId;
//         this.snapShotDate = snapShotDate;
//         this.totalPortfolioValue = totalPortfolioValue;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Long getInvestorId() {
//         return investorId;
//     }

//     public void setInvestorId(Long investorId) {
//         this.investorId = investorId;
//     }

//     public LocalDateTime getSnapShotDate() {
//         return snapShotDate;
//     }

//     public void setSnapShotDate(LocalDateTime snapShotDate) {
//         this.snapShotDate = snapShotDate;
//     }

//     public double getTotalPortfolioValue() {
//         return totalPortfolioValue;
//     }

//     public void setTotalPortfolioValue(double totalPortfolioValue) {
//         this.totalPortfolioValue = totalPortfolioValue;
//     }

//     public String getAllocationString() {
//         return allocationString;
//     }

//     public void setAllocationString(String allocationString) {
//         this.allocationString = allocationString;
//     }
// }


package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AllocationSnapshotRecord {
    @Id
    private Long id;
    private Long investorId;
    private LocalDateTime snapshotDate;
    private double totalPortfolioValue;
    private String allocationJson;

    public AllocationSnapshotRecord(Long investorId, LocalDateTime snapshotDate,
                                    Double totalPortfolioValue, String allocationJson) {
        this.investorId = investorId;
        this.snapshotDate = snapshotDate;
        this.totalPortfolioValue = totalPortfolioValue;
        this.allocationJson = allocationJson;
    }

    public AllocationSnapshotRecord() {
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

    public LocalDateTime getSnapShotDate() {
        return snapshotDate;
    }

    public void setSnapShotDate(LocalDateTime snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public double getTotalPortfolioValue() {
        return totalPortfolioValue;
    }

    public void setTotalPortfolioValue(double totalPortfolioValue) {
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public String getAllocationJson() {
        return allocationJson;
    }

    public void setAllocationJson(String allocationJson) {
        this.allocationJson = allocationJson;
    }


}
