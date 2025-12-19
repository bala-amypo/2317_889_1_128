package com.example.demo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.exception.ResourceNotFoundException;
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
    public InvestorProfile getInvestorById(Long id) {
        return investorProfileRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("not found"));
    }

    @Override
    public InvestorProfile findInvestorById(String investorId) {
        return investorProfileRepository.findByInvestorId(investorId).orElseThrow(
                () -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<InvestorProfile> getAllInvestors() {
        return investorProfileRepository.findAll();
    }

    @Override
    public InvestorProfile updateInvestorStatus(Long id, boolean active) {
        if (active) {
            return investorProfileRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("not found"));
        }
        throw new ResourceNotFoundException("not found");
    }
}
