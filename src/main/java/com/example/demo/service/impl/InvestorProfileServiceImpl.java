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

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;

@Service
public class InvestorProfileServiceImpl implements InvestorProfileService {
    private final InvestorProfileRepository repo;

    public InvestorProfileServiceImpl(InvestorProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public InvestorProfile createInvestor(InvestorProfile p) {
        return repo.save(p);
    }
    
    @Override
    public InvestorProfile getInvestorById(Long id) {
        return repo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Investor not found: " + id));
    }
    
    @Override
    public List<InvestorProfile> getAllInvestors() {
        return repo.findAll();
    }
    
    @Override
    public InvestorProfile updateInvestorStatus(Long id, Boolean active) {
        InvestorProfile p = getInvestorById(id);
        p.setActive(active);
        return repo.save(p);
    }
    
    @Override
    public Optional<InvestorProfile> findByInvestorId(String investorId) {
        return repo.findByInvestorId(investorId);
    }
}
