// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.entity.AssetClassAllocationRule;
// import com.example.demo.service.AllocationRuleService;

// @RestController
// @RequestMapping("/api/allocation-rules")
// public class AllocationRuleController {

//     private final AllocationRuleService ruleService;

//     public AllocationRuleController(AllocationRuleService ruleService) {
//         this.ruleService = ruleService;
//     }

//     @PostMapping
//     public AssetClassAllocationRule createRule(
//             @RequestBody AssetClassAllocationRule rule) {
//         return ruleService.createRule(rule);
//     }

//     @PutMapping("/{id}")
//     public AssetClassAllocationRule updateRule(
//             @PathVariable Long id,
//             @RequestBody AssetClassAllocationRule rule) {
//         return ruleService.updateRule(id, rule);
//     }

//     @GetMapping("/investor/{investorId}")
//     public List<AssetClassAllocationRule> getRulesByInvestor(
//             @PathVariable Long investorId) {
//         return ruleService.getRulesByInvestor(investorId);
//     }

//     @GetMapping("/{id}")
//     public AssetClassAllocationRule getRuleById(@PathVariable Long id) {
//         return ruleService.getRuleById(id);
//     }

//     @GetMapping
//     public List<AssetClassAllocationRule> getAllRules() {
//         return ruleService.getAllRule();
//     }
// }


package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.impl.AllocationRuleServiceImpl;

@RestController
@RequestMapping("/api/allocation-rules")
public class AllocationRuleController {

    private final AllocationRuleServiceImpl service;

    public AllocationRuleController(AllocationRuleServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public AssetClassAllocationRule createRule(
            @RequestBody AssetClassAllocationRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public AssetClassAllocationRule updateRule(
            @PathVariable Long id,
            @RequestBody AssetClassAllocationRule rule) {
        return service.updateRule(id, rule);
    }

    @GetMapping("/investor/{investorId}")
    public List<AssetClassAllocationRule> getRulesByInvestor(
            @PathVariable Long investorId) {
        return service.getRulesByInvestor(investorId);
    }
}
