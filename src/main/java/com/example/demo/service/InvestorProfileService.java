// package com.example.demo.service;

// import java.util.List;

// import com.example.demo.entity.InvestorProfile;

// public interface InvestorProfileService {
//     InvestorProfile createInvestorProfile(InvestorProfile investor);
//     InvestorProfile getInvestorById(Long id);
//     InvestorProfile findInvestorById(String investorId);
//     List<InvestorProfile> getAllInvestors();
//     InvestorProfile updateInvestorStatus(Long id, boolean active);
// }

// src/main/java/com/example/demo/service/InvestorProfileService.java
package com.example.demo.service;

import com.example.demo.entity.InvestorProfile;

import java.util.List;
import java.util.Optional;

public interface InvestorProfileService {
    InvestorProfile createInvestor(InvestorProfile investor);
    InvestorProfile getInvestorById(Long id);
    List<InvestorProfile> getAllInvestors();
    InvestorProfile updateInvestorStatus(Long id, boolean active);
    InvestorProfile findByInvestorId(String investorId);
}
