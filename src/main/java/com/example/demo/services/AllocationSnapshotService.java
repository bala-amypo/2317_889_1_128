package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.AllocationSnapshotRecord;

public interface AllocationSnapshotService {
    AllocationSnapshotRecord computeSnapshot(Long investorId);
    Optional<AllocationSnapshotRecord> getSnapshotById(Long id);
    Optional<AllocationSnapshotRecord> getSnapshotsByInvestor(Long investorId);
    List<AllocationSnapshotRecord> getAllSnapshots();
}