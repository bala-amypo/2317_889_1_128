package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.AssetClassAllocationRule;

public interface AssetClassAllocationRepository extends JpaRepository<AssetClassAllocationRule, Long> {
    List<AssetClassAllocationRule> findByInvestorId(Long investorId);
    // @Query
    List<AssetClassAllocationRule> findActiveRulesHql(Long investorId);
}
