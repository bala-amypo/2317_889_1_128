package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.InvestorProfile;

public interface InvestorProfileService {
    InvestorProfile createInvestorProfile(InvestorProfile investor);
    Optional<InvestorProfile> getInvestorById(Long id);
    Optional<InvestorProfile> findInvestorById(String investorId);
    List<InvestorProfile> getAllInvestors();
    Optional<InvestorProfile> updateInvestorStatus(Long id, boolean active);
}