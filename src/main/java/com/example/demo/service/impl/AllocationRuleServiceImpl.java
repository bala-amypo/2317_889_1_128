// package com.example.demo.service.impl;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.AssetClassAllocationRule;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.AssetClassAllocationRuleRepository;
// import com.example.demo.service.AllocationRuleService;

// @Service
// public class AllocationRuleServiceImpl implements AllocationRuleService {
//     private final AssetClassAllocationRuleRepository assetClassAllocationRepository;

//     public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository assetClassAllocationRepository) {
//         this.assetClassAllocationRepository = assetClassAllocationRepository;
//     }

//     @Override
//     public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
//         if (rule.getTargetPercentage() <= 0 || rule.getTargetPercentage() > 100) {
//             throw new IllegalArgumentException("between 0 and 100");
//         }
//         assetClassAllocationRepository.saveAndFlush(rule);
//         return rule;
//     }

//     @Override
//     public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule updatedRule) {
//         assetClassAllocationRepository.deleteById(id);
//         assetClassAllocationRepository.saveAndFlush(updatedRule);
//         return assetClassAllocationRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("not found"));
//     }

//     @Override
//     public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
//         return assetClassAllocationRepository.findActiveRulesHql(investorId);
//     }

//     @Override
//     public List<AssetClassAllocationRule> getActiveRules(Long investorId) {
//         return assetClassAllocationRepository.findActiveRulesHql(investorId);
//     }

//     @Override
//     public AssetClassAllocationRule getRuleById(Long id) {
//         return assetClassAllocationRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("not found"));
//     }

//     @Override
//     public List<AssetClassAllocationRule> getAllRule() {
//         return assetClassAllocationRepository.findAll();
//     }
// }


// src/main/java/com/example/demo/service/impl/AllocationRuleServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;

import java.util.List;

public class AllocationRuleServiceImpl implements AllocationRuleService {

    private final AssetClassAllocationRuleRepository repository;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        if (rule.getTargetPercentage() < 0 || rule.getTargetPercentage() > 100) {
            throw new IllegalArgumentException("between 0 and 100");
        }
        return repository.save(rule);
    }

    @Override
    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule data) {
        AssetClassAllocationRule rule = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found " + id));
        rule.setTargetPercentage(data.getTargetPercentage());
        return repository.save(rule);
    }

    @Override
    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }
}
