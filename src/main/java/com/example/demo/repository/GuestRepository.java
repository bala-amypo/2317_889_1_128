package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AllocationSnapshotRecord;

public interface GuestRepository extends JpaRepository<AllocationSnapshotRecord, Long> {
}