// package com.example.demo.service;

// import java.util.List;
// import com.example.demo.entity.AllocationSnapshotRecord;

// public interface AllocationSnapshotService {
//     AllocationSnapshotRecord computeSnapshot(Long investorId);
//     AllocationSnapshotRecord getSnapshotById(Long id);
//     AllocationSnapshotRecord getSnapshotsByInvestor(Long investorId);
//     List<AllocationSnapshotRecord> getAllSnapshots();
// }

package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AllocationSnapshotRecord;

public interface AllocationSnapshotService {

    AllocationSnapshotRecord computeSnapshot(Long investorId);

    AllocationSnapshotRecord getSnapshotById(Long id);

    List<AllocationSnapshotRecord> getAllSnapshots();
}
