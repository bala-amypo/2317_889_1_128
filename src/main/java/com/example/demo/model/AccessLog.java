package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class AccessLog {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private DigitalKey digitalKey;

    @ManyToOne
    private Guest guest;

    private Instant accessTime;
    private String result;

    public AccessLog(Long id, DigitalKey digitalKey, Guest guest, Instant accessTime, String result) {
        this.id = id;
        this.digitalKey = digitalKey;
        this.guest = guest;
        this.accessTime = accessTime;
        this.result = result;
    }
    public AccessLog() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public DigitalKey getDigitalKey() {
        return digitalKey;
    }
    public void setDigitalKey(DigitalKey digitalKey) {
        this.digitalKey = digitalKey;
    }
    public Guest getGuest() {
        return guest;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    public Instant getAccessTime() {
        return accessTime;
    }
    public void setAccessTime(Instant accessTime) {
        this.accessTime = accessTime;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    
}
