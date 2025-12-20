package com.example.demo.service;

import com.example.demo.entity.DigitalKey;

public interface DigitalKeyService {

    DigitalKey generateKey(Long bookingId);

    DigitalKey getActiveKeyForBooking(Long bookingId);
}
