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

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.InvestorProfile;

public interface InvestorProfileService {

    InvestorProfile createInvestor(InvestorProfile investor);

    InvestorProfile getInvestorById(Long id);

    List<InvestorProfile> getAllInvestors();

    InvestorProfile updateInvestorStatus(Long id, Boolean active);

    Optional<InvestorProfile> findByInvestorId(String investorId);
}

