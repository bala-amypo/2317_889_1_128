// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

// import com.example.demo.entity.InvestorProfile;
// import com.example.demo.service.InvestorProfileService;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PutMapping;


// @RestController
// @RequestMapping("/api/investors")
// public class InvestorProfileController {
//     private final InvestorProfileService investorProfileService;

//     public InvestorProfileController(InvestorProfileService investorProfileService) {
//         this.investorProfileService = investorProfileService;
//     }

//     @PostMapping("/")
//     public InvestorProfile postInvestorProfile(@RequestBody InvestorProfile investor) {
//         return investorProfileService.createInvestorProfile(investor);
//     }

//     @GetMapping("/{id}")
//     public InvestorProfile getInvestorProfile(@PathVariable Long id) {
//         return investorProfileService.getInvestorById(id);
//     }
    
//     @GetMapping
//     public List<InvestorProfile> getAllInvestorProfile() {
//         return investorProfileService.getAllInvestors();
//     }

//     @PutMapping("/{id}/status")
//     public InvestorProfile putInvestorProfile(@PathVariable Long id, @RequestParam boolean active) {
//         return investorProfileService.updateInvestorStatus(id, active);
//     }

//     @GetMapping("/lookup/{investorId}")
//     public InvestorProfile getMethodName(@PathVariable String investorId) {
//         return investorProfileService.findInvestorById(investorId);
//     }    
// }


package com.example.demo.controller;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.InvestorProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investors")
public class InvestorProfileController {

    private final InvestorProfileService investorProfileService;

    public InvestorProfileController(InvestorProfileService investorProfileService) {
        this.investorProfileService = investorProfileService;
    }

    @PostMapping
    public InvestorProfile create(@RequestBody InvestorProfile investor) {
        return investorProfileService.createInvestor(investor);
    }

    @GetMapping("/{id}")
    public InvestorProfile getById(@PathVariable Long id) {
        return investorProfileService.getInvestorById(id);
    }

    @GetMapping
    public List<InvestorProfile> getAll() {
        return investorProfileService.getAllInvestors();
    }

    @GetMapping("/lookup/{investorId}")
    public InvestorProfile findByInvestorId(@PathVariable String investorId) {
        return investorProfileService.findByInvestorId(investorId);
    }

    @PutMapping("/{id}/status")
    public InvestorProfile updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {
        return investorProfileService.updateInvestorStatus(id, active);
    }
}
