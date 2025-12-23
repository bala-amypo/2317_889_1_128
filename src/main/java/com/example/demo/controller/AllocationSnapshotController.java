package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.AllocationSnapshotService;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {

    private final AllocationSnapshotService snapshotService;

    public AllocationSnapshotController(
            AllocationSnapshotService snapshotService) {
        this.snapshotService = snapshotService;
    }

    @PostMapping("/compute/{investorId}")
    public AllocationSnapshotRecord computeSnapshot(
            @PathVariable Long investorId) {
        return snapshotService.computeSnapshot(investorId);
    }

    @GetMapping("/investor/{investorId}")
    public AllocationSnapshotRecord getSnapshotsByInvestor(
            @PathVariable Long investorId) {
        return snapshotService.getSnapshotsByInvestor(investorId);
    }

    @GetMapping("/{id}")
    public AllocationSnapshotRecord getSnapshotById(
            @PathVariable Long id) {
        return snapshotService.getSnapshotById(id);
    }

    @GetMapping
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotService.getAllSnapshots();
    }
}

