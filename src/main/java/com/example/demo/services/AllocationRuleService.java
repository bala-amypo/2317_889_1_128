package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.AssetClassAllocationRule;

public interface AllocationRuleService {
    AssetClassAllocationRule createRule(AssetClassAllocationRule rule);
    Optional<AssetClassAllocationRule> updateRule(Long id, AssetClassAllocationRule updatedRule);
    List<AssetClassAllocationRule> getRulesByInvestor(Long investorId);
    List<AssetClassAllocationRule> getActiveRules(Long investorId);
    Optional<AssetClassAllocationRule> getRuleById(Long id);
    List<AssetClassAllocationRule> getAllRule();
}