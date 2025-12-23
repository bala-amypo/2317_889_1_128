// package com.example.demo.service.impl;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.InvestorProfile;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.InvestorProfileRepository;
// import com.example.demo.service.InvestorProfileService;

// @Service
// public class InvestorProfileServiceImpl implements InvestorProfileService {
//     private final InvestorProfileRepository investorProfileRepository;

//     public InvestorProfileServiceImpl(InvestorProfileRepository investorProfileRepository) {
//         this.investorProfileRepository = investorProfileRepository;
//     }

//     @Override
//     public InvestorProfile createInvestorProfile(InvestorProfile investor) {
//         investorProfileRepository.saveAndFlush(investor);
//         return investor;
//     }

//     @Override
//     public InvestorProfile getInvestorById(Long id) {
//         return investorProfileRepository.findById(id).orElseThrow(
//                 () -> new ResourceNotFoundException("not found"));
//     }

//     @Override
//     public InvestorProfile findInvestorById(String investorId) {
//         return investorProfileRepository.findByInvestorId(investorId);
//     }

//     @Override
//     public List<InvestorProfile> getAllInvestors() {
//         return investorProfileRepository.findAll();
//     }

//     @Override
//     public InvestorProfile updateInvestorStatus(Long id, boolean active) {
//         if (active) {
//             return investorProfileRepository.findById(id).orElseThrow(
//                     () -> new ResourceNotFoundException("not found"));
//         }
//         throw new ResourceNotFoundException("not found");
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;

import java.util.List;
import java.util.Optional;

public class InvestorProfileServiceImpl implements InvestorProfileService {

    private final InvestorProfileRepository repository;

    public InvestorProfileServiceImpl(InvestorProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public InvestorProfile createInvestor(InvestorProfile investor) {
        return repository.save(investor);
    }

    @Override
    public InvestorProfile getInvestorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Investor not found: " + id));
    }

    @Override
    public List<InvestorProfile> getAllInvestors() {
        return repository.findAll();
    }

    @Override
    public InvestorProfile updateInvestorStatus(Long id, boolean active) {
        InvestorProfile investor = getInvestorById(id);
        investor.setActive(active);
        return repository.save(investor);
    }

    @Override
    public Optional<InvestorProfile> findByInvestorId(String investorId) {
        return repository.findByInvestorId(investorId);
    }
}
