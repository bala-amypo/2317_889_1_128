package co.example.demo.services.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import co.example.demo.entity.HoldingRecord;
import co.example.demo.repository.HoldingRecordRepository;
import co.example.demo.services.HoldingRecordService;

@Service
public class HoldingRecordServiceImpl implements HoldingRecordService {

    private final HoldingRecordRepository holdingRepo;

    public HoldingRecordServiceImpl(HoldingRecordRepository holdingRepo) {
        this.holdingRepo = holdingRepo;
    }

    @Override
    public HoldingRecord recordHolding(HoldingRecord holding) {
        return holdingRepo.save(holding);
    }

    @Override
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        return holdingRepo.findByInvestorId(investorId);
    }

    @Override
    public Optional<HoldingRecord> getHoldingById(Long id) {
        return holdingRepo.findById(id);
    }

    @Override
    public List<HoldingRecord> getAllHoldings() {
        return holdingRepo.findAll();
    }
}