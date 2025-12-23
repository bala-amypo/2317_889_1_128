// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.entity.HoldingRecord;
// import com.example.demo.service.HoldingRecordService;

// @RestController
// @RequestMapping("/api/holdings")
// public class HoldingRecordController {

//     private final HoldingRecordService holdingService;

//     public HoldingRecordController(HoldingRecordService holdingService) {
//         this.holdingService = holdingService;
//     }

//     @PostMapping
//     public HoldingRecord createHolding(
//             @RequestBody HoldingRecord holding) {
//         return holdingService.recordHolding(holding);
//     }

//     @GetMapping("/investor/{investorId}")
//     public List<HoldingRecord> getHoldingsByInvestor(
//             @PathVariable Long investorId) {
//         return holdingService.getHoldingsByInvestor(investorId);
//     }

//     @GetMapping("/{id}")
//     public HoldingRecord getHoldingById(@PathVariable Long id) {
//         return holdingService.getHoldingById(id);
//     }

//     @GetMapping
//     public List<HoldingRecord> getAllHoldings() {
//         return holdingService.getAllHoldings();
//     }
// }


package com.example.demo.controller;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.HoldingRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/holdings")
public class HoldingRecordController {

    private final HoldingRecordService holdingService;

    public HoldingRecordController(HoldingRecordService holdingService) {
        this.holdingService = holdingService;
    }

    @PostMapping
    public HoldingRecord create(@RequestBody HoldingRecord record) {
        return holdingService.recordHolding(record);
    }

    @GetMapping("/investor/{investorId}")
    public List<HoldingRecord> getByInvestor(@PathVariable Long investorId) {
        return holdingService.getHoldingsByInvestor(investorId);
    }

    @GetMapping("/{id}")
    public Optional<HoldingRecord> getById(@PathVariable Long id) {
        return holdingService.getHoldingById(id);
    }
}
