// package com.example.demo.entity;

// import java.time.LocalDateTime;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;

// @Entity
// public class AllocationSnapshotRecord {
//     @Id
//     private Long id;
//     private Long investorId;
//     private LocalDateTime snapShotDate;
//     private double totalPortfolioValue;
//     private String allocationString;

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
