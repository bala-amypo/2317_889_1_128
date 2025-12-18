package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.services.RebalancingAlertService;

@RestController
@RequestMapping("/api/alerts")
public class RebalancingAlertController {

    private final RebalancingAlertService alertService;

    public RebalancingAlertController(
            RebalancingAlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public RebalancingAlertRecord createAlert(
            @RequestBody RebalancingAlertRecord alert) {
        return alertService.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public RebalancingAlertRecord resolveAlert(
            @PathVariable Long id) {
        return alertService.resolveAlert(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<RebalancingAlertRecord> getAlertsByInvestor(
            @PathVariable Long investorId) {
        return alertService.getAlertsByInvestor(investorId);
    }

    @GetMapping("/{id}")
    public Optional<RebalancingAlertRecord> getAlertById(
            @PathVariable Long id) {
        return alertService.getAlertById(id);
    }

    @GetMapping
    public List<RebalancingAlertRecord> getAllAlerts() {
        return alertService.getAllAlerts();
    }
}
