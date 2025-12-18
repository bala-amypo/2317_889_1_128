package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.services.AllocationRuleService;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {
    private final AssetClassAllocationRepository assetClassAllocationRuleRepository;

    public AllocationRuleServiceImpl(AssetClassAllocationRepository assetClassAllocationRuleRepository) {
        this.assetClassAllocationRuleRepository = assetClassAllocationRuleRepository;
    }

    @Override
    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        assetClassAllocationRuleRepository.saveAndFlush(rule);
        return rule;
    }

    @Override
    public Optional<AssetClassAllocationRule> updateRule(Long id, AssetClassAllocationRule updatedRule) {
        assetClassAllocationRuleRepository.deleteById(id);
        assetClassAllocationRuleRepository.saveAndFlush(updatedRule);
        return assetClassAllocationRuleRepository.findById(id);
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return assetClassAllocationRuleRepository.findActiveRulesHql(investorId);
    }
    
    @Override
    public List<AssetClassAllocationRule> getActiveRules(Long investorId) {
        return assetClassAllocationRuleRepository.findActiveRulesHql(investorId);
    }

    @Override
    public Optional<AssetClassAllocationRule> getRuleById(Long id) {
        return assetClassAllocationRuleRepository.findById(id);
    }
}
