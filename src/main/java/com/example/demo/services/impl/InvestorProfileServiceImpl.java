package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.services.InvestorProfileService;

@Service
public class InvestorProfileServiceImpl implements InvestorProfileService {
    private final InvestorProfileRepository investorProfileRepository;

    public InvestorProfileServiceImpl(InvestorProfileRepository investorProfileRepository) {
        this.investorProfileRepository = investorProfileRepository;
    }

    @Override
    public InvestorProfile createInvestorProfile(InvestorProfile investor) {
        investorProfileRepository.saveAndFlush(investor);
        return investor;
    }

    @Override
    public Optional<InvestorProfile> getInvestorById(Long id) {
        if (investorProfileRepository.findById(id) == null) {
            throw null;
        }
        return investorProfileRepository.findById(id);
    }

    @Override
    public Optional<InvestorProfile> findInvestorById(String investorId) {
        return investorProfileRepository.findByInvestorId(investorId);
    }

    @Override
    public List<InvestorProfile> getAllInvestors() {
        return investorProfileRepository.findAll();
    }

    @Override
    public Optional<InvestorProfile> updateInvestorStatus(Long id, boolean active) {
        if (active) {
            return investorProfileRepository.findById(id);
        }
        return null;
    }
}
