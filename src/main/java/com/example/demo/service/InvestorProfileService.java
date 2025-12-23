package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.InvestorProfile;

public interface InvestorProfileService {
    InvestorProfile createInvestorProfile(InvestorProfile investor);
    InvestorProfile getInvestorById(Long id);
    InvestorProfile findInvestorById(String investorId);
    List<InvestorProfile> getAllInvestors();
    InvestorProfile updateInvestorStatus(Long id, boolean active);
}
