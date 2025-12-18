package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.repository.AssetClassAllocationRepository;
import com.example.demo.services.AllocationRuleService;

@Service
public class AllocationRuleServiceImpl implements AllocationRuleService {
    private final AssetClassAllocationRepository assetClassAllocationRepository;

    public AllocationRuleServiceImpl(AssetClassAllocationRepository assetClassAllocationRepository) {
        this.assetClassAllocationRepository = assetClassAllocationRepository;
    }

    @Override
    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        assetClassAllocationRepository.saveAndFlush(rule);
        return rule;
    }

    @Override
    public Optional<AssetClassAllocationRule> updateRule(Long id, AssetClassAllocationRule updatedRule) {
        assetClassAllocationRepository.deleteById(id);
        assetClassAllocationRepository.saveAndFlush(updatedRule);
        return assetClassAllocationRepository.findById(id);
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return assetClassAllocationRepository.findActiveRulesHql(investorId);
    }
    
    @Override
    public List<AssetClassAllocationRule> getActiveRules(Long investorId) {
        return assetClassAllocationRepository.findActiveRulesHql(investorId);
    }

    @Override
    public Optional<AssetClassAllocationRule> getRuleById(Long id) {
        return assetClassAllocationRepository.findById(id);
    }

    @Override
    public List<AssetClassAllocationRule> getAllRule() {
        return assetClassAllocationRuleRepository.findAll();
    }
}
